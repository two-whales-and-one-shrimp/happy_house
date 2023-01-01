package com.ssafy.house.map.dao;

import java.util.HashSet;

import com.ssafy.house.map.data.dto.MapResultDto;

public interface MapDAO {
  HashSet<MapResultDto> getList(String keyword);
}
