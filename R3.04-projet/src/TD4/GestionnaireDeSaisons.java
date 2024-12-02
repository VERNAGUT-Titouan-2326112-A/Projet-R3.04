package TD4;

import java.util.*;

/**
 *
 * GestionnaireDeSaisons est une classe qui permet de gérer les saisons et les événements qui s'y déroulent.
 * Elle permet de changer de saison et d'appliquer des événements en fonction de la saison actuelle.
 * Elle permet aussi de démarrer et d'arrêter le cycle des saisons.
 * Elle contient une liste de meutes et une colonie.
 */

public class GestionnaireDeSaisons {
    private final String[] saisons = {"Printemps", "Été", "Automne", "Hiver"};
    private int indexSaisonActuelle = 0;
    private int dureeSaison; // Durée en millisecondes d'une saison
    private final Timer timer;
    private final List<Meute> meutes;
    private final Colonie colonie;

    /**
     * Constructeur de la classe GestionnaireDeSaisons
     * @param dureeSaison Durée en millisecondes d'une saison
     * @param meutes Liste des meutes
     * @param colonie Colonie
     */
    public GestionnaireDeSaisons(int dureeSaison, List<Meute> meutes, Colonie colonie) {
        this.dureeSaison = dureeSaison;
        this.timer = new Timer();
        this.meutes = meutes;
        this.colonie = colonie;
    }

    /**
     * Méthode permettant de démarrer le cycle des saisons
     */
    public void demarrer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changerSaison();
            }
        }, 0, dureeSaison);
    }

    /**
     * Méthode permettant d'arrêter le cycle des saisons
     */
    public void arreter() {
        timer.cancel();
    }

    /**
     * Méthode permettant de changer de saison et d'appliquer les événements associés
     */
    public void changerSaison() {
        indexSaisonActuelle = (indexSaisonActuelle + 1) % saisons.length;
        String saisonActuelle = saisons[indexSaisonActuelle];
        System.out.println("La nouvelle saison est : " + saisonActuelle);
        switch (saisonActuelle) {
            case "Printemps" -> {
                double rd = Math.random();
                if (rd < 0.35) {
                    for (Meute meute : meutes) {
                        ReproductionCommand reproductionCommande = new ReproductionCommand(meute);
                        reproductionCommande.execute();
                    }
                }

            }
            case "Été" -> {
                System.out.println("C'est l'été, les lycanthropes deviennent plus agressifs.");
                double proba = Math.random();
                if (proba < 0.25) {
                    for (Meute meute : meutes) {
                        Random random = new Random();
                        int index = random.nextInt(meute.getMembres().size());
                        Lycanthrope agresseur = meute.getMembres().get(index);
                        Lycanthrope cible = meute.getMembres().get(random.nextInt(meute.getMembres().size()));
                        ConflitCommand conflitCommand = new ConflitCommand(agresseur, cible , meute,colonie);
                        conflitCommand.execute();
                    }
                }
            }
            case "Automne" -> {
                System.out.println("En automne les lycanthropes ont plus de chances de devenir humain");
                for (Meute meute : meutes) {
                    List<Lycanthrope> membres = new ArrayList<>(meute.getMembres());
                    for (Lycanthrope lycanthrope : membres) {
                        double proba = Math.random();
                        if (proba < 0.15) {
                            lycanthrope.seTransformerEnHumain();
                        }
                    }
                }
            }

            case "Hiver" -> {
                for (Meute meute : meutes) {
                    int compteurMorts = 0;
                    List<Lycanthrope> membres = new ArrayList<>(meute.getMembres());
                    for (Lycanthrope lycanthrope : membres) {
                        switch (lycanthrope.getCategorieAge()) {
                            case "Jeune" -> {
                                lycanthrope.setCategorieAge("Adulte");
                                lycanthrope.setForce(lycanthrope.getForce() + lycanthrope.getForce() / 2);
                                lycanthrope.setFacteurImpetuosite(lycanthrope.getFacteurImpetuosite() + lycanthrope.getFacteurImpetuosite() / 2);
                                lycanthrope.setNiveau(lycanthrope.calculerNiveau());
                            }
                            case "Adulte" -> {
                                lycanthrope.setCategorieAge("Vieux");
                                lycanthrope.setForce(lycanthrope.getForce() - lycanthrope.getForce() / 3);
                                lycanthrope.setFacteurImpetuosite(lycanthrope.getFacteurImpetuosite() - lycanthrope.getFacteurImpetuosite() / 3);
                                lycanthrope.setNiveau(lycanthrope.calculerNiveau());
                            }
                            case "Vieux" -> {
                                meute.retirerMembre(lycanthrope);
                                compteurMorts++;
                            }
                        }

                    }
                    System.out.println(compteurMorts + " lycanthropes sont morts cette année dans la meute " + meute.getNom() + ".");
                }
            }
        }
    }

    /**
     * Méthode permettant de modifier la durée d'une saison
     * @param dureeSaison Durée en millisecondes d'une saison
     */
    public void setDureeSaison(int dureeSaison) {
        this.dureeSaison = dureeSaison;
    }

    public String getSaisonActuelle(){
        return this.saisons[indexSaisonActuelle];
    }

    public int getDureeSaison(){
        return dureeSaison ;
    }
}

