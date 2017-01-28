package persons;

import java.util.Collection;

/**
 * A class for running some basic tests on classes which implement interface IPersonDB.
 * @author Charlotte Lecluze and Bruno Zanuttini, Universit&eacute; de Caen Basse-Normandie, France
 * @since February, 2013
 */
public class TestPersonDB {

    /**
     * Runs a series of tests on an instance of a class which implements IPersonDB.
     * The instance is assumed to represent an empty database of persons when passed to
     * this method. If tests go well, the database is empty again when the method exits.
     * The method uses assertions to run tests.
     * @param instance An instance of the class to be tested, representing an empty
     * database of persons
     * @throws Exception if an unexpected error occurs
     */
    public static void test (IPersonDB instance) throws Exception {
        
        instance.create(new Person("Dupont","Marie","marie.dupont@mail.fr"));

        Person test1 = instance.find("marie.dupont@mail.fr");

        System.out.println(test1.toString());

    }

}
