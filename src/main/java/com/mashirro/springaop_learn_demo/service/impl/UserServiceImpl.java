package com.mashirro.springaop_learn_demo.service.impl;

import com.mashirro.springaop_learn_demo.entity.User;
import com.mashirro.springaop_learn_demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "tom", "15"));
        users.add(new User(2, "marin", "17"));
        //手动制造异常
        //int i = 1 / 0;
        return users;
    }
}
