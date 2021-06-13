package de.hanke.arnim.TSTool;

import java.time.Instant;

public class Interval {

    private Instant from;
    private Instant to;

    public Interval(Instant from, Instant to) {
        this.from = from;
        this.to = to;
    }

    public Instant getFrom() {
        return from;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getTo() {
        return to;
    }

    public void setTo(Instant to) {
        this.to = to;
    }

    public boolean contains(Instant instant) {
        return instant.isAfter(from) || instant.isBefore(to) || instant.equals(from);
    }

    /**
     * Example: 2007-12-03T10:15:30.00Z/2008-12-03T10:15:30.00Z
     */
    public static Interval parse(String interval) {

        String[] split = interval.split("/");
        if(split.length == 2) {
            Instant from = Instant.parse(split[0]);
            Instant to = Instant.parse(split[1]);

            if(from.isAfter(to)) {
                throw new IllegalArgumentException("The start must be before the end!");
            }

            return new Interval(from, to);
        } else {
            throw new IllegalArgumentException("The given interval-string does not have the correct format " + interval);
        }

    }

    @Override
    public String toString() {
        return from.toString() + "/" + to.toString();
    }
}
