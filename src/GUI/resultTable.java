package GUI;
/**
 * Lidor Feldman
 * 209297035
 * Itay Ventura
 * 208928333
 */
import game.racers.Racer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Vector;

/**
 * resultTable class for ShowInfo button, extends JFrame
 */
public class resultTable extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    /**
     * default constructor for resultTable Frame
     */
    public resultTable() {
        setSize(new Dimension(500,250));
        // Create the table model with the specified columns
        model = new DefaultTableModel(new Object[][]{}, new String[]{"Racer name", "Current Speed", "Max Speed", "Current X location", "Finished"});

        // Create the table with the model
        table = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * function to change visibility, depends on show info button
     */
    public void showMe(){
        setVisible(true);
    }

    /**
     * @param r
     * given racer adding a row to the table
     */
    public void addTask(Racer r) {
        // Create a new row with the task data
        String res = r.isFinished() ? "Yes" : "No";
        DecimalFormat df = new DecimalFormat("#.###");
        Object[] row = new Object[]{r.getName(), Double.parseDouble(df.format(r.getCurrentSpeed())), r.getMaxSpeed(), Double.parseDouble(df.format(r.getCurrentLocation().getX())), res};
        try {
            model.addRow(row);
        }
        catch (Exception e){
            System.out.println("problem editing row");
        }
    }

    /**
     * resetting the table rows
     */
    public synchronized void resetTable(){
        model.setRowCount(0);
    }

}
