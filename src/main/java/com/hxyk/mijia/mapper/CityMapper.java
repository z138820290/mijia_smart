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

    public City getCityByName(String cityName);
}
