package ui_DayCare.view;
import com.alexandriasoftware.swing.JInputValidator;
import com.alexandriasoftware.swing.JInputValidatorPreferences;
import com.alexandriasoftware.swing.Validation;
import io.codeworth.panelmatic.PanelBuilder;
import io.codeworth.panelmatic.PanelMatic;
import mdlaf.MaterialLookAndFeel;
import ui_DayCare.dao.StudentDAO;
import ui_DayCare.dao.TeacherDAO;
import ui_DayCare.model.GroupInfo;
import ui_DayCare.model.Student;
import ui_DayCare.model.Teacher;
import ui_DayCare.utils.FileUtils;
import ui_DayCare.utils.Helper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static io.codeworth.panelmatic.componentbehavior.Modifiers.*;
import static io.codeworth.panelmatic.util.Groupings.lineGroup;

public class AddTeacherPanel extends JPanel {

    private static final long serialVersionUID = 1l;

    private JTabbedPane uploadCSVPane;
    private JTabbedPane basicDetailsPane;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField emergencyContactName;
    private JTextField guardianPhoneNumber;
    private JTextField address;
    private JPanel dateOfBirthPanel;
    private JSpinner dobSpinner;
    private JPanel dateOfRegistrationPanel;
    private JSpinner dateOfRegSpinner;
    private JTabbedPane groupPane;
    private JButton uploadMultipleButton;
    private JButton buttonSave;
    private JComboBox groupComboBox;


    public AddTeacherPanel() {
        createComponents();
        setupValidations();
        addActions();
        PanelMatic.begin( this )
                .addHeader(PanelBuilder.HeaderLevel.H1, "Add Teacher")
                .add( uploadCSVPane, GROW_LESS)
                .add( basicDetailsPane, GROW_LESS)
                .add( groupPane, GROW_LESS)
                .add( buttonSave, L_END, P_FEET, GROW )
                .get();
    }

    private void createComponents() {
        firstName = new JTextField(20);
        lastName = new JTextField(20);
        emergencyContactName = new JTextField(20);
        guardianPhoneNumber = new JTextField(20);
        address = new JTextField(20);
        dateOfBirthPanel = new JPanel();
        dobSpinner = Helper.addSpinner(dateOfBirthPanel);
        dateOfRegistrationPanel = new JPanel();
        dateOfRegSpinner = Helper.addSpinner(dateOfRegistrationPanel);
        uploadMultipleButton = new JButton("Upload CSV");
        buttonSave = new JButton("Save");
        groupComboBox = new JComboBox (
                GroupInfo.getDisplayStrings()
        );

        /*Should be called in the end*/
        createUploadUsingCSVPane();
        createBasicDetailsPane();
        createGroupInfoPane();

    }
    private void createUploadUsingCSVPane() {
        uploadCSVPane = new JTabbedPane();
        uploadCSVPane.add("CSV Upload",
                PanelMatic.begin()
                        .add(uploadMultipleButton)
                        .get()
        );
    }

    private void createBasicDetailsPane() {
        basicDetailsPane = new JTabbedPane();
        basicDetailsPane.add("Basic Details",
                PanelMatic.begin()
                        .add("First Name", firstName)
                        .add("Last Name", lastName)
                        .add("Emergency Contact", emergencyContactName)
                        .add("Phone", guardianPhoneNumber)
                        .add("Address", address)
                        .add("Birth Date", dateOfBirthPanel)
                        .add("Joining Date", dateOfRegistrationPanel)
                        .get()
        );
    }

    private void createGroupInfoPane() {
        groupPane = new JTabbedPane();
        groupPane.add("Group Information",
                PanelMatic.begin()
                        .add("Select Group", groupComboBox)
                        .get()
        );
    }

    private void setupValidations() {
        firstName.setInputVerifier(new JInputValidator(firstName) {
            @Override
            protected Validation getValidation(JComponent input, JInputValidatorPreferences preferences) {
                if (!(firstName.getText().matches("[a-zA-Z]+")) ||
                        (firstName.getText().length() > 20)) {
                    return new Validation(Validation.Type.DANGER, "Please enter a valid first name", preferences);
                }
                return new Validation(Validation.Type.SUCCESS, "", preferences);
            }
        });
        lastName.setInputVerifier(new JInputValidator(lastName) {
            @Override
            protected Validation getValidation(JComponent input, JInputValidatorPreferences preferences) {
                if (!(lastName.getText().matches("[a-zA-Z]+")) ||
                        (lastName.getText().length() > 20)) {
                    return new Validation(Validation.Type.DANGER, "Please enter a valid last name", preferences);
                }
                return new Validation(Validation.Type.SUCCESS, "", preferences);
            }
        });
        emergencyContactName.setInputVerifier(new JInputValidator(emergencyContactName) {
            @Override
            protected Validation getValidation(JComponent input, JInputValidatorPreferences preferences) {
                if (!(emergencyContactName.getText().matches("[a-zA-Z]+")) ||
                        (emergencyContactName.getText().length() > 30)) {
                    return new Validation(Validation.Type.DANGER, "Please enter a valid guardian name", preferences);
                }
                return new Validation(Validation.Type.SUCCESS, "", preferences);
            }
        });
        guardianPhoneNumber.setInputVerifier(new JInputValidator(guardianPhoneNumber) {
            @Override
            protected Validation getValidation(JComponent input, JInputValidatorPreferences preferences) {
                if (!(guardianPhoneNumber.getText().matches("[0-9]+")) ||
                        (guardianPhoneNumber.getText().length() > 15) ||
                        (guardianPhoneNumber.getText().length() < 8)
                ) {
                    return new Validation(Validation.Type.DANGER, "Invalid phone number", preferences);
                }
                return new Validation(Validation.Type.SUCCESS, "", preferences);
            }
        });
    }

    private void addActions() {
        buttonSave.addActionListener( e -> {
            Teacher teacher = new Teacher();
            teacher.setFirstName(firstName.getText().toLowerCase());
            teacher.setLastName(lastName.getText().toLowerCase());
            teacher.setEmergencyContactName(emergencyContactName.getText().toLowerCase());
            teacher.setEmergencyContactNumber(guardianPhoneNumber.getText().toLowerCase());
            teacher.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").format(dobSpinner.getValue()));
            teacher.setDateOfJoining(new SimpleDateFormat("yyyy-MM-dd").format(dateOfRegSpinner.getValue()));
            teacher.setAssignedGroup((String) groupComboBox.getSelectedItem());

            if (teacher.checkValidity().isValid()) {
                System.out.println("Validation passed... Writing to database.");
                TeacherDAO dao = new TeacherDAO();
                dao.addTeacher(teacher);
                Helper.showOkDialogueBoxWithMessage("Success",
                        "Teacher Successfully Added",
                        this);
            } else {
                Helper.showOkDialogueBoxWithMessage("Error",
                        teacher.checkValidity().getMessage(),
                        this);
            }
        });

        uploadMultipleButton.addActionListener( e -> {
            JFileChooser fileChooser = new JFileChooser ();
            int returnValue = fileChooser.showOpenDialog (this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("File Path: " + selectedFile.getPath());

                // populate teacher from CSV
                java.util.List<String> studentStringList = FileUtils.readCSVFile(selectedFile.getPath(), true);
                List<Teacher> teacher = null;
                try {
                    teacher = new ArrayList<>();
                    for (String teacherString: studentStringList) {
                        teacher.add(new Teacher(teacherString));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.out.println("No teacher will be inserted: aborting database inserts");
                    return;
                }
                TeacherDAO dao = new TeacherDAO();
                teacher.stream().forEach(x -> dao.addTeacher(x));
                dao.getAllTeachers().stream().forEach(System.out::println);
                Helper.showOkDialogueBoxWithMessage("Success", "Students List Imported", this);

            }
        });
    }


    static void wrapInWindow( JComponent comp) {
        JFrame frm = new JFrame( comp.getClass().getName() );
        frm.getContentPane().add( comp );
        frm.pack();
        frm.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        frm.setLocation(0, 0);
        frm.setVisible(true);
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
                wrapInWindow(new AddTeacherPanel());
            }
        });
    }

}