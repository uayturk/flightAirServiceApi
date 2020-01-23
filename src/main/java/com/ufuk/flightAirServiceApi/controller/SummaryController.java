package com.ufuk.flightAirServiceApi.controller;


import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import com.ufuk.flightAirServiceApi.model.airportModel.ChosenCountryName;
import com.ufuk.flightAirServiceApi.model.airportModel.SearchedText;
import com.ufuk.flightAirServiceApi.service.AirlineService;
import com.ufuk.flightAirServiceApi.service.AirportService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

  @Autowired
  private SearchedText searchedText;

  @RequestMapping(value = "/getCountryNameFromJquery", method = RequestMethod.POST)
  /*@ResponseBody*/  //if you not use this annotation,you get " javax.servlet.ServletException: Circular view path [...] "
  private String getAirportsForCountry(String selectedCountryName) {
    List<List<BaseObject>> airports = new ArrayList<>();
    System.out.println("fffffffffffffffffff:" + selectedCountryName.replaceAll("\"", "").trim());
    chosenCountryName.setChosenCountryName(selectedCountryName.replaceAll("\"", "").trim());

    airports.add(airportService.getAirportsByCountryName(selectedCountryName.replaceAll("\"", "").trim()));

    return "redirect:/airportApi";
  }


  @RequestMapping(value = "/getSearchedResults", method = RequestMethod.POST)
  private String getAirportsForSearchedText(String searchText) {
    List<List<BaseObject>> airports = new ArrayList<>();

    searchedText.setSearchedText(searchText.replaceAll("\"", "").trim());
    System.out.println("GGGGGGGGGGGGgGGGG:" + searchedText.getSearchedText());

    airports.add(airportService.searchObjects(searchedText.getSearchedText()));

    return "redirect:/airportApi";
  }

  @RequestMapping(value = "/airportApi", method = RequestMethod.GET)
  public String summary(ModelMap modelMap) throws IOException {

    modelMap.addAttribute("summaryActive", airportService.getActiveOrInactiveAirports(true));

    if((searchedText.getSearchedText()!=null)){
      modelMap.addAttribute("summaryActive", airportService.searchObjects(searchedText.getSearchedText()));
    }
    //modelMap.addAttribute("summaryActive", airportService.searchObjects(searchedText.getSearchedText()));
    modelMap.addAttribute("summaryInactive", airportService.getActiveOrInactiveAirports(false));
    modelMap.addAttribute("summaryChosen", airportService.getAirportsByCountryName(chosenCountryName.getChosenCountryName()));

    return "summary";

  }


}
