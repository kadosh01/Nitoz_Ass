package PresentationLayer;

import java.sql.Time;

public class Constraints {
    int id;
    String day;
    java.sql.Time start_hour;
    java.sql.Time end_hour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Time getStart_hour() {
        return start_hour;
    }

    public void setStart_hour(Time start_hour) {
        this.start_hour = start_hour;
    }

    public Time getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(Time end_hour) {
        this.end_hour = end_hour;
    }

    public Constraints(int id, String day, Time start_hour, Time end_hour) {

        this.id = id;
        this.day = day;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
    }
}
