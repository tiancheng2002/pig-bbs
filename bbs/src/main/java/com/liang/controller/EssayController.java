package com.liang.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.constant.RedisConstant;
import com.liang.exception.ErrorException;
import com.liang.modal.dto.essay.EssayFavourRequest;
import com.liang.param.EssayParam;
import com.liang.pojo.Essay;
import com.liang.result.RespBean;
import com.liang.result.RespBeanEnum;
import com.liang.service.EssayService;
import com.liang.utils.BeanCopeUtils;
import com.liang.utils.RedisUtils;
import com.liang.vo.essay.EssayRejectVo;
import com.liang.vo.essay.EssayVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/essay")
public class EssayController {

    @Autowired
    private EssayService essayService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/all")
    @ApiOperation(value = "获取所有文章")
    //todo 分页获取
    public RespBean getEssay(@RequestParam(value = "key",required = false) String key){
        List<EssayVo> essays = essayService.getEssay(key);
//        if(key!=null){
//            return RespBean.success(essayService.list(new QueryWrapper<Essay>().like("title",key).or().like("context",key).eq("is_draft",0)));
//        }
//        return RespBean.success(essayService.list(new QueryWrapper<Essay>().eq("is_draft",0)));
        return RespBean.success(essays);
    }

    @GetMapping("/abroad")
    @ApiOperation(value = "获取所有留学生文章")
    //todo 分页获取
    public RespBean getEssayAbroad(){
        List<EssayVo> essays = essayService.getAbroad();
        return RespBean.success(essays);
    }

    @GetMapping("/byUser")
    @ApiOperation(value = "获取用户发表的所有文章")
    //todo 分页获取
    public RespBean getEssayByUser(@RequestParam("uid") Integer uid){
        List<EssayVo> essays = essayService.getByUser(uid);
        return RespBean.success(essays);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "文章详情")
    public RespBean getEssayDetail(@RequestParam("eid") Integer eid, @RequestParam(value = "uid",required = false) Integer uid){
        EssayVo essayVo = essayService.getEssayById(eid,uid);
        return RespBean.success(essayVo);
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发布文章")
    public RespBean essayAction(@RequestBody EssayParam essayParam){
        Essay essay = BeanCopeUtils.copyBean(essayParam, Essay.class);
        List<Integer> types = (List<Integer>) JSON.parse(essayParam.getType());
        essayService.action(essay,types);
        return RespBean.success("ok");
    }

    @GetMapping("/star")
    @ApiOperation(value = "收藏文章")
    public RespBean starEssay(@RequestParam("eid") Integer eid, @RequestParam("uid") Integer uid){
        return essayService.essayStar(eid,uid);
    }

    @PostMapping("/favour")
    @ApiOperation(value = "点赞文章")
    public RespBean favourEssay(@RequestBody EssayFavourRequest essayFavourRequest){
        Integer eid = essayFavourRequest.getEid();
        Integer uid = essayFavourRequest.getUid();
        //1、先判断文章是否存在
        Essay essay = essayService.getById(eid);
        if(essay==null){
            throw new ErrorException(RespBeanEnum.ERROR);
        }
        //2、去redis中判断用户是否已点赞该文章，如果已经点赞，那么将点赞状态更改为0；如果没有点赞，将点赞状态更改为1；并且需要更新文章点赞数量
        //todo 还可以考虑点赞时间
        long result = redisUtils.sSet(RedisConstant.USER_FAVOUR_ESSAY + uid, eid);
        Integer addNum = RedisConstant.ESSAY_ADD_NUM;
        if(result==0){
            redisUtils.setRemove(RedisConstant.USER_FAVOUR_ESSAY+uid,eid);
            addNum = RedisConstant.ESSAY_DEC_NUM;
        }
        redisUtils.hincr(RedisConstant.ESSAY_FAVOUR_NUM,String.valueOf(eid),addNum);
        //3、将结果进行返回，用定时任务去更新点赞数据即可
        return RespBean.success(result);
    }

    @GetMapping("/draft")
    @ApiOperation(value = "获取草稿文章")
    public RespBean draftEssay(@RequestParam("uid") Integer uid){
        return RespBean.success(essayService.list(new QueryWrapper<Essay>().eq("is_draft",1).eq("uid",uid)));
    }

    @GetMapping("/getStar")
    @ApiOperation(value = "获取所有的收藏")
    public RespBean getStar(@RequestParam("uid") Integer uid){
        Set<Integer> essay_star = (Set<Integer>) redisUtils.hget("essay_star", String.valueOf(uid));
        if(uid!=null){
            List<Essay> essays = essay_star.stream().map(id ->
                    essayService.getEssayById(id,null)
            ).collect(Collectors.toList());
            return RespBean.success(essays);
        }
        return RespBean.success(null);
    }

    @GetMapping("/edit")
    @ApiOperation(value = "编辑文章")
    public RespBean editEssay(@RequestParam("eid") Integer eid){
        return RespBean.success(essayService.getById(eid));
    }

    @GetMapping("/del")
    @ApiOperation(value = "删除文章")
    public RespBean delEssay(@RequestParam("eid") Integer eid){
        essayService.removeById(eid);
        return RespBean.success("删除成功");
    }

    @GetMapping("/getReject")
    @ApiOperation(value = "获取驳回信息")
    public RespBean getRejectInfo(@RequestParam("eid") Integer eid){
        EssayRejectVo rejectVo = essayService.getRejectInfo(eid);
        return RespBean.success(rejectVo);
    }

}
