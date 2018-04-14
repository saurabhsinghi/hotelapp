package com.hotels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class MainController {

  @RequestMapping(value = {"/home", "/"})
  public ModelAndView homePage(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("home");
    return mav;
  }
}
