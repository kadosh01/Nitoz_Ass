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

    public void createTable()
    {
        String url=dbPath;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Workers (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	first_name VARCHAR(100) NOT NULL,\n"
                + "	last_name VARCHAR (100) NOT NULL,\n"
                + "	salary INTEGER, \n"
                + "	leave_date DATE DEFAULT(NULL)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
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

