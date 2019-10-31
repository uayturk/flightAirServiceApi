package com.ufuk.flightAirServiceApi.service;

import java.io.IOException;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface AirportService {

  void readJsonFromUrlSaveMongoDb() throws IOException, JSONException;

}
