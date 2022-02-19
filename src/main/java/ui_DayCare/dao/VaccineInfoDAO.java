package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ui_DayCare.model.UpcomingVaccineInfo;
import ui_DayCare.model.db.DBStudentVaccineMap;
import ui_DayCare.model.db.DBVaccineInfo;
import ui_DayCare.utils.Helper;
import ui_DayCare.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VaccineInfoDAO {
    public void deleteTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DBVaccineInfo").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addVaccine(DBVaccineInfo info) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(info);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DBVaccineInfo> getAllVaccinationInfo() {
        List<DBVaccineInfo> rules = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<DBVaccineInfo> query = session.createQuery("FROM DBVaccineInfo");
            rules = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }

    public String[][] getAllPendingVaccinationInfo(Long currentDate) {
        VaccineInfoDAO vaccineInfoDAO = new VaccineInfoDAO();
        List<String[]> list = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<DBStudentVaccineMap> query = session.createQuery("SELECT new ui_DayCare.model.db.DBStudentVaccineMap(s.firstName, s.lastName, r.vaccineID)  " +
                    "FROM DBStudent s, DBVaccineRules r " +
                    "WHERE :current_date - s.dateOfBirth <= r.month AND :current_date - s.dateOfBirth + :one_year < r.month ");
            query.setParameter("current_date", currentDate);
            query.setParameter("one_year", 12 * Helper.get1MonthLong());
            List<DBStudentVaccineMap> dbStudentVaccineMaps = query.list();
            Map<String, UpcomingVaccineInfo> map = new HashMap<>();

            for (DBStudentVaccineMap obj: dbStudentVaccineMaps) {
                String key = obj.getFirstName() + "#" + obj.getLastName();
                if (!map.containsKey(key)) {
                    map.put(key, new UpcomingVaccineInfo(obj.getFirstName(), obj.getLastName()));
                }
                if (obj.getVaccineId().equals("1")) map.get(key).setHib(true);
                else if (obj.getVaccineId().equals("2")) map.get(key).setTdap(true);
                else if (obj.getVaccineId().equals("3")) map.get(key).setPolio(true);
                else if (obj.getVaccineId().equals("4")) map.get(key).setHepatitis(true);
                else if (obj.getVaccineId().equals("5")) map.get(key).setMmr(true);
                else if (obj.getVaccineId().equals("6")) map.get(key).setVaricella(true);
            }
            list = map.values().stream().map(x -> x.getStringArray()).collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list.toArray(new String[0][0]);
    }
}
