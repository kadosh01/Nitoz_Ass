package LogicLayer;

import java.util.Objects;
import java.util.Vector;

public class Employee {
    private int _Id;
    private String _firstName;
    private String _lastName;
    private int _bankAccount;
    private java.sql.Date _startDate;
    private String _conditions;
    private String _role;

    public Vector<Constraints> get_constraints() {
        return _constraints;
    }

    public void set_constraints(Vector<Constraints> _constraints) {
        this._constraints = _constraints;
    }

    private Vector<Constraints> _constraints;

    public String getRole() {
        return _role;
    }

    public void setRole(String role) {
        this._role = role;
    }

    public Employee(int _Id, String _firstName, String _lastName, int _bankAccount, java.sql.Date _startDate, String _conditions, String role) {
        this._Id = _Id;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._bankAccount = _bankAccount;
        this._startDate = _startDate;
        this._conditions = _conditions;
        this._role = role;
    }

    public Employee(int _Id,Vector<Constraints> constraints) {
        this._Id = _Id;
        _constraints=constraints;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return _Id == employee._Id &&
                _bankAccount == employee._bankAccount &&
                Objects.equals(_firstName, employee._firstName) &&
                Objects.equals(_lastName, employee._lastName) &&
                Objects.equals(_startDate, employee._startDate) &&
                Objects.equals(_conditions, employee._conditions) &&
                Objects.equals(_role, employee._role) &&
                Objects.equals(_constraints, employee._constraints);
    }

    @Override
    public int hashCode() {

        return Objects.hash(_Id, _firstName, _lastName, _bankAccount, _startDate, _conditions, _role, _constraints);
    }

    public boolean checkAvailability(Shift shift){

        for (Constraints cons: _constraints) {
            if (cons.day == shift.getDay()){
                if(shift.getNum()==1 && cons.start_hour.compareTo(java.sql.Time.valueOf("14:00:00"))<=0 ){return true;}
                else if(shift.getNum()==2 && cons.start_hour.compareTo(java.sql.Time.valueOf("14:00:00"))>=0){return true;}
            }

        }
        return false;
    }
}
