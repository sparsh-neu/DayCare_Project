package ui_DayCare.view;

import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import ui_DayCare.dao.GroupsDAO;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ViewGroupsPanel extends JPanel {

    JTable studentTable;
    private JButton refreshBtn;
    GroupsDAO groupsDAO;

    public ViewGroupsPanel() {
        groupsDAO = new GroupsDAO();
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
                populateGroupsTable();
            }
        });
        populateGroupsTable();
    }

    public void populateGroupsTable() {
        String[][] studentData = groupsDAO.getListOfStudentsToDisplay().toArray(new String[0][0]);
        String[] columns = {"First Name", "Last Name", "Group Information", "Teacher"};
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
