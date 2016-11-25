package logements;

import java.util.*;

public class LogementDBStub implements ILogementDB {

    protected List<Logement> logements = new ArrayList<Logement> ();

    public LogementDBStub() {
        //init var random
        String[] pays = new String[] {
            "France", "Italie", "Australie", "Canada" 
        };
        String[] type = new String[] {
            "Appartement", "Maison"
        };
        String[] adresse = new String[] {
            "avenue de l'europe", "rue de l'espoir", "rue de la pigacière", "boulevard des belles portes", "quartier du palais", "la petite héroudière", "place monges", "rue Emile Zola", "rue BBC", "place de l'égalité"
        };
        int minSurface = 30;
        int maxSurface = 150;

        int minSurfaceJardin = 50;
        int maxSurfaceJardin = 600;

        int minPiece = 1;
        int maxPiece = 7;

        //init random
        long seed = 123456;
        Random rnd = new Random(seed);

        //generate
        for (int i = 0; i < 100; i++) {
            String typeTmp = type[rnd.nextInt(type.length)];
            int surfaceTmp = rnd.nextInt(maxSurface + 1) + minSurface;
            int nbPieceTmp = rnd.nextInt(maxPiece + 1) + minPiece;
            String paysTmp = pays[rnd.nextInt(pays.length)];
            String adresseTmp = i + ", " + adresse[rnd.nextInt(adresse.length)];

            if (typeTmp.equals("Appartement")) {
               this.logements.add(new Appartement(paysTmp, surfaceTmp, nbPieceTmp, adresseTmp)); 
            }

            if (typeTmp.equals("Maison")) {
                int surfaceJardinTmp = rnd.nextInt(maxSurfaceJardin) + minSurfaceJardin;
                this.logements.add(new Maison(paysTmp, surfaceTmp, nbPieceTmp, adresseTmp, surfaceJardinTmp));
            }
        }
    }

    public void addLogement (Logement logement) {
    	if (!exist(logement)) {
    		this.logements.add(logement);
    	} else {
    		throw new IllegalArgumentException("This adress is already use in database");
    	}
    }

    @Override
    public List<Logement> getAll () {
        return this.logements;
    }
    
    public boolean exist(Logement logement) {
    	logements = this.getAll();
    	for (Logement l : logements){
    		if (l.adresse == logement.adresse){
    			return true;
    		}
    	}
    	return false;
    }
    
    public Logement find(String adresse) {
    	for (Logement logement : this.logements) {
    		if (logement.adresse == adresse) {
    			return logement;
    		}
    	}
    	return null;
    }
    
    public void delete (String adresse) {
    	int index = -1;
    	int i = 0;
    	for (Logement logement : this.logements) {
    		if (logement.adresse == adresse) {
    			index = i;
    			break;
    		}
    		i++;
    	}
        if (index == -1) {
            throw new IndexOutOfBoundsException("No person with adress: "+adresse);
        }
        this.logements.remove(index);
    }
}