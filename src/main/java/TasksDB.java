import sun.rmi.runtime.Log;

import java.sql.*;
import java.util.Vector;


/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class TasksDB {



    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //Configure the driver needed
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/tasks"; //Connection string – where's the database?
    static final String USER = "Jen";
    static final String PASSWORD = "kitty";

    //set up a logging object
    //private Log log = new Log();

    TasksDB(){

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe){
            //log.error("Can't instantiate driver class; check drives and classpath");
            cnfe.printStackTrace();
            System.exit(-1); //exit if driver doesn't work
        }
    }

    void createTables(){

        try (
                Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                //A statement object is used to run SQL statements
                Statement statement = conn.createStatement()){

            //Database should have already been created

            //Create table in the database if it doesn't already exist

            String createTableSQL[] = {
                    "CREATE TABLE IF NOT EXISTS Tasks(" +
                            "TaskID int NULL AUTO_INCREMENT, " +
                            "Description varchar(255), " +
                            "DueDate DATE, " +
                            "DateCompleted DATE, " +
                            "CompletedBy varchar(255), " +
                            "Attachment varchar(255), " +
                            "TypeOfTask varchar(255)" +
                            ")",
            };
            for (int x = 0; x < createTableSQL.length; x++){
                statement.execute(createTableSQL[x]);

            }

            //log.info("Created table");

            statement.close();
            conn.close();
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }

    void addTask(Task task){

        //try with resources connect to database

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)){

            //set up prepared statement
            String prepStatStr = "INSERT INTO Tasks VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPS = conn.prepareStatement(prepStatStr);
            insertPS.setInt(1, 0);
            insertPS.setString(2, task.getDescription()); //task.description? or method?
            insertPS.setDate(3, task.getDueDate());
            insertPS.setDate(4, task.getDateCompleted());
            insertPS.setString(5, task.getCompletedBy());
            insertPS.setString(6, task.getAttachment());
            insertPS.setString(7, task.getTypeOfTask());

            //actually put it in the database
            insertPS.execute();

            //log.info("Added Task for " + task);

            insertPS.close();
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    void updateTask(int currentID, Task task){

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)){

            String updateStr =
                    "UPDATE Tasks " +
                            "SET Description = ?, " +
                            "DueDate = ?, " +
                            "DateCompleted = ?, " +
                            "CompletedBy = ?, " +
                            "Attachment = ?, " +
                            "TypeOfTask = ?, " +

                            "WHERE TaskID = ?";
            PreparedStatement updatePS = conn.prepareStatement(updateStr);

            updatePS.setString(1, task.getDescription());
            updatePS.setDate(2, task.getDueDate());
            updatePS.setDate(3, task.getDateCompleted()); //get or set methods?? same question for above? in addTask()
            updatePS.setString(4, task.getCompletedBy());
            updatePS.setString(5, task.getAttachment());
            updatePS.setString(6, task.getTypeOfTask());
            updatePS.setInt(7, currentID);

            updatePS.executeUpdate();

            //log.info("Updated record " + currentID + " to " + task);

            updatePS.close();
            conn.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        Vector<Task> fetchAllTasks(){
            Vector<Task> allTasks = new Vector<>();

            try ( //try with resources

                  Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                  Statement statement = conn.createStatement()){

                String selectAllSQL = "SELECT * FROM Tasks";
                ResultSet rs = statement.executeQuery(selectAllSQL);

                while (rs.next()){
                    int id = rs.getInt("TaskID");
                    String description = rs.getString("Description");
                    Date dueDate = rs.getDate("DueDate");
                    Date dateCompleted = rs.getDate("DateCompleted");
                    String completedBy = rs.getString("CompletedBy");
                    String attachment = rs.getString("Attachment");
                    String typeOfTask = rs.getString("TypeOfTask");
                    Task task = new Task(description, id,dueDate, dateCompleted, completedBy, attachment, typeOfTask);
                    allTasks.add(task);
                }

                rs.close();
                statement.close();
                conn.close();

                //log.debug("Retrieved all Tasks");
            } catch (SQLException e){
                e.printStackTrace();
               // return null; //we have to return something OR DO WE?
            }
        }
    }}
