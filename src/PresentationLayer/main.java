package PresentationLayer;
import DataLayer.DataBase;
import LogicLayer.systemLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;
import java.lang.String;
import java.sql.*;

public class main {



    public static void main(String[] args) throws ParseException {
        DataBase db = new DataBase("WORKERS_MODULE");
        systemLogic sl = new systemLogic();
        db.createNewDatabase();
        db.createTables(); //if not exists


       // printMainMenu();
        Scanner scanner = new Scanner(System.in);
        String inputString = "1";

        while (inputString != "0") {
            printMainMenu();
            inputString = scanner.nextLine();
            switch (inputString) {
                case "1": //Insert New Employee
                {   int BankAccount;
                    int id;
                    java.sql.Date date;
                    System.out.println("Insert Employee ID, then press Enter");
                    try {id = Integer.parseInt(scanner.nextLine().trim());}
                    catch(NumberFormatException e){System.out.println("Error ID must be Numbers Only"); break;}
                    System.out.println("Insert First Name, then press Enter");
                    String first_name = scanner.nextLine().trim();
                    System.out.println("Insert Last Name, then press Enter");
                    String last_name = scanner.nextLine().trim();
                    System.out.println("Insert Bank Account, then press Enter");
                    try {BankAccount = Integer.parseInt(scanner.nextLine().trim());}
                    catch(NumberFormatException e){System.out.println("Error Bank Account must be Numbers Only"); break;}
                    System.out.println("Insert Working Conditions, then press Enter");
                    String working_Conditions = scanner.nextLine();
                    System.out.println("Insert Working start date (yyyy-MM-dd), then press Enter");
                    try {date = java.sql.Date.valueOf(scanner.nextLine());}
                    catch(Exception e){System.out.println("Invalid Date or Format"); break; }
                    System.out.println("Insert Employee Role, then press Enter");
                    String role = scanner.nextLine().trim();

                    System.out.println(sl.insertNewEmployee(id,first_name,last_name,BankAccount,date,working_Conditions));
                    System.out.println(sl.insertEmployeeRole(id,role));

                    break;
                }
                case "2": { //update Worker1
                    int id;
                    System.out.println("Insert Employee ID, then press Enter");
                    try { id = Integer.parseInt(scanner.nextLine().trim());}
                    catch(NumberFormatException e){System.out.println("Error ID must be Numbers Only"); break;}
                    if(!sl.checkIfIdExists(id)){
                        System.out.println("Employee Id not in System");
                        break;
                        }
                    printUpdateMenu();
                    String updateNum = scanner.nextLine().trim();
                    switch (updateNum){
                        case "1" : //name
                        {   System.out.println("Insert New First Name, then press Enter");
                            String first_name = scanner.nextLine().trim();
                            System.out.println("Insert New Last Name, then press Enter");
                            String last_name = scanner.nextLine().trim();
                            System.out.println(sl.updateEmployeeName(id,first_name,last_name));
                            break;
                        }
                        case "2" : //bank account
                        {
                            int BankAccount;
                            System.out.println("Insert New Bank Account, then press Enter");
                            try {BankAccount = Integer.parseInt(scanner.nextLine().trim());}
                            catch(NumberFormatException e){System.out.println("Error Bank Account must be Numbers Only"); break;}
                            System.out.println(sl.updateEmployeeBank(id,BankAccount));
                            break;
                        }
                        case "3" : //role
                        {
                            System.out.println("Insert New Employee Role, then press Enter");
                            String role = scanner.nextLine().trim();
                            System.out.println(sl.updateEmployeeRole(id,role));
                            break;
                        }
                        case "4" : //working conditions
                        {
                            System.out.println("Insert New Employee Working Conditions, then press Enter");
                            String working_conditions = scanner.nextLine().trim();
                            System.out.println(sl.updateWorkingConditions(id,working_conditions));
                            break;
                        }
                        case "5" : //add constraint
                        {
                            System.out.println("Enter Day of Constraint, then press Enter");
                            String day = scanner.nextLine().trim();
                            if(!isLegalDay(day))
                            {
                                System.out.println("Invalid day");
                                break;
                            }
                            java.sql.Time start_hour;
                            java.sql.Time end_hour;
                            try {
                                System.out.println("Enter Starting Hour of Constraint (HH:MM), then press Enter");
                                start_hour = java.sql.Time.valueOf(scanner.nextLine().trim() + ":00");
                                System.out.println("Enter Finish Hour of Constraint (HH:MM) , then press Enter");
                                end_hour = java.sql.Time.valueOf(scanner.nextLine().trim() + ":00");
                            }
                            catch (IllegalArgumentException e) {
                                System.out.println("Error: Invalid Hour Format");
                                break;
                            }
                            System.out.println(sl.insertNewConstraints(id,day,start_hour,end_hour));
                            break;
                        }

                        case "6" : //update constraint
                        {
                            System.out.println("Enter Day of Constraint to Change, then press Enter");
                            String day = scanner.nextLine().trim();
                            if(!isLegalDay(day))
                            {
                                System.out.println("Invalid day");
                                break;
                            }
                            java.sql.Time start_hour;
                            java.sql.Time end_hour;
                            try {
                                System.out.println("Enter New Starting Hour of Constraint (HH:MM), then press Enter");
                                start_hour = java.sql.Time.valueOf(scanner.nextLine().trim() + ":00");
                                System.out.println("Enter New Finish Hour of Constraint (HH:MM) , then press Enter");
                                end_hour = java.sql.Time.valueOf(scanner.nextLine().trim() + ":00");
                            }
                            catch (IllegalArgumentException e) {
                                System.out.println("Error: Invalid Hour Format");
                                break;
                            }
                            System.out.println(sl.updateConstraints(id,day,start_hour,end_hour));
                            break;
                        }

                        case "7" : //delete constraint
                        {
                            System.out.println("Enter Day of Constraint, then press Enter");
                            String day = scanner.nextLine().trim();
                            if(!isLegalDay(day))
                            {
                                System.out.println("Invalid day");
                                break;
                            }
                            System.out.println(sl.deleteConstraints(id,day));
                            break;
                        }
                        case "0" : //back
                        {
                            break;
                        }
                        default :
                            System.out.println("Invalid Number, Please Try Again");

                        {

                            break;
                        }
                    }


                    break;
                }
                case "3": { ////// Get Worker Info
                    int id;
                    System.out.println("Insert Employee ID, then press Enter");
                    try { id = Integer.parseInt(scanner.nextLine().trim());}
                    catch(NumberFormatException e){System.out.println("Error ID must be Numbers Only"); break;}
                    if(!sl.checkIfIdExists(id)){
                        System.out.println("Employee Id not in System");
                        break;
                    }
                    sl.getEmployeeInfo(id);
                    break;
                }

                case "4": { ////// Get Worker Hour Constraints
                    int id;
                    System.out.println("Insert Employee ID, then press Enter");
                    try { id = Integer.parseInt(scanner.nextLine().trim());}
                    catch(NumberFormatException e){System.out.println("Error ID must be Numbers Only"); break;}
                    if(!sl.checkIfIdExists(id)){
                        System.out.println("Employee Id not in System");
                        break;
                    }
                    sl.getEmployeeConstraints(id);

                    break;
                }

                case "5": { ////// Get Shift Schedule
                    sl.getWeekSchedule();
                    break;
                }



                case "6": { ////// Update Shift Requirements
                    java.sql.Date date;
                    int shift_number;
                    System.out.println("Insert Shift date (yyyy-MM-dd), then press Enter");
                    try {date = java.sql.Date.valueOf(scanner.nextLine());}
                    catch(Exception e){System.out.println("Invalid Date or Format"); break; }
                    System.out.println("Insert Shift Number 1 or 2, then press Enter");
                    String shift = scanner.nextLine().trim();
                    if (!shift.equals("1") && !shift.equals("2")) {
                        System.out.println("Invalid Shift");
                        break;
                    }
                    shift_number = Integer.parseInt(shift);
                    System.out.println("Insert New Requirement, then press Enter");
                    String req = scanner.nextLine().trim();
                    System.out.println(sl.insertShiftRequirement(date,shift_number,req));
                    break;
                }

                case "7": { ////// Get Shift History
                    java.sql.Date date;
                    int shift_number;
                    System.out.println("Insert Shift date (yyyy-MM-dd), then press Enter");
                    try {date = java.sql.Date.valueOf(scanner.nextLine());}
                    catch(Exception e){System.out.println("Invalid Date or Format"); break; }
                    System.out.println("Insert Shift Number 1 or 2, then press Enter");
                    String shift = scanner.nextLine().trim();
                    if (!shift.equals("1") && !shift.equals("2")) {
                        System.out.println("Invalid Shift");
                        break;
                    }
                    shift_number = Integer.parseInt(shift);
                    sl.getShiftSchedule(date,shift_number);
                    break;
                }

                case "8": { //////Insert Employee to shift
                    int id;
                    java.sql.Date date;
                    int shift_number;
                    System.out.println("Insert Employee ID, then press Enter");
                    try { id = Integer.parseInt(scanner.nextLine().trim());}
                    catch(NumberFormatException e){System.out.println("Error ID must be Numbers Only"); break;}
                    if(!sl.checkIfIdExists(id)){
                        System.out.println("Employee Id not in System");
                        break;
                    }
                    System.out.println("Insert Shift date (yyyy-MM-dd), then press Enter");
                    try {date = java.sql.Date.valueOf(scanner.nextLine());}
                    catch(Exception e){System.out.println("Invalid Date or Format"); break; }
                    System.out.println("Insert Shift Number 1 or 2, then press Enter");
                    String shift = scanner.nextLine().trim();
                    if (!shift.equals("1") && !shift.equals("2")) {
                        System.out.println("Invalid Shift");
                        break;
                    }
                    shift_number = Integer.parseInt(shift);
                    System.out.println(sl.insertIntoShift(id,shift_number,date));

                }
                default: {
                    System.out.println("Command Invalid:" );
                    break;
                }

            }

        }
    }

    private static void printMainMenu() {
        System.out.println("" +
                "Menu: \n" +
                "1 - Insert New Worker \n" +
                "2 - Update Worker \n" + //constraints/data/role
                "3 - Get Worker Info \n" +
                "4 - Get Worker Hour Constraints \n" +
                "5 - Get Shift Schedule \n" +
                "6 - Update Shift Requirements \n" +
                "7 - Get Shift History \n" +
                "8 - Insert Worker To Shift \n" +
                "0 - Exit System"
        );
    }

    private static void printUpdateMenu() {
        System.out.println("" +
                "ID found, Choose Update And Press Enter: \n" +
                "1 - Update Name \n" +
                "2 - Update Bank Account \n" +
                "3 - Update Role \n" +
                "4 - Update Working Conditions \n" +
                "5 - Add Shift Constraint \n" +
                "6 - Change Shift Constraint \n" +
                "7 - Delete Shift Constraint \n" +
                "0 - Back"
        );
    }

    private static boolean isLegalDay(String day){
        switch (day.toLowerCase()) {
            case "monday":
            case "tuesday":
            case "wednesday":
            case "thursday":
            case "friday":
            case "saturday":
            case "sunday":
                return true;
            default: return false;
        }
    }



}
