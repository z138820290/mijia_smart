package com.hxyk.mijia.mapper;

import com.hxyk.mijia.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Luck on 2017/6/30.
 */
@Mapper
@Component
public interface UserMapper {
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
     * 获取所有用户
     * @return List<User>
     */
    public List<User> getAllUsers();

    /**
     * 修改用户
     * @param user
     */
    public void updateUser(User user);
}
