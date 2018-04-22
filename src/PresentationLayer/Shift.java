package PresentationLayer;

import java.sql.Date;

public class Shift {
    private int num;
    private java.sql.Date date;


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
