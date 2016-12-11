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
                            "TaskID int NOT NULL AUTO_INCREMENT, " +
                            "Description VARCHAR(255)," +
                            "DueDate VARCHAR(255)," +
                            "DateCompleted VARCHAR(255)," +
                            "CompletedBy VARCHAR(255)," +
                            "Attachment VARCHAR(255)," +
                            "TypeOfTask VARCHAR(255)," +
                            "PRIMARY KEY (TaskID)" +
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
            insertPS.setInt(1, task.getTaskID());
            insertPS.setString(2, task.getDescription()); //task.description? or method?
            insertPS.setString(3, task.getDueDate());
            insertPS.setString(4, task.getDateCompleted());
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

    void updateTask(int currentID, Task task) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {


            String updateStr =
                    "UPDATE Tasks " +
                            "SET Description = ?, " +
                            "DueDate = ?, " +
                            "DateCompleted = ?, " +
                            "CompletedBy = ?, " +
                            "Attachment = ?, " +
                            "TypeOfTask = ? " +

                            "WHERE TaskID = " + String.valueOf(task.getTaskID());

            PreparedStatement updatePS = conn.prepareStatement(updateStr);

            updatePS.setString(1, task.getDescription());
            updatePS.setString(2, task.getDueDate());
            updatePS.setString(3, task.getDateCompleted());
            updatePS.setString(4, task.getCompletedBy());
            updatePS.setString(5, task.getAttachment());
            updatePS.setString(6, task.getTypeOfTask());

            updatePS.executeUpdate();
          //  updatePS.execute(updateStr);
            updatePS.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Task> fetchAllTasks(){


            Vector<Task> allTasks = new Vector<>();

            try ( //try with resources

                  Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
                  Statement statement = conn.createStatement()) {

                String selectAllSQL = "SELECT * FROM Tasks";
                ResultSet rs = statement.executeQuery(selectAllSQL);

                while (rs.next()) {
                    int id = rs.getInt("TaskID");
                    String description = rs.getString("Description");
                    String dueDate = rs.getString("DueDate");
                    String dateCompleted = rs.getString("DateCompleted");
                    String completedBy = rs.getString("CompletedBy");
                    String attachment = rs.getString("Attachment");
                    String typeOfTask = rs.getString("TypeOfTask");
                    Task task = new Task(id, description, dueDate, dateCompleted, completedBy, attachment, typeOfTask);
                    allTasks.add(task);
                }

                rs.close();
                statement.close();
                conn.close();

                return allTasks;
                //log.debug("Retrieved all Tasks");
            } catch (SQLException e) {
                e.printStackTrace();
                return null; //we have to return something OR DO WE?
            }


        }

    public void deleteTask(int taskID) {

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {

            String deleteStr = "DELETE FROM Tasks WHERE TaskID = ?";
            PreparedStatement deletePs = conn.prepareStatement(deleteStr);

            deletePs.setInt(1, taskID);
            deletePs.executeUpdate();

            deletePs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int nextTaskID(){

        try ( //try with resources

              Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
              Statement statement = conn.createStatement()) {

            String selectAllSQL = "SELECT MAX(TaskID)FROM Tasks";
            ResultSet rs = statement.executeQuery(selectAllSQL);

            int id = 0;

            while (rs.next()) {

                if (rs.getInt("TaskID") > id){

                    id = rs.getInt("TaskID");
                }

            }

            rs.close();
            statement.close();
            conn.close();

            return id;

            //log.debug("Retrieved all Tasks");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; //we have to return something OR DO WE?
        }


    }
}

