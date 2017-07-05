package com.hxyk.mijia.service;

import com.hxyk.mijia.domain.City;
import com.hxyk.mijia.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luck on 2017/6/28.
 */
public interface CityService {
    /**
     * 根据首字母获取城市集合
     * @param firstWord
     * @return 城市集合
     */
    public List<City> getAllCities(String firstWord);

    /**
     * 根据名称获取城市
     * @param cityName
     * @return 城市对象
     */
    public City getCityByName(String cityName);
}
