package com.liang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.param.RegisterParam;
import com.liang.pojo.User;
import com.liang.result.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
public interface UserService extends IService<User> {

    RespBean register(RegisterParam registerParam);

    RespBean login(User user);

    RespBean attention(Integer uid, Integer authorId);

    List<User> getAttention(String key, Integer uid);

    User userByName(String username);

    List<User> getUser(User user);

}
