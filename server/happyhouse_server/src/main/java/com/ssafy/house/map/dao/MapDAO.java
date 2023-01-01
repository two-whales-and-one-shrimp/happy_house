package com.ssafy.house.map.dao;

import java.util.HashSet;

import com.ssafy.house.map.data.entity.Gugun;

public interface MapDAO {
  HashSet<String> getList(String keyword);

  Gugun getGugunCode(String keyword);
}
