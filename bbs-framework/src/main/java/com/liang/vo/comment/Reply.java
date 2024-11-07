package com.liang.vo.comment;

import com.liang.pojo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {

    private Integer id;

    private Integer total;

    private List<Comment> list;

}
