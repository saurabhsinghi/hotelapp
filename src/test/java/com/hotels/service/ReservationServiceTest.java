package com.hotels.service;

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


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/hotel-servlet.xml")
public class ReservationServiceTest {

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  ReservationService reservationService;
  public ReservationBean convertJSONtoBean(String jsonFile) throws Exception{
    ObjectMapper mapper = new ObjectMapper();
    ReservationBean obj;
    obj = mapper.readValue(IOUtils.toString(this.getClass().getResourceAsStream(jsonFile)), ReservationBean.class);
    return  obj;
  }

  @Test (expected = RoomUnavailableForDatesException.class)
  public void testReserveRoomWithInvalidHotelId() throws Exception{
    ReservationBean bean = convertJSONtoBean("/valid.json");
    int result = reservationService.reserveRoomInHotel(0, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = MissingInputException.class)
  public void testReserveRoomWithMissingCheckin() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_checkin.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = MissingInputException.class)
  public void testReserveRoomWithMissingCheckout() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_checkout.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = MissingInputException.class)
  public void testReserveRoomWithMissingEmail() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_email.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = MissingInputException.class)
  public void testReserveRoomWithMissingFName() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_firstname.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = MissingInputException.class)
  public void testReserveRoomWithMissingLName() throws Exception{
    ReservationBean bean = convertJSONtoBean("/missing_lastname.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = InvalidInputException.class)
  public void testReserveRoomWithInvalidDateFormat() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_date_format.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = InvalidDateRangeException.class)
  public void testReserveRoomWithInvalidDateRange() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_daterange.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = InvalidDateRangeException.class)
  public void testReserveRoomWithInvalidDateRangeSameInAndOut() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_daterange_same_inout.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test (expected = InvalidDateRangeException.class)
  public void testReserveRoomWithInvalidDateRangePast() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_daterange_past.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }


  @Test (expected = InvalidInputException.class)
  public void testReserveRoomWithInvalidEmail() throws Exception{
    ReservationBean bean = convertJSONtoBean("/invalid_email.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==0);
  }

  @Test
  public void testReserveRoomValid() throws Exception{
    ReservationBean bean = convertJSONtoBean("/valid.json");
    int result = reservationService.reserveRoomInHotel(100, bean);
    Assert.assertTrue(result==1);
  }
}
