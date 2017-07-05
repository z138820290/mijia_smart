package com.hxyk.mijia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Luck on 2017/6/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area implements Serializable{
    private int areaId;
    //小区名称
    private String areaName;
    //外键  城市对象
    private City city;
}
