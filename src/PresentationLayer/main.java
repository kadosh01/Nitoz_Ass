package PresentationLayer;
import DataLayer.DataBase;
import java.sql.*;

public class main {

    public static void main(String[] args)
    {
        DataBase db=new DataBase();

        db.createNewDatabase("ASS1");
        db.createTable(); //if not exists
        if(args.length <1) {
            System.out.println("No command has been entered");
            return;
        }
        switch (args[0])
        {
            case "insert":
            {
                try {
                    int id = Integer.parseInt(args[1]);
                    String fn=args[2];
                    String ln=args[3];
                    int salary = Integer.parseInt(args[4]);
                    if(args.length<6){
                        System.out.println(db.insert(id, fn, ln, salary,null));

                    }else System.out.println(db.insert(id, fn, ln, salary, java.sql.Date.valueOf(args[5])));
                }
                catch(Exception e){System.out.println(e.getMessage());}

                break;
            }
            case "update":
            {
                try {
                    //update date goes like this 2017-06-15
                    int id = Integer.parseInt(args[1]);
                    String fn=args[2];
                    String ln=args[3];
                    int salary = Integer.parseInt(args[4]);
                    System.out.println(db.update(id, fn, ln, salary, java.sql.Date.valueOf(args[5])));
                }
                catch(Exception e){System.out.println(e.getMessage());}

                break;
            }
            case "info":
            {
                try {
                    //update date goes like this 2017-06-15
                    int id = Integer.parseInt(args[1]);
                    db.get(id);
                }
                catch(Exception e){System.out.println(e.getMessage());}

                break;
            }
            default:
            {
                System.out.println("Command Invalid: " + args[0]);
                break;
            }

        }

    }
}
