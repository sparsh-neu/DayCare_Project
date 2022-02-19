package ui_DayCare.view;

import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import ui_DayCare.dao.ReviewDAO;
import ui_DayCare.dao.TeacherDAO;
import ui_DayCare.model.Employee;
import ui_DayCare.model.Review;
import ui_DayCare.model.Teacher;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

public class ReviewForm extends JPanel {
    JButton saveBtn;
    JComboBox teacherList;
    JSlider ratingSlider;// = new JSlider (JSlider.HORIZONTAL, 0, 5, 2)
    private JPanel reviewDatePanel;
    private JSpinner reviewDateSpinner;
    Teacher selectedTeacher;
    Integer selectedRating;
    TeacherDAO teacherDAO;
    ReviewDAO reviewDAO;
    Long reviewDate;
    public ReviewForm() {
        teacherDAO = new TeacherDAO();
        reviewDAO = new ReviewDAO();
        selectedRating = 3;
        createComponents();
        PanelMatic.begin(this)
                .add("Select Teacher", teacherList)
                .add("Rating", ratingSlider)
                .add("Review Date", reviewDatePanel)
                .add(saveBtn)
                .get();
    }

    public void createComponents() {
        teacherList = new JComboBox();
        for (Employee teacher: teacherDAO.getAllTeachers()) {
            teacherList.addItem(teacher);
        }
        teacherList.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectTeacherAction();
            }
        });
        ratingSlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(1, new JLabel("1"));
        labels.put(2, new JLabel("2"));
        labels.put(3, new JLabel("3"));
        labels.put(4, new JLabel("4"));
        labels.put(5, new JLabel("5"));
        ratingSlider.setLabelTable(labels);
        ratingSlider.setPaintLabels(true);
        ratingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ratingChangedAction();
            }
        });
        saveBtn = new JButton("SAVE");
        saveBtn.setBackground(MaterialColors.LIGHT_BLUE_500);
        reviewDatePanel = new JPanel();
        reviewDateSpinner = Helper.addSpinner(reviewDatePanel);

//        saveBtn.addMouseListener(MaterialUIMovement.getMovement(saveBtn, MaterialColors.LIGHT_BLUE_200));
        saveBtn.setAction(new AbstractAction() {
            {
                putValue(Action.NAME, "SAVE");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBtnOnClick();
            }
        });
    }

    private void selectTeacherAction() {
        selectedTeacher = (Teacher) teacherList.getSelectedItem();
        System.out.println("teacher: " + selectedTeacher);
    }

    private void ratingChangedAction() {
        selectedRating = ratingSlider.getValue();
        System.out.println("Rating: " + ratingSlider.getValue());
    }

    private void saveBtnOnClick() {
        if (selectedTeacher == null) return;
        reviewDate = Helper.convertStringDateToLong(new SimpleDateFormat("yyyy-MM-dd").format(reviewDateSpinner.getValue()));
        Review review = new Review(selectedRating, selectedTeacher.getId(), reviewDate);
        reviewDAO.addReviewForEmployee(review);
        Helper.showOkDialogueBoxWithMessage("Success",
                "Review Successfully Added",
                this);
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
                Helper.wrapInWindow(new ReviewForm(), 2);
            }
        });
    }
}
