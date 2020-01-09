package com.ufuk.flightAirServiceApi.model.airportModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "airportCollection") //This annotation marks a class as being a domain object that we want to persist to the database:
public class BaseObject {

  @SerializedName(value = "fs")
  private String fs;

  @SerializedName(value = "iata")
  private String iata;

  @SerializedName(value = "icao")
  private String icao;

  @SerializedName(value = "faa")
  private String faa;

  /**
   * First we need to set up text indexes on the fields on which we want to perform your text query.
   * If we are using Spring data mongo to insert your documents in your database,
   * we can use @TextIndexed annotation and indexes will be built while inserting your document.
   */
  @TextIndexed
  @SerializedName(value = "name")
  private String name;

  @SerializedName(value = "street1")
  private String street1;

  @TextIndexed
  @SerializedName(value = "city")
  private String city;

  @SerializedName(value = "cityCode")
  private String cityCode;

  @SerializedName(value = "stateCode")
  private String stateCode;

  @SerializedName(value = "postalCode")
  private String postalCode;

  @SerializedName(value = "countryCode")
  private String countryCode;

  @TextIndexed
  @SerializedName(value = "countryName")
  private String countryName;

  @TextIndexed
  @SerializedName(value = "regionName")
  private String regionName;

  @SerializedName(value = "timeZoneRegionName")
  private String timeZoneRegionName;

  @SerializedName(value = "weatherZone")
  private String weatherZone;

  @SerializedName(value = "localTime")
  private String localTime;

  @SerializedName(value = "utcOffsetHours")
  private String utcOffsetHours;

  @SerializedName(value = "latitude")
  private Double latitude;

  @SerializedName(value = "longitude")
  private Double longitude;

  @SerializedName(value = "elevationFeet")
  private int elevationFeet;

  @SerializedName(value = "classification")
  private int classification;

  @SerializedName(value = "active")
  private boolean active;

  @SerializedName(value = "weatherUrl")
  private String weatherUrl;

  @SerializedName(value = "delayIndexUrl")
  private String delayIndexUrl;

  @TextIndexed
  private String keywords;

}
