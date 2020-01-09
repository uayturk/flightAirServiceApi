package com.ufuk.flightAirServiceApi.beans.Search;

import com.ufuk.flightAirServiceApi.model.airportModel.BaseObject;
import java.util.List;
import lombok.Data;

@Data
public class SearchResponse<BaseObject> {
  private List<BaseObject> result;
  private long total;
}
