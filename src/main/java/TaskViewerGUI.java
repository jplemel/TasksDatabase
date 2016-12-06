import sun.awt.image.ImageWatched;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by Jennifer Plemel on 12/4/2016.
 */
public class TaskViewerGUI extends JFrame {
    private JPanel rootPanel;
    private JButton addNewTaskButton;
    private JButton editTaskButton;
    private JButton deleteTaskButton;
    private JList taskList;
    private JLabel InstructUserLabel;
    private JButton taskCompletedButton;

    DefaultListModel<Task> TaskListModel;

    protected TaskViewerGUI(LinkedList<Task> resolved, LinkedList<Task> queue) {


        setContentPane(rootPanel);

        //Create listModel - the list starts empty, so no data to add yet
        //When you add data to the list, you actually need to add it to the list's *model*
        TaskListModel = new DefaultListModel<Task>();

        //Configure the JList to use this model as its data source
        taskList.setModel(TaskListModel);

        LinkedList<Task> completedTasks = new LinkedList<>();

        //Configure JList to only allow user to select one item at a time
        //Default is multiple selections
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        // Listener methods
        addListeners(resolved, queue);


        pack();
        setVisible(true);


        //Click Event
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Display current tasks in J List
        if (!queue.isEmpty()){

            for (Task t : queue){
                TaskListModel.addElement(t);
                InstructUserLabel.setText("Select task, then click Edit, Delete, or Complete button");
            }

        }
        else{

            InstructUserLabel.setText("No tasks in queue");
        }


    }

    private void addListeners(LinkedList<Task> resolved, LinkedList<Task> queue){

        taskList.addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {

        int selectedIndex = taskList.getSelectedIndex();
        }
    });
        addNewTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Navigate to NewTaskGUI

            }
        });
        editTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Navigate to EditTaskGUI
            }
        });
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Ask the JList what task is selected
                Task toDelete = taskList.getSelectedValue();

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
