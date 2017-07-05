package com.hxyk.mijia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 广告轮播类
 * Created by Luck on 2017/7/2.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advert implements Serializable{
    private  int adId;
    private  String url;
}
