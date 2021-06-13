package de.hanke.arnim.TSPersistence;


import javax.persistence.Column;
import java.io.Serializable;

public class TimeseriesHeadKey implements Serializable {

    @Column
    String tsId;

    @Column
    String databaseName;

    public TimeseriesHeadKey(String tsId, String databaseName) {
        this.tsId = tsId;
        this.databaseName = databaseName;
    }

    public TimeseriesHeadKey() {
    }

    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId = tsId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
