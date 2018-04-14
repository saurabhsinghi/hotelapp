package com.hotels.exception;

/**
 * © 2015 – 2018. Talix, Inc. All rights reserved.
 *
 * @author: ssinghi
 */
public class HotelNotFoundException extends RuntimeException {
   public HotelNotFoundException(Long id){
     super("Invalid Hotel ("+id+"). Cannot set configuration.");
   }
}
