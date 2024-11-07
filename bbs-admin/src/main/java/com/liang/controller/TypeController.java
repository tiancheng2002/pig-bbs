package com.liang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.exception.ErrorException;
import com.liang.pojo.Type;
import com.liang.result.RespBeanEnum;
import com.liang.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/all")
    public List<Type> getType(Type type){
        List<Type> types = typeService.getType(type);
        return types;
    }

    @GetMapping("/detail")
    public Type getTypeDetail(@RequestParam("tid") Integer tid){
        Type type = typeService.getById(tid);
        return type;
    }

    @PostMapping("/action")
    public String actionType(@RequestBody Type type){
        QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.eq("type_name",type.getTypeName());
        Type typeData = typeService.getOne(typeQueryWrapper);
        //如果对应标签名已经存在，则不允许创建
        if(typeData!=null){
            throw new ErrorException(RespBeanEnum.TYPE_EXIST);
        }
        //判断前端传递参数是否有标签id，如果有表示更新；否则插入
        if(type.getTid()!=null){
            typeService.updateById(type);
        }else{
            typeService.save(type);
        }
        return "ok";
    }

    @PostMapping("/del")
    public String delType(@RequestBody List<Integer> selectionList){
        typeService.removeBatchByIds(selectionList);
        return "ok";
    }

}
