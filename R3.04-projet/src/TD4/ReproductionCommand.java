package TD4;

/**
 * Classe représentant une simulation de reproduction d'une meute appelée sur commande
 */
public class ReproductionCommand implements Command {
    private final Meute meute;

    /**
     * Constructeur de la classe ReproductionCommand
     * @param meute Meute sur laquelle la commande de reproduction est appelée
     */
    public ReproductionCommand(Meute meute) {
        this.meute = meute;
    }

    /**
     * Méthode permettant d'exécuter la commande de reproduction et simuler celle-ci
     */
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
                    meute.getMembres().get(meute.getMembres().size()-1).setRang("β");
                } else {
                    meute.getMembres().get(meute.getMembres().size()-1).setRang("γ");
                }
            }
            System.out.println(nbNouveaux + " jeunes Lycanthropes ont été ajoutés à la meute.");
        } else {
            System.out.println("Pas de couple α disponible pour la reproduction.");
        }
    }
}
