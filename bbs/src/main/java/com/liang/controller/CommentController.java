package com.liang.controller;

import com.liang.pojo.Comment;
import com.liang.result.RespBean;
import com.liang.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //根据文章id查询所有评论
    @GetMapping("/all")
    @ApiOperation(value = "获取所有评论信息")
    public RespBean getCommentByEssay(@RequestParam("eid") Integer eid){
        return RespBean.success(commentService.getComment(eid));
    }

    //添加评论
    @PostMapping("/action")
    @ApiOperation(value = "添加评论")
    public RespBean subComment(@RequestBody Comment comment){
        return RespBean.success(commentService.save(comment));
    }

}
