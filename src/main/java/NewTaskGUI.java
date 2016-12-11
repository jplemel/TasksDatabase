import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class NewTaskGUI extends JFrame {
    private JPanel rootPanel1;
    private JTextField taskDescriptionTextField;
    private JTextField taskTypeTextField;
    private JTextField dueDateTextField;
    private JButton addAttachmentButton;
    private JButton saveButton;
    private JLabel informUserLabel;

    private Controller controller;

    public NewTaskGUI(Controller task) {

        setContentPane(rootPanel1);
        pack();
        getRootPane().setDefaultButton(saveButton);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //map the controller
        this.controller = new Controller();



        addListener();



    }

    private void addListener(){
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                //Data Validation (is valid input/not null)

                //(Due Date can be null) (Attachment can be null) (Due Date can be null)
                if (testEntryNotNull(taskDescriptionTextField.getText(), "Description")){

                    if (testEntryNotNull(taskTypeTextField.getText(), "Task Type")){

                        addNewButtonAddTasks();
                    }

                }else{

                    //Data invalid, user cannot continue

                }

                //controller.showGui();
            }
        });
    }

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
    private void addNewButtonAddTasks(){



        int taskID = controller.nextTaskID;
        String description = taskDescriptionTextField.getText();
        String dueDate = dueDateTextField.getText();
        String dateCompleted = "";
        String completedBy = "";
        String attachment = "";
        String taskType = taskTypeTextField.getText();

        //New Task
        //TODO Add attachment to task
        Task tNew = new Task(taskID, description, dueDate, dateCompleted, completedBy, attachment, taskType);
        //Add task to database via controller
        controller.addTaskToDatabase(tNew);

        //Add task to Table Model via controller
        //controller.allTasks.add(tNew);


        //controller.updateGUIJTableAfterAdd();

        controller.showGui();

//        //Refresh allTasks object (updateData calls fireTableDataChanged (which updates JTable)
//        controller.ttm.updateData(controller.allTasks);
//
//        //Refreshed all tasks and reassigning values to taskTM (tableModel)
//        TaskTableModel taskTM = new TaskTableModel(controller.getAllTasks());
//
//        //refresh GUI display
//        //Configure the JTable to use this model as its data source
//
//        //taskTable.setModel(taskTM);



    }
}
