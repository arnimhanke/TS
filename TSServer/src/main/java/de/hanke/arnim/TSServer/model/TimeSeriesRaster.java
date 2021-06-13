package de.hanke.arnim.TSServer.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets TimeSeriesRaster
 */
public enum TimeSeriesRaster {
  
  PT15M("PT15M"),
  
  PT1H("PT1H"),

  PT15S("PT15S"),
  
  PT1D("PT1D");

  private String value;

  TimeSeriesRaster(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TimeSeriesRaster fromValue(String text) {
    for (TimeSeriesRaster b : TimeSeriesRaster.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

