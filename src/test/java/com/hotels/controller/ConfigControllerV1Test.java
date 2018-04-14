package com.hotels.controller;

import org.junit.Before;
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

public class ConfigControllerV1Test {
  private MockMvc mockMvc;

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Autowired
  protected WebApplicationContext wac;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }

}
