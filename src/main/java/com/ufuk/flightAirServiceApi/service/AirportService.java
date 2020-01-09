package com.ufuk.flightAirServiceApi.service;

import com.ufuk.flightAirServiceApi.beans.Search.SearchRequest;
import com.ufuk.flightAirServiceApi.beans.Search.SearchResponse;
import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface AirportService {

  void readJsonFromUrlSaveMongoDb() throws IOException, JSONException;

  List<BaseObject> getActiveOrInactiveAirports(Boolean active);

  List<BaseObject> getAirportsByCode(String code);

  List<BaseObject> getAirportsByCityCode(String cityCode);

  List<BaseObject> getAirportsByIataCode(String iataCode);

  List<BaseObject> getAirportsByIcaoCode(String icaoCode);

  List<BaseObject> getAirportsByCity(String city);

  List<BaseObject> getAirportsByCountryName(String country);

  List<BaseObject> getActiveOrInactiveAirportsByCountryName(Boolean active,String country);

  /**
   * @param searchCriteria search request parameters.
   * @return returns specified page of airport result.
   */
  List<BaseObject> searchObjects(String searchCriteria);

}
