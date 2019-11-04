package com.ufuk.flightAirServiceApi.model.airlineModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data //This means @Getter,@Setter,@ToString
public class BaseObjectAirline {

  @SerializedName(value = "fs") //this is "fs " from json object.
  private String fs; //we are gave a name "fs",afterwards our "fs" is matcing to "fs" from JSON.

  @SerializedName(value = "iata")
  private String iata;

  @SerializedName(value = "icao")
  private String icao;

  @SerializedName(value = "name")
  private String name;

  @SerializedName(value = "active")
  private boolean active;

}
