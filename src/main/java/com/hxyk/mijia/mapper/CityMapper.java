package com.hxyk.mijia.mapper;

import com.hxyk.mijia.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

import java.util.List;

/**
 * Created by Luck on 2017/6/28.
 */
@Component
@Mapper
public interface CityMapper {
    /**
     * 获取所有城市
     * @return 城市集合
     */
    public List<City> getAllCities(@Param("firstWord") String firstWord);

    /**
     * 根据名称获取城市
     * @param cityName
     * @return
     */
    public City getCityByName(String cityName);

    /**
     * 获取所有城市
     * @return
     */
    public List<City> getAllCity();

    /**
     * 添加城市
     * @param city
     */
    public void addCity(City city);
}
