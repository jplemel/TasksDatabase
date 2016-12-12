import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class EditTaskGUI extends JFrame{
    private JPanel rootPanel2;
    private JLabel taskIDLabel;
    private JTextField descriptionTextField;
    private JTextField dueDateTextField;
    private JTextField dateCompleteTextField;
    private JTextField completedByTextField;
    private JButton attachmentButton;
    private JTextField typeOfTaskTextField;
    private JButton updateTaskButton;
    private JLabel informUserLabel;

    private Controller controller;

    public Task taskToEdit;


    public EditTaskGUI (Task task){


        setContentPane(rootPanel2);
        pack();
        getRootPane().setDefaultButton(updateTaskButton);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //map the controller
        this.controller = new Controller();



        if (task != null){

            taskToEdit = task;

            //get taskID of Task to edit
            int taskID = taskToEdit.getTaskID();

            //set label on form to taskID
            taskIDLabel.setText(String.valueOf(taskID));
        }





        addListener();

    }

    private void addListener(){

        //Listener for updateTaskButton
        updateTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //Data Validation (is valid input/not null)

                //(Due Date, Completed By, Attachment, Date Completed can all be null)
                if (testEntryNotNull(descriptionTextField.getText(), "Description")){

                    if (testEntryNotNull(typeOfTaskTextField.getText(), "Task Type")){


                        //If it gets here, text fields are not null and user can continue
                        updateTask();
                    }

                }else{

                    //Data invalid, user cannot continue

                }

            }
        });
    }
    //Method for data validation
    private boolean testEntryNotNull(String stringEntry, String fieldName){

        //Data Validation to determine if is not null

        //stringEntry is the string to test
        //fieldName displayed to user (@inform user label)

        if (stringEntry == null || stringEntry.length() == 0){

            informUserLabel.setText("Must enter a " + fieldName + " to continue.");

            return false;

        } else {
            return true;
        }
    }

    //Method with logic to update the task
    private void updateTask(){

        int taskID =  taskToEdit.getTaskID();
        String description = descriptionTextField.getText();
        String dueDate = dueDateTextField.getText();
        String dateCompleted = dateCompleteTextField.getText();
        String completedBy = completedByTextField.getText();
        byte[] attachment = new byte[0];
        String taskType = typeOfTaskTextField.getText();

        //New Task
        //TODO Add attachment to task
        Task tNew = new Task(taskID, description, dueDate, dateCompleted, completedBy, attachment, taskType);
        //Update task to database via controller
        controller.updateTask(taskID,tNew);

        controller.showGui();


    }
}
