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
 * @since 2023-05-04
 */
@TableName("b_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    private Integer uid;

    private Integer level;

    private String address;

    private String content;

    private Integer like;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Integer eid;

}
