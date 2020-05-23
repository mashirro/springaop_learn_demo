package com.mashirro.springaop_learn_demo.controller;


import com.mashirro.springaop_learn_demo.entity.Result;
import com.mashirro.springaop_learn_demo.entity.User;
import com.mashirro.springaop_learn_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public Result getUsers() {
        try {
            List<User> users = userService.getUsers();
            return new Result(true, "查询用户成功", users);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询用户失败", null);
        }
    }
}
