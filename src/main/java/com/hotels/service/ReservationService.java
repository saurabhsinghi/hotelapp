package com.hotels.service;

import com.hotels.dao.ReservationDao;
import com.hotels.exception.RoomUnavailableForDatesException;
import com.hotels.model.ReservationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * © 2015 – 2018. Talix, Inc. All rights reserved.
 *
 * @author: ssinghi
 */
@Service
public class ReservationService {

  @Autowired
  ReservationDao reservationDao;
  public void reserveRoomInHotel(long hotelId, ReservationBean reservationDetails){
    int result = reservationDao.reserveRoomInHotel(hotelId, reservationDetails);
    if(result<1){
      throw new RoomUnavailableForDatesException(reservationDetails.getCheckIn(), reservationDetails.getCheckOut());
    }
  }
}
