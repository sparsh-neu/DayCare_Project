package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ui_DayCare.model.db.DBVaccines;
import ui_DayCare.utils.HibernateUtil;

import java.util.List;

public class VaccinesDAO {
    public void deleteTable() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM DBVaccines").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addVaccine(DBVaccines vaccine) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(vaccine);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DBVaccines> getAllVaccines() {
        List<DBVaccines> vaccines = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<DBVaccines> query = session.createQuery("FROM DBVaccines");
            vaccines = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vaccines;
    }
}