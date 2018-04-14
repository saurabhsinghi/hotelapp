package com.hotels.service;

import com.hotels.dao.ConfigDao;
import com.hotels.exception.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * © 2015 – 2018. Talix, Inc. All rights reserved.
 *
 * @author: ssinghi
 */
@Service
public class ConfigService {

  @Autowired
  ConfigDao configDao;

  public void setRoomConfigForHotel(long hotelId,int rooms,float ovrbkg_level){
    int result = configDao.setRoomConfigForHotel(hotelId, rooms, ovrbkg_level);
    if(result<1){
      throw new HotelNotFoundException(hotelId);
    }
  }
}
