package com.hotels.exception;



public class RoomUnavailableForDatesException extends RuntimeException{
  public RoomUnavailableForDatesException(String from, String to){
    super("Hotel room unavailable for the specified dates: "+from+" to "+to);
  }
}
