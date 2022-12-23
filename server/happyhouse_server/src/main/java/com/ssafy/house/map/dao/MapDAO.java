package com.ssafy.house.map.dao;

import java.util.HashSet;

public interface MapDAO {
  HashSet<String> getList(String keyword);
}
