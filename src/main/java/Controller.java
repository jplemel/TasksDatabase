import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Vector;
//figure out import statements!!




/**
 * Created by Jennifer Plemel on 12/9/2016.
 */

//This is the main controller class for the application
public class Controller {

    private static TaskViewerGUI gui;
    private static NewTaskGUI guiNewTask;
    private static EditTaskGUI guiEditTask;
    public static TasksDB db;
    public static TaskTableModel ttm;
    public Vector<Task> allTasks;
    public int nextTaskID;
    public String path = null;
    public byte[] userimage = null;
    ///public static Task taskToEdit;



    public JTable controllerJTable;

    public static void main(String[] args){

        Controller controller = new Controller();
        controller.startApp();

        showGui();


    }

    public void startApp(){

        db = new TasksDB();

        //Generate new tables if they aren't there
        db.createTables();

        //Query database to create the collection

        allTasks = db.fetchAllTasks();

        //Query to determine highest taskID present in the database

        nextTaskID = db.nextTaskID();

        //gui

        gui = new TaskViewerGUI(this);

        guiNewTask = new NewTaskGUI(this);

      //  guiEditTask = new EditTaskGUI(this);

        //send the collections to the model/GUI

        gui.setTaskListData(allTasks);

        ttm = new TaskTableModel(allTasks);

    }
    //trying to show gui
    public static void showGui(){
        gui.setVisible(true);
        guiNewTask.setVisible(false);
//        guiEditTask.setVisible(false);
    }

    //trying to show guiNewTask
    public static void showGuiNewTask(){
        guiNewTask.setSize(600,400);
        guiNewTask.setVisible(true);
        gui.setVisible(false);
 //       guiEditTask.setVisible(false);
    }
    //show guiEditTask
    public void showGuiEditTask(Task task){


        guiEditTask = new EditTaskGUI(task);

       // guiEditTask.taskToEdit = task;
        guiEditTask.setSize(500,300);
        guiEditTask.setVisible(true);
      //  guiNewTask.setVisible(false);
        gui.setVisible(false);
    }


    public Vector<Task> getAllTasks(){
        return db.fetchAllTasks();
    }

    public void addTaskToDatabase(Task task) throws FileNotFoundException {
        //this is a thing
        byte[] attachment = task.getAttachment();
        if (attachment != null){

            db.addTask(task,path);
        }
        else {

           db.addTask(task);
        }

    }

    void updateTask(int currentID, Task task){
        db.updateTask(currentID,task);
    }

    void deleteTask(int taskID){
        db.deleteTask(taskID);
    }

    public void updateGUIJTableAfterDelete(){

        //Refresh allTasks object (updateData calls fireTableDataChanged (which updates JTable)

        ttm.updateData(allTasks);

        //Refreshed all tasks and reassigning values to taskTM (tableModel)
        ttm = new TaskTableModel(getAllTasks());

        //refresh GUI display
        //Configure the JTable to use this model as its data source
        controllerJTable.setModel(ttm);
    }

    public TaskTableModel updateGUIJTableAfterAdd(){


        //Refresh allTasks object (updateData calls fireTableDataChanged (which updates JTable)

        ttm.updateData(allTasks);

        //Refreshed all tasks and reassigning values to taskTM (tableModel)
        ttm = new TaskTableModel(getAllTasks());

        //refresh GUI display
        //Configure the JTable to use this model as its data source
        //controllerJTable.setModel(ttm);
        return ttm;//
    }
    public void updateGUIJTableAfterUpdate(){

        //Refresh allTasks object (updateData calls fireTableDataChanged (which updates JTable)

        ttm.updateData(allTasks);

        //Refreshed all tasks and reassigning values to taskTM (tableModel)
        ttm = new TaskTableModel(getAllTasks());

        //refresh GUI display
        //Configure the JTable to use this model as its data source
        controllerJTable.setModel(ttm);
    }



}
