package LogicLayer;

import DataLayer.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class systemLogic {


    private DataBase db ;


    public systemLogic(){
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


    public String insertNewEmployee(int id, String name, String last_name,int bank ,java.sql.Date start_date,String conditions) {
        // set the corresponding param

        String sql = "INSERT INTO Workers (id, first_name , last_name , bank_account , working_conditions , start_date) "
                + "VALUES ( ? ,? , ? ,?, ?, ?)";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, last_name);
            pstmt.setInt(4, bank);
            pstmt.setString(5, conditions);
            pstmt.setDate(6,start_date);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                return "Error: Worker Id already in System";
            return e.getMessage();
        }
        return "The employee was successfully inserted";
    }

    public String updateEmployeeName(int id, String first_name, String last_name) {
        String sql = "UPDATE Workers SET first_name = ? , "
                + "last_name = ? "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String updateEmployeeRole(int id, String Role) {
        String sql = "UPDATE Roles SET role = ?  "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, Role);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String insertEmployeeRole(int id, String Role) {
        String sql = "Insert Roles SET role = ?  "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, Role);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String updateWorkingConditions(int id, String wk) {
        String sql = "UPDATE Workers SET working_conditions = ?  "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, wk);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String updateEmployeeBank(int id, int bank) {
        String sql = "UPDATE Workers SET bank_account = ? "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, bank);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String updateEmployeeInfo(int id, String name, String last_name,int bank, java.sql.Date date,String conditions) {
        String sql = "UPDATE Workers SET first_name = ? , "
                + "last_name = ? , start_date = ? , bank_account = ? , working_conditions = ?"
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setString(2, last_name);
            pstmt.setDate(3, date);
            pstmt.setInt(4, bank);
            pstmt.setString(5, conditions);
            pstmt.setInt(6, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains(("SQLITE_CONSTRAINT_PRIMARYKEY")))
                return "Worker Id already in System";
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public void getEmployeeInfo(int id) {
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
                return;
            }
            while(rs.next())
            {
                System.out.println("-----Employee Information-----\n" +
                        "ID: " + rs.getInt("id") +  "\n" +
                        "First Name: " + rs.getString("first_name") + "\n" +
                        "Last Name: " +rs.getString("last_name") + "\n" +
                        "Bank Acccount No.: " + rs.getString("bank_account") + "\n" +
                        "Working Conditions : " + rs.getString("working_conditions") + "\n" +
                        "Starting Date: " + rs.getDate("start_date") + "\n" +
                        "------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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

    public String updateConstraints(int id, String day,java.sql.Time start_hour, java.sql.Time end_hour){
        // set the corresponding param
        deleteConstraints(id,day);
        insertNewConstraints(id,day,start_hour,end_hour);

        return "Employee constraint was successfully updated";

    }

    public String insertNewConstraints(int id, String day, java.sql.Time start_hour, java.sql.Time end_hour) {
        // set the corresponding param

        String sql = "INSERT INTO Constraints (id, day_of_week , start_time , end_time) "
                + "VALUES ( ? ,? , ? ,?)";

        try (Connection conn = db.connect();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            pstmt.setString(2, day);
            pstmt.setTime(3, start_hour);
            pstmt.setTime(4, end_hour);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "The Employee Constraint was successfully inserted";
    }

    public void getEmployeeConstraints(int id) {
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
                return;
            }
            System.out.println();
            while(rs.next())
            {
                System.out.println(
                         "Day: " + rs.getString("day_of_week") + "\t" +
                        "Start Time: " + rs.getTime("start_time") + "\t" +
                        "End Time " + rs.getTime("end_time"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String insertIntoShift(int id, int shift_num, java.sql.Date date) {
        String sql = "INSERT INTO Shifts (shift_date, shift_of_day , shift_worker ) "
                + "VALUES ( ? ,? , ?)";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param

            pstmt.setDate(1,date);
            pstmt.setInt(2, shift_num);
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

    public String insertShiftRequirement(java.sql.Date date, int shift_num, String req) {


        String sql = "INSERT INTO Requirements (shift_date, shift_of_day , shift_requirement ) "
                + "VALUES ( ? ,? , ?)";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param

            pstmt.setDate(1,date);
            pstmt.setInt(2, shift_num);
            pstmt.setString(3, req);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Requirement Succesfully Added";
    }

    public void getShiftSchedule(java.sql.Date date, int shift_num) {
        String sql = "SELECT * from Shifts,Roles "
                + "WHERE shift_date = ? AND shift_of_day = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the c¡orresponding param
            pstmt.setDate(1, date);
            pstmt.setInt(2, shift_num);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Empty Shift");
                return;
            }
            System.out.println("Date : " + date.toString() + "\t Shift Number: " + shift_num);
            while (rs.next()) {
                System.out.println(
                        "Employee ID: " + rs.getInt("shift_worker") + "\t" +
                                "Role: " + rs.getString("role") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void getWeekSchedule() {
        String sql = "SELECT * from Shifts "
                + "WHERE shift_date BETWEEN datetime('now', '-6 days') AND datetime('now', 'localtime')";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("Empty Shift");
                return;
            }
            java.sql.Date lastDate = null;
            while (rs.next()) {
                    System.out.println( "Date: " + rs.getDate("shift_date")  +  "\t" );
                        System.out.println(
                        "Shift: " + rs.getInt("shift_of_day")  +  "\t" +
                        "Employee ID: " + rs.getInt("shift_worker") + "\t" );

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}