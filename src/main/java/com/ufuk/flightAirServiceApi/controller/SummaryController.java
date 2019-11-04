package com.ufuk.flightAirServiceApi.controller;


import com.ufuk.flightAirServiceApi.service.AirlineService;
import com.ufuk.flightAirServiceApi.service.AirportService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SummaryController {

  @Autowired
  AirportService airportService;

  @Autowired
  AirlineService airlineService;

  @RequestMapping(value = "/airportApi", method = RequestMethod.GET)

  public String summary(ModelMap modelMap) throws IOException {

    modelMap.addAttribute("summaryActive", airportService.getActiveOrInactiveAirports(true));

    return "summary";

  }


}
