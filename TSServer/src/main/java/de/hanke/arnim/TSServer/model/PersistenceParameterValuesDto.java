package de.hanke.arnim.TSServer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * PersistenceParameterValuesDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

public class PersistenceParameterValuesDto   {
  @JsonProperty("tsComposedKey")
  private TimeSeriesComposedKey tsComposedKey = null;

  @JsonProperty("tsvalues")
  @Valid
  private List<TimeSeriesValue> tsvalues = null;

  public PersistenceParameterValuesDto tsComposedKey(TimeSeriesComposedKey tsComposedKey) {
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

  public PersistenceParameterValuesDto tsvalues(List<TimeSeriesValue> tsvalues) {
    this.tsvalues = tsvalues;
    return this;
  }

  public PersistenceParameterValuesDto addTsvaluesItem(TimeSeriesValue tsvaluesItem) {
    if (this.tsvalues == null) {
      this.tsvalues = new ArrayList<TimeSeriesValue>();
    }
    this.tsvalues.add(tsvaluesItem);
    return this;
  }

  /**
   * Get tsvalues
   * @return tsvalues
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<TimeSeriesValue> getTsvalues() {
    return tsvalues;
  }

  public void setTsvalues(List<TimeSeriesValue> tsvalues) {
    this.tsvalues = tsvalues;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersistenceParameterValuesDto persistenceParameterValuesDto = (PersistenceParameterValuesDto) o;
    return Objects.equals(this.tsComposedKey, persistenceParameterValuesDto.tsComposedKey) &&
        Objects.equals(this.tsvalues, persistenceParameterValuesDto.tsvalues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tsComposedKey, tsvalues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersistenceParameterValuesDto {\n");
    
    sb.append("    tsComposedKey: ").append(toIndentedString(tsComposedKey)).append("\n");
    sb.append("    tsvalues: ").append(toIndentedString(tsvalues)).append("\n");
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

