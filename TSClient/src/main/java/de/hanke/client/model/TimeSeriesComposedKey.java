/*
 * TSTool-service
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package de.hanke.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * TimeSeriesComposedKey
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-02-28T12:57:13.425Z")
public class TimeSeriesComposedKey {
    @SerializedName("tsId")
    private String tsId = null;

    @SerializedName("databaseName")
    private String databaseName = null;

    public TimeSeriesComposedKey tsId(String tsId) {
        this.tsId = tsId;
        return this;
    }

    /**
     * Get tsId
     *
     * @return tsId
     **/
    @ApiModelProperty(value = "")
    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId = tsId;
    }

    public TimeSeriesComposedKey databaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    /**
     * The Databasename, where the timeseries should be stored. The user must have access to this database, otherwise the timeseries can&#39;t be stored!
     *
     * @return databaseName
     **/
    @ApiModelProperty(value = "The Databasename, where the timeseries should be stored. The user must have access to this database, otherwise the timeseries can't be stored!")
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
        TimeSeriesComposedKey timeSeriesComposedKey = (TimeSeriesComposedKey) o;
        return Objects.equals(this.tsId, timeSeriesComposedKey.tsId) &&
                Objects.equals(this.databaseName, timeSeriesComposedKey.databaseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsId, databaseName);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TimeSeriesComposedKey {\n");

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

