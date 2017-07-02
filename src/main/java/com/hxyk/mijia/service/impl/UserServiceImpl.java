package com.hxyk.mijia.service.impl;

import com.hxyk.mijia.domain.User;
import com.hxyk.mijia.mapper.UserMapper;
import com.hxyk.mijia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户业务
 * Created by Luck on 2017/6/30.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 根据手机号获取用户
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }
    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    public int login(String phone,String password){
        User user=userMapper.getUserByPhone(phone);
        //判断用户是否存在
        if (user==null){
            return 1;
        }else{
            //判断密码是否正确
            if (password.equals(user.getPassword())){
                return 2;
            }else {
                return 3;
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void updataUser(User user) {
        userMapper.updateUser(user);
    }
}
