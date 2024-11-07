package com.liang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.constant.EssayConstant;
import com.liang.exception.ErrorException;
import com.liang.mapper.EssayMapper;
import com.liang.param.ApproveParam;
import com.liang.pojo.Answer;
import com.liang.pojo.Essay;
import com.liang.pojo.User;
import com.liang.result.RespBean;
import com.liang.result.RespBeanEnum;
import com.liang.service.AnswerService;
import com.liang.service.EssayService;
import com.liang.service.TypeService;
import com.liang.utils.RedisUtils;
import com.liang.vo.essay.EssayRejectVo;
import com.liang.vo.essay.EssayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@Service
public class EssayServiceImpl extends ServiceImpl<EssayMapper, Essay> implements EssayService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private TypeService typeService;

    @Override
    public RespBean essayStar(Integer eid, Integer uid) {
        Set<Integer> essay_star = (Set<Integer>) redisUtils.hget("essay_star", String.valueOf(uid));
        if(essay_star==null){
            essay_star = new HashSet<>();
        }
        String msg;
        if(essay_star.contains(eid)){
            essay_star.remove(eid);
            msg = "取消收藏成功";
        }else{
            essay_star.add(eid);
            msg = "收藏成功";
        }
        redisUtils.hset("essay_star", String.valueOf(uid),essay_star);
        return RespBean.success(msg);
    }

    @Override
    public RespBean getStar(Integer uid) {
        Set<Integer> essay_star = (Set<Integer>) redisUtils.hget("essay_star", String.valueOf(uid));
        if(essay_star!=null){
            return RespBean.success(baseMapper.selectBatchIds(essay_star));
        }
        return RespBean.success(null);
    }

    @Override
    public List<EssayVo> getEssay(String key) {
        return baseMapper.getEssay(key);
    }

    @Override
    public EssayVo getEssayById(Integer eid,Integer uid) {
        EssayVo essay = baseMapper.getEssayById(eid);
        if(uid!=null){
            Set<Integer> essay_star = (Set<Integer>) redisUtils.hget("essay_star", String.valueOf(uid));
            Set<Integer> user_attention = (Set<Integer>) redisUtils.hget("user_attention", String.valueOf(uid));
            if(user_attention==null){
                user_attention = new HashSet<>();
                redisUtils.hset("user_attention", String.valueOf(uid),user_attention);
            }
            if(essay_star==null){
                essay_star = new HashSet<>();
                redisUtils.hset("essay_star", String.valueOf(uid),essay_star);
            }
            essay.setStar(essay_star.contains(eid));
            essay.getUser().setAttention(user_attention.contains(essay.getUid()));
        }
        setUserData(essay.getUser());
        return essay;
    }

    @Override
    public List<EssayVo> getByUser(Integer uid) {
        return baseMapper.getByUser(uid);
    }

    @Override
    public void setUserData(User user) {
        user.setAnswerTotal(answerService.count(new QueryWrapper<Answer>().eq("uid",user.getUid())));
        user.setEssayTotal(baseMapper.selectCount(new QueryWrapper<Essay>().eq("uid",user.getUid()).eq("is_draft",0).eq("status",1)));
        Set<Integer> user_fans = (Set<Integer>) redisUtils.hget("user_fans", String.valueOf(user.getUid()));
        user.setFans(user_fans==null?0:user_fans.size());
    }

    @Override
    public List<EssayVo> getAbroad() {
        return baseMapper.getAbroad();
    }

    //添加是先新增文章，然后新增文章对应的标签
    //修改是先修改文章，然后先删除文章相关的所有标签，在新增文章对应的标签
    @Override
    @Transactional
    public void action(Essay essay,List<Integer> types) {
        if(essay.getEid()!=null){
            if(essay.getStatus()==EssayConstant.ESSAY_REJECT){
                essay.setStatus(EssayConstant.ESSAY_WAITING);
                baseMapper.delRejectInfo(essay.getEid());
            }
            baseMapper.updateById(essay);
            typeService.delType(essay.getEid());
        }else{
            baseMapper.insert(essay);
        }
        typeService.insertType(essay.getEid(),types);
    }

    @Override
    public void approve(ApproveParam approveParam) {
        Integer eid = approveParam.getEid();
        Essay essay = getById(eid);
        if(essay==null){
            throw new ErrorException(RespBeanEnum.PARAM_ERROR);
        }
        if(approveParam.getStatus()==EssayConstant.ESSAY_REJECT){
            //将驳回信息插入到数据表当中
            baseMapper.insertRejectInfo(approveParam.getEid(),approveParam.getInfo());
        }
    }

    @Override
    public List<EssayVo> getEssayWithAdmin(String key,Integer status) {
        return baseMapper.getEssayWithAdmin(key,status);
    }

    @Override
    public EssayRejectVo getRejectInfo(Integer eid) {
        return baseMapper.getReject(eid);
    }

}
