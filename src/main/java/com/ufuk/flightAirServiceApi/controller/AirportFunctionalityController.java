package com.ufuk.flightAirServiceApi.controller;

import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
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

  /**
   * @param active true/false valid active status.
   * @return returns active or deactive datas which is read from DB.
   */
  @RequestMapping(value = "/getActiveOrDeactivateAirports", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getActiveOrDeactivateAirports.\n",
      notes = "getActiveOrDeactivateAirports is loads Active or Deactive airports values from MongoDb.\n "
  )
  public List<BaseObject> getActiveOrDeactivateAirports(@RequestParam(required = true) Boolean active) {
    return airportService.getActiveOrDeactivateAirports(active);
  }

  /**
   *
   * @param code valid fs code for airports.
   * @return valid airports for given fs codes.
   */
  @RequestMapping(value = "/getAirportsByCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByCode.\n",
      notes = "getAirportsByCode is loads airports Json object values from MongoDb.\n "
  )
  public List<BaseObject> getAirportsByCode(@RequestParam(required = true) String code) {
    return airportService.getAirportsByCode(code);
  }


  /**
   *
   * @param cityCode valid city code for cities.
   * @return airports for given city code.
   */
  @RequestMapping(value = "/getAirportsByCityCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByCityCode.\n",
      notes = "getAirportsByCityCode is loads airports Json object values from MongoDb.\n "
  )
  public List<BaseObject> getAirportsByCityCode(@RequestParam(required = true) String cityCode) {
    return airportService.getAirportsByCityCode(cityCode);
  }



}
