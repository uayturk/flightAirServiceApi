package com.ufuk.flightAirServiceApi.model.airportModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Airports {

  @SerializedName("airports")
  private List<BaseObject> baseObjects;

}
