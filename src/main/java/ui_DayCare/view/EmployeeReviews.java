package ui_DayCare.view;

import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import ui_DayCare.dao.TeacherDAO;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

public class EmployeeReviews extends JPanel {

    JButton addReviewBtn;
    JTable teacherReviewTable;
    private JPanel fromDatePanel;
    private JSpinner fromDateSpinner;
    private JPanel toDatePanel;
    private JSpinner toDateSpinner;
    private JButton refreshBtn;
    TeacherDAO teacherDAO;
    Long fromDate, toDate;

    public EmployeeReviews() {
        teacherDAO = new TeacherDAO();
        createComponents();
        PanelMatic.begin(this)
                .add(addReviewBtn)
                .add("FROM DATE", fromDatePanel)
                .add("TO DATE", toDatePanel)
                .add(refreshBtn)
                .add(new JScrollPane(teacherReviewTable)).get();
    }

    private void createComponents() {
        addReviewBtn = new JButton("ADD REVIEW");
        addReviewBtn.setBackground(MaterialColors.LIGHT_BLUE_500);
//        addReviewBtn.setName("ADD REVIEW");
//        addReviewBtn.addMouseListener(MaterialUIMovement.getMovement(addReviewBtn, MaterialColors.LIGHT_BLUE_200));
        addReviewBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    putValue(Action.NAME, "ADD REVIEW");
                }
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Helper.wrapInWindow(new ReviewForm(), 2);
                    }
                });
            }
        });
        teacherReviewTable = new JTable();
        fromDatePanel = new JPanel();
        fromDateSpinner = Helper.addSpinner(fromDatePanel);
        toDatePanel = new JPanel();
        toDateSpinner = Helper.addSpinner(toDatePanel);
        refreshBtn = new JButton("REFRESH");
        refreshBtn.setAction(new AbstractAction() {
            {
                putValue(Action.NAME, "REFRESH");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTeacherReviewTable();
            }
        });
    }

    public void populateTeacherReviewTable() {
        fromDate = Helper.convertStringDateToLong(new SimpleDateFormat("yyyy-MM-dd").format(fromDateSpinner.getValue()));
        toDate = Helper.convertStringDateToLong(new SimpleDateFormat("yyyy-MM-dd").format(toDateSpinner.getValue()));
        String[][] teacherReviewData = teacherDAO.getAllTeacherAvgReviewStrArr(fromDate, toDate);
        String[] columns = {"First Name", "Last Name", "Average rating in the given date range"};
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(columns);
        teacherReviewTable.setShowGrid(true);
        teacherReviewTable.getShowVerticalLines();
        teacherReviewTable.getShowHorizontalLines();
        teacherReviewTable.setBackground(MaterialColors.LIGHT_BLUE_500);
        for (String[] row: teacherReviewData) {
            dtm.addRow(row);
        }
        teacherReviewTable.setModel(dtm);
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
                Helper.wrapInWindow(new EmployeeReviews(), 2);
            }
        });
    }
}
