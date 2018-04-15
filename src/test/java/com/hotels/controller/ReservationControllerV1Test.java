package com.hotels.controller;

import org.apache.tika.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/hotel-servlet.xml")

public class ReservationControllerV1Test {

  private MockMvc mockMvc;

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  protected WebApplicationContext wac;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }

  @Test
  public void testPUTforPOSTReservation() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/valid.json"))
         ))
        .andExpect(status().isMethodNotAllowed());

  }

  @Test
  public void testGETforPOSTReservation() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/valid.json"))
                ))
        .andExpect(status().isMethodNotAllowed());
  }

  @Test
  public void testPOSTReservationForInvalidHotelId() throws Exception {
    String url = "/v1/reservations/hotel/0";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/valid.json"))
                ))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testPOSTReservationForInvalidDateFormat() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/invalid_date_format.json"))
                ))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPOSTReservationForInvalidDateRange() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/invalid_daterange.json"))
                ))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testPOSTReservationForInvalidDateRangeSameInAndOut() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/invalid_daterange_same_inout.json"))
                ))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testPOSTReservationForInvalidDateRangePast() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/invalid_daterange_past.json"))
                ))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testPOSTReservationForInvalidEmail() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/invalid_email.json"))
                ))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPOSTReservationForMissingEmail() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/missing_email.json"))
                ))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPOSTReservationForMissingFirstName() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/missing_firstname.json"))
                ))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPOSTReservationForMissingLastName() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/missing_lastname.json"))
                ))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPOSTReservationForMissingCheckin() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/missing_checkin.json"))
                ))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPOSTReservationForMissingCheckout() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/missing_checkout.json"))
                ))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testPOSTReservationValid() throws Exception {
    String url = "/v1/reservations/hotel/100";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(IOUtils.toString(this.getClass().getResourceAsStream("/valid.json"))
                ))
        .andExpect(status().isOk());

  }
}
