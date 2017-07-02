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
    public List<City> getAllCities(String firstWord);
    public City getCityByName(String cityName);
}
