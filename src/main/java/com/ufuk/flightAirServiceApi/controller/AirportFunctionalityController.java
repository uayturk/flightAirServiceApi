package com.ufuk.flightAirServiceApi.controller;

import com.ufuk.flightAirServiceApi.service.AirportService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AirportFunctionalityController {

  @Autowired
  private final AirportService airportService;

  public AirportFunctionalityController(AirportService airportService) {
    this.airportService = airportService;
  }

  /**
   * Check {@link AirportService}.
   */
  @RequestMapping(value = "/readJsonFromUrlSaveMongoDb", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for readJsonFromUrlSaveMongoDb.\n",
      notes = "readJsonFromUrlSaveMongoDb is read Json object values from url and then saves values to MongoDb.\n "
  )
  public void jsonReadAndSaveDb() throws IOException, JSONException {
    airportService.readJsonFromUrlSaveMongoDb();
  }

}
