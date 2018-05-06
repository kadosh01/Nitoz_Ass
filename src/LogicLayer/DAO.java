package LogicLayer;

import DataLayer.db.DataBase;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Vector;

public class DAO {


    private DataBase db ;



    public DAO(){
        db = new DataBase("WORKERS_MODULE");
        db.createNewDatabase();
        db.createTables(); //if not exists
    }

    public boolean checkIfIdExists(int id){
        String sql = "SELECT (count(*) > 0) as found FROM workers WHERE id = " + id;
        boolean exists;
        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                // Only expecting a single result
                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                System.out.println("error");
            return false;

        }
        return false;
    }

    public String deleteEmployee(int id) {
        // set the corresponding param

        String sql = "DELETE FROM Workers"
                + " WHERE id = ?";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                return "Error: Worker Id not in System";
            return e.getMessage();
        }
        return "The employee was successfully deleted";
    }

    public String insertNewEmployee(Employee emp) {
        // set the corresponding param

        String sql = "INSERT INTO Workers (id, first_name , last_name , bank_account , working_conditions , start_date, role) "
                + "VALUES ( ? ,? , ? ,?, ?, ?,?)";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, emp.get_Id());
            pstmt.setString(2, emp.get_firstName());
            pstmt.setString(3, emp.get_lastName());
            pstmt.setInt(4, emp.get_bankAccount());
            pstmt.setString(5, emp.get_conditions());
            pstmt.setDate(6,emp.get_startDate());
            pstmt.setString(7,emp.getRole());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                return "Error: Worker Id already in System";
            return e.getMessage();
        }
        return "The employee was successfully inserted";
    }


    public String updateEmployeeName(Employee emp) {
        String sql = "UPDATE Workers SET first_name = ? , "
                + "last_name = ? "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, emp.get_firstName());
            pstmt.setString(2, emp.get_lastName());
            pstmt.setInt(3, emp.get_Id());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String updateEmployeeRole(int id, String role) {
        String sql = "UPDATE Workers SET role = ?  "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, role);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String getEmployeeRole(int id) {
        String sql = "SELECT * FROM Workers "
                + "WHERE id = ? ";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the c¡orresponding param
            pstmt.setInt(1, id);
            // update
            ResultSet rs =pstmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                System.out.println("Employee doesn't exist");
                return null;
            }
            while(rs.next())
            {
                return (rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String updateWorkingConditions(Employee emp) {
        String sql = "UPDATE Workers SET working_conditions = ?  "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, emp.get_conditions());
            pstmt.setInt(2, emp.get_Id());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String updateEmployeeBank(Employee emp) {
        String sql = "UPDATE Workers SET bank_account = ? "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, emp.get_bankAccount());
            pstmt.setInt(2, emp.get_Id());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String updateEmployeeInfo(Employee emp) {
        String sql = "UPDATE Workers SET first_name = ? , "
                + "last_name = ? , start_date = ? , bank_account = ? , working_conditions = ? , role = ?"
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, emp.get_firstName());
            pstmt.setString(2, emp.get_lastName());
            pstmt.setDate(3, emp.get_startDate());
            pstmt.setInt(4, emp.get_bankAccount());
            pstmt.setString(5, emp.get_conditions());
            pstmt.setString(6, emp.getRole());
            pstmt.setInt(7, emp.get_Id());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                return "Worker Id already in System";
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public Employee getEmployeeInfo(int id) {
        Employee ret;
        String sql = "SELECT * from Workers "
                + "WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the c¡orresponding param
            pstmt.setInt(1, id);
            // update
            ResultSet rs =pstmt.executeQuery();

            if(!rs.isBeforeFirst()) {
                System.out.println("Employee doesn't exist");
                return null;
            }
            while(rs.next())
            {
                ret=new Employee(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getInt("bank_account"),rs.getDate("start_date"),rs.getString("working_conditions"),rs.getString("role"));

                return ret;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String deleteConstraints(int id, String day) {
        String sql = "DELETE from Constraints "
                + "WHERE id = ? AND day_of_week = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            pstmt.setString(2, day);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }

        return "Employee constraint was successfully deleted";
    }

    public String updateConstraints(Constraints c){
        String sql = "UPDATE Constraints SET start_time = ? , "
                + "end_time = ? WHERE day_of_week = ? AND id = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setTime(1, c.getStart_hour());
            pstmt.setTime(2, c.getEnd_hour());
            pstmt.setString(3, c.getDay());
            pstmt.setInt(4, c.getId());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                return "Constraint not exist";
            return e.getMessage();
        }

        return "Employee constraint was successfully updated";

    }

    public String insertNewConstraints(Constraints c) {
        // set the corresponding param

        String sql = "INSERT INTO Constraints (id, day_of_week , start_time , end_time) "
                + "VALUES ( ? ,? , ? ,?)";

        try (Connection conn = db.connect();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, c.getId());
            pstmt.setString(2, c.getDay());
            pstmt.setTime(3, c.getStart_hour());
            pstmt.setTime(4, c.getEnd_hour());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "The Employee Constraint was successfully inserted";
    }

    public Employee getEmployeeConstraints(int id) { /////////////////////////////
        Vector<Constraints> list=new Vector<>();
        String sql = "SELECT * from Constraints "
                + "WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the c¡orresponding param
            pstmt.setInt(1, id);
            // update
            ResultSet rs =pstmt.executeQuery();

            if(!rs.isBeforeFirst()){
                System.out.println("Employee doesn't exist");
                return null;
            }
            System.out.println();
            while(rs.next())
            {
                Constraints c=new Constraints(id,rs.getString("day_of_week"),rs.getTime("start_time"),rs.getTime("end_time"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Employee emp=new Employee(id,list);
        return emp;
    }

    public String insertIntoShift(int id, Shift s) {
        String sql = "INSERT INTO Shifts (shift_date, shift_of_day , shift_worker ) "
                + "VALUES ( ? ,? , ?)";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param

            pstmt.setDate(1,s.getDate());
            pstmt.setInt(2, s.getNum());
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                return "Error: Worker Id already in Shift ";
            return e.getMessage();
        }
        return "The employee was successfully inserted into Shift";
    }

    public String deleteFromShift(int id,java.sql.Date date) {
        String sql = "DELETE from Shifts "
                + "WHERE shift_worker = ? AND shift_date = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            pstmt.setDate(2, date);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }

        return "Employee was successfully deleted from shift";
    }

    public String insertShiftRequirement(String day, int shift_num, String req,int sum) {


        String sql = "INSERT INTO Requirements (shift_day, shift_of_day , shift_requirement_role , amount ) "
                + "VALUES ( ? ,? , ? , ?)";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param

            pstmt.setString(1,day);
            pstmt.setInt(2, shift_num);
            pstmt.setString(3, req);
            pstmt.setInt(4, sum);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Requirement Succesfully Added";
    }

    public Vector<Pair<String,Integer>> getShiftRequirement(String day, int shift_num) {
        Vector<Pair<String,Integer>> p=new Vector<>();
        String sql = "SELECT * FROM Requirements "
                + "WHERE shift_day = ? AND shift_of_day = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the c¡orresponding param
            pstmt.setString(1, day);
            pstmt.setInt(2, shift_num);
            // update
            ResultSet rs =pstmt.executeQuery();

            if(!rs.isBeforeFirst()){
                System.out.println("shift doesn't exist");
                return null;
            }
            while(rs.next())
            {
                p.add(new Pair<>(rs.getString("shift_requirement_role"),rs.getInt("amount")));
            }


        } catch (SQLException e) {
            return null;
        }
        return p;
    }

    public String deleteShiftrequirement(String day,int num,String role) {
        String sql = "DELETE from Requirements "
                + "WHERE shift_day = ? AND shift_of_day = ? AND shift_requirement_role = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, day);
            pstmt.setInt(2, num);
            pstmt.setString(3, role);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }

        return "Requirements was successfully deleted from shift";
    }

    public LinkedList<Pair<Integer,Shift>> getShiftScheduleHistory(java.sql.Date date, int shift_num) {
        LinkedList<Pair<Integer,Shift>> list=new LinkedList<>();
        String sql = "SELECT * from Shifts "
                + "WHERE shift_date = ? AND shift_of_day = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setDate(1, date);
            pstmt.setInt(2, shift_num);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Empty Shift");
                return list;
            }
            //System.out.println("Date : " + date.toString() + "\t Shift Number: " + shift_num);
            while (rs.next()) {
                list.add(new Pair<Integer, Shift>(rs.getInt("shift_worker"),new Shift(rs.getInt("shift_of_day"),rs.getDate("shift_date"))));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }



    public void getWeekSchedule() {
        String sql = "SELECT * from Shifts,Roles "
                + "Where shift_date BETWEEN ? And ? And shift_worker=Roles.id;";

        LocalDate today = LocalDate.now();

        // Go backward to get Monday
        LocalDate monday = today;
        while (monday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            monday = monday.minusDays(1);
        }

        // Go forward to get Sunday
        LocalDate sunday = today;
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.plusDays(1);
        }


        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(monday));
            pstmt.setDate(2, java.sql.Date.valueOf(sunday));
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Empty Shift");
                return;
            }
            java.sql.Date lastDate = null;
            System.out.println("-------------------------------------------------------------");
            System.out.println( "|     Date     |  Shift Number  |  Employee Id |    Role    |");
            System.out.println("-------------------------------------------------------------");
            while (rs.next()) {
                System.out.println("| "+rs.getDate("shift_date")  + "   |"+"\t\t" +
                        + rs.getInt("shift_of_day")  +  "\t\t" +
                        "| "+rs.getInt("shift_worker") + "   |\t"
                        + rs.getString("role")  +  "\t|" );

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
