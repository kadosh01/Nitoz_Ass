package PresentationLayer;


import org.junit.After;
import org.junit.Before;


import static org.junit.Assert.*;


public class Tests {

    private DAO _dao;

    public Tests() {
    }

    @Before
    public void setUp() throws Exception {
        Role r=new Role("manager",11111111);
        DAO dao=new DAO();
        _dao=dao;
    }

    @After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void insertNewEmployee() {
        Employee e=new Employee(11111111,"David","Ben-Gurion",123456,java.sql.Date.valueOf("1990-01-18"),"i ask for Good selary!");
        _dao.insertNewEmployee(e);
        Employee test=_dao.getEmployeeInfo(e.get_Id());
        assertEquals(e,test);

    }

    @org.junit.Test
    public void insertEmployeeRole() {
        Role r=new Role("manager",11111111);
        _dao.insertEmployeeRole(r);
        Role test=_dao.getEmployeeRole(r.get_id());
        assertEquals(r,test);
    }

    @org.junit.Test
    public void updateEmployeeInfo() {
    }

    @org.junit.Test
    public void getEmployeeInfo() {
    }

    @org.junit.Test
    public void deleteConstraints() {
    }

    @org.junit.Test
    public void updateConstraints() {
    }

    @org.junit.Test
    public void insertNewConstraints() {
    }

    @org.junit.Test
    public void insertIntoShift() {
    }

    @org.junit.Test
    public void insertShiftRequirement() {
    }

    @org.junit.Test
    public void getShiftSchedule() {
    }
}
