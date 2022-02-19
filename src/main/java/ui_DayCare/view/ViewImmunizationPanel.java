package ui_DayCare.view;

import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import ui_DayCare.dao.StudentDAO;
import ui_DayCare.dao.VaccineInfoDAO;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class ViewImmunizationPanel extends JPanel {
    JTable studentTable;
    private JButton refreshBtn;
    private JPanel currentDatePanel;
    private JSpinner currentDateSpinner;
    VaccineInfoDAO vaccineInfoDAO;

    public ViewImmunizationPanel() {
        vaccineInfoDAO = new VaccineInfoDAO();
        createComponents();
        PanelMatic.begin(this)
                .add("Current Date", currentDateSpinner)
                .add(refreshBtn)
                .add(new JScrollPane(studentTable)).get();
    }

    private void createComponents() {
        studentTable = new JTable();
        refreshBtn = new JButton("REFRESH");
        currentDatePanel = new JPanel();
        currentDateSpinner = Helper.addSpinner(currentDatePanel);
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
        Long currentDate = Helper.convertStringDateToLong(new SimpleDateFormat("yyyy-MM-dd").format(currentDateSpinner.getValue()));
        String[][] studentData = vaccineInfoDAO.getAllPendingVaccinationInfo(currentDate);
        String[] columns = {"First Name", "Last Name", "Hib", "DTaP", "Polio", "Hepatitis B", "MMR", "Varicella"};
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
                Helper.wrapInWindow(new ViewImmunizationPanel(), JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }


}
