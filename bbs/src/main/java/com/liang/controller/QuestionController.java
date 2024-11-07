package com.liang.controller;

import com.liang.pojo.Question;
import com.liang.result.RespBean;
import com.liang.service.QuestionService;
import com.liang.vo.question.QuestionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    @ApiOperation(value = "获取所有问题")
    public RespBean getQuestion(@RequestParam(value = "uid",required = false) Integer uid){
        List<QuestionVo> questionVoList = questionService.getQuestion(uid);
        return RespBean.success(questionVoList);
    }

    @GetMapping("/abroad")
    @ApiOperation(value = "获取所有留学生提问")
    public RespBean getQuestionAbroad(){
        List<QuestionVo> questionVoList = questionService.questionAbroad();
        return RespBean.success(questionVoList);
    }

    @PostMapping("/save")
    @ApiOperation(value = "发表问题")
    public RespBean pubQuestion(@RequestBody Question question){
        questionService.save(question);
        return RespBean.success("发表成功");
    }

    @GetMapping("/detail")
    @ApiOperation(value = "问题详情")
    public RespBean questionAnswer(@RequestParam("qid") Integer qid,@RequestParam(value = "uid",required = false) Integer uid){
        QuestionVo questionVoList = questionService.questionDetail(qid,uid);
        return RespBean.success(questionVoList);
    }

}
