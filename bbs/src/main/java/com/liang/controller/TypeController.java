package com.liang.controller;

import com.liang.pojo.Type;
import com.liang.result.RespBean;
import com.liang.service.TypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/all")
    @ApiOperation(value = "获取所有类型")
    public RespBean getType(@RequestParam(value = "key",required = false) String key){
        List<Type> types = typeService.getTypeList(key);
        return RespBean.success(types);
    }

}
