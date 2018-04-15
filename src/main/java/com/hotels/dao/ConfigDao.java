package com.hotels.dao;

import com.hotels.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ConfigDao {
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


  public int setRoomConfigForHotel(long hotelId,int rooms, float ovrbkg_level){
    Map<String, Object> params = new HashMap();
    params.put("rooms", rooms);
    params.put("level", ovrbkg_level);
    params.put("hotelId", hotelId);
    int count = namedParameterJdbcTemplate.update(Utils.SQL_SET_CONFIG, params);
    return count;
  }

}
