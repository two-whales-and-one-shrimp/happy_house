package com.ssafy.house.map.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.map.dao.MapDAO;
import com.ssafy.house.map.data.dto.MapResultDto;
import com.ssafy.house.map.service.MapService;

@Service
public class MapServiceImpl implements MapService{

  @Autowired
  private MapDAO mapDAO;
  
  @Override
  public HashSet<MapResultDto> getList(String keyword) {
    return mapDAO.getList(keyword);
  }
  
}
