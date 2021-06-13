package de.hanke.arnim.TSServer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Timeseries
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

public class Timeseries   {
  @JsonProperty("timeSeriesHead")
  private TimeSeriesHead timeSeriesHead = null;

  @JsonProperty("timeSeriesValues")
  @Valid
  private List<TimeSeriesValue> timeSeriesValues = null;

  public Timeseries timeSeriesHead(TimeSeriesHead timeSeriesHead) {
    this.timeSeriesHead = timeSeriesHead;
    return this;
  }

  /**
   * Get timeSeriesHead
   * @return timeSeriesHead
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TimeSeriesHead getTimeSeriesHead() {
    return timeSeriesHead;
  }

  public void setTimeSeriesHead(TimeSeriesHead timeSeriesHead) {
    this.timeSeriesHead = timeSeriesHead;
  }

  public Timeseries timeSeriesValues(List<TimeSeriesValue> timeSeriesValues) {
    this.timeSeriesValues = timeSeriesValues;
    return this;
  }

  public Timeseries addTimeSeriesValuesItem(TimeSeriesValue timeSeriesValuesItem) {
    if (this.timeSeriesValues == null) {
      this.timeSeriesValues = new ArrayList<TimeSeriesValue>();
    }
    this.timeSeriesValues.add(timeSeriesValuesItem);
    return this;
  }

  /**
   * Get timeSeriesValues
   * @return timeSeriesValues
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<TimeSeriesValue> getTimeSeriesValues() {
    return timeSeriesValues;
  }

  public void setTimeSeriesValues(List<TimeSeriesValue> timeSeriesValues) {
    this.timeSeriesValues = timeSeriesValues;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Timeseries timeseries = (Timeseries) o;
    return Objects.equals(this.timeSeriesHead, timeseries.timeSeriesHead) &&
        Objects.equals(this.timeSeriesValues, timeseries.timeSeriesValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeSeriesHead, timeSeriesValues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Timeseries {\n");
    
    sb.append("    timeSeriesHead: ").append(toIndentedString(timeSeriesHead)).append("\n");
    sb.append("    timeSeriesValues: ").append(toIndentedString(timeSeriesValues)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

