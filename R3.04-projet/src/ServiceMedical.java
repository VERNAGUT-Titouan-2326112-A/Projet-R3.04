import java.util.ArrayList;
import java.util.List;

public class ServiceMedical {
    private String nom;
    private double superficie;
    private int capaciteMax;
    private int nombreCreatures;
    private List<Creature> creatures;
    private String budget;

    public static final String BUDGET_FAIBLE = "faible";
    public static final String BUDGET_MEDIOCRE = "médiocre";
    public static final String BUDGET_MOYEN = "moyen";
    public static final String BUDGET_ELEVE = "élevé";
    public static final String BUDGET_INSUFFISANT = "insuffisant";
    public static final String BUDGET_INEXISTANT = "inexistant";

    public ServiceMedical(String nom, double superficie, int capaciteMax, String budget) {
        if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide.");
        }
        if (superficie <= 0) {
            throw new IllegalArgumentException("La superficie doit être positive.");
        }
        if (capaciteMax <= 0) {
            throw new IllegalArgumentException("La capacité maximale doit être positive.");
        }
        if (!isValidBudget(budget)) {
            throw new IllegalArgumentException("Budget invalide.");
        }

        this.nom = nom;
        this.superficie = superficie;
        this.capaciteMax = capaciteMax;
        this.budget = budget;
        this.creatures = new ArrayList<>();
        this.nombreCreatures = 0;
    }

    /* public void afficherDetails() {
        System.out.println("Nom: " + nom);
        System.out.println("Superficie: " + superficie + " m²");
        System.out.println("Capacité maximale: " + capaciteMax + " créatures");
        System.out.println("Nombre de créatures: " + nombreCreatures);
        System.out.println("Budget: " + budget);
    }*/

    public void ajouterCreature(Creature creature) {
        if (nombreCreatures < capaciteMax) {
            creatures.add(creature);
            nombreCreatures++;
            System.out.println(creature.getNom() + " a été ajouté(e) au service médical.");
        }
        else{
            System.out.println("Le service médical est plein." + creature.getNom() + " n'a pas été ajouté(e).");
        }
    }

    public void enleverCreature(Creature creature ) {
        if (creatures.remove(creature)) {
            nombreCreatures--;
            System.out.println(creature.getNom() + " a été retiré(e) du service médical.");
        }
        else {
            System.out.println("La créature" + creature.getNom() + " n'a pas été trouvée dans le service médical.");
        }
    }

    public void soignerCreatures() {
        for (Creature creature : creatures) {
            creature.soigner();
        }
    }

    public void revisionBudget() {
        System.out.println("Révision du budget du service : " + nom);
        switch (budget) {
            case BUDGET_INEXISTANT:
                System.out.println("Le service n'a aucun budget.");
                break;
            case BUDGET_MEDIOCRE:
                System.out.println("Le budget est médiocre, il est insuffisant pour bien fonctionner.");
                break;
            case BUDGET_INSUFFISANT:
                System.out.println("Le budget est insuffisant, des améliorations sont nécessaires.");
                break;
            case BUDGET_FAIBLE:
                System.out.println("Le budget est faible, mais il permet de fonctionner.");
                break;
            default:
                System.out.println("Budget inconnu.");
                break;
        }
    }

    public String getNom() {
        return nom;
    }

    public double getSuperficie() {
        return superficie;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public int getNombreCreatures() {
        return nombreCreatures;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        if (!isValidBudget(budget)) {
            throw new IllegalArgumentException("Budget invalide.");
        }
        this.budget = budget;
    }

    private boolean isValidBudget(String budget) {
        return BUDGET_INEXISTANT.equals(budget) || BUDGET_MEDIOCRE.equals(budget) ||
                BUDGET_INSUFFISANT.equals(budget) || BUDGET_FAIBLE.equals(budget);
    }
}
