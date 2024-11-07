package com.liang.vo.comment;

import com.liang.pojo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentVo extends Comment {

    private String username;

    private String avatar;

    private Reply reply;

}
