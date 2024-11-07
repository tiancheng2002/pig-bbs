package com.liang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaozhu
 * @since 2022-09-02
 */
@TableName("b_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    private String name;

    private Long parentId;

    private String path;

    private String component;

    private String perms;

    private String icon;

    private Integer type;

    private Boolean isExternal;

    @TableField(value = "orderNum")
    private Integer orderNum;

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remark;

}
