package de.hanke.arnim.TSServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;

/**
 * PeriodicTimeseries
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-28T21:27:24.391Z")


public class PeriodicTimeseries {
    @JsonProperty("timeSeriesHead")
    private TimeSeriesHead timeSeriesHead = null;

    @JsonProperty("timeSeriesValues")
    private Object timeSeriesValues = null;

    public PeriodicTimeseries timeSeriesHead(TimeSeriesHead timeSeriesHead) {
        this.timeSeriesHead = timeSeriesHead;
        return this;
    }

    /**
     * Get timeSeriesHead
     *
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

    public PeriodicTimeseries timeSeriesValues(Object timeSeriesValues) {
        this.timeSeriesValues = timeSeriesValues;
        return this;
    }

    /**
     * Get timeSeriesValues
     *
     * @return timeSeriesValues
     **/
    @ApiModelProperty(value = "")


    public Object getTimeSeriesValues() {
        return timeSeriesValues;
    }

    public void setTimeSeriesValues(Object timeSeriesValues) {
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
        PeriodicTimeseries periodicTimeseries = (PeriodicTimeseries) o;
        return Objects.equals(this.timeSeriesHead, periodicTimeseries.timeSeriesHead) &&
                Objects.equals(this.timeSeriesValues, periodicTimeseries.timeSeriesValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSeriesHead, timeSeriesValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PeriodicTimeseries {\n");

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

