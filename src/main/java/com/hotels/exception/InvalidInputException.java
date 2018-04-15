package com.hotels.exception;

public class InvalidInputException extends RuntimeException {
  public InvalidInputException(String message){
    super("Invalid Input: "+message);
  }
}
