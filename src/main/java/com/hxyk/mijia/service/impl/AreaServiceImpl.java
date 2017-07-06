package com.hxyk.mijia.service.impl;

import com.hxyk.mijia.domain.Area;
import com.hxyk.mijia.mapper.AreaMapper;
import com.hxyk.mijia.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Luck on 2017/7/6.
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public void addArea(Area area) {
        areaMapper.addArea(area);
    }
}
