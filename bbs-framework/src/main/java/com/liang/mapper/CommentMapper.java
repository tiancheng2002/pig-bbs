package com.liang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.pojo.Comment;
import com.liang.vo.comment.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVo> getComment(@Param("eid") Integer eid);

    List<CommentVo> getCommentWithAdmin(String key);

}
