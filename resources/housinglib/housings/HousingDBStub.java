/**
 * A class to work without database (hibernate)
 * @author Pierre Labadille, Yoann Boyer
 * @since 2016-11-18
 */

package housings;

import java.util.*;

public class HousingDBStub implements IHousingDB {

    protected List<Housing> housings = new ArrayList<Housing> ();

    public HousingDBStub() {
        //init var random
        String[] country = new String[] {
            "France", "Italie", "Australie", "Canada"
        };
        String[] type = new String[] {
            "Apartment", "Home"
        };
        String[] address = new String[] {
            "avenue de l'europe", "rue de l'espoir", "rue de la pigacière", "boulevard des belles portes", "quartier du palais", "la petite héroudière", "place monges", "rue Emile Zola", "rue BBC", "place de l'égalité"
        };
        int minSurface = 30;
        int maxSurface = 150;

        int minGardenSurface = 50;
        int maxGardenSurface = 600;

        int minPiece = 1;
        int maxPiece = 7;

        //init random
        long seed = 123456;
        Random rnd = new Random(seed);

        //generate
        for (int i = 0; i < 100; i++) {
            String typeTmp = type[rnd.nextInt(type.length)];
            int surfaceTmp = rnd.nextInt(maxSurface + 1) + minSurface;
            int nbRoomTmp = rnd.nextInt(maxPiece + 1) + minPiece;
            String countryTmp = country[rnd.nextInt(country.length)];
            String addressTmp = i + ", " + address[rnd.nextInt(address.length)];

            if (typeTmp.equals("Apartment")) {
               this.housings.add(new Apartment(countryTmp, surfaceTmp, nbRoomTmp, addressTmp));
            }

            if (typeTmp.equals("Home")) {
                int surfaceJardinTmp = rnd.nextInt(maxGardenSurface) + minGardenSurface;
                this.housings.add(new Home(countryTmp, surfaceTmp, nbRoomTmp, addressTmp, surfaceJardinTmp));
            }
        }
    }

    public void create (Housing housing) {
    	if (!exist(housing)) {
    		this.housings.add(housing);
    	} else {
    		throw new IllegalArgumentException("This adress is already use in database");
    	}
    }

    @Override
    public List<Housing> getAll () {
        return this.housings;
    }

    public boolean exist(Housing housing) {
    	housings = this.getAll();
    	for (Housing l : housings){
    		if (l.address == housing.address){
    			return true;
    		}
    	}
    	return false;
    }

    public Housing find(String address) {
    	for (Housing housing : this.housings) {
    		if (housing.address == address) {
    			return housing;
    		}
    	}
    	return null;
    }

    public void delete (Housing housing) {
    	int index = -1;
    	int i = 0;
    	for (Housing h : this.housings) {
    		if (h.address == housing.address) {
    			index = i;
    			break;
    		}
    		i++;
    	}
        if (index == -1) {
            throw new IndexOutOfBoundsException("No person with address: " + housing.address);
        }
        this.housings.remove(index);
    }
}
