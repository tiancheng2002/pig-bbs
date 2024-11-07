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
@TableName("b_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    private String typeName;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
