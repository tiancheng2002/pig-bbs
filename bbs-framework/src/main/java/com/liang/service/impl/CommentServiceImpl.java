package com.liang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.mapper.CommentMapper;
import com.liang.pojo.Comment;
import com.liang.service.CommentService;
import com.liang.vo.comment.CommentVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<CommentVo> getComment(Integer eid) {
        return baseMapper.getComment(eid);
    }

    @Override
    public List<CommentVo> getCommentWithAdmin(String key) {
        return baseMapper.getCommentWithAdmin(key);
    }

}
