package com.hotels.exception;

/**
 * © 2015 – 2018. Talix, Inc. All rights reserved.
 *
 * @author: ssinghi
 */
public class InvalidDateRangeException extends RuntimeException {
  public InvalidDateRangeException(String in, String out){
    super("Invalid date range: "+in +" - "+out);
  }
}
