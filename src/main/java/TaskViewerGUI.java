import sun.awt.image.ImageWatched;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class TaskViewerGUI extends JFrame {
    private JPanel rootPanel;
    private JButton addNewTaskButton;
    private JButton editTaskButton;
    private JButton deleteTaskButton;
    private JLabel InstructUserLabel;
    private JButton taskCompletedButton;
    private JTable taskTable;

    DefaultListModel<Task> TaskListModel;

    private TaskTableModel taskTM;

    private Controller controller;

    private int selectedRecord;

    //private Log log = new Log();


    public TaskViewerGUI(Task task) {


        setContentPane(rootPanel);

        //map the controller
        this.controller = controller;

        //Create listModel - the list starts empty, so no data to add yet
        //When you add data to the list, you actually need to add it to the list's *model*
        taskTM = new TaskTableModel(controller.getAllTasks());

        //Configure the JList to use this model as its data source
        taskTable.setModel(taskTM);

        LinkedList<Task> completedTasks = new LinkedList<>();

        //Configure JList to only allow user to select one item at a time
        //Default is multiple selections
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectedRecord = -1 ; //this means no record is selected

        // Listener methods
        addListeners();

        pack();
        setVisible(true);


        //Click Event
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Display current tasks in J List
//        if (!queue.isEmpty()){
//
//            for (Task t : queue){
//                TaskListModel.addElement(t);
//                InstructUserLabel.setText("Select task, then click Edit, Delete, or Complete button");
//            }
//
//        }
//        else{
//
//            InstructUserLabel.setText("No tasks in queue");
//        }


    }

    private void addListeners(){

        taskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
//        taskTable.addTableSelectionListener(new ListSelectionListener() {
//        public void valueChanged(ListSelectionEvent e) {
//
//        int selectedIndex = taskTable.getSelectedIndex();
//        }
//    });
        addNewTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Navigate to NewTaskGUI

            }
        });
        editTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Navigate to EditTaskGUI
                // I dont know if this is the call...
                EditTaskGUI(setVisible(true));
            }
        });
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Ask the JTable what task is selected
                Task toDelete = taskTable.getSelectedValue();

                //Delete this task from the MODEL
                TaskListModel.removeElement(toDelete);



            }
        });
        taskCompletedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Call delete task method

                //Remove element from JList

                //Remove task from current task file (open)
            }
        });
    }


}
