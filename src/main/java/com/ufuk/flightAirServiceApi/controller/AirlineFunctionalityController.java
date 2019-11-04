package com.ufuk.flightAirServiceApi.controller;

import com.ufuk.flightAirServiceApi.model.airlineModel.BaseObjectAirline;
import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import com.ufuk.flightAirServiceApi.service.AirlineService;
import com.ufuk.flightAirServiceApi.service.AirportService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AirlineFunctionalityController {

  @Autowired
  AirlineService airlineService;

  public AirlineFunctionalityController(AirlineService airlineService) {
    this.airlineService = airlineService;
  }

  @RequestMapping(value = "/readAirlineJsonFromUrlSaveMongoDb", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for readAirlineJsonFromUrlSaveMongoDb.\n",
      notes = "readAirlineJsonFromUrlSaveMongoDb is read Json object values from url and then saves values to MongoDb.\n "
  )
  public void readAirlineJsonFromUrlSaveMongoDb() throws IOException, JSONException {
    airlineService.readAirlineJsonFromUrlSaveMongoDb();
  }


  @RequestMapping(value = "/getActiveOrInactiveAirlines", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getActiveOrInactiveAirlines.\n",
      notes = "getActiveOrInactiveAirlines is loads Active or Inactive airlines values from MongoDb.\n "
  )
  public List<BaseObjectAirline> getActiveOrInactiveAirlines(@RequestParam(required = true) Boolean active) {
    return airlineService.getActiveOrInactiveAirlines(active);
  }


  @RequestMapping(value = "/getAirlinesByCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirlinesByCode.\n",
      notes = "getAirlinesByCode is returns the airlines with the given FlightStats code from MongoDb.\n "
  )
  public List<BaseObjectAirline> getAirlinesByCode(@RequestParam(required = true) String code) {
    return airlineService.getAirlinesByCode(code);
  }

  @RequestMapping(value = "/getAirlinesByIataCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirlinesByIataCode.\n",
      notes = "getAirlinesByIataCode is returns the airline with the given Iata code from MongoDb.\n "
  )
  public List<BaseObjectAirline> getAirlinesByIataCode(@RequestParam(required = true) String iataCode) {
    return airlineService.getAirlinesByIataCode(iataCode);
  }

  @RequestMapping(value = "/getAirlinesByIcaoCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirlinesByIcaoCode.\n",
      notes = "getAirlinesByIcaoCode is returns the airline with the given icao code from MongoDb.\n "
  )
  public List<BaseObjectAirline> getAirlinesByIcaoCode(@RequestParam(required = true) String icaoCode) {
    return airlineService.getAirlinesByIcaoCode(icaoCode);
  }



}
