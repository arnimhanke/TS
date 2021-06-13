package de.hanke.arnim.TSServer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * AccessParamterHeadDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

public class AccessParamterHeadDto   {
  @JsonProperty("tsComposedKey")
  private TimeSeriesComposedKey tsComposedKey = null;

  public AccessParamterHeadDto tsComposedKey(TimeSeriesComposedKey tsComposedKey) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccessParamterHeadDto accessParamterHeadDto = (AccessParamterHeadDto) o;
    return Objects.equals(this.tsComposedKey, accessParamterHeadDto.tsComposedKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tsComposedKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccessParamterHeadDto {\n");
    
    sb.append("    tsComposedKey: ").append(toIndentedString(tsComposedKey)).append("\n");
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

