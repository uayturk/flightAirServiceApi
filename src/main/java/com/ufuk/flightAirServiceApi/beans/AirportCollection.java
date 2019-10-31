package com.ufuk.flightAirServiceApi.beans;

import lombok.Getter;

@Getter
public enum AirportCollection {

  OBJECTS("airportCollection");

  private String airportCollection;

  AirportCollection(String airportCollection) {
    this.airportCollection = airportCollection;
  }

  @Override
  public String toString() {
    return airportCollection;
  }

}
