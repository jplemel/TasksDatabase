import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.Vector;


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
    private JScrollPane scrollPane;
    private JFrame frame;


    //DefaultListModel<Task> TaskListModel;

    public TaskTableModel taskTM;

    private Controller controller;

    private int selectedRecord;

    //private static NewTaskGUI gui;



    //private Log log = new Log();


    public TaskViewerGUI(Controller task) {



        setContentPane(rootPanel);
        setSize(800,400);


        //map the controller
        this.controller = new Controller();

        //Create listModel - the list starts empty, so no data to add yet
        //When you add data to the list, you actually need to add it to the list's *model*
        controller.ttm = new TaskTableModel(controller.getAllTasks());

        //Configure the JTable to use this model as its data source
        taskTable.setModel(controller.updateGUIJTableAfterAdd());

        //map the taskTable to be accessible from controller
        controller.controllerJTable = taskTable;

        //LinkedList<Task> completedTasks = new LinkedList<>();

        //Configure JTable to only allow user to select one item at a time
        //Default is multiple selections
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectedRecord = -1 ; //this means no record is selected
//        //TODO: FIX JSCROLLPANE
//        //The column names are only displayed when you add the table to a JScrollPane
//        scrollPane = new JScrollPane(taskTable);
//        frame = new JFrame();
//        frame.add(scrollPane);
//        scrollPane.add(taskTable);
//        scrollPane.setViewportView(taskTable);
//        frame.setVisible(true);
        // Listener methods
        addListeners();

        //pack();
        //setVisible(true);


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

        //taskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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

                //NewTaskGUI.getFrames();
                controller.showGuiNewTask();

                //rootPanel1.setVisible(true);
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {


                controller.db = new TasksDB();

                //Generate new tables if they aren't there
                controller.db.createTables();


                //Query database to create the collection

                controller.allTasks = controller.db.fetchAllTasks();

                controller.db.nextTaskID();

                setTaskListData(controller.allTasks);

                taskTM = controller.updateGUIJTableAfterAdd();
               // taskTable.setModel(controller.ttm);

              //  taskTable.setModel(taskTM);
//                controller.controllerJTable.setModel(taskTM);
                controller.controllerJTable.setModel(taskTM);
            }
        });


        editTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Navigate to EditTaskGUI
                // I dont know if this is the call...
                //rootPanel2(setVisible(true));
                //controller.addTaskToDatabase(taskTM.getTasksAtRow(taskTable.getSelectedRow()));
            }
        });
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Ask the JTable what task is selected @ row
                int toDelete = taskTable.getSelectedRow();

                //Get task object to delete from corresponding row
                Task taskToDelete = controller.ttm.getTasksAtRow(toDelete);

                //Get taskID of task to be deleted
                int taskIDtoDelete = taskToDelete.getTaskID();

                //Delete from database with task item to delete
                controller.deleteTask(taskIDtoDelete);

                controller.updateGUIJTableAfterDelete(taskTable);

                //map the tasktable to be accessible from controller
                //controller.controllerJTable = taskTable;

                taskTable.setModel(controller.updateGUIJTableAfterAdd());


                //Refresh allTasks object (updateData calls fireTableDataChanged (which updates JTable)
                //controller.ttm.updateData(controller.allTasks);

                //Refreshed all tasks and reassigning values to taskTM (tableModel)
                //taskTM = new TaskTableModel(controller.getAllTasks());

                //controller.ttm = taskTM;

                //refresh GUI display
                //Configure the JTable to use this model as its data source
                //taskTable.setModel(taskTM);


            }
        });
        taskCompletedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //this is where you would add completed date and completed by information

            }
        });
    }


    public void setTaskListData(Vector<Task> allTasks) {

       // taskTM.updateData(allTasks);

        controller.ttm.updateData(allTasks);
    }
}
