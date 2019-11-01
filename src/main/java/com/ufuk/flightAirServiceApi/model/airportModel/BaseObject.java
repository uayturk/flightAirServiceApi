package com.ufuk.flightAirServiceApi.model.airportModel;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseObject {

  @SerializedName(value = "fs")
  private String fs;

  @SerializedName(value = "iata")
  private String iata;

  @SerializedName(value = "icao")
  private String icao;

  @SerializedName(value = "faa")
  private String faa;

  @SerializedName(value = "name")
  private String name;

  @SerializedName(value = "street1")
  private String street1;

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

  @SerializedName(value = "countryName")
  private String countryName;

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

}
