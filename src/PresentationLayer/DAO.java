package PresentationLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;

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


    public String insertNewEmployee(Employee emp) {
        // set the corresponding param

        String sql = "INSERT INTO Workers (id, first_name , last_name , bank_account , working_conditions , start_date) "
                + "VALUES ( ? ,? , ? ,?, ?, ?)";

        try (Connection conn = db.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, emp.get_Id());
            pstmt.setString(2, emp.get_firstName());
            pstmt.setString(3, emp.get_lastName());
            pstmt.setInt(4, emp.get_bankAccount());
            pstmt.setString(5, emp.get_conditions());
            pstmt.setDate(6,emp.get_startDate());
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

    public String updateEmployeeRole(Role role) {
        String sql = "UPDATE Roles SET role = ?  "
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, role.get_role());
            pstmt.setInt(2, role.get_id());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
    }

    public String insertEmployeeRole(Role role) {
        String sql = "Insert INTO Roles (id,role) "
                + "VALUES (? , ?) ";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, role.get_id());
            pstmt.setString(2, role.get_role());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Employee was successfully updated";
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
                + "last_name = ? , start_date = ? , bank_account = ? , working_conditions = ?"
                + "WHERE id = ?";
        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, emp.get_firstName());
            pstmt.setString(2, emp.get_lastName());
            pstmt.setDate(3, emp.get_startDate());
            pstmt.setInt(4, emp.get_bankAccount());
            pstmt.setString(5, emp.get_conditions());
            pstmt.setInt(6, emp.get_Id());
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

            // set the corresponding param
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
