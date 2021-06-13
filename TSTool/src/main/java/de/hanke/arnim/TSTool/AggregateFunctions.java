package de.hanke.arnim.TSTool;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AggregateFunctions {

    public static List<PeriodicTimeseriesValue> aggregateSeiresForGivenAggregationType(AggregationTypes.AggregationType aggregationType, List<PeriodicTimeseriesValue> data, Instant start, Instant end, Raster raster, String id) {
        int durationOfRaster = Raster.getDuration(raster);
        if (data == null || data.size() == 0) {
            return new ArrayList<>();
        }

        // fill gaps for Intervall
//        data = fillSeries(data, start, end, durationOfRaster);

//        if (durationOfRaster == 15 * 1000) {
//            return data;
//        }

        switch (aggregationType) {
            case COUNT:
                return aggregateSeriesCount( fillSeries(data, start, end, durationOfRaster), start, end, Raster.getDuration(Raster.PT1D), id);
            case AVERAGE:
                return aggregateSeriesAverage( fillSeries(data, start, end, durationOfRaster), start, end, durationOfRaster, id);
            case MAX:
                return aggregateSeriesMax( fillSeries(data, start, end, durationOfRaster), start, end, durationOfRaster, id);
            case MAX_BEFOR_MINOR:
                return aggregateSeriesMaxBeforeMinor( fillSeries(data, start, end, durationOfRaster), start, end, Raster.getDuration(Raster.PT1D), id);
            case DIV_VORTAG:
                return aggregateSeriesDivVortag( fillSeries(data, start, end, durationOfRaster), start, end, durationOfRaster, id);
            default:
                return data;

        }
    }

    /**
     * Für das gegebene Intervall werden die Veränderung von 'LEER' zu 'Enthält etwas' gezählt
     *
     * @param data
     * @param start
     * @param end
     * @param interval
     * @param id
     */
    public static List<PeriodicTimeseriesValue> aggregateSeriesCount(List<PeriodicTimeseriesValue> data, Instant start, Instant end, int interval, String id) {
        System.out.println("aggregateSeriesCount " + id);

        List<PeriodicTimeseriesValue> ret = new ArrayList<>();
        long startAsMilliseconds = start.toEpochMilli();
        Instant date = Instant.ofEpochMilli(startAsMilliseconds);
        long foundIntervals = 0;
        long countForInterval = 0;
        int i = 0;
        double lastFoundStatus = data.get(i).getValue();
        while ((date.isBefore(end) || date.equals(end)) && data.size() > i) {
            if (startAsMilliseconds + (foundIntervals + 1L) * ((long) interval) == date.toEpochMilli()) {
                ret.add(new PeriodicTimeseriesValue(Instant.ofEpochMilli(start.toEpochMilli() + (foundIntervals) * interval), countForInterval));
                foundIntervals++;
                countForInterval = 0;
                if (data.get(i).getValue() != 0) {
                    countForInterval++;
                    lastFoundStatus = data.get(i).getValue();
                }
            } else {
                if (data.get(i).getValue() != 0 && lastFoundStatus != (data.get(i).getValue())) {
                    countForInterval++;
                    lastFoundStatus = data.get(i).getValue();
                } else if (data.get(i).getValue() == 0) {
                    lastFoundStatus = 0;
                }
            }
            i++;
            date = date.plus(15, ChronoUnit.SECONDS);
        }

        return ret;
    }

    /**
     * Für das gegebene Intervall wird der Durchschnitt gebildet
     *
     * @param data
     * @param start
     * @param end
     * @param interval
     * @param id
     */
    public static List<PeriodicTimeseriesValue> aggregateSeriesAverage(List<PeriodicTimeseriesValue> data, Instant start, Instant end, int interval, String id) {
        System.out.println("aggregateSeriesAverage " + id);

        List<PeriodicTimeseriesValue> ret = new ArrayList<>();
        long startAsMilliseconds = start.toEpochMilli();
        Instant date = Instant.ofEpochMilli(start.toEpochMilli());
        double sumForInterval = 0;
        int i = 0;
        long foundIntervals = 0;
        while (date.isBefore(end) || date.equals(end)) {
            if (i >= data.size()) {
                break;
            }
            if (startAsMilliseconds + (foundIntervals + 1L) * ((long) interval) == date.toEpochMilli()) {
                double value = 1000 * sumForInterval / (interval / 15l);
                ret.add(new PeriodicTimeseriesValue(Instant.ofEpochMilli(start.toEpochMilli() + (foundIntervals) * interval), value));
                foundIntervals++;
                sumForInterval = 0;
                sumForInterval += data.get(i).getValue();
            } else {
                double parsedValue = data.get(i).getValue();
                sumForInterval += parsedValue;
            }
            i++;
            date = date.plus(interval, ChronoUnit.MILLIS);
        }
        return ret;
    }

    /**
     * Ermittelt das Maximum für eine Zeitreihe
     *
     * @param data
     * @param start
     * @param end
     * @param interval
     * @param id
     */
    public static List<PeriodicTimeseriesValue> aggregateSeriesMax(List<PeriodicTimeseriesValue> data, Instant start, Instant end, int interval, String id) {
        System.out.println("aggregateSeriesMax " + id);

        List<PeriodicTimeseriesValue> ret = new ArrayList<>();
        long startAsMilliseconds = start.toEpochMilli();
        Instant date = Instant.ofEpochMilli(start.toEpochMilli());
        long foundIntervals = 0;
        double lastmax = data.get(0).getValue();
        int i = 0;
        while (date.isBefore(end) || date.equals(end)) {
            if (i < data.size()) {
                if (startAsMilliseconds + (foundIntervals + 1) * ((long) interval) == date.toEpochMilli()) {
                    ret.add(new PeriodicTimeseriesValue(Instant.ofEpochMilli(start.toEpochMilli() + ((foundIntervals) * interval)), lastmax));
                    foundIntervals++;
                    lastmax = data.get(i).getValue();
                    i++;
                    date = date.plus(15, ChronoUnit.SECONDS);
                } else {
                    double parsedValue = data.get(i).getValue();

                    if (lastmax < parsedValue) {
                        lastmax = parsedValue;
                    }
                    i++;
                    date = date.plus(15, ChronoUnit.SECONDS);
                }
            } else {
                break;
            }
        }

        return ret;
    }

    public static Instant moveInstantToStartOfDay(Instant instant) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        Instant instantAtStartOfDay = localDateTime.toLocalDate().atStartOfDay().toInstant(ZoneId.systemDefault().getRules().getOffset(localDateTime));
        return instantAtStartOfDay;
    }

    public static void main(String[] args) {
        Instant instant = moveInstantToStartOfDay(Instant.parse("2007-12-03T00:05:00.00Z"));
        System.out.println(instant);
    }

    /**
     * Aggregiert die Zeitreihe; Taktik ist dass solange ein größerer Wert gesucht wird, bis der Wert signifikant abfällt
     *
     * @param data
     * @param start
     * @param end
     * @param interval
     * @param id
     */
    public static List<PeriodicTimeseriesValue> aggregateSeriesMaxBeforeMinor(List<PeriodicTimeseriesValue> data, Instant start, Instant end, int interval, String id) {
        System.out.println("aggregateSeriesMaxBeforeMinor " + id);

        List<PeriodicTimeseriesValue> ret = new ArrayList<>();
        double lastMax = 0;
        double lastCommitedValue = 0;
        int aktInterval = 0;

        for (int i = 0; i < data.size(); i++) {
            // TODO: Das hier ist nicht wirklich safe, wenn es um <image ...> geht...
            double parsedValue = data.get(i).getValue();
            if (data.get(i).getTime().isAfter(end)) {
                break;
            }
            if (aktInterval == interval) {
                // Ende des Intervalls erreicht => Übernahme des Wertes und Intervallsuche von neuem starten
                Instant time = data.get(i).getTime().minusMillis(interval);
                ret.add(new PeriodicTimeseriesValue(moveInstantToStartOfDay(time), lastMax));
                lastCommitedValue = lastMax;
                aktInterval = 0;
                lastMax = 0;
            } else {
                if (lastMax < parsedValue && lastCommitedValue != parsedValue) {
                    lastMax = parsedValue;
                }
            }
            aktInterval += 15 * 1000;
        }
        return ret;
    }

    /**
     * Aggregiert die Zeitreihe; Taktik ist, dass über die Werte iteriert wird und bei jedem vollständigen Intervall der Wert genommen wird.
     *
     * @param data
     * @param start
     * @param end
     * @param interval
     * @param id
     */
    public static List<PeriodicTimeseriesValue> aggregateSeriesDivVortag(List<PeriodicTimeseriesValue> data, Instant start, Instant end, int interval, String id) {
        System.out.println("aggregateSeriesDivVortag " + id);
        List<PeriodicTimeseriesValue> ret = new ArrayList<>();

        int aktInterval = 0;

        if (data.size() > 0) {
            ret.add(new PeriodicTimeseriesValue(data.get(0).getTime(), data.get(0).getValue()));
        }
        for (int i = 0; i < data.size(); i++) {
            // Sind wir am Ende angekommen?
            Instant parsedDate = data.get(i).getTime();
            if (parsedDate.isAfter(end) || parsedDate.equals(end)) {
                break;
            }

            if (aktInterval == interval) {
                // Ende des Intervalls erreicht => Übernahme des Wertes und Intervallsuche von neuem starten
                ret.add(new PeriodicTimeseriesValue(moveInstantToStartOfDay(data.get(i).getTime()), data.get(i).getValue()));
                aktInterval = 0;
            }
            aktInterval += interval;
        }
        return ret;
    }

    /**
     * Füllt die Zeitreihe auf und aggregiert diese auf den entsprechenden Zeitraum
     *
     * @param data  Not-Filled Data
     * @param start Start-TimeStamp
     * @param end   End-TimeStamp
     */
    public static List<PeriodicTimeseriesValue> fillSeries(List<PeriodicTimeseriesValue> data, Instant start, Instant end, int durationOfRaster) {
        List<PeriodicTimeseriesValue> ret = new LinkedList<>();


        Instant date = Instant.ofEpochMilli(start.toEpochMilli());
        for (int i = 0; i < data.size(); i++) {
            PeriodicTimeseriesValue actualPeriodicTimeseriesValue = data.get(i);
            // Der aktuelle Wert liegt nicht vor dem Startzeitpunkt
            if (!actualPeriodicTimeseriesValue.getTime().isBefore(start)) {
                if (i == data.size() - 1) {
                    while (Instant.ofEpochMilli(end.toEpochMilli()).isAfter(date) || Instant.ofEpochMilli(end.toEpochMilli()).equals(date)) {
                        ret.add(new PeriodicTimeseriesValue(date, actualPeriodicTimeseriesValue.getValue()));
                        date = date.plus(durationOfRaster, ChronoUnit.MILLIS);
                    }
                } else {
                    PeriodicTimeseriesValue nextPeriodicTimeseriesValue = data.get(i + 1);
                    while (nextPeriodicTimeseriesValue.getTime().isAfter(date)) {
                        ret.add(new PeriodicTimeseriesValue(date, actualPeriodicTimeseriesValue.getValue()));
                        date = date.plus(durationOfRaster, ChronoUnit.MILLIS);
                    }
                }
                // Der aktuelle Wert liegt vor dem Startzeitpunkt
            } else {
                if (i == data.size() - 1) {
                    while (Instant.ofEpochMilli(date.toEpochMilli()).isBefore(end) || Instant.ofEpochMilli(date.toEpochMilli()).equals(end)) {
                        ret.add(new PeriodicTimeseriesValue(date, actualPeriodicTimeseriesValue.getValue()));
                        date = date.plus(durationOfRaster, ChronoUnit.MILLIS);
                    }
                } else {
                    PeriodicTimeseriesValue nextPeriodicTimeseriesValue = data.get(i + 1);
                    while (Instant.ofEpochMilli(date.toEpochMilli()).isBefore(nextPeriodicTimeseriesValue.getTime())) {
                        ret.add(new PeriodicTimeseriesValue(date, actualPeriodicTimeseriesValue.getValue()));
                        date = date.plus(durationOfRaster, ChronoUnit.MILLIS);
                    }
                }

            }
        }
        return ret;
    }

}
