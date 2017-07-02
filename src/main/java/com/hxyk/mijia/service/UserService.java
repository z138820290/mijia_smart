package com.hxyk.mijia.service;

import com.hxyk.mijia.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务层接口
 * Created by Luck on 2017/6/30.
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据手机号查找用户
     * @param phone
     */
    public User getUserByPhone(String phone);

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return int
     */
    public int login(String phone,String password);

    /**
     * 获取所有用户
     * @return List<User>
     */
    public List<User> getAllUsers();

    /**
     * 修改用户
     * @param user
     */
    public void updataUser(User user);
}
