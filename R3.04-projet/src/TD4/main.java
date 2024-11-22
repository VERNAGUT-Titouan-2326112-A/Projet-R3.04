package TD4;

public class main {
    public static void main(String[] args) {
        // Initialisation de la colonie et des meutes
        Colonie colonie = new Colonie();
        Meute meute1 = new Meute();

        Lycanthrope alphaM = new LycanthropeFactoryImpl().creerLycanthrope("Mâle", "Adulte");
        Lycanthrope alphaF = new LycanthropeFactoryImpl().creerLycanthrope("Femelle", "Adulte");
        meute1.setMaleAlpha(alphaM);
        meute1.setFemelleAlpha(alphaF);

        Lycanthrope betaM = new LycanthropeFactoryImpl().creerLycanthrope("Mâle", "Adulte");
        Lycanthrope omegaF = new LycanthropeFactoryImpl().creerLycanthrope("Femelle", "Adulte");

        meute1.ajouterMembre(betaM);
        meute1.ajouterMembre(omegaF);

        colonie.ajouterMeute(meute1);


        GestionnaireDeSaisons gestionnaireDeSaisons = new GestionnaireDeSaisons(5000,meute1); // Une saison dure 5 secondes

        gestionnaireDeSaisons.demarrer();

        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000); // Pause de 1 seconde
                if (i % 5 == 0) { // Tous les 5 secondes
                    meute1.ajusterPourSaison(gestionnaireDeSaisons.getSaisonActuelle());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gestionnaireDeSaisons.arreter();
        // Planification des commandes
        colonie.planifierConflit(betaM, omegaF);
        colonie.planifierReproduction(meute1);

        // Exécution des actions
        colonie.executerActions();
    }
}
