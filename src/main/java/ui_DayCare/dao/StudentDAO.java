package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ui_DayCare.model.AnnualEnrollmentStat;
import ui_DayCare.model.Student;
import ui_DayCare.model.db.DBStudent;
import ui_DayCare.utils.Helper;
import ui_DayCare.utils.HibernateUtil;

import java.util.*;
import java.util.stream.Collectors;

public class StudentDAO {

    public boolean addStudent(String firstName, String lastName,
                              String emergencyContactName, String emergencyContactNumber, String address,
                              Long dateOfBirth, Long registrationDate) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new DBStudent(firstName, lastName, emergencyContactName,
                    emergencyContactNumber, address, dateOfBirth, registrationDate));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void addStudent(Student student) {
        addStudent(student.getFirstName(),
                student.getLastName(),
                student.getGetEmergencyContactName(),
                student.getEmergencyContactNumber(),
                student.getAddress(),
                student.getDateOfBirth(),
                student.getRegistrationDate());
    }

    // TODO: Get All Students
    // TODO: Get Students Under Teacher
    // TODO: Get Students In A Group //Declare an Enums for groups//
    // TODO: Get Students With Upcoming Immunizations
    public List<Student> getStudents() {
        List<Student> students = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from DBStudent", DBStudent.class);
            List<DBStudent> dbStudents = query.list();
            students = dbStudents.stream().map(x -> new Student((DBStudent) x)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public String[][] getAllStudentsAsStringArray() {
        List<Student> students = getStudents();
        List<String[]> studentArray = students.stream().map(x -> ((Student) x).toStringArray()).collect(Collectors.toList());
        return studentArray.toArray(new String[0][0]);
    }

    private HashMap<String, AnnualEnrollmentStat> countEnrollmentInMap(HashMap<String, AnnualEnrollmentStat> map, String year, boolean enroll) {
        if (!map.containsKey(year)) map.put(year, new AnnualEnrollmentStat(year));
        if (enroll) {
            map.get(year).incrementEnrollment();
        } else {
            map.get(year).incrementUnEnrollment();
        }
        return map;
    }

    public List<AnnualEnrollmentStat> getAnnualEnrollments() {
        List<Student> students = getStudents();
        List<AnnualEnrollmentStat> annualEnrollmentStats = new ArrayList<>();
        HashMap<String, AnnualEnrollmentStat> annualEnrollmentStatsMap = new HashMap<>();
        for (Student s: students) {
            String regYear = Helper.getStringYear(s.getRegistrationDate());
            String exitYear = Helper.getStringYear(s.getDateOfBirth() + Helper.get60MonthEpochOffset());
            annualEnrollmentStatsMap = countEnrollmentInMap(annualEnrollmentStatsMap, regYear, true);
            annualEnrollmentStatsMap = countEnrollmentInMap(annualEnrollmentStatsMap, exitYear, false);
        }
        annualEnrollmentStatsMap.values().stream().forEach(x -> annualEnrollmentStats.add(x));
        return annualEnrollmentStats;
    }

    public void deleteTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DBStudent").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public boolean updateStudent(String immunizationId, Immunization immunization) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update Immunization i set i.administrationDate = :administration_date, i.dueDate = :due_date, i.dosesSoFar = :doses_so_far where i.id = :id");
            query.setParameter("id", immunizationId);
            query.setParameter("administration_date", immunization.getAdministrationDate());
            query.setParameter("due_date", immunization.getDueDate());
            query.setParameter("doses_so_far", immunization.getDosesSoFar());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }*/
}
