package com.ssafy.house.map.service;

import java.util.HashSet;

import com.ssafy.house.map.data.dto.MapResultDto;

public interface MapService {
  HashSet<MapResultDto> getList(String keyword);
}
