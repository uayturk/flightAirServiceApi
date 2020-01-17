package com.ufuk.flightAirServiceApi.controller;

import com.ufuk.flightAirServiceApi.beans.Search.SearchRequest;
import com.ufuk.flightAirServiceApi.beans.Search.SearchResponse;
import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import com.ufuk.flightAirServiceApi.service.AirportService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @CrossOrigin For security reasons, browsers prohibit AJAX calls to resources residing outside the current origin. For example,
 * as you’re checking your bank account in one tab,you could have the evil.com website in another tab.
 * The scripts from evil.com shouldn’t be able to make AJAX requests to your bank API (withdrawing money from your account!) using your credentials.
 *
 * @CrossOrigin sadece belirli kaynaklardan gelen requestlere cevap verilmesini sağlar. Fakat metotları GET yapmak gereklidir. POST'ta hata alındı fakat desteklemekte.
 * Global şekilde aşağıya da vererek kullanılır. Fakat biz bunu diable edip configden okuyarak aldık bir başka yöntem olarak.
 */
@Controller
//@CrossOrigin(origins = "http://localhost:8086", methods = RequestMethod.GET)
public class AirportFunctionalityController {

  @Autowired
  private final AirportService airportService;

  public AirportFunctionalityController(AirportService airportService) {
    this.airportService = airportService;
  }

  /**
   * See {@link AirportService#readJsonFromUrlSaveMongoDb()}.
   *
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
   * See {@link AirportService#getActiveOrInactiveAirports(Boolean)}
   *
   * @param active true/false valid active status.
   * @return returns active or inactive datas which is read from DB.
   */
  @RequestMapping(value = "/getActiveOrInactiveAirports", method = {RequestMethod.GET}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getActiveOrInactiveAirports.\n",
      notes = "getActiveOrInactiveAirports is loads Active or Inactive airports values from MongoDb.\n "
  )
  public List<BaseObject> getActiveOrInactiveAirports(@RequestParam(required = true) Boolean active) {
    return airportService.getActiveOrInactiveAirports(active);
  }

  /**
   * See {@link AirportService#getAirportsByCode(String)}
   *
   * @param code valid fs code for airports.
   * @return valid airports for given fs codes.
   */
  @RequestMapping(value = "/getAirportsByCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByCode.\n",
      notes = "getAirportsByCode is returns the airports with the given FlightStats code from MongoDb.\n "
  )
  public List<BaseObject> getAirportsByCode(@RequestParam(required = true) String code) {
    return airportService.getAirportsByCode(code);
  }


  /**
   * See {@link AirportService#getAirportsByCityCode(String)}
   *
   * @param cityCode valid city code for cities.
   * @return airports for given city code.
   */
  @RequestMapping(value = "/getAirportsByCityCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByCityCode.\n",
      notes = "getAirportsByCityCode is returns airports that which is have the given city code from MongoDB.\n "
  )
  public List<BaseObject> getAirportsByCityCode(@RequestParam(required = true) String cityCode) {
    return airportService.getAirportsByCityCode(cityCode);
  }

  /**
   * See {@link AirportService#getAirportsByIataCode(String)}
   *
   * @param iataCode valid iata code for airports.
   * @return airports for given iata.
   */
  @RequestMapping(value = "/getAirportsByIataCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByIataCode.\n",
      notes = "getAirportsByIataCode is returns the airports with the given Iata code from MongoDb.\n "
  )
  public List<BaseObject> getAirportsByIataCode(@RequestParam(required = true) String iataCode) {
    return airportService.getAirportsByIataCode(iataCode);
  }

  /**
   * See {@link AirportService#getAirportsByIcaoCode(String)}
   *
   * @param icaoCode valid icao code.
   * @return airports for given icao codes.
   */
  @RequestMapping(value = "/getAirportsByIcaoCode", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByIcaoCode.\n",
      notes = "getAirportsByIcaoCode is returns airports which is have the given ICAO code from MongoDB.\n "
  )
  public List<BaseObject> getAirportsByIcaoCode(@RequestParam(required = true) String icaoCode) {
    return airportService.getAirportsByIcaoCode(icaoCode);
  }

  /**
   * See {@link AirportService#getAirportsByCity(String)}
   *
   * @param city valid city names.
   * @return airports for given city name.
   */
  @RequestMapping(value = "/getAirportsByCity", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByCity.\n",
      notes = "getAirportsByCity is returns airports that which is have the given city name from MongoDB.\n "
  )
  public List<BaseObject> getAirportsByCity(@RequestParam(required = true) String city) {
    return airportService.getAirportsByCity(city);
  }

  /**
   *See {@link AirportService#getAirportsByCountryName(String)}
   *
   * @param country valid country name.
   * @return airports for given country name.
   */
  @RequestMapping(value = "/getAirportsByCountryName", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getAirportsByCountryName.\n",
      notes = "getAirportsByCity is returns airports which is have the given country name from MongoDB.\n "
  )
  public List<BaseObject> getAirportsByCountryName(@RequestParam(required = true) String country) {
    return airportService.getAirportsByCountryName(country);
  }



  @RequestMapping(value = "/getActiveOrInactiveAirportsByCountryName", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for getActiveOrInactiveAirportsByCountryName.\n",
      notes = "getActiveOrInactiveAirportsByCountryName returns airports which have the specified country name and activity status from MongoDB.\n "
  )
  public List<BaseObject> getActiveOrInactiveAirportsByCountryName(@RequestParam(required = true) Boolean active,@RequestParam(required = true) String country) {
    return airportService.getActiveOrInactiveAirportsByCountryName(active,country);
  }

  /**
   * See {@link AirportService#searchObjects(String)}.
   *
   * @param searchCriteria includes all search params.
   * @return returns specified page of airports result
   */
  @RequestMapping(value = "/searchAirports", method = {RequestMethod.POST}, produces = "application/json")
  @ResponseBody
  @ApiOperation(value = "Necessary doc is the below for searchObjects",
      notes = "searchObjects returns searched text from mongoDB.")
  public List<BaseObject> searchObjects(@RequestParam("criteria") String searchCriteria) {
    return airportService.searchObjects(searchCriteria);
  }

}
