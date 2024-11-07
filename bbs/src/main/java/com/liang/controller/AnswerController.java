package com.liang.controller;

import com.liang.pojo.Answer;
import com.liang.result.RespBean;
import com.liang.service.AnswerService;
import com.liang.vo.answer.AnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-11
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/all")
    public RespBean getAnswer(@RequestParam("uid") Integer uid){
        List<AnswerVo> answerVoList = answerService.getAnswer(uid);
        return RespBean.success(answerVoList);
    }

    @PostMapping("/save")
    public RespBean answerQuestion(@RequestBody Answer answer){
        answerService.save(answer);
        return RespBean.success(null);
    }

}
