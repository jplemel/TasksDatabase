import java.util.Date;
/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class Task {

    //ID for each task - instance variable
    private int taskID;


    private String description;
    private Date dueDate;
    private Date dateCompleted;
    private String completedBy;
    private String attachment;
    private String typeOfTask;

    //If any task object modifies this counter, all Ticket objects will have the modified value
    //Private
    private static int staticTaskIDCounter = 1;

    Task(String description){
        this.description = description;
    }
    //task has no ID
    public Task(String description, Date dueDate, String attachment, String typeOfTask){

        this.taskID = staticTaskIDCounter;
        this.description = description;
        this.dueDate = dueDate;
        this.attachment = attachment;
        this. typeOfTask = typeOfTask;
        staticTaskIDCounter++;
    }
    //override task constructor for task without due date
    public Task(String description, String attachment, String typeOfTask){

        this.taskID = staticTaskIDCounter;
        this.description = description;
        this.attachment = attachment;
        this. typeOfTask = typeOfTask;
        staticTaskIDCounter++;
    }
    //override task constructor for task that already has ID
    public Task (int taskID, String description, String attachment, String typeOfTask ){

        this.taskID = taskID;
        this.description = description;
        this.attachment = attachment;
        this.typeOfTask = typeOfTask;
    }
    //override task constructor for task with ID and due date
    public Task (int taskID, String description, Date dueDate, String attachment, String typeOfTask ){

        this.taskID = taskID;
        this.description = description;
        this.dueDate = dueDate;
        this.attachment = attachment;
        this.typeOfTask = typeOfTask;
    }
    //override task constructor generic constructor
    public Task ( ){

        this.taskID = staticTaskIDCounter;
        staticTaskIDCounter ++;
    }

    //excluding attachment because I don't know how I'm going to display/handle that yet
    public String toString(){
        return ("ID#: " + this.getTaskID() + " \nTask: " + this.description + " \nType of Task: " + this.typeOfTask +
                " \nDue Date: " + this.dueDate);
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

    public Date getDueDate(){
        return dueDate;
    }

    public Date getDateCompleted(){
        return dateCompleted;
    }

    public  String getTypeOfTask(){
        return typeOfTask;
    }

    public String getCompletedBy(){
        return completedBy;
    }

    public void setDateCompleted(Date dateCompleted){
        this.dateCompleted = dateCompleted;
    }
    public void setCompletedBy(String completedBy){
        this.completedBy = completedBy;
    }
    public String getAttachment(){
        return attachment;
    }

}
