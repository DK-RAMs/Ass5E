
import java.sql.*;
import java.util.Scanner;

public class experSQL{
    public static void main(String[] args){
        try{
            Scanner inp = new Scanner(System.in);
            System.out.println("Please enter username");
            String username = inp.nextLine();
            System.out.println("Please enter password");
            String password = inp.nextLine();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assignment4_Database", username, password); //Database being used with relevant user credentials. Not sure how we're gonna communicate this to marker
            Statement stmt = con.createStatement();
            int querynum = -1;
            int querystuid = -1;
            while(querynum != 4 || querystuid != 2){
                System.out.println("Please select the query that you'd like to run:\n1.) Check course details of a student\n2.) \n3.) Check number of credits student has"); //Asks user to input which query they'd like to run
                querynum = inp.nextInt();
                if(querynum == 1){
                    System.out.println("Enter StuID that will be queried (or enter 2 to close program)");
                    querystuid = inp.nextInt();
                    ResultSet Rs = stmt.executeQuery("select * from uniData where StuID = " + querystuid);
                
                    while(Rs.next()){
                        System.out.println(Rs.getString("Course") + " - " + Rs.getString("Percent") + " (" + Rs.getString("Symbol") + ")");
                    }
                }
                else if(querynum == 2){
                    //missing queries will be added to if ladder and add Rs + getString to every query
                }
                else if(querynum == 3){
                    System.out.println("Enter StuID that will be queried (or enter 2 to close program)");
                    querystuid = inp.nextInt();
                    ResultSet Rs = stmt.executeQuery("select sum(Credits) from uniData where Symbol != "AB" and Symbol != "DPR" and Symbol != "F" and StuID = " querystuid);
                }
                System.out.println("Would you like to query again?\n1.) Yes\n2.) No"); //At end of while loop so that user can decide whether or not to query database again
                querystuid = inp.nextInt();
                
            }
        }
        catch(Exception exc){
            exc.printStackTrace();
        }
    }
}
