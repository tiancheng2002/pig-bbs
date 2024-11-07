package com.liang.controller;

import com.liang.exception.ErrorException;
import com.liang.pojo.Question;
import com.liang.result.RespBeanEnum;
import com.liang.service.QuestionService;
import com.liang.vo.question.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/answer")
public class AnswerController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<Answers> getAnswers(@RequestParam("qid") Integer qid){
        Question question = questionService.getById(qid);
        if(question==null){
            new ErrorException(RespBeanEnum.QUESTION_NO_EXIST);
        }
        List<Answers> answers = questionService.getAnswersWithAdmin(qid);
        return answers;
    }

}
