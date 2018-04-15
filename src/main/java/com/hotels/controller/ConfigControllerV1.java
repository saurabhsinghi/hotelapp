package com.hotels.controller;

import com.hotels.exception.HotelNotFoundException;
import com.hotels.exception.InvalidInputException;
import com.hotels.service.ConfigService;
import com.hotels.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/v1/config")
public class ConfigControllerV1 {
  @Autowired
  ConfigService configService;

  @RequestMapping(method = RequestMethod.PUT, value="/hotel/{id}",  produces = "application/json")
  public ResponseEntity<? extends Object> setRoomConfig(@PathVariable("id") long hotelId,
                                       @RequestParam(required = true) int rooms,
                                       @RequestParam(required = true) float ovrbkg_level,
                                       HttpServletResponse response){
    Map<String, String> responseMsg = new HashMap<>();
    try{
      configService.setRoomConfigForHotel(hotelId, rooms, ovrbkg_level);
      responseMsg.put("message", Utils.CONFIG_SUCCESSFUL_MESSAGE);
      return new ResponseEntity(responseMsg, HttpStatus.OK);
    }
    catch(InvalidInputException iie){
      responseMsg.put("message", "Error: "+iie.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.BAD_REQUEST);
    }
    catch(HotelNotFoundException hnf){
      responseMsg.put("message", "Error: "+hnf.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.NOT_FOUND);
    }
    catch (Exception exp){
      responseMsg.put("message", "Error: "+exp.getMessage());
      return new ResponseEntity(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
