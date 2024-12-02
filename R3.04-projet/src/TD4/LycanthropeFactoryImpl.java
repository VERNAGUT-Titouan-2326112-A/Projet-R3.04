package TD4;

/**
 * Classe permettant de créer des lycanthropes via l'interface LycanthropeFactory
 */
public class LycanthropeFactoryImpl implements LycanthropeFactory {
    /**
     * Méthode permettant de créer un lycanthrope
     * @param sexe Sexe du lycanthrope
     * @param categorieAge Catégorie d'âge du lycanthrope
     * @return Lycanthrope
     */
    @Override
    public Lycanthrope creerLycanthrope(String sexe, String categorieAge) {
        int force = (int) (Math.random() * 50 + 50); // Force aléatoire
        double impetuosite = Math.random(); // Facteur d'impétuosité aléatoire
        return new Lycanthrope(sexe, categorieAge, force, impetuosite);
    }
}
