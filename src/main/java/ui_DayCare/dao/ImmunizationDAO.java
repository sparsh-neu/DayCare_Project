package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ui_DayCare.model.Immunization;
import ui_DayCare.utils.HibernateUtil;

import java.util.List;

public class ImmunizationDAO {

    public boolean addImmunization(String personId, String immunization_rule_id, Long administrationDate, Long dueDate, Integer dosesSoFar) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new Immunization(immunization_rule_id, personId, administrationDate, dueDate, dosesSoFar));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Immunization> getImmunizations(String personId) {
        List<Immunization> immunizations = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from DBImmunization where personId = :person_id", Immunization.class);
            query.setParameter("person_id", personId);
            immunizations = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return immunizations;
    }

    public boolean updateImmunization(String immunizationId, Immunization immunization) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update DBImmunization i set i.administrationDate = :administration_date, i.dueDate = :due_date, i.dosesSoFar = :doses_so_far where i.id = :id");
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
    }
}
