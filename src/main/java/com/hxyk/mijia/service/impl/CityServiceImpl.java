package com.hxyk.mijia.service.impl;

import com.hxyk.mijia.domain.City;
import com.hxyk.mijia.mapper.CityMapper;
import com.hxyk.mijia.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Luck on 2017/6/28.
 */
@Service
public class CityServiceImpl implements CityService{
    @Autowired
    private CityMapper cityMapper;

    /**
     * 根据首字母获取城市集合
     * @param firstWord
     * @return 城市集合
     */
    @Override
    public List<City> getAllCities(String firstWord) {
        List<City> cities=cityMapper.getAllCities(firstWord);
        return cities;
    }

    /**
     * 根据名称获取城市
     * @param cityName
     * @return城市对象
     */
    @Override
    public City getCityByName(String cityName) {
        return cityMapper.getCityByName(cityName);
    }

    /**
     * 获取所有城市
     * @return
     */
    public List<City> getAllcity() {
        return cityMapper.getAllCity();
    }

    /**
     * 添加城市
     * @param city
     */
    @Override
    public void addCity(City city) {
        //获取所有城市
        List<City> cities=cityMapper.getAllCity();
        //临时变量
        int i=0;
        //遍历城市集合
        for (City city1: cities) {
            //如果传入城市的名称在数据库已经存在，结束循环，将此次魂环的城市赋值给参数
            if (city.getCityName().equals(city1.getCityName())){
                city=city1;
                i=1;
                break;
            }
        }
        //如果城市名不存在，向数据库中添加城市
        if (i!=1){
            cityMapper.addCity(city);
        }
    }
}
