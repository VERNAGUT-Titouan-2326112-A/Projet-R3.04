package TD4;

/**
 * Interface Command pour forcer toutes les commandes à implémenter la méthode execute
 */
public interface Command {

    /**
     * Méthode execute à implémenter pour chaque commande servant à exécuter celle-ci
     */
    void execute();
}

