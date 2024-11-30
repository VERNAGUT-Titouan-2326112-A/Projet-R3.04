package TD4;

import java.util.ArrayList;
import java.util.List;

public class ReproductionCommand implements Command {
    private Meute meute;

    public ReproductionCommand(Meute meute) {
        this.meute = meute;
    }

    @Override
    public void execute() {
        if (meute.getMaleAlpha() != null && meute.getFemelleAlpha() != null) {
            System.out.println("Reproduction du couple α de la meute.");
            int cptBeta = 0;
            for (Lycanthrope lycanthrope : meute.getMembres()) {
                if (lycanthrope.getRang().equals("β")) {
                    cptBeta++;
                }
            }
            int nbNouveaux = (int) (Math.random() * 7 + 1); // Entre 1 et 7
            for (int i = 0; i < nbNouveaux; i++) {
                String sexe = Math.random() < 0.5 ? "Mâle" : "Femelle";
                meute.ajouterMembre(new LycanthropeFactoryImpl().creerLycanthrope(sexe, "Jeune"));
                if (cptBeta == 0) {
                    meute.getMembres().getLast().setRang("β");
                } else {
                    meute.getMembres().getLast().setRang("γ");
                }
            }
            System.out.println(nbNouveaux + " jeunes lycanthropes ont été ajoutés à la meute.");
        } else {
            System.out.println("Pas de couple α disponible pour la reproduction.");
        }
    }
}
