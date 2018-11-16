package ch.zhaw.ads.prakt9;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Competitor implements Comparable<Competitor> {
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor(int startNr, String name, int jg, String country, String time) throws ParseException {
        this.name = name;
        this.country = country;
        this.time = parseTime(time);
        this.jg = jg;
        this.startNr = startNr;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append(rank);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(Integer.toString(jg));
        sb.append(" ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }

    @Override
    public int compareTo(Competitor competitorCompare) {
        if (this.equals(competitorCompare)) {
            return 0;
        }
        if (this.jg < competitorCompare.jg) {
            return -1;
        } else if (this.jg > competitorCompare.jg) {
            return 1;
        }

        return this.name.compareTo(competitorCompare.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competitor that = (Competitor) o;
        return jg == that.jg &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jg);
    }
}