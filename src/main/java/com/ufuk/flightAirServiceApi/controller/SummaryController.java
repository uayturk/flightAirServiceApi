package com.ufuk.flightAirServiceApi.controller;


import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import com.ufuk.flightAirServiceApi.model.airportModel.ChosenCountryName;
import com.ufuk.flightAirServiceApi.service.AirlineService;
import com.ufuk.flightAirServiceApi.service.AirportService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

  @Autowired
  private ChosenCountryName chosenCountryName;


  @RequestMapping(value = "/getCountryNameFromJquery", method = RequestMethod.POST)
  /*@ResponseBody*/  //if you not use this annotation,you get " javax.servlet.ServletException: Circular view path [...] "
  private String getAirportsForCountry(String selectedCountryName) {
    List<List<BaseObject>> airports = new ArrayList<>();
    chosenCountryName.setChosenCountryName(selectedCountryName.replaceAll("\"", "").trim());

    airports.add(airportService.getAirportsByCountryName(selectedCountryName.replaceAll("\"", "").trim()));

    return "redirect:/airportApi";
  }

  @RequestMapping(value = "/airportApi", method = RequestMethod.GET)
  public String summary(ModelMap modelMap) throws IOException {

    modelMap.addAttribute("summaryActive", airportService.getActiveOrInactiveAirports(true));
    modelMap.addAttribute("summaryInactive", airportService.getActiveOrInactiveAirports(false));
    modelMap.addAttribute("summaryChosen", airportService.getAirportsByCountryName(chosenCountryName.getChosenCountryName()));

    return "summary";

  }


}
