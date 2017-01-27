package persons;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
 * A stub for interface IPersonDB, which does provide RAM persistency but no long-term persistency.
 * @author Charlotte Lecluze and Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France
 * @since February, 2013
 */
public class PersonDB implements IPersonDB {

    /** The list of persons itself (without passwords). */
    protected List<Person> persons;

    /** A list of passwords, in the same order as associated persons in {@link #persons}. */
    protected List<String> passwords;

    private SessionFactory sessionFactory;
    /**
     * Builds a new list of persons for testing.
     * @author Pierre Labadille, Yoann Boyer
     */


    // Handling Hibernate sessions ===================================================

    protected void initialize () throws InvalidMappingException {
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

    protected void close () throws HibernateException {
        if (this.sessionFactory!=null) {
            this.sessionFactory.close();
        }
    }

    @Override
    public void create (Person p) throws IllegalArgumentException {
        Session session = sessionFactory.openSession();
        Query query=session.createQuery("from Person");
        if (((List<Person>)query.list()).isEmpty()) {
            Transaction transaction=null;
            try {
                transaction=session.beginTransaction();
                session.save(new Person(p));
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
    }

    @Override
    public Collection<Person> getAll () {
        Session session=sessionFactory.openSession();
        Collection<Person> allPersons = null;
        try {
            Query query=session.createQuery("from Person");
            allPersons=(List<Person>)query.list();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return allPersons;
    }

    @Override
    public Collection<String> getAllEmails () {
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        List<String> emails = null;
        try {
            transaction=session.beginTransaction();
            Query query=session.createQuery("from Person");
            List<Person> allPersons=(List<Person>)query.list();
            for (Person person: allPersons) {
                emails.add(person.getEmail());
            }
            assert allProducts.size()==2;
            transaction.commit();
        } catch (Exception e) {
            if (transaction!=null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return emails;
    }

    @Override
    public Person find (String email) {

        Session session=sessionFactory.openSession();
        try{
            Query query=session.createQuery("from Person where email='"+email+"'");
            Person person = (Person)query.uniqueResult();
        } catch (Exception e){
            return null;
        } finally {
            session.close();
        }
        return person;
    }

    @Override
    public boolean isValid (String email, String password) {
        try{
            Person person = find(email);
        } catch (Exception e){
            return false;
        }
        return person.getPassword().equals(md5(password));
    }

    @Override
    public void update (String email, Person person) throws HibernateException {
        try{
            Person dbPerson = find(email);
        } catch (Exception e){
            throw e;
        }
        dbPerson=person;
        Session session=sessionFactory.openSession();
        session.update(dbPerson);
    }

    @Override
    public void updatePassword (String email, String password) throws IndexOutOfBoundsException {
        try{
            Person dbPerson = find(email);
        } catch (Exception e){
            throw e;
        }
        dbPerson.setPassword(password);
        Session session=sessionFactory.openSession();
        session.update(dbPerson);
    }

    //Can bug if there is housing attached to the person, need to be verified
    @Override
    public void delete (String email) throws IndexOutOfBoundsException {
        try{
            Person dbPerson = find(email);
        } catch (Exception e){
            throw e;
        }
        Session session=sessionFactory.openSession();
        session.delete(dbPerson);
    }

}
