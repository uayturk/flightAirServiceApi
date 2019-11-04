package com.ufuk.flightAirServiceApi.service.impl;


import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.google.gson.Gson;
import com.ufuk.flightAirServiceApi.beans.AirlineCollection;
import com.ufuk.flightAirServiceApi.model.airlineModel.Airlines;
import com.ufuk.flightAirServiceApi.model.airlineModel.BaseObjectAirline;
import com.ufuk.flightAirServiceApi.service.AirlineService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirlineServiceImpl implements AirlineService {

  private final MongoTemplate mongoTemplate;

  public AirlineServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  //@Scheduled(fixedRate = 1000*60*720)
  public void readAirlineJsonFromUrlSaveMongoDb() throws IOException, JSONException {

    /*String url = "https://api.flightstats.com/flex/airlines/rest/v1/json/all?appId=6354af1a&appKey=+131acf5588626fec70d69a7b5ea59583&details=true&ormat=json";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    int responseCode = con.getResponseCode();
    String responseMessage = con.getResponseMessage();
    System.out.println("\nHere we sending 'POST' request to URL : " + url);
    System.out.println("Response Code : " + responseCode);
    System.out.println("Response Message : " + responseMessage);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));*/

    FileReader fr = new FileReader("/home/ufuk/Masaüstü/test");
    BufferedReader in = new BufferedReader(fr);

    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();


    /**
     * Here is a JSON formant comes like:
     * {
     *   "airlines": [
     *     {
     *       "fs": "00M",
     *       .
     *       .
     *     },
     *     {
     *       "fs": "00N",
     *       .
     *       .
     *     },
     *   ]
     * }
     */

    Gson gson = new Gson();
    Airlines baseObjectsAirline = gson.fromJson(response.toString(), Airlines.class);

    for (BaseObjectAirline o : baseObjectsAirline.getBaseObjectAirlines()) {
      String jsonObject = gson.toJson(o); //Converting our Object type to JSON type.
      BaseObjectAirline baseObjectAirline = gson.fromJson(jsonObject, BaseObjectAirline.class);
      log.info("trying to save airline object: {}", baseObjectAirline);
      mongoTemplate.save(baseObjectAirline, AirlineCollection.OBJECTS.toString());
      log.info("successfully saved airline object with fs code {} and icao {}", o.getFs(),o.getIcao());

      System.out.println(baseObjectAirline);

    }
  }



  public List<BaseObjectAirline> getActiveOrInactiveAirlines(Boolean active) {
    log.info("trying to load active/inactive airlines.");
    Query query;
    if(active.equals(true)) {
      query = new Query(where("active").is(true));
      log.info("query to fetch active airline objects: {}", query);
    }else {
      query = new Query(where("active").is(false));
      log.info("query to fetch inactive airlines objects: {}", query);
    }

    List<BaseObjectAirline> result = mongoTemplate.find(query,BaseObjectAirline.class,AirlineCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT: {}", result);

    return result;
  }

  public List<BaseObjectAirline> getAirlinesByCode(String code){
    log.info("trying to get airlines for given fs code {}.",code);
    Query query = new Query(where("fs").is(code));

    log.info("query to fetch airlines for given codes objects: {}", query);
    List<BaseObjectAirline> result = mongoTemplate.find(query,BaseObjectAirline.class,AirlineCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRLINE BY GIVEN FS CODE: {}", result);
    return result;
  }

  public List<BaseObjectAirline> getAirlinesByIataCode(String iataCode){
    log.info("trying to get airlines for given iata code {}.",iataCode);

    Query query = new Query(where("iata").is(iataCode));

    log.info("query to fetch airports for given iata codes objects: {}", query);
    List<BaseObjectAirline> result = mongoTemplate.find(query,BaseObjectAirline.class,AirlineCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRLINE BY GIVEN IATA CODE: {}", result);
    return result;
  }


  public List<BaseObjectAirline> getAirlinesByIcaoCode(String icaoCode){
    log.info("trying to get airlines for given icao code {}.",icaoCode);

    Query query = new Query(where("icao").is(icaoCode));

    log.info("query to fetch airlines for given icao codes objects: {}", query);
    List<BaseObjectAirline> result = mongoTemplate.find(query,BaseObjectAirline.class,AirlineCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRLINE BY GIVEN ICAO CODE: {}", result);
    return result;
  }



}
