package PresentationLayer;
import DataLayer.DataBase;
import LogicLayer.systemLogic;

import java.util.Scanner;
import java.lang.String;
import java.sql.*;

public class main {

    public static void main(String[] args) {
        DataBase db = new DataBase();
        systemLogic sl = new systemLogic();
        db.createNewDatabase("WORKERS_MODULE");
        db.createTables(); //if not exists


        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        inputString.trim();
        printMainMenu();


        Main_Menu:
        while (inputString != "0") {
            printMainMenu();
            switch (inputString) {
                case "1": //Insert New Employee
                {
                    System.out.println("Insert Employee ID, then press Enter");
                    Integer id = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Insert First Name, then press Enter");
                    String first_name = scanner.nextLine().trim();
                    System.out.println("Insert Last Name, then press Enter");
                    String last_name = scanner.nextLine().trim();
                    System.out.println("Insert Bank Account, then press Enter");
                    Integer BankAccount = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println("Insert Working Conditions, then press Enter");
                    String working_Conditions = scanner.nextLine();
                    System.out.println("Insert Employee Role, then press Enter");
                    String role = scanner.nextLine().trim();



                    break;
                }
                case "2": { //update Worker
                    Update_Worker:
                    printUpdateMenu();
                    String updateNum = scanner.nextLine().trim();
                    switch (updateNum){
                        case "1" : //name
                        {   System.out.println("Insert New First Name, then press Enter");
                            String first_name = scanner.nextLine().trim();
                            System.out.println("Insert New Last Name, then press Enter");
                            String last_name = scanner.nextLine().trim();
                            break;
                        }
                        case "2" : //bank account
                        {
                            System.out.println("Insert New Bank Account, then press Enter");
                            Integer BankAccount = Integer.parseInt(scanner.nextLine().trim());
                            break;
                        }
                        case "3" : //role
                        {
                            System.out.println("Insert New Employee Role, then press Enter");
                            String role = scanner.nextLine().trim();
                            break;
                        }
                        case "4" : //working conditions
                        {
                            System.out.println("Insert Employee Role, then press Enter");
                            String role = scanner.nextLine().trim();
                            break;
                        }
                        case "5" : //add constraint
                        {
                            System.out.println("Insert Employee ID, then press Enter");
                            Integer id = Integer.parseInt(scanner.nextLine().trim());
                            System.out.println("Enter Day of Constraint, then press Enter");
                            String day = scanner.nextLine().trim();
                            System.out.println("Enter Starting Hour of Constraint, then press Enter");
                            String start_hour = scanner.nextLine().trim();
                            System.out.println("Enter Finish Hour of Constraint, then press Enter");
                            String end_hour = scanner.nextLine().trim();
                            break;
                        }

                        case "6" : //update constraint
                        {
                            System.out.println("Insert Employee ID, then press Enter");
                            Integer id = Integer.parseInt(scanner.nextLine().trim());
                            System.out.println("Enter Day of Constraint, then press Enter");
                            String day = scanner.nextLine().trim();
                            System.out.println("Enter Starting Hour of Constraint, then press Enter");
                            String start_hour = scanner.nextLine().trim();
                            System.out.println("Enter Finish Hour of Constraint, then press Enter");
                            String end_hour = scanner.nextLine().trim();
                            break;
                        }

                        case "7" : //delete constraint
                        {
                            System.out.println("Insert Employee ID, then press Enter");
                            Integer id = Integer.parseInt(scanner.nextLine().trim());
                            System.out.println("Enter Day of Constraint, then press Enter");
                            String day = scanner.nextLine().trim();
                            break;
                        }
                        case "0" : //back
                        {
                            break;
                        }
                        default :
                            System.out.println("Invalid Number, Please Try Again");
                            goTo
                        {

                            break;
                        }
                    }


                    break;
                }
                case "3": { ////// Get Worker Info
                    try {
                        //update date goes like this 2017-06-15
                        int id = Integer.parseInt(args[1]);
                        db.get(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case "4": { ////// Get Worker Hour Constraints
                    try {
                        //update date goes like this 2017-06-15
                        int id = Integer.parseInt(args[1]);
                        db.get(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case "5": { ////// Get Shift Schedule
                    try {
                        //update date goes like this 2017-06-15
                        int id = Integer.parseInt(args[1]);
                        db.get(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case "6": { ////// Update Shift Requirements
                    try {
                        //update date goes like this 2017-06-15
                        int id = Integer.parseInt(args[1]);
                        db.get(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case "7": { ////// Get Shift History
                    try {
                        //update date goes like this 2017-06-15
                        int id = Integer.parseInt(args[1]);
                        db.get(id);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
                default: {
                    System.out.println("Command Invalid: " + args[0]);
                    break;
                }

            }

        }
    }

    private static void printMainMenu() {
        System.out.println("" +
                "Menu: \n " +
                "1 - Insert New Worker \n" +
                "2 - Update Worker \n" + //constraints/data/role
                "3 - Get Worker Info \n" +
                "4 - Get Worker Hour Constraints \n" +
                "5 - Get Shift Schedule \n" +
                "6 - Update Shift Requirements \n " +
                "7 - Get Shift History \n" +
                "0 - Exit System"
        );
    }

    private static void printUpdateMenu() {
        System.out.println("" +
                "Menu: \n " +
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




}
