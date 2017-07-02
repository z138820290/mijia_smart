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

    @Override
    public List<City> getAllCities(String firstWord) {
        List<City> cities=cityMapper.getAllCities(firstWord);
        return cities;
    }

    @Override
    public City getCityByName(String cityName) {
        return cityMapper.getCityByName(cityName);
    }
}
