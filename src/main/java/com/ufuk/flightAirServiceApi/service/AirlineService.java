package com.ufuk.flightAirServiceApi.service;


import com.ufuk.flightAirServiceApi.model.airlineModel.BaseObjectAirline;
import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface AirlineService {

  void readAirlineJsonFromUrlSaveMongoDb() throws IOException, JSONException;

  List<BaseObjectAirline> getActiveOrInactiveAirlines(Boolean active);

  List<BaseObjectAirline> getAirlinesByCode(String code);

  List<BaseObjectAirline> getAirlinesByIataCode(String iataCode);

  List<BaseObjectAirline> getAirlinesByIcaoCode(String icaoCode);

}
