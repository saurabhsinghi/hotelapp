package com.hotels.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

/**
 * © 2015 – 2018. Talix, Inc. All rights reserved.
 *
 * @author: ssinghi
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationBean {

  int id;
  long hotelId;
  String guestFirstName;
  String guestLastName;
  String guestEmail;
  String checkIn;
  String checkOut;

  public String getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(String checkIn) {
    this.checkIn = checkIn;
  }

  public String getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(String checkOut) {
    this.checkOut = checkOut;
  }

  public String getGuestEmail() {
    return guestEmail;
  }

  public void setGuestEmail(String guestEmail) {
    this.guestEmail = guestEmail;
  }

  public String getGuestFirstName() {
    return guestFirstName;
  }

  public void setGuestFirstName(String guestFirstName) {
    this.guestFirstName = guestFirstName;
  }

  public String getGuestLastName() {
    return guestLastName;
  }

  public void setGuestLastName(String guestLastName) {
    this.guestLastName = guestLastName;
  }

  public long getHotelId() {
    return hotelId;
  }

  public void setHotelId(long hotelId) {
    this.hotelId = hotelId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
