package ui_DayCare.view;

import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import ui_DayCare.dao.StudentDAO;
import ui_DayCare.dao.TeacherDAO;
import ui_DayCare.model.Student;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class ViewStudentsPanel extends JPanel {
    JTable studentTable;
    private JButton refreshBtn;
    StudentDAO studentDAO;

    public ViewStudentsPanel() {
        studentDAO = new StudentDAO();
        createComponents();
        PanelMatic.begin(this)
                .add(refreshBtn)
                .add(new JScrollPane(studentTable)).get();
    }

    private void createComponents() {
        studentTable = new JTable();
        refreshBtn = new JButton("REFRESH");
        refreshBtn.setAction(new AbstractAction() {
            {
                putValue(Action.NAME, "REFRESH");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                populateStudentTable();
            }
        });
        populateStudentTable();
    }

    public void populateStudentTable() {
        String[][] studentData = studentDAO.getAllStudentsAsStringArray();
        String[] columns = {"First Name", "Last Name", "Emergency Contact Name", "Emergency Contact Number", "Address", "Date of Birth", "Date of Registration"};
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(columns);
        studentTable.setShowGrid(true);
        studentTable.getShowVerticalLines();
        studentTable.getShowHorizontalLines();
        studentTable.setBackground(MaterialColors.LIGHT_BLUE_500);
        for (String[] row: studentData) {
            dtm.addRow(row);
        }
        studentTable.setModel(dtm);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel (new MaterialLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Helper.wrapInWindow(new ViewStudentsPanel(), JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }
}
