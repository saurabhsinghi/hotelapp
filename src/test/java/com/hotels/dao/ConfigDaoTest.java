package com.hotels.dao;

import com.hotels.exception.HotelNotFoundException;
import com.hotels.exception.InvalidInputException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/hotel-servlet.xml")
public class ConfigDaoTest {
  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  ConfigDao configDao;

  @Test
  public void testSetRoomConfigForHotelWithInvalidHotelId(){
    int result = configDao.setRoomConfigForHotel(0, 100, 12);
    Assert.assertTrue(result == 0);
  }

  @Test
  public void testSetRoomConfigForHotelWithInvalidRoomCount(){
    int result = configDao.setRoomConfigForHotel(100, -1, 12);
    Assert.assertTrue(result == 1);
  }

  @Test
  public void testSetRoomConfigForHotelWithInvalidBookingLevel_Under(){
    int result = configDao.setRoomConfigForHotel(100, 100, -1);
    Assert.assertTrue(result == 1);
  }

  @Test
  public void testSetRoomConfigForHotelWithInvalidBookingLevel_Over(){
    int result = configDao.setRoomConfigForHotel(100, 100, 101);
    Assert.assertTrue(result == 1);
  }

  @Test
  public void testSetRoomConfigForHotelWithInvalidRoomCountAndBookingLevel(){
    int result = configDao.setRoomConfigForHotel(100, -1, 101);
    Assert.assertTrue(result == 1);
  }

  @Test
  public void testSetRoomConfigForHotelWithZeroRoomCount(){
    int result = configDao.setRoomConfigForHotel(100, 0, 100);
    Assert.assertTrue(result == 1);
  }

  @Test
  public void testSetRoomConfigForHotelWithNonZeroRoomCount(){
    int result = configDao.setRoomConfigForHotel(100, 100, 2);
    Assert.assertTrue(result==1);
  }

  @Test
  public void testSetRoomConfigForHotelWithZeroBookingLevel(){
    int result = configDao.setRoomConfigForHotel(100, 100, 0);
    Assert.assertTrue(result==1);
  }

}
