package ui_DayCare.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ui_DayCare.model.Review;
import ui_DayCare.model.db.DBReview;
import ui_DayCare.utils.HibernateUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewDAO {
    public List<Review> getReviewsForEmployee(String employeeId) {
        List<Review> reviews = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM DBReview WHERE personId = :person_id", DBReview.class);
            query.setParameter("person_id", employeeId);
            List<DBReview> dbReviews = query.list();
            reviews = dbReviews.stream().map(x -> new Review(x)).collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public boolean addReviewForEmployee(Review review) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            DBReview dbReview = new DBReview(review);
            session.save(dbReview);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
