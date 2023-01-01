package com.ssafy.house.map.data.dto;

public class MapResultDto {
  private String gugunCode;
  private String keyWord;

  public MapResultDto() {
  }

  public MapResultDto(String gugunCode, String keyWord) {
    this.gugunCode = gugunCode;
    this.keyWord = keyWord;
  }

  public String getGugunCode() {
    return gugunCode;
  }

  public void setGugunCode(String gugunCode) {
    this.gugunCode = gugunCode;
  }

  public String getKeyWord() {
    return keyWord;
  }

  public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
  }
  
}
