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
public class PersonDBStub implements IPersonDB {

    /** The list of persons itself (without passwords). */
    protected List<Person> persons;

    /** A list of passwords, in the same order as associated persons in {@link #persons}. */
    protected List<String> passwords;

    private SessionFactory sessionFactory;
    /**
     * Builds a new list of persons for testing.
     * @author Pierre Labadille, Yoann Boyer
     */
    public PersonDB () {

    	// this.persons = new ArrayList<Person> ();
        // this.passwords = new ArrayList<String> ();
        //
    	// // nom<String> + prénom<String> + email<unique> + password<hash>
    	// String[] name = new String[] {
    	// 	"Serrano", "Rodgers", "Coleman", "Garza", "Nolan", "Mcguire", "Campbell", "Elliott", "Koch", "Harris", "Wells", "Kline", "Washington", "Fischer", "Fowler", "Morin", "Lott", "Henry", "Guzman", "Harrington", "Todd", "Franklin", "Mendez", "Barrera", "Gay", "Calhoun", "Short", "Herman", "Donovan", "Marquez", "Mccullough", "Battle", "Williams", "Phelps", "Vaughn", "Gilliam", "Weaver", "Burke", "Conrad", "Hanson", "Armstrong", "Goodwin", "Fox", "Acevedo", "Hunt", "Middleton", "Hewitt", "Bender", "Soto", "Huber", "Diaz", "Torres", "Horne", "Vaughn", "Ewing", "Little", "Bright", "Richard", "Hurley", "Hogan", "Ferrell", "Acevedo", "Kidd", "Howard", "Silva", "Sosa", "Rose", "Osborn", "Small", "Browning", "Foster", "Payne", "Cruz", "Blankenship", "Gonzales", "Chandler", "Dyer", "Vega", "Holland", "Guzman", "Delaney", "Wiley", "Cook", "Savage", "Rose", "Puckett", "Doyle", "Pace", "Perkins", "Huff", "Griffin", "Burke", "Melton", "Holder", "Whitney", "Britt", "Nixon", "Slater", "Parrish", "Galloway"
        // };
        // String[] firstName = new String[] {
    	// 	"Adena", "Kellie", "Ori", "Emmanuel", "Macon", "Lila", "Moses", "Shea", "Micah", "Kieran", "Sheila", "Nadine", "Francis", "Hop", "Marvin", "Imelda", "Miranda", "Vance", "Mallory", "Avye", "Clare", "Hayfa", "Sonia", "Forrest", "Dante", "Rhiannon", "TaShya", "Vielka", "Illana", "Denise", "Basil", "Phyllis", "Wesley", "Tamara", "Carly", "Celeste", "Cullen", "Isaac", "Macey", "Chancellor", "Aileen", "Maris", "Isadora", "Charlotte", "Maris", "Lane", "Yvonne", "Kylan", "Chiquita", "Beatrice", "Sandra", "Yuli", "Jasmine", "Georgia", "Cailin", "Malcolm", "Murphy", "Karleigh", "Briar", "Malik", "Blythe", "Raven", "Isaac", "Raymond", "Christian", "Josephine", "Eric", "Adrienne", "Sydnee", "Erich", "Gregory", "Keelie", "Kalia", "Mallory", "Allistair", "Nita", "Alice", "Halee", "Maggie", "Jameson", "Ciaran", "Jonas", "Wang", "Fritz", "Jordan", "Kimberly", "Simon", "Grant", "Maya", "Drew", "Casey", "Quemby", "Daniel", "Hiram", "Idola", "Holly", "Amanda", "Kerry", "Dahlia", "Keane"
        // };
        // String[] email = new String [] {
    	// 	"fames.ac@consequatauctor.org", "mollis.Integer@Aliquam.com", "turpis@leoin.edu", "nec.ante.Maecenas@fringillaornare.ca", "arcu@blandit.edu", "lacus@aodio.com", "urna@Maurisvelturpis.co.uk", "ultricies@Ut.ca", "suscipit.nonummy.Fusce@necurna.ca", "tellus.non@lectus.net", "semper.dui@acmieleifend.net", "quis.pede.Praesent@sem.ca", "purus.sapien.gravida@arcu.ca", "inceptos.hymenaeos.Mauris@diamlorem.ca", "urna.Ut.tincidunt@Pellentesquetincidunttempus.edu", "risus.Quisque@cursusNuncmauris.co.uk", "tempus.scelerisque.lorem@vitaeorci.co.uk", "arcu@tristiquenequevenenatis.net", "lectus.pede.et@bibendumullamcorper.org", "fermentum.convallis@Suspendisseac.edu", "eget.mollis.lectus@variusultricesmauris.co.uk", "mauris@mifelis.net", "semper.pretium.neque@seddictumeleifend.ca", "Aliquam@variusultricesmauris.org", "eu.tellus@atfringilla.edu", "ut@nequeMorbi.net", "arcu@Aenean.net", "sem@metus.com", "Fusce@eratneque.net", "magna@vitae.edu", "Ut.sagittis.lobortis@feugiattellus.com", "vel@congueelitsed.co.uk", "lobortis.quam@auctorodio.com", "sapien.molestie.orci@nuncIn.edu", "ultricies.sem.magna@auguemalesuada.co.uk", "Phasellus@consequat.net", "In@massaSuspendisseeleifend.com", "purus.ac@turpisnon.com", "semper.et.lacinia@volutpatNulladignissim.net", "elit.Etiam.laoreet@mollisdui.net", "Vivamus@dolorFuscemi.com", "sed.est.Nunc@torquent.org", "Lorem@vel.co.uk", "parturient.montes.nascetur@ligula.edu", "ante.dictum.mi@aliquet.org", "massa.Integer@lobortis.com", "Pellentesque.ultricies@convallisin.ca", "Vivamus.euismod@Phasellusfermentumconvallis.ca", "tristique.senectus@ridiculusmusDonec.org", "et.tristique@leoelementum.com", "Phasellus.vitae@Etiamligula.org", "erat.vel@euaccumsansed.co.uk", "at@Nunccommodoauctor.org", "sem@ategestas.co.uk", "Nunc.ac@tempordiamdictum.co.uk", "commodo.at@Proinnon.com", "dui.semper@felisadipiscing.ca", "penatibus.et@tempordiam.com", "diam@semperNam.ca", "mattis.Cras.eget@maurisSuspendissealiquet.ca", "ut.dolor@tristiquesenectus.com", "eu@ornaresagittisfelis.co.uk", "malesuada@mus.com", "dignissim.magna.a@magna.co.uk", "Ut.tincidunt@pharetraQuisque.ca", "Duis.ac.arcu@vulputatemaurissagittis.org", "odio.tristique.pharetra@purusMaecenaslibero.net", "sagittis@maurisid.org", "magnis.dis@NuncmaurisMorbi.edu", "dictum.mi@mollisvitae.com", "luctus.et.ultrices@Integer.org", "blandit.viverra.Donec@Phasellus.net", "Ut@Curae.edu", "Cras.convallis@NullamnislMaecenas.org", "lorem.eu@ornareegestas.com", "Morbi@faucibus.ca", "erat.vel@sapienCrasdolor.edu", "pharetra@inmagna.edu", "sodales.nisi.magna@sodalesatvelit.net", "tincidunt@egetnisidictum.com", "odio@in.com", "tellus@eu.com", "tincidunt.congue@malesuadautsem.co.uk", "et@ultriciessem.org", "sit.amet.faucibus@utaliquam.ca", "ac@pretiumaliquetmetus.net", "orci.lobortis.augue@ipsumPhasellus.ca", "natoque.penatibus.et@lorem.org", "Curabitur.ut.odio@inaliquetlobortis.ca", "ultrices.a.auctor@mattisCraseget.org", "feugiat.tellus@quam.net", "orci.consectetuer.euismod@egettinciduntdui.edu", "a@Proinvelarcu.co.uk", "vestibulum.neque.sed@aliquam.edu", "semper.cursus.Integer@Pellentesquehabitant.edu", "tincidunt.Donec.vitae@id.com", "sit.amet.ornare@urnaconvalliserat.co.uk", "Lorem@malesuadaIntegerid.ca", "interdum.libero@risusDonec.org", "lorem.Donec@DonectinciduntDonec.org"
        // };
        //
        // for (int i = 0; i < 100; i++) {
        // 	this.persons.add(new Person(name[i], firstName[i], email[i]));
        // 	String hash = passFromEmail(email[i]);
        // 	this.passwords.add(hash);
        // }
    }


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
    public Person find (String email) throws IndexOutOfBoundsException {
        for (Person p: this.persons) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        throw new IndexOutOfBoundsException("No person with email "+email);
    }

    @Override
    public boolean isValid (String email, String password) {
        int i;
        for (i=0; i<this.persons.size(); i++) {
            if (this.persons.get(i).getEmail().equals(email)) {
                break;
            }
        }
        if (i==this.persons.size()) {
            return false;
        }
        return this.passwords.get(i).equals(md5(password));
    }

    @Override
    public boolean exists (String email) {
        for (Person p: this.persons) {
            if (p.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update (String email, Person person) throws IndexOutOfBoundsException {
        int index=-1;
        for (int i=0; i<this.persons.size(); i++) {
            if (this.persons.get(i).getEmail().equals(email)) {
                index=i;
                break;
            }
        }
        if (index==-1) {
            throw new IndexOutOfBoundsException("No person with email "+email);
        }
        this.persons.set(index,person);
    }

    @Override
    public void updatePassword (String email, String password) throws IndexOutOfBoundsException {
        int index=-1;
        for (int i=0; i<this.persons.size(); i++) {
            if (this.persons.get(i).getEmail().equals(email)) {
                index=i;
                break;
            }
        }
        if (index==-1) {
            throw new IndexOutOfBoundsException("No person with email "+email);
        }
        this.passwords.set(index,password);
    }

    @Override
    public void delete (String email) throws IndexOutOfBoundsException {
        int index=-1;
        for (int i=0; i<this.persons.size(); i++) {
            if (this.persons.get(i).getEmail().equals(email)) {
                index=i;
                break;
            }
        }
        if (index==-1) {
            throw new IndexOutOfBoundsException("No person with email "+email);
        }
        this.persons.remove(index);
    }

}
