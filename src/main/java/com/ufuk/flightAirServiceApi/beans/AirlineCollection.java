package com.ufuk.flightAirServiceApi.beans;

import lombok.Getter;

@Getter
public enum AirlineCollection {

  OBJECTS("airlineCollection");

  private String airlineCollection;

  AirlineCollection(String airlineCollection) {
    this.airlineCollection = airlineCollection;
  }

  @Override
  public String toString() {
    return airlineCollection;
  }

}
