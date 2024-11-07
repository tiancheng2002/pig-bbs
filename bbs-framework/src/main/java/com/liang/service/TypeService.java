package com.liang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.pojo.Type;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
public interface TypeService extends IService<Type> {

    List<Type> getType(Type type);

    List<Type> getTypeList(String key);

    void delType(Integer eid);

    void insertType(Integer eid,List<Integer> types);

}
