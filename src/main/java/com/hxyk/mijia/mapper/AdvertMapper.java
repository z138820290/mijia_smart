package com.hxyk.mijia.mapper;

import com.hxyk.mijia.domain.Advert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Luck on 2017/7/2.
 */
@Component
@Mapper
public interface AdvertMapper {
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
     * 修改广告
     * @param advert
     */
    public void updateAdvert(Advert advert);

    /**
     * 添加广告
     * @param advert
     */
    public void addAdvert(Advert advert);
}
