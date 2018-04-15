package com.hotels.exception;

public class HotelNotFoundException extends RuntimeException {
   public HotelNotFoundException(Long id){
     super("Invalid Hotel ("+id+"). Cannot set configuration.");
   }
}
