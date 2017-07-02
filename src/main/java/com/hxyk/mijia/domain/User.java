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
    private String nickName;
    private String phoneNumber;
    private String realName;
    private String address;
    private String password;
    private String city;

    public User(String nickName, String phoneNumber, String realName, String address, String password, String city) {
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.realName = realName;
        this.address = address;
        this.password = password;
        this.city = city;
    }
}
