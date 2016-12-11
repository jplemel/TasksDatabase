import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created by Jennifer Plemel on 12/9/2016.
 */
public class TaskTableModel extends AbstractTableModel {
    //This models Task objects for JTable display

    //private Log log = new Log();

    private Vector<Task> allTasks;

    //Column names, displayed as headers in the JTable

    private String[] columnNames = {

            "TaskID",
            "Description",
            "DueDate",
            "DateCompleted",
            "CompletedBy",
            "Attachment",
            "TypeOfTask"
    };

    public TaskTableModel(Vector<Task> tasks){
        this.allTasks = tasks;
    }

    public void updateData(Vector<Task> updatedTasks){
        //completely replace data in table with fresh data from DB
        this.allTasks = updatedTasks;
        fireTableDataChanged();


        if (allTasks != null){

            fireTableRowsInserted(1, allTasks.size());
        }

        //int rowcount = getRowCount();


    }

    @Override
    public int getRowCount(){
        return allTasks.size();
    }

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch (columnIndex){
            case 0: // ID
                return allTasks.get(rowIndex).getTaskID();
            case 1: // Description
                return allTasks.get(rowIndex).getDescription();
            case 2: //DueDate
                return allTasks.get(rowIndex).getDueDate();
            case 3: //DateCompleted
                return allTasks.get(rowIndex).getDateCompleted();
            case 4: //CompletedBy
                return allTasks.get(rowIndex).getCompletedBy();
            case 5: //Attachment
                return allTasks.get(rowIndex).getAttachment();
            case 6: //TypeOfTask
                return allTasks.get(rowIndex).getTypeOfTask();
            default: //should never get here
                //log.warn("Trying to access OOB column in Task");
                return null;
        }
    }
    Task getTasksAtRow(int rowIndex){
        return allTasks.get(rowIndex);
    }
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
}
