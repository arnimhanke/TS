package de.hanke.arnim.TSServer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.LocalDate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PeriodicTimeSeriesValue
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-28T21:27:24.391Z")


public class PeriodicTimeSeriesValue {
    @JsonProperty("startTime")
    private LocalDate startTime = null;

    @JsonProperty("value")
    @Valid
    private List<BigDecimal> value = null;

    public PeriodicTimeSeriesValue startTime(LocalDate startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Get startTime
     *
     * @return startTime
     **/
    @ApiModelProperty(value = "")

    @Valid

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public PeriodicTimeSeriesValue value(List<BigDecimal> value) {
        this.value = value;
        return this;
    }

    public PeriodicTimeSeriesValue addValueItem(BigDecimal valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<BigDecimal>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<BigDecimal> getValue() {
        return value;
    }

    public void setValue(List<BigDecimal> value) {
        this.value = value;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PeriodicTimeSeriesValue periodicTimeSeriesValue = (PeriodicTimeSeriesValue) o;
        return Objects.equals(this.startTime, periodicTimeSeriesValue.startTime) &&
                Objects.equals(this.value, periodicTimeSeriesValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PeriodicTimeSeriesValue {\n");

        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

