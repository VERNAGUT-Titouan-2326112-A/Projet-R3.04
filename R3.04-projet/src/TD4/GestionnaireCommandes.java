package TD4;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireCommandes {
    private List<Command> commandes = new ArrayList<>();

    public void ajouterCommande(Command commande) {
        commandes.add(commande);
    }

    public void executerCommandes() {
        for (Command commande : commandes) {
            commande.execute();
        }
        commandes.clear();
    }
}
