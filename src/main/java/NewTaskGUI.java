import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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

    public NewTaskGUI(LinkedList<Task> queue) {

        setContentPane(rootPanel1);
        pack();
        getRootPane().setDefaultButton(saveButton);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        addListener(queue);



    }

    private void addListener(final LinkedList<Task> queue){
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //New Task
                Task tNew = new Task(taskDescriptionTextField.getText(), dueDateTextField.getText(), taskTypeTextField.getText());

                //Add Task to queue
                addTaskTopPriority(queue, tNew);

                //Write New Task to file (and database?)
                writeOpen(queue);

                //Clear J List


                //Fill J List with Tasks (sorted by top priority)


            }
        });
    }
}
