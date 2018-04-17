package DataLayer;

import java.sql.*;
public class DataBase {

    private String dbPath="jdbc:sqlite:src/DataLayer/db/";
    public void DataBase(){}

    public void createNewDatabase(String fileName) {

        String url = dbPath + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables()
    {
        String url=dbPath;

        // SQL statement for creating a new table
        String workers = "CREATE TABLE IF NOT EXISTS Workers (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	first_name VARCHAR(100) NOT NULL,\n"
                + "	last_name VARCHAR (100) NOT NULL,\n"
                + " bank_account INTEGER, NOT NULL\n "
                + " working_conditions TEXT, NOT NULL \n"
                + "	salary INTEGER, \n"
                + ");";

        String roles = "CREATE TABLE IF NOT EXISTS Roles (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	role VARCHAR(100) NOT NULL,\n"
                + ");";

        String shhift_constraints = "CREATE TABLE IF NOT EXISTS Constraints (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	day_of_week DAY PRIMARY KEY \n"
                + "	start_time TIME PRIMARY  KEY\n"
                + "	end_time TIME NOT NULL,\n"
                + ");";

        String shifts = "CREATE TABLE IF NOT EXISTS Shifts (\n"
                + "	shift_date DATE PRIMARY  KEY \n"
                + "	shift_of_day INTEGER PRIMARY  KEY,\n"
                + "	shift_worker INTEGER PRIMARY KEY,\n"
                + ");";



        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(workers);
            stmt.execute(roles);
            stmt.execute(availability);
            stmt.execute(shifts);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        String url = dbPath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String update(int id, String name, String last_name, int salary , java.sql.Date date) {
        String sql = "UPDATE Workers SET first_name = ? , "
                + "last_name = ? , salary = ? , leave_date = ?"
                + "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setString(2, last_name);
            pstmt.setInt(3, salary);
            pstmt.setDate(4, date);
            pstmt.setInt(5, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }

        return "Employee was successfully updated";
    }

    public void get(int id) {
        String sql = "SELECT * from Workers "
                + "WHERE id = ?";

        try (Connection conn = connect();
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
                        rs.getString("first_name") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("salary") + "\t" +
                        rs.getDate("leave_date"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String insert(int id, String name, String last_name, int salary, java.sql.Date date) {
        // set the corresponding param

        String sql = "INSERT INTO Workers (id, first_name , last_name , salary , leave_date) "
                + "VALUES ( ? ,? , ? ,?, ?)";

        try (Connection conn = connect();

             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, last_name);
            pstmt.setInt(4, salary);
            pstmt.setDate(5,date);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "The employee was successfully inserted";
    }

}

