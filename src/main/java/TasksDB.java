import java.sql.*;
import java.util.Scanner;

/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class TasksDB {



    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/tasks"; //Connection string – where's the database?
    static final String USER = "Jen"; //TODO replace with your username
    static final String PASSWORD = "kitty"; //TODO replace with your password

    public static void main(String[] args) throws Exception { //TODO handle exceptions properly

        TaskViewerGUI taskViewerGUI = new TaskViewerGUI();

        Scanner scanner = new Scanner(System.in);

        Class.forName(JDBC_DRIVER); //Instantiate the driver class
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD); //Create a connection to DB

        Statement statement = connection.createStatement(); //A statement object is used to run SQL statements



        statement.execute("CREATE TABLE IF NOT EXISTS tasks (Task_description VARCHAR(255),Type_of_task VARCHAR(255), Attachments VARCHAR(255), Due_date DATE, Date_Completed DATE, Completed_By VARCHAR(255))"); //Run some SQL – create table

//        statement.execute("INSERT INTO cubes VALUES ('Cubestormer II robot', 5) "); //Add some test data
//        statement.execute("INSERT INTO cubes VALUES ('Fakhri Raihaan (using his feet)', 27) "); //And some more test data
//        statement.execute("INSERT INTO cubes VALUES ('Ruxin Liu (age 3)', 99) "); //Add some test data
//        statement.execute("INSERT INTO cubes VALUES ('Mats Valk (human record holder)', 6) "); //Add some test data

//        boolean cont = true; //Does the user want to continue, if yes, cont = true
//
//        System.out.println("Would you like to make a new entry to the Cube Database? (y or n)");
//        String addNew = scanner.nextLine();
//
//        if (addNew.equalsIgnoreCase("n")) {
//            cont = false;
//        } else if (addNew.equalsIgnoreCase("y")) {
//            enterSomething(cont, statement);
//        }


        ResultSet rs = statement.executeQuery("SELECT * FROM cubes"); //Fetch all data; data is returned in a ResultSet
        while (rs.next()) {    //Loop over ResultSet, and print data
            System.out.println("Who solved the rubiks cube: " + rs.getString(1));
            System.out.println("Time taken, in seconds: " + rs.getInt(2));
            System.out.println("*****");
        }
        statement.execute("DROP TABLE cubes"); //Delete the table (you don't usually do this in your applications :)
        rs.close(); //Close the result set, statement and connection, release resources
        statement.close();
        connection.close();
    }

}