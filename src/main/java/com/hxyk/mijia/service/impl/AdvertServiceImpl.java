package com.hxyk.mijia.service.impl;

import com.hxyk.mijia.domain.Advert;
import com.hxyk.mijia.mapper.AdvertMapper;
import com.hxyk.mijia.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告业务实现类
 * Created by Luck on 2017/7/2.
 */
@Service
public class AdvertServiceImpl implements AdvertService{
    @Autowired
    private AdvertMapper advertMapper;
    @Override
    public List<Advert> getAllAdverts() {
        return advertMapper.getAllAdverts();
    }

    @Override
    public void deleteAdvert(int id) {
        advertMapper.deleteAdvert(id);
    }


    @Override
    public void addAdvert(Advert advert) {
        advertMapper.addAdvert(advert);
    }
}
