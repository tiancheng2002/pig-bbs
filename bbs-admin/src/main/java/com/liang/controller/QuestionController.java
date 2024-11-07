package com.liang.controller;

import com.liang.service.QuestionService;
import com.liang.vo.question.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public List<QuestionVo> getQuestion(@RequestParam(value = "key",required = false) String key){
        return questionService.getQuestionWithAdmin(key);
    }

}
