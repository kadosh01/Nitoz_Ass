package DataLayer;

import java.sql.*;
public class DataBase {

    private String dbPath="jdbc:sqlite:src/DataLayer/db/";
    private static Connection conn;
    private String filename;
    public DataBase(String name){
        filename=name;
        try {
            Connection conn = DriverManager.getConnection(dbPath+filename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewDatabase() {

        try  (Connection conn=connect()){
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables()
    {
        String url=dbPath + filename;

        // SQL statement for creating a new table
        String workers = "CREATE TABLE IF NOT EXISTS Workers (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	first_name VARCHAR(100) NOT NULL,\n"
                + "	last_name VARCHAR (100) NOT NULL,\n"
                + " bank_account INTEGER NOT NULL,\n "
                + " working_conditions TEXT,\n"
                + "	salary INTEGER, \n"
                + " start_date DATE \n"
                + ");";

        String roles = "CREATE TABLE IF NOT EXISTS Roles (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	role VARCHAR(100) NOT NULL\n"
                + ");";

        String constraints = "CREATE TABLE IF NOT EXISTS Constraints (\n"
                + "	id integer ,\n"
                + "	day_of_week DAY , \n"
                + "	start_time TIME ,\n"
                + "	end_time TIME NOT NULL,\n"
                + "PRIMARY KEY (id, day_of_week)"
                + ");";

        String shifts = "CREATE TABLE IF NOT EXISTS Shifts (\n"
                + "	shift_date DATE , \n"
                + "	shift_of_day INTEGER ,\n"
                + "	shift_worker INTEGER ,\n"
                + "PRIMARY KEY (shift_date , shift_of_day , shift_worker)"
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
        String url = dbPath + filename;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }



}

