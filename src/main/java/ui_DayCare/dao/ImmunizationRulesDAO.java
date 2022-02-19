package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ui_DayCare.model.db.DBImmunizationRules;
import ui_DayCare.utils.HibernateUtil;

public class ImmunizationRulesDAO {
    public boolean populateImmunizationRules() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.createQuery("DELETE DBImmunizationRules").executeUpdate();
            session.save(new DBImmunizationRules("1", "Hib", 4, Long.valueOf(24), Long.valueOf(60)));
            session.save(new DBImmunizationRules("2", "DTaP", 4, Long.valueOf(24), Long.valueOf(60)));
            session.save(new DBImmunizationRules("3", "Polio", 3, Long.valueOf(24), Long.valueOf(60)));
            session.save(new DBImmunizationRules("4", "Hepatitis B", 3, Long.valueOf(24), Long.valueOf(60)));
            session.save(new DBImmunizationRules("5", "MMR", 1, Long.valueOf(24), Long.valueOf(60)));
            session.save(new DBImmunizationRules("6", "Varicella", 1, Long.valueOf(24), Long.valueOf(60)));
            session.save(new DBImmunizationRules("7", "DTaP", 5, Long.valueOf(61), Long.valueOf(84)));
            session.save(new DBImmunizationRules("8", "Polio", 4, Long.valueOf(61), Long.valueOf(84)));
            session.save(new DBImmunizationRules("9", "Hepatitis B", 3, Long.valueOf(61), Long.valueOf(84)));
            session.save(new DBImmunizationRules("10", "MMR", 2, Long.valueOf(61), Long.valueOf(84)));
            session.save(new DBImmunizationRules("11", "Varicella", 2, Long.valueOf(61), Long.valueOf(84)));
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void printAllRules() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // save the student objects
            session.createQuery("from DBImmunizationRules", DBImmunizationRules.class).list().stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
