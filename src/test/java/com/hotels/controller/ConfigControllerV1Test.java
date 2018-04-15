package com.hotels.controller;

import com.hotels.exception.HotelNotFoundException;
import com.hotels.util.Utils;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/hotel-servlet.xml")

public class ConfigControllerV1Test {
  private MockMvc mockMvc;

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  protected WebApplicationContext wac;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }

  @Test
  public void testPUTConfigWithInvalidHotelId() throws Exception {
    String url = "/v1/config/hotel/0?rooms=15&ovrbkg_level=2 ";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());

  }

  @Test
  public void testPUTConfigWithInvalidRoomCount() throws Exception {
    String url = "/v1/config/hotel/100?rooms=-1&ovrbkg_level=2 ";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testPUTConfigWithInvalidHotelIdAndRoomCount() throws Exception {
    String url = "/v1/config/hotel/100?rooms=-1&ovrbkg_level=-1 ";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testPUTConfigWithInvalidBookingLevel_101() throws Exception {
    String url = "/v1/config/hotel/100?rooms=100&ovrbkg_level=101";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testPUTConfigWithInvalidBookingLevel_Minus1() throws Exception {
    String url = "/v1/config/hotel/100?rooms=100&ovrbkg_level=-1";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testPUTConfigWithMissingBookingLevel() throws Exception {
    String url = "/v1/config/hotel/100?rooms=100";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testPUTConfigWithMissingRoomCount() throws Exception {
    String url = "/v1/config/hotel/100?rooms=100";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testPUTConfigWithMissingRoomCountAndBookingLevel() throws Exception {
    String url = "/v1/config/hotel/100";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isBadRequest());

  }

  @Test
  public void testPUTConfigWithMissingHotelId() throws Exception {
    String url = "/v1/config/hotel?rooms=100&ovrbkg_level=1";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());

  }

  @Test
  public void testGETForPUT() throws Exception {
    String url = "/v1/config/hotel?rooms=100&ovrbkg_level=1";
    mockMvc
        .perform(
            get(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());

  }

  @Test
  public void testPOSTForPUT() throws Exception {
    String url = "/v1/config/hotel?rooms=100&ovrbkg_level=1";
    mockMvc
        .perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());

  }

  @Test
  public void testPUTConfigValid() throws Exception {
    String url = "/v1/config/hotel/100?rooms=15&ovrbkg_level=2";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().string("{\"message\":\"" + Utils.CONFIG_SUCCESSFUL_MESSAGE + "\"}"));
  }

  @Test
  public void testPUTConfigValidWithMinRoomCount() throws Exception {
    String url = "/v1/config/hotel/100?rooms=0&ovrbkg_level=2";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().string("{\"message\":\"" + Utils.CONFIG_SUCCESSFUL_MESSAGE + "\"}"));
  }

  @Test
  public void testPUTConfigValidWithMinRoomCountAndMinBookingLevel() throws Exception {
    String url = "/v1/config/hotel/100?rooms=0&ovrbkg_level=0";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().string("{\"message\":\"" + Utils.CONFIG_SUCCESSFUL_MESSAGE + "\"}"));
  }

  @Test
  public void testPUTConfigValidWithMaxBookingLevel() throws Exception {
    String url = "/v1/config/hotel/100?rooms=10&ovrbkg_level=100";
    mockMvc
        .perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().string("{\"message\":\"" + Utils.CONFIG_SUCCESSFUL_MESSAGE + "\"}"));
  }

}
