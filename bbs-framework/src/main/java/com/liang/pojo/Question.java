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
 * @since 2023-05-06
 */
@TableName("b_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "qid", type = IdType.AUTO)
    private Integer qid;

    private String questionTitle;

    private String questionText;

    private Boolean isAnonymity;

    private Integer uid;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Boolean isAbroad;

}
