package com.hxyk.mijia.service;

import com.hxyk.mijia.domain.Advert;

import java.util.List;

/**
 * 广告业务接口
 * Created by Luck on 2017/7/2.
 */
public interface AdvertService {
    /**
     * 获取所有轮播广告
     * @return 广告集合
     */
    public List<Advert> getAllAdverts();

    /**
     * 删除广告
     * @param id
     */
    public void deleteAdvert(int id);


    /**
     * 添加广告
     * @param advert
     */
    public void addAdvert(Advert advert);

    public void updateAdvert(Advert advert);
}
