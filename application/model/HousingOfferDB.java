package model;

import java.util.List;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.InvalidMappingException;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * A Database manager based on Hibernate for managing HousingOffer objects.
 * @since February, 2017
 */
public class HousingOfferDB {

    private SessionFactory sessionFactory;

    /**
    * Handling Hibernate sessions
    * @throw InvalidMappingException
    */
    public void initialize() throws InvalidMappingException {
        ServiceRegistry serviceRegistry = null;
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            serviceRegistry = builder.build();
            this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            System.out.println("Erreur lors de l'initialisation de la session factory: "+e);
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
            throw e;
        }
    }

    /**
    * Close Hibernate session
    * @throw HibernateException
    */
    protected void close() throws HibernateException {
        if (this.sessionFactory!=null) {
            this.sessionFactory.close();
        }
    }

    /**
    * Create a new entry in db
    * @throw IllegalArgumentException
    * @param <HousingOffer>
    */
    public void create(HousingOffer hOffer) throws IllegalArgumentException {
        Session session = sessionFactory.openSession();
        Query query=session.createQuery("from HousingOffer");
        Transaction transaction=null;

        try {
            transaction=session.beginTransaction();
            session.save(hOffer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    /**
    * Return evevery HousingOffers in the state 2 or 3 (available or booked).
    * Used by the administration panel
    * @throw Exception
    * @return Collection<HousingOffer>
    */
    public Collection<HousingOffer> getAllOffers() {
        Session session=sessionFactory.openSession();
        Collection<HousingOffer> allOffers = null;

        try {
            Query query=session.createQuery("from HousingOffer");
            Collection<HousingOffer> allHousing = (List<HousingOffer>)query.list();

            if (!allHousing.isEmpty()) {
                for (HousingOffer housing : allHousing) {
                    if (housing.isAvailable() || housing.isBooked()) {
                        allOffers.add(housing);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }

        return allOffers;
    }

    /**
    * Return evevery HousingOffers in the state 2 (available).
    * Used by the search module
    * @throw Exception
    * @return Collection<HousingOffer>
    */
    public Collection<HousingOffer> getAllAvailableOffers() {
        Session session=sessionFactory.openSession();
        Collection<HousingOffer> allOffers = null;

        try {
            Query query=session.createQuery("from HousingOffer");
            Collection<HousingOffer> allHousing =(List<HousingOffer>)query.list();

            if (!allHousing.isEmpty()) {
                for (HousingOffer housing : allHousing) {
                    if (housing.isAvailable()) {
                        allOffers.add(housing);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return allOffers;
    }

    /**
    * Return evevery housing belonging to a given user.
    * Used by the accountManagement module
    * @throw Exception
    * @param <long> id (person)
    * @return Collection<HousingOffer>
    */
    public Collection<HousingOffer> getAllUserHousing(long id) {
        Session session=sessionFactory.openSession();
        List<HousingOffer> housing = null;

        try{
            Query query=session.createQuery("from HousingOffer where idOwner='"+id+"'");
            housing = (List<HousingOffer>)query.list();
        } catch (Exception e){
            throw e;
        } finally {
            session.close();
        }

        return housing;
    }

    /**
    * Return evevery reservation made by a given user.
    * Used by the accountManagement module
    * @throw Exception
    * @param <long> id (person)
    * @return Collection<HousingOffer>
    */
    public Collection<HousingOffer> getAllUserReservation(long id) {
        Session session=sessionFactory.openSession();
        List<HousingOffer> housingReservation = null;

        try{
            Query query=session.createQuery("from HousingOffer where idGuest='"+id+"'");
            housingReservation = (List<HousingOffer>)query.list();
        } catch (Exception e){
            throw e;
        } finally {
            session.close();
        }

        return housingReservation;
    }

    /**
    * Update an existing HousingOffer in db
    * @throw HibernateException
    * @param <long> id (person)
    */
    public void update(HousingOffer housingOffer) throws HibernateException {
        Session session=sessionFactory.openSession();

        try{
            Transaction tx = session.beginTransaction();
            session.update(housingOffer);
            tx.commit();
        } catch (Exception e){
            throw e;
        } finally {
            session.close();
        }
    }

    /**
    * Delete a HousingOffer in db
    * @throw IndexOutOfBoundsException
    * @param <HousingOffer>
    */
    public void delete(HousingOffer housingOffer) throws IndexOutOfBoundsException {
        Session session=sessionFactory.openSession();

        try{
            Transaction tx = session.beginTransaction();
            session.delete(housingOffer);
            tx.commit();
        } catch (Exception e){
            throw e;
        } finally {
            session.close();
        }
    }

}
