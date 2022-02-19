package ui_DayCare.utils.db;

import ui_DayCare.dao.*;
import ui_DayCare.model.Student;
import ui_DayCare.model.Teacher;
import ui_DayCare.model.db.DBVaccineRules;
import ui_DayCare.model.db.DBVaccines;
import ui_DayCare.utils.FileUtils;
import ui_DayCare.utils.Helper;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DBInit {
    public static void initializeDataBase() {
        // initialize DAOs
        TeacherDAO teacherDAO = new TeacherDAO();
        ImmunizationRulesDAO immunizationRulesDAO = new ImmunizationRulesDAO();
        StudentDAO studentDAO = new StudentDAO();
        VaccinesDAO vaccinesDAO = new VaccinesDAO();
        VaccineRulesDAO vaccineRulesDAO = new VaccineRulesDAO();
        vaccinesDAO.deleteTable();
        studentDAO.deleteTable();
        vaccineRulesDAO.deleteTable();
        String[][] teachers6to12 = {{"Daniel", "Peters", "Mr. Peters", "+1 (617)6890886", "Huntington Ave, Boston", "1974-4-20", "2017-9-3", "6 - 12"},
                {"Raushan", "Khan", "Mr. Habib", "+1 (617)6577886", "Huntington Ave, Boston", "1965-7-14", "2017-6-3", "6 - 12"},
                {"Robin", "Hillyard", "Mr. Smith", "+1 (617)6897866", "Huntington Ave, Boston", "1959-12-13", "2017-3-6", "6 - 12"}};
        String[][] teachers13to24 = {{"Dinesh", "Chugtai", "Mr. Henry", "+1 (646)6678886", "Huntington Ave, Boston", "1978-5-4", "2015-5-30", "13 - 24"},
                {"Rajesh", "Kuthrapali", "Mr. Mark", "+1 (546)6784886", "Huntington Ave, Boston", "1967-3-23", "2019-2-1", "13 - 24"},
                {"Luke", "Skywalker", "Mr. Aniken Skywaker", "+1 (617)6890345", "Huntington Ave, Boston", "1974-6-21", "2015-6-4", "13 - 24"}};
        String[][] teachers25to35 = {{"Li", "Uzumaki", "Xu Uzumaki", "+1 (456)6644886", "Huntington Ave, Boston", "1970-5-6", "2017-7-3", "25 - 35"},
                {"Gilfoyle", "Bertram", "Mr. Bertram", "+1 (617)5644886", "Huntington Ave, Boston", "1984-3-25", "2017-8-23", "25 - 35"},
                {"Saskue", "Uchiha", "Mr. Itachi", "+1 (517)6895466", "Huntington Ave, Boston", "1967-6-5", "2017-5-23", "25 - 35"}};
        String[][] teachers36to47 = {{"Kakashi", "Hatake", "White Fang", "+1 (347)5678886", "Huntington Ave, Boston", "1973-5-23", "2017-10-30", "36 - 47"},
                {"Shin", "Chan", "Harry", "+1 (563)4567542", "Huntington Ave, Boston", "1980-5-10", "2017-8-30", "36 - 47"},
                {"Rachel", "Green", "Mr. Blue", "+1 (567)64564566", "Huntington Ave, Boston", "1984-3-2", "2017-8-21", "36 - 47"}};
        String[][] teachers48to59 = {{"Monica", "Geller", "Mr. Geller", "+1 (675)12345567", "Huntington Ave, Boston", "1980-5-30", "2017-9-30", "48 - 59"},
                {"Phoebe", "Buffay", "Mr. Buffay", "+1 (345)5632886", "Huntington Ave, Boston", "1957-3-12", "2017-2-12", "48 - 59"},
                {"Greg", "O-Geller", "Mr. Geller", "+1 (567)2340886", "Huntington Ave, Boston", "1960-5-26", "2017-6-3", "48 - 59"}};
        String[][] teachers60Above = {{"Emily", "Blunt", "Mr. Blunt", "+1 (617)68684886", "Huntington Ave, Boston", "1974-4-20", "2017-9-3", "60+"},
                {"Nelsen", "Bighetti", "Mr. Bighetti", "+1 (617)6865786", "Huntington Ave, Boston", "1991-7-10", "2017-5-3", "60+"},
                {"Jin", "Yang", "Mr. Yang", "+1 (897)2340886", "Huntington Ave, Boston", "1993-6-23", "2017-10-5", "60+"}};


        // add data to DAOs
        immunizationRulesDAO.populateImmunizationRules();
        for (String[] s: teachers6to12) {
            teacherDAO.addTeacher(new Teacher(s[0], s[1], s[2], s[3], s[4], Helper.convertStringDateToLong(s[5]), Helper.convertStringDateToLong(s[6]), s[7]));
        }
        for (String[] s: teachers13to24) {
            teacherDAO.addTeacher(new Teacher(s[0], s[1], s[2], s[3], s[4], Helper.convertStringDateToLong(s[5]), Helper.convertStringDateToLong(s[6]), s[7]));
        }
        for (String[] s: teachers25to35) {
            teacherDAO.addTeacher(new Teacher(s[0], s[1], s[2], s[3], s[4], Helper.convertStringDateToLong(s[5]), Helper.convertStringDateToLong(s[6]), s[7]));
        }
        for (String[] s: teachers36to47) {
            teacherDAO.addTeacher(new Teacher(s[0], s[1], s[2], s[3], s[4],  Helper.convertStringDateToLong(s[5]), Helper.convertStringDateToLong(s[6]), s[7]));
        }
        for (String[] s: teachers48to59) {
            teacherDAO.addTeacher(new Teacher(s[0], s[1], s[2], s[3], s[4],  Helper.convertStringDateToLong(s[5]), Helper.convertStringDateToLong(s[6]), s[7]));
        }
        for (String[] s: teachers60Above) {
            teacherDAO.addTeacher(new Teacher(s[0], s[1], s[2], s[3], s[4],  Helper.convertStringDateToLong(s[5]), Helper.convertStringDateToLong(s[6]), s[7]));
        }
//        teacherDAO.getAllTeachers().stream().forEach(System.out::println);
        // populate students from CSV
        List<String> studentStringList = FileUtils.readCSVFile(Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "students.csv").toString(), true);
        List<Student> students = null;
        try {
            students = new ArrayList<>();
            for (String studentString: studentStringList) {
                students.add(new Student(studentString));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("No students will be inserted: aborting database inserts");
            return;
        }
        students.stream().forEach(x -> studentDAO.addStudent(x));

        studentDAO.getStudents().stream().forEach(System.out::println);
        // populate all vaccines
        vaccinesDAO.addVaccine(new DBVaccines("1", "Hib"));
        vaccinesDAO.addVaccine(new DBVaccines("2", "DTaP"));
        vaccinesDAO.addVaccine(new DBVaccines("3", "Polio"));
        vaccinesDAO.addVaccine(new DBVaccines("4", "Hepatitis B"));
        vaccinesDAO.addVaccine(new DBVaccines("5", "MMR"));
        vaccinesDAO.addVaccine(new DBVaccines("6", "Varicella"));
        vaccinesDAO.getAllVaccines().stream().forEach(System.out::println);

        // populate all vaccination rules
        // Hib
        vaccineRulesDAO.addVaccine(new DBVaccineRules(24 * Helper.get1MonthLong(),"1"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(25 * Helper.get1MonthLong(),"1"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(26 * Helper.get1MonthLong(),"1"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(27 * Helper.get1MonthLong(),"1"));
        // DTaP
        vaccineRulesDAO.addVaccine(new DBVaccineRules(28 * Helper.get1MonthLong(),"2"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(29 * Helper.get1MonthLong(),"2"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(30 * Helper.get1MonthLong(),"2"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(31 * Helper.get1MonthLong(),"2"));
        // polio
        vaccineRulesDAO.addVaccine(new DBVaccineRules(32 * Helper.get1MonthLong(),"3"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(33 * Helper.get1MonthLong(),"3"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(34 * Helper.get1MonthLong(),"3"));
        // Hepatitis B
        vaccineRulesDAO.addVaccine(new DBVaccineRules(35 * Helper.get1MonthLong(),"4"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(38 * Helper.get1MonthLong(),"4"));
        vaccineRulesDAO.addVaccine(new DBVaccineRules(37 * Helper.get1MonthLong(),"4"));
        // MMR (1st) birthday
        vaccineRulesDAO.addVaccine(new DBVaccineRules(12 * Helper.get1MonthLong(),"5"));
        // Varicella (1st) birthday
        vaccineRulesDAO.addVaccine(new DBVaccineRules(13 * Helper.get1MonthLong(),"6"));

        // DTaP 4th birthday
        vaccineRulesDAO.addVaccine(new DBVaccineRules(48 * Helper.get1MonthLong(),"1"));

        // Polio 4th birthday
        vaccineRulesDAO.addVaccine(new DBVaccineRules(49 * Helper.get1MonthLong(),"3"));

        // MMR 1st birthday
        vaccineRulesDAO.addVaccine(new DBVaccineRules(14 * Helper.get1MonthLong(),"5"));

        // Varicella 1st birthday 2nd dose
        vaccineRulesDAO.addVaccine(new DBVaccineRules(15 * Helper.get1MonthLong(),"6"));

        vaccineRulesDAO.getAllVaccinationRules().stream().forEach(System.out::println);
    }
}
