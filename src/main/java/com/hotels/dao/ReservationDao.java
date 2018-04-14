package com.hotels.dao;


import com.hotels.model.ReservationBean;
import com.hotels.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReservationDao {
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


  public int reserveRoomInHotel(long hotelId, ReservationBean reservationDetails){
    MapSqlParameterSource params = new MapSqlParameterSource();

    params.addValue("hotelId", hotelId);
    params.addValue("fName", reservationDetails.getGuestFirstName());
    params.addValue("lName", reservationDetails.getGuestLastName());
    params.addValue("email", reservationDetails.getGuestEmail());
    params.addValue("checkIn", reservationDetails.getCheckIn());
    params.addValue("checkOut", reservationDetails.getCheckOut());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    int count = 0;
    //Making the insert thread safe.
    synchronized (this){
      count = namedParameterJdbcTemplate.update(Utils.SQL_ADD_RESERVATION, params, keyHolder);
    }
    return count;
  }
}
