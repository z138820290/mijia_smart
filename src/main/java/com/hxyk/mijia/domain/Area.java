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
    private String areaName;
    private City city;
}
