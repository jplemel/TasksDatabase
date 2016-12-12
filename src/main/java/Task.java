/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class Task {

    //ID for each task - instance variable
    private int taskID;


    private String description;
    private String dueDate;
    private String dateCompleted;
    private String completedBy;
    private byte[] attachment;
    private String typeOfTask;
    private Controller controller;


    //If any task object modifies this counter, all Ticket objects will have the modified value
    //Private
    private static int staticTaskIDCounter;

//    public Task(String description){
//        this.description = description;
//    }
    //task has no ID
    public Task(String description, String dueDate, byte[] attachment, String typeOfTask){

        this.taskID = staticTaskIDCounter;
        this.description = description;
        this.dueDate = dueDate;
        this.attachment = attachment;
        this. typeOfTask = typeOfTask;
        staticTaskIDCounter++;
    }
    public Task(String description, String typeOfTask){
        this.taskID = staticTaskIDCounter;
        this.description = description;
        this.typeOfTask = typeOfTask;
        staticTaskIDCounter++;
    }
    //override task constructor for task without due date
    public Task(String description, byte[] attachment, String typeOfTask){

        this.taskID = staticTaskIDCounter;
        this.description = description;
        this.attachment = attachment;
        this. typeOfTask = typeOfTask;
        staticTaskIDCounter++;
    }
    //override task constructor for task that already has ID
    public Task (int taskID, String description, byte[] attachment, String typeOfTask ){

        this.taskID = taskID;
        this.description = description;
        this.attachment = attachment;
        this.typeOfTask = typeOfTask;
    }
    //override task constructor for task with ID and due date
    public Task (int taskID, String description, String dueDate, byte[] attachment, String typeOfTask ){

        this.taskID = taskID;
        this.description = description;
        this.dueDate = dueDate;
        this.attachment = attachment;
        this.typeOfTask = typeOfTask;
    }
    //completed task constructor
    public Task (int taskID, String description, String dueDate, String dateCompleted, String completedBy, byte[] attachment, String typeOfTask ){

        this.taskID = taskID;
        this.description = description;
        this.dueDate = dueDate;
        this.attachment = attachment;
        this.typeOfTask = typeOfTask;
        this.dateCompleted = dateCompleted;
        this.completedBy = completedBy;
    }
    //override task constructor generic constructor
    public Task ( ){

        this.taskID = staticTaskIDCounter;
        staticTaskIDCounter ++;
        this.description = "";
        this.dueDate = null;
        this.attachment = null;
        this.typeOfTask = "";
        this.dateCompleted = null;
        this.completedBy = "";
    }
    //constuctor no task id
    public Task(String description, String dueDate, String dateCompleted, String completedBy, byte[] attachment, String typeOfTask){

        this.taskID = staticTaskIDCounter;
        staticTaskIDCounter ++;
        this.description = description;
        this.dueDate = dueDate;
        this.attachment = attachment;
        this.typeOfTask = typeOfTask;
        this.dateCompleted = dateCompleted;
        this.completedBy = completedBy;
    }
    //excluding attachment because I don't know how I'm going to display/handle that yet
    public String toString(){
        return ("ID#: " + this.getTaskID() + "Task: " + this.description +  "Due Date: " + this.dueDate + "Date Completed: " + this.dateCompleted +
                "Completed By: " + this.completedBy + "Attachment: " + this.attachment + "Type of Task: " + this.typeOfTask);
    }

    //for completed tasks excluding attachment
    public String toString(String completed){
        return ("ID#: " + this.getTaskID() + "\nTask: " + this.description + "\nType of Task: " + this.typeOfTask +
        " \nDue Date: " + this.dueDate + "\nDate Completed: " + this.dateCompleted + "\nCompleted By: " + this.completedBy);
    }

    public int getTaskID(){
        return taskID;
    }

    //setter method for static task property
    public static void setStaticTaskIDCounter(int staticTaskIDCounter){
        Task.staticTaskIDCounter = staticTaskIDCounter;
    }

    public String getDescription(){
        return description;
    }

    public String getDueDate(){
        return dueDate;
    }

    public String getDateCompleted(){
        return dateCompleted;
    }

    public  String getTypeOfTask(){
        return typeOfTask;
    }

    public String getCompletedBy(){
        return completedBy;
    }

    public void setDateCompleted(String dateCompleted){
        this.dateCompleted = dateCompleted;
    }
    public void setCompletedBy(String completedBy){
        this.completedBy = completedBy;
    }
    public byte[] getAttachment(){
        return attachment;
    }

}
