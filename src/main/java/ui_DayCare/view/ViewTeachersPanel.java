package ui_DayCare.view;

import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import ui_DayCare.dao.TeacherDAO;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ViewTeachersPanel extends JPanel {
    JTable teacherTable;
    private JButton refreshBtn;
    TeacherDAO teacherDAO;

    public ViewTeachersPanel() {
        teacherDAO = new TeacherDAO();
        createComponents();
        PanelMatic.begin(this)
                .add(refreshBtn)
                .add(new JScrollPane(teacherTable)).get();
    }

    private void createComponents() {
        teacherTable = new JTable();
        refreshBtn = new JButton("REFRESH");
        refreshBtn.setAction(new AbstractAction() {
            {
                putValue(Action.NAME, "REFRESH");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTeacherTable();
            }
        });
        populateTeacherTable();
    }

    public void populateTeacherTable() {
        String[][] studentData = teacherDAO.getAllTeachersAsStringArray();
        String[] columns = {"First Name", "Last Name", "Emergency Contact Name", "Emergency Contact Number", "Address", "Date of Birth", "Date of Registration"};
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(columns);
        teacherTable.setShowGrid(true);
        teacherTable.getShowVerticalLines();
        teacherTable.getShowHorizontalLines();
        teacherTable.setBackground(MaterialColors.LIGHT_BLUE_500);
        for (String[] row: studentData) {
            dtm.addRow(row);
        }
        teacherTable.setModel(dtm);
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
                Helper.wrapInWindow(new ViewTeachersPanel(), JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }
}
