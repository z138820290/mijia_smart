package com.hxyk.mijia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luck on 2017/6/26.
 * 城市类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City implements Serializable{
    private int cityId;
    //城市名称
    private String cityName;
    //小区集合
    private List<Area> areas;
}
