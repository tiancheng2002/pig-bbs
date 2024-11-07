package com.liang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.exception.ErrorException;
import com.liang.pojo.User;
import com.liang.result.RespBeanEnum;
import com.liang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getUser(User user){
        List<User> users = userService.getUser(user);
        return users;
    }

    @RequestMapping("/action")
    @Transactional
    public String action(@RequestBody User user){
        if(user==null){
            throw new ErrorException(RespBeanEnum.ERROR);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email",user.getEmail());
        User userData = userService.getOne(userQueryWrapper);
        if(userData!=null){
            throw new ErrorException(RespBeanEnum.EMAIL_EXIST);
        }
        if(user.getUid()==null){
            userService.save(user);
        }else{
            userService.updateById(user);
        }
        return "ok";
    }

    @GetMapping("/detail")
    public User getUserDetail(@RequestParam("uid") Integer uid){
        return userService.getById(uid);
    }

    @PostMapping("/del")
    public String delUser(@RequestBody List<Integer> selectionList){
        userService.removeBatchByIds(selectionList);
        return "ok";
    }

}