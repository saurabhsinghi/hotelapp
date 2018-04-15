package com.hotels.exception;

/**
 * © 2015 – 2018. Talix, Inc. All rights reserved.
 *
 * @author: ssinghi
 */
public class MissingInputException extends RuntimeException {
  public MissingInputException (String message){
    super("Missing the following required field(s): "+message);
  }
}
