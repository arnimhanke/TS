package de.hanke.arnim.TSServer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets TimeSeriesUnit
 */
public enum TimeSeriesUnit {

    mW("mW"),

    mWH("mWh"),

    kW("kW"),

    kWH("kWh"),

    EUR("EUR"),

    GRADCELCIUS("GRADCELCIUS"),

    LITERPROMINUTE("LITERPROMINUTE"),

    BAR("BAR"),

    STATUS("STATUS"),

    STUNDEN("STUNDEN"),

    MINUTEN("MINUTEN"),

    SEKUNDEN("SEKUNDEN"),

    PROZENT("PROZENT"),

    ANZAHL("ANZAHL"),

    VERSION("VERSION");

    private String value;

    TimeSeriesUnit(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TimeSeriesUnit fromValue(String text) {
        for (TimeSeriesUnit b : TimeSeriesUnit.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}

