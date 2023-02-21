package com.ssafy.house.map.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.map.dao.MapDAO;
import com.ssafy.house.map.data.entity.Gugun;
import com.ssafy.house.map.service.MapService;

@Service
public class MapServiceImpl implements MapService{

  @Autowired
  private MapDAO mapDAO;
  
  @Override
  public HashSet<String> getList(String keyword) {
    return mapDAO.getList(keyword);
  }

  @Override
  public String getGugunCode(String keyword) {
    Gugun gugun = mapDAO.getGugunCode(keyword);
    String gugunCode = null;
    if (gugun != null) {
      gugunCode = gugun.getGugunCode().substring(0, 5);
      return gugunCode;
    } else {
      return null;
    }
  }
  
}
