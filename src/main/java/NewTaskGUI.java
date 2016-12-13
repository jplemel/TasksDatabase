import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
        //setSize(800,400);
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


                        //If it gets here, text fields are not null and user can continue
                        try {
                            addNewButtonAddTasks();
                        }
                        catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }

                }else{

                    //Data invalid, user cannot continue

                }

                //controller.showGui();
            }
        });

        addAttachmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                JFileChooser picchooser = new JFileChooser();
                picchooser.setDialogTitle("Select Image");
                picchooser.showOpenDialog(null);

                //this is the file the user selected from JFileChooser
                File pic = picchooser.getSelectedFile();


                controller.path = pic.getAbsolutePath();
                informUserLabel.setText(controller.path.replace('\\','/'));

                try{
                    File image = new File(controller.path);
                    FileInputStream fis = new FileInputStream(image);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    for(int readNum; (readNum=fis.read(buff)) !=-1 ; ){
                        baos.write(buff,0,readNum);
                    }
                    controller.userimage = baos.toByteArray();
                }
                catch(Exception e1){
                    JOptionPane.showMessageDialog(null, e);
                }




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
    private void addNewButtonAddTasks() throws FileNotFoundException {



//        int taskID = controller.nextTaskID;
//        if (taskID == 0){
//            taskID++;
//        }

//        int taskID = controller.allTasks.lastElement().getTaskID();

    //    taskID++;

        String description = taskDescriptionTextField.getText();
        String dueDate = dueDateTextField.getText();
        String dateCompleted = "";
        String completedBy = "";
        byte[] attachment = controller.userimage;
        String taskType = taskTypeTextField.getText();

        //New Task
        //TODO Add attachment to task
        Task tNew = new Task(description, dueDate, dateCompleted, completedBy, attachment, taskType);
        //Add task to database via controller
        controller.addTaskToDatabase(tNew);



        controller.showGui();




    }
}
