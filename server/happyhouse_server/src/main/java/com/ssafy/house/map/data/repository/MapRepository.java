package com.ssafy.house.map.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.house.map.data.entity.Gugun;

public interface MapRepository extends JpaRepository<Gugun,String>{
  public List<Gugun> findBySidoNameContaining(String sidoName);
  public List<Gugun> findByGugunNameContaining(String gugunName);
}
