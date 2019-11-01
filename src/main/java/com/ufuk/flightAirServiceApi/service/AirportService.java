package com.ufuk.flightAirServiceApi.service;

import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface AirportService {

  void readJsonFromUrlSaveMongoDb() throws IOException, JSONException;

  List<BaseObject> getActiveOrDeactivateAirports(Boolean active);

}
