package TD4;

import java.util.ArrayList;
import java.util.List;

public class Colonie {
    private List<Meute> meutes;
    private GestionnaireCommandes gestionnaireCommandes;

    public Colonie() {
        this.meutes = new ArrayList<>();
        this.gestionnaireCommandes = new GestionnaireCommandes();
    }

    public void ajouterMeute(Meute meute) {
        meutes.add(meute);
    }

    public void planifierConflit(Lycanthrope agresseur, Lycanthrope cible) {
        gestionnaireCommandes.ajouterCommande(new ConflitCommand(agresseur, cible, meutes.get(0)));
    }

    public void planifierReproduction(Meute meute) {
        gestionnaireCommandes.ajouterCommande(new ReproductionCommand(meute));
    }

    public void executerActions() {
        System.out.println("Exécution des actions planifiées...");
        gestionnaireCommandes.executerCommandes();
    }
}
