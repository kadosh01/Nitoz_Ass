package LogicLayer;

import java.sql.Time;
import java.util.Objects;

public class Constraints {
    int id;
    String day;
    java.sql.Time start_hour;
    java.sql.Time end_hour;

    public Constraints(int id, String day, Time start_hour, Time end_hour) {

        this.id = id;
        this.day = day;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constraints that = (Constraints) o;
        return id == that.id &&
                Objects.equals(day, that.day) &&
                Objects.equals(start_hour, that.start_hour) &&
                Objects.equals(end_hour, that.end_hour);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, day, start_hour, end_hour);
    }

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
}
