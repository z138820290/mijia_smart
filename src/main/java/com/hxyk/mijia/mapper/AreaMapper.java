package com.hxyk.mijia.mapper;

import com.hxyk.mijia.domain.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by Luck on 2017/7/6.
 */
@Component
@Mapper
public interface AreaMapper {
    /**
     * 添加小区
     * @param area
     */
    public void addArea(Area area);
}
