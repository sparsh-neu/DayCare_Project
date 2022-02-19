package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ui_DayCare.model.db.DBVaccineRules;
import ui_DayCare.model.db.DBVaccines;
import ui_DayCare.utils.HibernateUtil;

import java.util.List;

public class VaccineRulesDAO {
    public void deleteTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DBVaccineRules").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addVaccine(DBVaccineRules rule) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(rule);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DBVaccineRules> getAllVaccinationRules() {
        List<DBVaccineRules> rules = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<DBVaccineRules> query = session.createQuery("FROM DBVaccineRules");
            rules = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rules;
    }
}