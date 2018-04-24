package LogicLayer;

import javafx.util.Pair;

import java.sql.Date;
import java.util.Vector;

public class Shift {
    private int num;
    private java.sql.Date date;
    private String day;
    private Vector<Pair<String ,Integer>> Requirements;
    private Vector<Employee> employees;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Vector<Pair<String, Integer>> getRequirements() {
        return Requirements;
    }

    public void setRequirements(Vector<Pair<String, Integer>> requirements) {
        Requirements = requirements;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shift(int num, Date date) {
        this.num = num;
        this.date = date;

    }
}
