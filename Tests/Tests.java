package Tests;


import LogicLayer.Constraints;
import LogicLayer.DAO;
import LogicLayer.Employee;
import LogicLayer.Shift;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;


import java.util.LinkedList;
import java.util.Vector;

import static org.junit.Assert.*;


public class Tests {

    private DAO _dao;

    public Tests() {
    }

    @Before
    public void setUp() throws Exception {
        DAO dao=new DAO();
        _dao=dao;
        Employee e=new Employee(11111111,"David","Ben-Gurion",123456,java.sql.Date.valueOf("1990-01-18"),"i ask for Good selary!","manager");
        dao.insertNewEmployee(e);
    }

    @After
    public void tearDown() throws Exception {
        _dao.deleteEmployee(11111111);
        _dao.deleteConstraints(11111111,"saterday");
        _dao.deleteFromShift(11111111,java.sql.Date.valueOf("2018-04-15"));
        _dao.deleteShiftrequirement("friday",2,"cleaner");

    }

    @org.junit.Test
    public void insertNewEmployee() {
        Employee e=new Employee(11111111,"David","Ben-Gurion",123456,java.sql.Date.valueOf("1990-01-18"),"i ask for Good selary!","manager");
        _dao.insertNewEmployee(e);
        Employee test=_dao.getEmployeeInfo(e.get_Id());
        assertEquals(e,test);

    }

    @org.junit.Test
    public void insertEmployeeRole() {

        _dao.updateEmployeeRole(11111111,"cashier");
        String test=_dao.getEmployeeRole(11111111);
        assertEquals("cashier",test);
    }

    @org.junit.Test
    public void updateEmployeeInfo() {
        Employee e=new Employee(11111111,"David","Gurion",123,java.sql.Date.valueOf("1990-01-18"),"i ask for fine selary!","cleaner");
        _dao.updateEmployeeInfo(e);
        Employee a=_dao.getEmployeeInfo(11111111);
        assertEquals(e, a);
    }

    @org.junit.Test
    public void getEmployeeInfo() {
        Employee e=new Employee(11111111,"David","Ben-Gurion",123456,java.sql.Date.valueOf("1990-01-18"),"i ask for Good selary!","manager");
        _dao.insertNewEmployee(e);
        Employee test=_dao.getEmployeeInfo(e.get_Id());
        assertEquals(e,test);
    }

    @org.junit.Test
    public void deleteConstraints() {
        Constraints c=new Constraints(11111111,"saterday",java.sql.Time.valueOf("13:30:00"),java.sql.Time.valueOf("22:30:00"));
        _dao.insertNewConstraints(c);
        _dao.deleteConstraints(11111111,"saterday");
        Vector<Constraints> l = _dao.getEmployeeConstraints(11111111).get_constraints();
        if(l!=null && !l.isEmpty()) {
            for (Constraints e : l) {
                if (e.equals(c)) {
                    assertTrue(false);
                }
            }
        }
        assertTrue(true);
    }

    @org.junit.Test
    public void updateConstraints() {
        Constraints c=new Constraints(11111111,"saterday",java.sql.Time.valueOf("13:30:00"),java.sql.Time.valueOf("22:30:00"));
        _dao.insertNewConstraints(c);
        Constraints c1=new Constraints(11111111,"saterday",java.sql.Time.valueOf("10:30:00"),java.sql.Time.valueOf("22:30:00"));
        _dao.updateConstraints(c1);
        Vector<Constraints> l = _dao.getEmployeeConstraints(11111111).get_constraints();
        if(l!=null && !l.isEmpty()) {
            for (Constraints e : l) {
                if (e.equals(c1)) {
                    assertTrue(true);
                    return;
                }
            }
        }
        assertTrue(false);
    }


    @org.junit.Test
    public void insertNewConstraints() {
        Constraints c=new Constraints(11111111,"saterday",java.sql.Time.valueOf("13:30:00"),java.sql.Time.valueOf("10:30:00"));
        _dao.insertNewConstraints(c);
        Vector<Constraints> l = _dao.getEmployeeConstraints(11111111).get_constraints();
        if(l!=null && !l.isEmpty()) {
            for (Constraints e : l) {
                if (e.equals(c)) {
                    assertTrue(true);
                    return;
                }
            }
        }
        assertTrue(false);
    }

    @org.junit.Test
    public void insertIntoShift() {
        Employee e=new Employee(11111111,"David","Ben-Gurion",123456,java.sql.Date.valueOf("1990-01-18"),"i ask for Good selary!","manager");
        _dao.insertNewEmployee(e);
        Shift s=new Shift(1,java.sql.Date.valueOf("2018-04-15"));
        _dao.insertIntoShift(e.get_Id(),s);
        LinkedList<Pair<Integer,Shift>> list =_dao.getShiftScheduleHistory(s.getDate(),s.getNum());
        for (Pair<Integer,Shift> p:list) {
            if(p.getKey().equals(11111111) && p.getValue().getNum()==s.getNum() && s.getDate().toString().equals(s.getDate().toString())) {
                assertTrue(true);
                return;
            }
        }
        assertTrue(false);

    }

    @org.junit.Test
    public void insertShiftRequirement() {
        _dao.insertShiftRequirement("friday",2,"cleaner",2);
       Vector<Pair<String,Integer>> l= _dao.getShiftRequirement("friday",2);
        for (Pair<String,Integer> p:l) {
       assertEquals(p.getKey(),"cleaner");
       assertEquals(p.getValue().intValue(),2);
        }
    }

    @org.junit.Test
    public void getShiftRequirement() {
        _dao.insertShiftRequirement("friday",2,"cleaner",2);
        Vector<Pair<String,Integer>> l= _dao.getShiftRequirement("friday",2);
        for (Pair<String,Integer> p:l) {
            assertEquals(p.getKey(),"cleaner");
            assertEquals(p.getValue().intValue(),2);
        }
    }
}
