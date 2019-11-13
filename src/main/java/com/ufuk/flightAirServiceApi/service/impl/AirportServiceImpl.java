package com.ufuk.flightAirServiceApi.service.impl;


import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.google.gson.Gson;
import com.ufuk.flightAirServiceApi.beans.AirportCollection;
import com.ufuk.flightAirServiceApi.model.airportModel.Airports;
import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import com.ufuk.flightAirServiceApi.service.AirportService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AirportServiceImpl implements AirportService {

  // url for airport
  // https://api.flightstats.com/flex/airports/rest/v1/json/all?appId=6354af1a&appKey=+131acf5588626fec70d69a7b5ea59583&details=true&ormat=json
  private final MongoTemplate mongoTemplate;

  @Autowired
  AirportServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  /**
   * readJsonFromUrlSaveMongoDb method reads JSOn from server and afterwards save it to the mongoDB.
   * @throws IOException valid exception for method.
   * @throws JSONException
   */
   //                     msec sec min  (12 hours delay)
  //@Scheduled(fixedRate = 1000*60*720)
  public void readJsonFromUrlSaveMongoDb() throws IOException, JSONException {

    /*String url = "https://api.flightstats.com/flex/airports/rest/v1/json/all?appId=6354af1a&appKey=+131acf5588626fec70d69a7b5ea59583&details=true&ormat=json";
    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    int responseCode = con.getResponseCode();
    String responseMessage = con.getResponseMessage();
    System.out.println("\nHere we sending 'POST' request to URL : " + url);
    System.out.println("Response Code : " + responseCode);
    System.out.println("Response Message : " + responseMessage);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));*/

    FileReader fr = new FileReader("/home/ufuk/Masaüstü/test2");
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
     *   "airports": [
     *     {
     *       "fs": "00M",
     *       .
     *       .
     *       .
     *
     *     },
     *     {
     *       "fs": "00N",
     *       .
     *       .
     *       .
     *     },
     *   ]
     * }
     */

    Gson gson = new Gson();
    Airports baseObjects = gson.fromJson(response.toString(), Airports.class);  //Converting JSON to Java Object. From response.toString() (JSON) to Airport.class(JAVA OBJECT)

    for (BaseObject o : baseObjects.getBaseObjects()) {
      String jsonObject = gson.toJson(o); //Converting our Object type to JSON type.
      BaseObject baseObject = gson.fromJson(jsonObject, BaseObject.class);//Converting our JSON type to baseobject type.In here,our datas are filled in Baseobject fields.From jsonObject(JSON) to BaseObject.class(BaseObject type)
                                                                           //This code's shortly explain is: It is save baseobject to database true format.
      log.info("trying to save airport object: {}", baseObject);
      mongoTemplate.save(baseObject, AirportCollection.OBJECTS.toString());
      log.info("successfully saved airport object with fs code{}", o.getFs()); //fs code means ICAO code and it is unique for airports.

      System.out.println(baseObject);

    }
  }


  /**
   * getActiveOrInactiveAirports gets active or inactive airports.
   * @param active valid boolean value. (true or false)
   * @return active or inactive airports.
   */
  public List<BaseObject> getActiveOrInactiveAirports(Boolean active) {
    log.info("trying to load active/inactive airports.");
    Query query;
    if(active.equals(true)) {
      query = new Query(where("active").is(true));
      log.info("query to fetch active airports objects: {}", query);
    }else {
      query = new Query(where("active").is(false));
      log.info("query to fetch inactive airports objects: {}", query);
    }

    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT: {}", result);

    return result;
  }


  /**
   *
   * @param code valid fs (FlightStats code) for airport.
   * @return airports for given fs code.
   */
  public List<BaseObject> getAirportsByCode(String code){
    log.info("trying to get airports for given fs code {}.",code);
    Query query = new Query(where("fs").is(code));

    log.info("query to fetch airports for given codes objects: {}", query);
    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRPORTS BY GIVEN FS CODE: {}", result);
    return result;
  }

  /**
   *
   * @param cityCode valid city code for airpots.
   * @return airports for given city code.
   */
  public List<BaseObject> getAirportsByCityCode(String cityCode){
    log.info("trying to get airports for given city code {}.",cityCode);

    Query query = new Query(where("cityCode").is(cityCode));

    log.info("query to fetch airports for given city codes objects: {}", query);
    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRPORTS BY GIVEN CITY CODE: {}", result);
    return result;
  }


  /**
   *
   * @param iataCode valid iata code.
   * @return airports for given iata.
   */
  public List<BaseObject> getAirportsByIataCode(String iataCode){
    log.info("trying to get airports for given iata code {}.",iataCode);

    Query query = new Query(where("iata").is(iataCode));

    log.info("query to fetch airports for given iata codes objects: {}", query);
    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRPORTS BY GIVEN IATA CODE: {}", result);
    return result;
  }

  /**
   *
   * @param icaoCode valid icao code.
   * @return airports for given icao codes.
   */
  public List<BaseObject> getAirportsByIcaoCode(String icaoCode){
    log.info("trying to get airports for given icao code {}.",icaoCode);

    Query query = new Query(where("icao").is(icaoCode));

    log.info("query to fetch airports for given icao codes objects: {}", query);
    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRPORTS BY GIVEN ICAO CODE: {}", result);
    return result;
  }


  /**
   *
   * @param city valid city name.
   * @return airports for given city name.
   */
  public List<BaseObject> getAirportsByCity(String city){
    log.info("trying to get airports for given city {}.",city);

    Query query = new Query(where("city").is(city));

    log.info("query to fetch airports for given city objects: {}", query);
    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRPORTS BY GIVEN CITY NAME: {}", result);
    return result;
  }

  /**
   *
   * @param country valid country name.
   * @return airports for given country name.
   */
  public List<BaseObject> getAirportsByCountryName(String country){
    log.info("trying to get airports for given city {}.",country);

    Query query = new Query(where("countryName").is(country));

    log.info("query to fetch airports for given country objects: {}", query);
    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched result size: {}", result.size());
    log.info("FINALLY RESULT AIRPORTS BY GIVEN COUNTRY NAME: {}", result);
    return result;
  }

  public List<BaseObject> getActiveOrInactiveAirportsByCountryName(Boolean active,String country){
    log.info("trying to get active/inactive airports according to specific country.");
    Query query;
    if(active.equals(true)) {
      query = new Query(where("active").is(true).and("countryName").is(country));
      log.info("query to fetch active airports objects for given country: {}", query);
    }else {
      query = new Query(where("active").is(false).and("countryName").is(country));
      log.info("query to fetch inactive airports objects for given country: {}", query);
    }

    List<BaseObject> result = mongoTemplate.find(query,BaseObject.class,AirportCollection.OBJECTS.toString());
    log.info("successfully fetched active/inactive for given country result size: {}", result.size());
    log.info("FINALLY RESULT: {}", result);

    return result;
  }



}
