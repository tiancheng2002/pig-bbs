package com.liang.controller;

import com.liang.service.CommentService;
import com.liang.vo.comment.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public List<CommentVo> getComment(@RequestParam(value = "key",required = false) String key){
        //获取所有评论信息
        return commentService.getCommentWithAdmin(key);
    }

}
