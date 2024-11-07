package com.liang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-11
 */
@TableName("b_answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    private String answerText;

    private Integer uid;

    private Integer qid;

    private Boolean isAnonymity;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
