package com.hxyk.mijia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Luck on 2017/6/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
    private int id;
    private String nickName;        //昵称
    private String phoneNumber;     //手机号
    private String realName;        //真实姓名
    private String address;         //详细地址
    private String password;        //密码
    private String city;            //  城市——小区

    public User(String nickName, String phoneNumber, String realName, String address, String password, String city) {
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.realName = realName;
        this.address = address;
        this.password = password;
        this.city = city;
    }
}
