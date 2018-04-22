package PresentationLayer;

import java.util.Date;

public class Employee {
    private int _Id;
    private String _firstName;
    private String _lastName;
    private int _bankAccount;
    private java.sql.Date _startDate;
    private String _conditions;

    public Employee(int _Id, String _firstName, String _lastName, int _bankAccount, java.sql.Date _startDate, String _conditions) {
        this._Id = _Id;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._bankAccount = _bankAccount;
        this._startDate = _startDate;
        this._conditions = _conditions;
    }


    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public int get_bankAccount() {
        return _bankAccount;
    }

    public void set_bankAccount(int _bankAccount) {
        this._bankAccount = _bankAccount;
    }

    public java.sql.Date get_startDate() {
        return _startDate;
    }

    public void set_startDate(java.sql.Date _startDate) {
        this._startDate = _startDate;
    }

    public String get_conditions() {
        return _conditions;
    }

    public void set_conditions(String _conditions) {
        this._conditions = _conditions;
    }
}
