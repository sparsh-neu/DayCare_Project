package ui_DayCare.view;

import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import ui_DayCare.dao.GroupsDAO;
import ui_DayCare.utils.Helper;
import ui_DayCare.utils.db.DBInit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainView {

    private static JFrame frame;

    public static void main(String[] args) {
        DBInit.initializeDataBase();
        // set look and feel
        try {
            UIManager.setLookAndFeel (new MaterialLookAndFeel());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }

//         JFrame
        frame = new JFrame ("DayCare");
        frame.setMinimumSize (new Dimension(800, 600));

        // TODO: add stuff to content
        JTabbedPane tabbedPane = new JTabbedPane ();

        tabbedPane.addTab ("Dashboard", getDashBoardTab());
        tabbedPane.addTab ("Students", getStudentTab());
        tabbedPane.addTab ("Teachers", getTeacherTab());

        frame.add (tabbedPane, BorderLayout.CENTER);


        // make everything visible to the world
        frame.pack ();
        frame.setVisible(true);

    }

     private static JScrollPane getDashBoardTab() {
        JPanel dashBoardContent = new JPanel ();
        // show summary
        // student registration details
        JTabbedPane tabs = new JTabbedPane();
         YearlyRegistrationStats yearlyRegistrationStats = new YearlyRegistrationStats();
         tabs.add("Annual Registration", PanelMatic.begin()
                 .add(yearlyRegistrationStats.getChart()).get());
         tabs.add("Employee Reviews", PanelMatic.begin()
                 .add(new EmployeeReviews()).get());
         tabs.add("Groups", PanelMatic.begin()
                 .add(new ViewGroupsPanel()).get());
         tabs.add("Immunization Anniversaries", PanelMatic.begin()
                 .add(new ViewImmunizationPanel()).get());
        dashBoardContent.add(tabs);
        JScrollPane scrollPane = new JScrollPane(dashBoardContent);
        scrollPane.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

    private static JScrollPane getStudentTab() {
        JPanel studentContent = new JPanel ();
        // show summary
        // student registration details
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("View Student List", PanelMatic.begin()
                .add(new ViewStudentsPanel()).get());
        tabs.add("Add Students", PanelMatic.begin()
                .add(new AddStudentPanel()).get());
        studentContent.add(tabs);
        JScrollPane scrollPane = new JScrollPane(studentContent);
        scrollPane.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

    private static JScrollPane getTeacherTab() {
        JPanel teacherContent = new JPanel ();
        // show summary
        // student registration details
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("View Teacher List", PanelMatic.begin()
                .add(new ViewTeachersPanel()).get());
        tabs.add("Add Teacher", PanelMatic.begin()
                .add(new AddTeacherPanel()).get());
        teacherContent.add(tabs);
        JScrollPane scrollPane = new JScrollPane(teacherContent);
        scrollPane.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scrollPane;
    }

}


