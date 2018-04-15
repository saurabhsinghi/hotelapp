package com.hotels.service;

import com.hotels.dao.ReservationDao;
import com.hotels.exception.RoomUnavailableForDatesException;
import com.hotels.model.ReservationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  @Autowired
  ReservationDao reservationDao;
  public int reserveRoomInHotel(long hotelId, ReservationBean reservationDetails){
    //Validate request input before proceeding - throws necessary exceptions.
    reservationDetails.validateInput();
    int result = reservationDao.reserveRoomInHotel(hotelId, reservationDetails);
    if(result<1){
      throw new RoomUnavailableForDatesException(reservationDetails.getCheckIn(), reservationDetails.getCheckOut());
    }
    return result;
  }
}
