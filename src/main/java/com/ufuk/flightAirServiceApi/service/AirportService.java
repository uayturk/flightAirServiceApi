package com.ufuk.flightAirServiceApi.service;

import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface AirportService {

  void readJsonFromUrlSaveMongoDb() throws IOException, JSONException;

  List<BaseObject> getActiveOrDeactivateAirports(Boolean active);

  List<BaseObject> getAirportsByCode(String code);

  List<BaseObject> getAirportsByCityCode(String cityCode);

  List<BaseObject> getAirportsByIataCode(String iataCode);

  List<BaseObject> getAirportsByIcaoCode(String icaoCode);

  List<BaseObject> getAirportsByCity(String city);

  List<BaseObject> getAirportsByCountryName(String country);

}
