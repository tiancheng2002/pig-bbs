package com.liang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.pojo.Comment;
import com.liang.vo.comment.CommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
public interface CommentService extends IService<Comment> {

    List<CommentVo> getComment(Integer eid);

    List<CommentVo> getCommentWithAdmin(String key);

}
