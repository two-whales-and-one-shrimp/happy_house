package com.ssafy.house.map.dao.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.house.map.dao.MapDAO;
import com.ssafy.house.map.data.repository.MapRepository;
import com.ssafy.house.map.data.dto.MapResultDto;
import com.ssafy.house.map.data.entity.Gugun;

@Repository
public class MapDAOImpl implements MapDAO{

  @Autowired
  private MapRepository mapRepository;

  @Override
  public HashSet<MapResultDto> getList(String keyword) {
    List<Gugun> list = null;
    HashSet<MapResultDto> resultList = new HashSet<>();
    //구군 이름 중 검색
    list = mapRepository.findBySidoNameContaining(keyword);
    for (Gugun gugun : list) {
      resultList.add(new MapResultDto(gugun.getGugunCode(), gugun.getSidoName() + " " + gugun.getGugunName()));
    }
    //시도 이름 중 검색
    list = mapRepository.findByGugunNameContaining(keyword);
    for (Gugun gugun : list) {
      resultList.add(new MapResultDto(gugun.getGugunCode(), gugun.getSidoName() + " " + gugun.getGugunName()));
    }
    return resultList;
  }
  
}
