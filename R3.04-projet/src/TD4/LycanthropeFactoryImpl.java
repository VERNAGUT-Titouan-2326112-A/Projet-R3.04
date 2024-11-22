package TD4;

public class LycanthropeFactoryImpl implements LycanthropeFactory {
    @Override
    public Lycanthrope creerLycanthrope(String sexe, String categorieAge) {
        int force = (int) (Math.random() * 50 + 50); // Force aléatoire
        double impetuosite = Math.random(); // Facteur d'impétuosité aléatoire
        return new Lycanthrope(sexe, categorieAge, force, impetuosite);
    }
}
