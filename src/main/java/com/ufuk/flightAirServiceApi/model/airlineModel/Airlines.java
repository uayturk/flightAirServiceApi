package com.ufuk.flightAirServiceApi.model.airlineModel;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class Airlines {

  @SerializedName("airlines")
  private List<BaseObjectAirline> baseObjectAirlines;


}
