package TD4;

/**
 * Interface permettant de créer des Lycanthropes
 */
public interface LycanthropeFactory {
    /**
     * Méthode permettant de créer un lycanthrope
     * @param sexe Sexe du lycanthrope
     * @param categorieAge Catégorie d'âge du lycanthrope
     * @return Lycanthrope
     */
    Lycanthrope creerLycanthrope(String sexe, String categorieAge);
}

