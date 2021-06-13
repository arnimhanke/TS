package de.hanke.arnim.TSServer.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * DeleteParameterValuesDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

public class DeleteParameterValuesDto   {
  @JsonProperty("tsId")
  private String tsId = null;

  @JsonProperty("databaseName")
  private String databaseName = null;

  public DeleteParameterValuesDto tsId(String tsId) {
    this.tsId = tsId;
    return this;
  }

  /**
   * Get tsId
   * @return tsId
  **/
  @ApiModelProperty(value = "")


  public String getTsId() {
    return tsId;
  }

  public void setTsId(String tsId) {
    this.tsId = tsId;
  }

  public DeleteParameterValuesDto databaseName(String databaseName) {
    this.databaseName = databaseName;
    return this;
  }

  /**
   * Get databaseName
   * @return databaseName
  **/
  @ApiModelProperty(value = "")


  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteParameterValuesDto deleteParameterValuesDto = (DeleteParameterValuesDto) o;
    return Objects.equals(this.tsId, deleteParameterValuesDto.tsId) &&
        Objects.equals(this.databaseName, deleteParameterValuesDto.databaseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tsId, databaseName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteParameterValuesDto {\n");
    
    sb.append("    tsId: ").append(toIndentedString(tsId)).append("\n");
    sb.append("    databaseName: ").append(toIndentedString(databaseName)).append("\n");
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

