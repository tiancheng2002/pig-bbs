package com.liang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.constant.UserConstant;
import com.liang.mapper.UserMapper;
import com.liang.param.RegisterParam;
import com.liang.pojo.User;
import com.liang.result.RespBean;
import com.liang.result.RespBeanEnum;
import com.liang.service.UserService;
import com.liang.utils.RedisUtils;
import com.liang.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public RespBean register(RegisterParam registerParam) {
        if(!registerParam.getPassword().equals(registerParam.getConfirmPassword())){
            return RespBean.error(RespBeanEnum.PASSWORD_NO_MATCH);
        }
        baseMapper.insert(registerParam);
        return RespBean.success(null);
    }

    @Override
    public RespBean login(User user) {
        User userData = baseMapper.selectOne(new QueryWrapper<User>().eq("email", user.getEmail()));
        if(userData==null){
            return RespBean.error(RespBeanEnum.USER_NO);
        }
        if(!userData.getPassword().equals(user.getPassword())){
            return RespBean.error(RespBeanEnum.USERNAME_PASSWORD);
        }
        if(userData.getStatus()==UserConstant.USER_PROHIBIT){
            return RespBean.error(RespBeanEnum.USER_STATUS_PROHIBIT);
        }
        //将用户登录信息添加到session当中
        String ticket = UUIDUtils.getUUID();
        redisUtils.hset("user",ticket,userData);
        return RespBean.success(ticket);
    }

    @Override
    public RespBean attention(Integer uid, Integer authorId) {
        Set<Integer> user_fans = (Set<Integer>) redisUtils.hget("user_fans", String.valueOf(authorId));
        Set<Integer> user_attention = (Set<Integer>) redisUtils.hget("user_attention", String.valueOf(uid));
        if(user_fans==null){
            user_fans = new HashSet<>();
        }
        if(user_attention==null){
            user_attention = new HashSet<>();
        }
        String msg;
        if(user_fans.contains(uid)){
            user_fans.remove(uid);
            user_attention.remove(authorId);
            msg = "取消关注成功";
        }else{
            user_fans.add(uid);
            user_attention.add(authorId);
            msg = "关注成功";
        }
        redisUtils.hset("user_fans", String.valueOf(authorId),user_fans);
        redisUtils.hset("user_attention", String.valueOf(uid),user_attention);
        return RespBean.success(msg);
    }

//    @Override
//    public List<User> getFans(Integer uid) {
//        Set<Integer> user_fans = (Set<Integer>) redisUtils.hget("user_fans", String.valueOf(uid));
//        if(user_fans!=null){
//            List<User> users = user_fans.stream().map(id ->
//                    baseMapper.selectOne(new QueryWrapper<User>().eq("uid", id).select("uid", "username", "avatar"))
//            ).collect(Collectors.toList());
////        List<User> users = baseMapper.selectBatchIds(user_fans);
//            return users;
//        }
//        return null;
//    }

    @Override
    public List<User> getAttention(String key, Integer uid) {
        Set<Integer> userData = (Set<Integer>) redisUtils.hget(key, String.valueOf(uid));
        if(userData!=null){
            Set<Integer> user_attention = null;
            if(key.equals("user_fans")){
                user_attention = (Set<Integer>) redisUtils.hget("user_attention", String.valueOf(uid));
            }
            Set<Integer> finalUser_attention = user_attention;
            List<User> users = userData.stream().map(id ->{
                User user = baseMapper.selectOne(new QueryWrapper<User>().eq("uid", id).select("uid", "username", "avatar"));
                if(finalUser_attention !=null){
                    user.setAttention(finalUser_attention.contains(id));
                }
                if(key.equals("user_attention")){
                    user.setAttention(true);
                }
                return user;
            }).collect(Collectors.toList());
            return users;
        }
        return null;
    }

    @Override
    public User userByName(String username) {
        return getOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public List<User> getUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(user.getUsername()!=null&& !StringUtils.isEmpty(user.getUsername())){
            queryWrapper.like("username",user.getUsername());
        }
        if(user.getStatus()!=null){
            queryWrapper.eq("status",user.getStatus());
        }
        return baseMapper.selectList(queryWrapper.orderByDesc("create_time"));
    }


}
