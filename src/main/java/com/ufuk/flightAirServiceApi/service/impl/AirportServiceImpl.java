package com.ufuk.flightAirServiceApi.service.impl;


import com.google.gson.Gson;
import com.ufuk.flightAirServiceApi.beans.AirportCollection;
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

    FileReader fr = new FileReader("/home/ufuk/Masaüstü/test");
    BufferedReader in = new BufferedReader(fr);

    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();


    Gson gson = new Gson();
    List<Object> baseObjects = gson.fromJson(response.toString(), List.class);//Converting JSON to Java Object. From response.toString() (JSON) to List.class(JAVA OBJECT)
    log.info("AAAAAAAAAAAAAAAAA:{}" + baseObjects);
    //Object baseObjects = gson.fromJson(response.toString(),Object.class);
    for (Object o : baseObjects) {
      String jsonObject = gson.toJson(o); //Converting our Object type to JSON type.
      BaseObject baseObject = gson.fromJson(jsonObject, BaseObject.class);//Converting our JSON type to baseobject type.In here,our datas are filled in Baseobject fields.
      //From jsonObject(JSON) to BaseObject.class(BaseObject type)
      log.info("trying to save airport object: {}", baseObject);
      mongoTemplate.save(baseObject, AirportCollection.OBJECTS.toString());
      log.info("successfully saved airport object with fs code{}", baseObject.getFs()); //fs code means ICAO code and it is unique for airports.

      System.out.println(baseObject);

    }
  }

  /*public static void main(String[] args) throws IOException {
    FileReader fr = new FileReader("/home/ufuk/Masaüstü/test");
    BufferedReader in = new BufferedReader(fr);

    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();

    String modifiedServerResponse = response.toString();

    log.info("AAAAAAAAAAA:" + modifiedServerResponse);
  }*/
}
