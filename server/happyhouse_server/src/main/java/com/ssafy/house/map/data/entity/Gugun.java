package com.ssafy.house.map.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "guguncode")
public class Gugun {
  @Id
  @Column(nullable = false, name = "gugun_code")
  private String gugunCode;
  @Column(nullable = true, name = "sido_name")
  private String sidoName;
  @Column(nullable = true, name = "gugun_name")
  private String gugunName;
  public String getGugunCode() {
    return gugunCode;
  }
  public void setGugunCode(String gugunCode) {
    this.gugunCode = gugunCode;
  }
  public String getSidoName() {
    return sidoName;
  }
  public void setSidoName(String sidoName) {
    this.sidoName = sidoName;
  }
  public String getGugunName() {
    return gugunName;
  }
  public void setGugunName(String gugunName) {
    this.gugunName = gugunName;
  }
}
