package de.hanke.arnim.TSTool;

public enum TimeseriesUnit {

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

    private final String value;

    TimeseriesUnit(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
