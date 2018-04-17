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
                + " working_conditions TEXT,\n"
                + "	salary INTEGER, \n"
                + " start_date DATE \n"
                + ");";

        String roles = "CREATE TABLE IF NOT EXISTS Roles (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	role VARCHAR(100) NOT NULL,\n"
                + ");";

        String constraints = "CREATE TABLE IF NOT EXISTS Constraints (\n"
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
            stmt.execute(constraints);
            stmt.execute(shifts);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection connect() {
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



}

