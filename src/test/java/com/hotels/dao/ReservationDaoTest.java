package com.hotels.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotels.exception.InvalidDateRangeException;
import com.hotels.exception.InvalidInputException;
import com.hotels.exception.MissingInputException;
import com.hotels.exception.RoomUnavailableForDatesException;
import com.hotels.model.ReservationBean;
import junit.framework.Assert;
import org.apache.tika.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLIntegrityConstraintViolationException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/hotel-servlet.xml")
public class ReservationDaoTest {
  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  ReservationDao reservationDao;

  public ReservationBean convertJSONtoBean(String jsonFile) throws Exception{
    ObjectMapper mapper = new ObjectMapper();
    ReservationBean obj;
    obj = mapper.readValue(IOUtils.toString(this.getClass().getResourceAsStream(jsonFile)), ReservationBean.class);
    return  obj;
  }

  @Test
  public void testReserveRoomWithInvalidHotelId() throws Exception{
    ReservationBean bean = convertJSONtoBean("/valid.json");
    int result = reservationDao.reserveRoomInHotel(0, bean);
    Assert.assertTrue(result == 0);
  }

  @Test (expected = SQLIntegrityConstraintViolationException.class)
  public void testReserveRoomWithMissingCheckin() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_checkin.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test (expected = SQLIntegrityConstraintViolationException.class)
  public void testReserveRoomWithMissingCheckout() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_checkout.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test (expected = SQLIntegrityConstraintViolationException.class)
  public void testReserveRoomWithMissingEmail() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_email.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test (expected = SQLIntegrityConstraintViolationException.class)
  public void testReserveRoomWithMissingFName() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_firstname.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test (expected = SQLIntegrityConstraintViolationException.class)
  public void testReserveRoomWithMissingLName() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_lastname.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test (expected = Exception.class)
  public void testReserveRoomWithInvalidDateFormat() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_date_format.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test
  public void testReserveRoomWithInvalidDateRange() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_daterange.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test
  public void testReserveRoomWithInvalidDateRangeSameInAndOut() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_daterange_same_inout.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test
  public void testReserveRoomWithInvalidDateRangePast() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_daterange_past.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }


  @Test
  public void testReserveRoomWithInvalidEmail() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_email.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }

  @Test
  public void testReserveRoomValid() throws Exception{
    ReservationBean bean = convertJSONtoBean("/valid.json");
    int result = reservationDao.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }
}
