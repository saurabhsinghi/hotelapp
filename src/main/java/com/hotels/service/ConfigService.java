package com.hotels.service;

import com.hotels.dao.ConfigDao;
import com.hotels.exception.HotelNotFoundException;
import com.hotels.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

  @Autowired
  ConfigDao configDao;

  public int setRoomConfigForHotel(long hotelId,int rooms,float ovrbkg_level){
    if(rooms<0 || (ovrbkg_level<0 || ovrbkg_level>100)){
      StringBuilder msg = new StringBuilder();
      if(rooms<0){
        msg.append("- {Rooms must be greater than 0, current input = "+rooms+"}");
      }
      //Since overbooking level denotes %, it can be only b/w 0-100
      if((ovrbkg_level<0 || ovrbkg_level>100)){
        msg.append("- {Overbooking level must be between 0 and 100, current input = "+ovrbkg_level+"}");
      }
      throw new InvalidInputException(msg.toString());
    }
    int result = configDao.setRoomConfigForHotel(hotelId, rooms, ovrbkg_level);
    if(result<1){
      throw new HotelNotFoundException(hotelId);
    }
    return result;
  }
}
