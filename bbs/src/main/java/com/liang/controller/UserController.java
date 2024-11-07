package com.liang.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liang.param.RegisterParam;
import com.liang.pojo.User;
import com.liang.result.RespBean;
import com.liang.result.RespBeanEnum;
import com.liang.service.EssayService;
import com.liang.service.UserService;
import com.liang.utils.BeanCopeUtils;
import com.liang.utils.RedisUtils;
import com.liang.vo.user.UserAttentionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EssayService essayService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public RespBean userRegister(@RequestBody RegisterParam registerParam){
        return userService.register(registerParam);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public RespBean userLogin(@RequestBody User user){
        return userService.login(user);
    }

    @GetMapping("/getStar")
    @ApiOperation(value = "获取用户所有收藏")
    public RespBean showStar(@RequestParam("uid") Integer uid){
        return essayService.getStar(uid);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息")
    public RespBean userInfo(@RequestParam("token") String token){
        if(token==null|| StringUtils.isEmpty(token)){
            return RespBean.error(RespBeanEnum.ERROR);
        }
        User user = (User) redisUtils.hget("user", token);
        if(user==null){
            return RespBean.error(RespBeanEnum.USER_NO);
        }
        UserAttentionVo userAttentionVo = BeanCopeUtils.copyBean(user, UserAttentionVo.class);
        userAttentionVo.setAttentions((Set<Integer>) redisUtils.hget("user_attention",String.valueOf(user.getUid())));
        return RespBean.success(userAttentionVo);
    }

    @GetMapping("/logout")
    @ApiOperation(value = "用户退出")
    public RespBean userLogout(@RequestParam("token") String token){
        redisUtils.hdel("user",token);
        return RespBean.success("退出成功");
    }

    @GetMapping("/attention")
    @ApiOperation(value = "关注作者")
    public RespBean userAttention(@RequestParam("uid") Integer uid, @RequestParam("authorId") Integer authorId){
        return userService.attention(uid,authorId);
    }

    @GetMapping("/getFans")
    @ApiOperation(value = "获取粉丝列表")
    public RespBean getFans(@RequestParam("uid") Integer uid){
        List<User> users = userService.getAttention("user_fans",uid);
        return RespBean.success(users);
    }

    @GetMapping("/getAttention")
    @ApiOperation(value = "获取关注列表")
    public RespBean getAttention(@RequestParam("uid") Integer uid){
        List<User> users = userService.getAttention("user_attention",uid);
        return RespBean.success(users);
    }

    @PostMapping("/updateInfo")
    @ApiOperation(value = "更新用户信息")
    public RespBean updateUserInfo(@RequestBody User user){
        userService.updateById(user);
        return RespBean.success("更新成功");
    }

    @GetMapping("/authorInfo")
    @ApiOperation(value = "获取用户信息")
    public RespBean getAuthorInfo(@RequestParam("uid") Integer uid){
        User user = userService.getById(uid);
        UserAttentionVo userAttentionVo = BeanCopeUtils.copyBean(user, UserAttentionVo.class);
        userAttentionVo.setAttentions((Set<Integer>) redisUtils.hget("user_attention",String.valueOf(user.getUid())));
        userAttentionVo.setUserFans((Set<Integer>) redisUtils.hget("user_fans",String.valueOf(user.getUid())));
        return RespBean.success(userAttentionVo);
    }

}
