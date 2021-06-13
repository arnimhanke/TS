package de.hanke.arnim.TSServer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TimeSeriesHead
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

public class TimeSeriesHead   {
  @JsonProperty("tsId")
  private String tsId = null;

  @JsonProperty("database")
  private String database = null;

  @JsonProperty("tsRaster")
  private TimeSeriesRaster tsRaster = null;

  @JsonProperty("tsUnit")
  private TimeSeriesUnit tsUnit = null;

  public TimeSeriesHead tsId(String tsId) {
    this.tsId = tsId;
    return this;
  }

  /**
   * Timeseries ID
   * @return tsId
  **/
  @ApiModelProperty(value = "Timeseries ID")


  public String getTsId() {
    return tsId;
  }

  public void setTsId(String tsId) {
    this.tsId = tsId;
  }

  public TimeSeriesHead database(String database) {
    this.database = database;
    return this;
  }

  /**
   * The Databasename, where the timeseries should be stored. The user must have access to this database, otherwise the timeseries can't be stored!
   * @return database
  **/
  @ApiModelProperty(value = "The Databasename, where the timeseries should be stored. The user must have access to this database, otherwise the timeseries can't be stored!")


  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public TimeSeriesHead tsRaster(TimeSeriesRaster tsRaster) {
    this.tsRaster = tsRaster;
    return this;
  }

  /**
   * Get tsRaster
   * @return tsRaster
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TimeSeriesRaster getTsRaster() {
    return tsRaster;
  }

  public void setTsRaster(TimeSeriesRaster tsRaster) {
    this.tsRaster = tsRaster;
  }

  public TimeSeriesHead tsUnit(TimeSeriesUnit tsUnit) {
    this.tsUnit = tsUnit;
    return this;
  }

  /**
   * Get tsUnit
   * @return tsUnit
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TimeSeriesUnit getTsUnit() {
    return tsUnit;
  }

  public void setTsUnit(TimeSeriesUnit tsUnit) {
    this.tsUnit = tsUnit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeSeriesHead timeSeriesHead = (TimeSeriesHead) o;
    return Objects.equals(this.tsId, timeSeriesHead.tsId) &&
        Objects.equals(this.database, timeSeriesHead.database) &&
        Objects.equals(this.tsRaster, timeSeriesHead.tsRaster) &&
        Objects.equals(this.tsUnit, timeSeriesHead.tsUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tsId, database, tsRaster, tsUnit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeSeriesHead {\n");
    
    sb.append("    tsId: ").append(toIndentedString(tsId)).append("\n");
    sb.append("    database: ").append(toIndentedString(database)).append("\n");
    sb.append("    tsRaster: ").append(toIndentedString(tsRaster)).append("\n");
    sb.append("    tsUnit: ").append(toIndentedString(tsUnit)).append("\n");
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

