package com.liang.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.liang.common.CommonEntity;
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
@TableName("b_essay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Essay extends CommonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;

    private String title;

    private String description;

    private String context;

    private Integer status;

    private String coverImg;

    private Integer visited;

    private Integer isDraft;

    private String type;

    private Boolean isAbroad;

    private Integer uid;

    @TableField(exist = false)
    private boolean isStar;

}
