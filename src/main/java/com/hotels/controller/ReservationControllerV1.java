package com.hotels.controller;

import com.hotels.exception.*;
import com.hotels.model.ReservationBean;
import com.hotels.service.ReservationService;
import com.hotels.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/v1/reservations")
public class ReservationControllerV1 {
  @Autowired ReservationService reservationService;
  @RequestMapping(method = RequestMethod.POST, value="/hotel/{id}", produces = "application/json")
   public ResponseEntity<? extends Object> reserveRoom(@PathVariable("id") long hotelId,
                                          @RequestBody ReservationBean reservationDetails){
    Map<String, String> responseMsg = new HashMap<>();
    try{
      reservationService.reserveRoomInHotel(hotelId, reservationDetails);
      responseMsg.put("message", Utils.RESERVATION_SUCCESSFUL_MESSAGE);
      return new ResponseEntity(responseMsg, HttpStatus.OK);
    }
    catch(MissingInputException mie){
      responseMsg.put("message", "Error: "+mie.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    catch(InvalidInputException iie){
      responseMsg.put("message", "Error: "+iie.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    catch(InvalidDateRangeException ide){
      responseMsg.put("message", "Error: "+ide.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    catch(RoomUnavailableForDatesException rna){
      responseMsg.put("message", "Error: "+rna.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    catch(Exception exp){
      responseMsg.put("message", "Error: "+exp.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
