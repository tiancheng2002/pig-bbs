package com.liang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.mapper.TypeMapper;
import com.liang.pojo.Type;
import com.liang.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Override
    public List<Type> getType(Type type) {
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        if(type.getTypeName()!=null&& !StringUtils.isEmpty(type.getTypeName())){
            queryWrapper.like("type_name",type.getTypeName());
        }
        if(type.getStatus()!=null){
            queryWrapper.eq("status",type.getStatus());
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Type> getTypeList(String key) {
        return baseMapper.getTypeList(key);
    }

    @Override
    public void delType(Integer eid) {
        baseMapper.delType(eid);
    }

    @Override
    public void insertType(Integer eid, List<Integer> types) {
        types.forEach(tid->{
            baseMapper.insertType(eid,tid);
        });
    }

}
