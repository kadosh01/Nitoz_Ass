package LogicLayer;

import DataLayer.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class systemLogic {
    private DataBase db;
    public void systemLogic(){
        db=new DataBase();
    }

    public String insertNewEmployee(int id, String name, String last_name,int bank, int salary , java.sql.Date date,String conditions) {
        // set the corresponding param

        String sql = "INSERT INTO Workers (id, first_name , last_name , bank_account , working_condotions , salary , start_date) "
                + "VALUES ( ? ,? , ? ,?, ?, ?, ?)";

        try (Connection conn = db.connect();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, last_name);
            pstmt.setInt(4, bank);
            pstmt.setString(5, conditions);
            pstmt.setInt(6, salary);
            pstmt.setDate(7,date);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "The employee was successfully inserted";
    }

    public String updateEmployeeInfo(int id, String name, String last_name,int bank, int salary , java.sql.Date date,String conditions) {
        String sql = "UPDATE Workers SET first_name = ? , "
                + "last_name = ? , salary = ? , start_date = ? , bank_account = ? , working_conditions = ?"
                + "WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setString(2, last_name);
            pstmt.setInt(3, salary);
            pstmt.setDate(4, date);
            pstmt.setInt(5, bank);
            pstmt.setString(6, conditions);
            pstmt.setInt(7, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
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
                System.out.println("employee doesn't exist");
                return;
            }
            while(rs.next())
            {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("first_name") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("bank_account") + "\t" +
                        rs.getString("working_conditions") + "\t" +
                        rs.getString("salary") + "\t" +
                        rs.getDate("start_date"));
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

    public String updateConstrainsts(int id, String day, String newDay ,String start_hour, String end_hour){
        // set the corresponding param
        deleteConstraints(id,day);
        insertNewConstraints(id,newDay,start_hour,end_hour);

        return "Employee constraint was successfully updated";

    }

    public String insertNewConstraints(int id, String day, String start_hour, String end_hour) {
        // set the corresponding param

        String sql = "INSERT INTO Constraints (id, day_of_week , start_time , end_time) "
                + "VALUES ( ? ,? , ? ,?)";

        try (Connection conn = db.connect();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            pstmt.setString(2, day);
            pstmt.setString(3, start_hour);
            pstmt.setString(4, end_hour);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "The employee was successfully inserted";
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
                System.out.println("employee doesn't exist");
                return;
            }
            while(rs.next())
            {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("day_of_week") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("start_time") + "\t" +
                        rs.getString("end_time"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

