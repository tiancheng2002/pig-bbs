package com.liang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@Mapper
@Repository
public interface TypeMapper extends BaseMapper<Type> {

    List<Type> getTypeList(String key);

    void delType(Integer eid);

    void insertType(@Param("eid") Integer eid,@Param("tid") Integer tid);

}
