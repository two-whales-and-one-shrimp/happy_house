package com.ssafy.house.map.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.map.service.impl.MapServiceImpl;

@RestController
@RequestMapping("/map")
public class MapController {
  
  @Autowired
  private MapServiceImpl mapService;

  @GetMapping("/{keyword}")
  public ResponseEntity<?> getList(@PathVariable String keyword) {
    HashSet<String> list = mapService.getList(keyword);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @GetMapping("guguncode/{keyword}")
  public ResponseEntity<?> getGugunCode(@PathVariable String keyword) {
    String gugunCode = mapService.getGugunCode(keyword);
    if(gugunCode != null)
      return new ResponseEntity<>(gugunCode, HttpStatus.OK);
    else 
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
