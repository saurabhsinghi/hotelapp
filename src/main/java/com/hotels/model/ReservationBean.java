package com.hotels.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotels.exception.InvalidDateRangeException;
import com.hotels.exception.InvalidInputException;
import com.hotels.exception.MissingInputException;
import com.hotels.util.Utils;

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

  public void setCheckIn(String checkIn){
    this.checkIn = checkIn;
  }

  public String getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(String checkOut){
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

  public void validateInput(){

    StringBuilder errMessage = new StringBuilder();
    if(guestFirstName==null || guestFirstName.trim().equals("")){
      errMessage.append("First name ");
    }
    if(guestLastName==null || guestLastName.trim().equals("")){
      errMessage.append("Last name ");
    }
    if(guestEmail==null || guestEmail.trim().equals("")){
      errMessage.append("Email ");
    }
    if(checkIn==null || checkIn.trim().equals("")){
      errMessage.append("Check in date ");
    }
    if(checkOut==null || checkOut.trim().equals("")){
      errMessage.append("Check out date ");
    }

    if(errMessage.length()>0){
      throw new MissingInputException(errMessage.toString());
    }
    //Booking period cannot be in the past or today.
    if(Utils.getTimeInMsFromString(checkIn)<=System.currentTimeMillis()
        || Utils.getTimeInMsFromString(checkOut)<=System.currentTimeMillis()){
      throw new InvalidDateRangeException(checkIn, checkOut);
    }

    if(Utils.getTimeInMsFromString(checkIn)>=Utils.getTimeInMsFromString(checkOut)){
      throw new InvalidDateRangeException(checkIn, checkOut);
    }
    if(!Utils.validateEmail(guestEmail)){
      throw new InvalidInputException("Email address: "+guestEmail);
    }
  }
}
