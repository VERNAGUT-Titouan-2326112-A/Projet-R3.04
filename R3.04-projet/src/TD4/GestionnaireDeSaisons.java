package TD4;

import java.util.Timer;
import java.util.TimerTask;

public class GestionnaireDeSaisons {
    private String[] saisons = {"Printemps", "Été", "Automne", "Hiver"};
    private int indexSaisonActuelle = 0;
    private int dureeSaison; // Durée en millisecondes d'une saison
    private Timer timer;
    private Meute meute;

    public GestionnaireDeSaisons(int dureeSaison, Meute meute) {
        this.dureeSaison = dureeSaison;
        this.timer = new Timer();
        this.meute = meute;
    }

    // Commence le cycle des saisons
    public void demarrer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changerSaison();
            }
        }, 0, dureeSaison);
    }

    // Arrête le cycle
    public void arreter() {
        timer.cancel();
    }

    // Change la saison actuelle
    private void changerSaison() {
        indexSaisonActuelle = (indexSaisonActuelle + 1) % saisons.length;
        String saisonActuelle = saisons[indexSaisonActuelle];
        System.out.println("La nouvelle saison est : " + saisonActuelle);
        notifierChangementDeSaison(saisonActuelle);
        int compteurMorts = 0;
        for (Lycanthrope lycanthrope : meute.getMembres()) {
            if (lycanthrope.getCategorieAge() == "Jeune") {
                lycanthrope.setCategorieAge("Adulte");
            }
            else if (lycanthrope.getCategorieAge() == "Adulte") {
                lycanthrope.setCategorieAge("Vieux");
            }
            else if (lycanthrope.getCategorieAge() == "Vieux") {
                meute.retirerMembre(lycanthrope);
                compteurMorts++;
            }
            System.out.println(compteurMorts + " lycanthropes sont morts cette saison.");
        }
    }

    // Notifie les autres systèmes (ex. : Meute, Lycanthrope) des changements de saison
    private void notifierChangementDeSaison(String saison) {
       Meute.ajusterPourSaison(saison);
    }

    public String getSaisonActuelle() {
        return saisons[indexSaisonActuelle];
    }
}

