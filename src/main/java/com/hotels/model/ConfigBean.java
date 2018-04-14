package com.hotels.model;

public class ConfigBean {
  int id;
  long hotelId;
  String hotelName;
  String address;
  String city;
  String state;
  String country;
  String zip;
  int roomCount;
  float overbookingLevel;
  String customerId;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getOverbookingLevel() {
    return overbookingLevel;
  }

  public void setOverbookingLevel(float overbookingLevel) {
    this.overbookingLevel = overbookingLevel;
  }

  public int getRoomCount() {
    return roomCount;
  }

  public void setRoomCount(int roomCount) {
    this.roomCount = roomCount;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public long getHotelId() {
    return hotelId;
  }

  public void setHotelId(long hotelId) {
    this.hotelId = hotelId;
  }
}
