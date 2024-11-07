package com.liang.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liang.param.ApproveParam;
import com.liang.pojo.Essay;
import com.liang.service.EssayService;
import com.liang.vo.essay.EssayVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/essay")
public class EssayController {

    @Autowired
    private EssayService essayService;

    @GetMapping("/all")
    public List<EssayVo> getEssay(@RequestParam(value = "key",required = false) String key,@RequestParam(value = "status",required = false) Integer status){
        List<EssayVo> essay = essayService.getEssayWithAdmin(key,status);
        return essay;
    }

    @PostMapping("/approve")
    @ApiOperation(value = "审核文章")
    @Transactional
    public String approveEssay(@RequestBody ApproveParam approveParam){
        essayService.update(new UpdateWrapper<Essay>().setSql("status="+approveParam.getStatus()).eq("eid",approveParam.getEid()));
        essayService.approve(approveParam);
        return "审核成功";
    }

}
