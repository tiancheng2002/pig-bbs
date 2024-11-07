package com.liang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface UserMapper extends BaseMapper<User> {

}
