package de.hanke.arnim.TSServer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * PersistenceParameterHeadDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

public class PersistenceParameterHeadDto   {
  @JsonProperty("tsComposedKey")
  private TimeSeriesComposedKey tsComposedKey = null;

  @JsonProperty("tsraster")
  private TimeSeriesRaster tsraster = null;

  @JsonProperty("tsUnit")
  private TimeSeriesUnit tsUnit = null;

  public PersistenceParameterHeadDto tsComposedKey(TimeSeriesComposedKey tsComposedKey) {
    this.tsComposedKey = tsComposedKey;
    return this;
  }

  /**
   * Get tsComposedKey
   * @return tsComposedKey
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TimeSeriesComposedKey getTsComposedKey() {
    return tsComposedKey;
  }

  public void setTsComposedKey(TimeSeriesComposedKey tsComposedKey) {
    this.tsComposedKey = tsComposedKey;
  }

  public PersistenceParameterHeadDto tsraster(TimeSeriesRaster tsraster) {
    this.tsraster = tsraster;
    return this;
  }

  /**
   * Get tsraster
   * @return tsraster
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TimeSeriesRaster getTsraster() {
    return tsraster;
  }

  public void setTsraster(TimeSeriesRaster tsraster) {
    this.tsraster = tsraster;
  }

  public PersistenceParameterHeadDto tsUnit(TimeSeriesUnit tsUnit) {
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
    PersistenceParameterHeadDto persistenceParameterHeadDto = (PersistenceParameterHeadDto) o;
    return Objects.equals(this.tsComposedKey, persistenceParameterHeadDto.tsComposedKey) &&
        Objects.equals(this.tsraster, persistenceParameterHeadDto.tsraster) &&
        Objects.equals(this.tsUnit, persistenceParameterHeadDto.tsUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tsComposedKey, tsraster, tsUnit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersistenceParameterHeadDto {\n");
    
    sb.append("    tsComposedKey: ").append(toIndentedString(tsComposedKey)).append("\n");
    sb.append("    tsraster: ").append(toIndentedString(tsraster)).append("\n");
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

