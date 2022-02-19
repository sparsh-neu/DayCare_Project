package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.stat.SessionStatistics;
import ui_DayCare.model.Employee;
import ui_DayCare.model.Teacher;
import ui_DayCare.model.db.DBReview;
import ui_DayCare.model.db.DBTeacher;
import ui_DayCare.model.db.DBTeacherAvgReview;
import ui_DayCare.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherDAO {
    public boolean addTeacher(Teacher teacher) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            DBTeacher dbTeacher = new DBTeacher(teacher);
            session.save(dbTeacher);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Employee> getAllTeachers() {
        List<Employee> employees = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM DBTeacher", DBTeacher.class);
            List<DBTeacher> dbTeachers = query.list();
            employees = dbTeachers.stream().map(x -> new Teacher(x)).collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    public String[][] getAllTeachersAsStringArray() {
        List<Employee> employees = getAllTeachers();
        List<String[]> employeeArray = employees.stream().map(x -> ((Teacher) x).toStringArray()).collect(Collectors.toList());
        return employeeArray.toArray(new String[0][0]);
    }

    public String[][] getAllTeacherAvgReviewStrArr(Long from, Long to) {
        List<String[]> stringList = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // first name, last name, avg rating, next review date
            Query<DBTeacherAvgReview> query = session.createQuery("SELECT NEW ui_DayCare.model.db.DBTeacherAvgReview(t.firstName, t.lastName, avg(r.rating)) " +
                    "FROM DBTeacher t, DBReview r " +
                    "WHERE t.id = r.personId AND r.reviewDate >= :from_date and r.reviewDate <= :to_date " +
                    "GROUP BY t.lastName, t.firstName", DBTeacherAvgReview.class);
            query.setParameter("from_date", from);
            query.setParameter("to_date", to);
            List<DBTeacherAvgReview> dbTeacherAvgReviews = query.list();
            dbTeacherAvgReviews.stream().forEach(x -> stringList.add(x.getStringArray()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stringList.toArray(new String[0][0]);
    }
}
