package TD3.Hopital;

import TD3.Creature.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe ServiceMedical représentant un service médical de l'hôpital
 */
public class ServiceMedical {
    private double superficie;
    private int capaciteMax;
    private int nombreCreatures;
    private List<Creature> creatures;
    private String budget;
    private String type;
    private String nom;
    private int id;
    private static int DERNIER_ID = 0;
    private boolean isole = false;

    public static final String BUDGET_FAIBLE = "faible";
    public static final String BUDGET_MEDIOCRE = "médiocre";
    public static final String BUDGET_MOYEN = "moyen";
    public static final String BUDGET_ELEVE = "élevé";
    public static final String BUDGET_INSUFFISANT = "insuffisant";
    public static final String BUDGET_INEXISTANT = "inexistant";

    /**
     * Constructeur de ServiceMedical
     * @param superficie superficie du service médical
     * @param capaciteMax capacité maximale du service médical
     * @param budget budget du service médical
     * @param type type de créature contenues dans le service médical
     */
    public ServiceMedical(double superficie, int capaciteMax, String budget, String type) {
        if (superficie <= 0) {
            throw new IllegalArgumentException("La superficie doit être positive.");
        }
        if (capaciteMax <= 0) {
            throw new IllegalArgumentException("La capacité maximale doit être positive.");
        }
        if (!isValidBudget(budget)) {
            throw new IllegalArgumentException("Budget invalide.");
        }

        this.superficie = superficie;
        this.capaciteMax = capaciteMax;
        this.budget = budget;
        this.creatures = new ArrayList<>();
        this.nombreCreatures = 0;
        this.type = type;
        this.id = DERNIER_ID++;
        this.nom = "Service médical " + id;

    }

    /**
     * Méthode pour afficher les détails du service médical
     */
    public void afficherDetails() {
        System.out.println("Nom: " + nom);
        System.out.println("Superficie: " + superficie + " m²");
        System.out.println("Capacité maximale: " + capaciteMax + " créatures");
        System.out.println("Nombre de créatures: " + nombreCreatures);
        System.out.println("Budget: " + budget);
    }

    /**
     * Méthode pour ajouter une créature au service médical
     * @param creature créature à ajouter
     */
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

    /**
     * Méthode pour enlever une créature du service médical
     * @param creature créature à enlever
     */
    public void enleverCreature(Creature creature ) {
        if (creatures.remove(creature)) {
            nombreCreatures--;
            System.out.println(creature.getNom() + " a été retiré(e) du service médical.");
        }
        else {
            System.out.println("La créature" + creature.getNom() + " n'a pas été trouvée dans le service médical.");
        }
    }

    /**
     * Méthode pour soigner les créatures du service médical
     */
    public void soignerCreatures() {
        for (Creature creature : creatures) {
            creature.soigner();
        }
        System.out.println("Les créatures du service médical ont été soignées.");
    }

    /**
     * Méthode pour réviser le budget du service médical
     */
    public void revisionBudget() {
        Random random = new Random();
        System.out.println("Révision du budget du service : " + nom);
        String[] budgets = {BUDGET_INEXISTANT, BUDGET_MEDIOCRE, BUDGET_INSUFFISANT, BUDGET_FAIBLE, BUDGET_MOYEN, BUDGET_ELEVE};
        budget = budgets[random.nextInt(budgets.length)];

        switch (budget) {
            case BUDGET_INEXISTANT:
                System.out.println("Le service n'a aucun budget.");
                break;
            case BUDGET_MEDIOCRE:
                System.out.println("Le budget est médiocre.");
                break;
            case BUDGET_INSUFFISANT:
                System.out.println("Le budget est insuffisant.");
                break;
            case BUDGET_FAIBLE:
                System.out.println("Le budget est faible.");
                break;
            case BUDGET_MOYEN:
                System.out.println("Le budget est moyen");
                break;
            case BUDGET_ELEVE:
                System.out.println("Le budget est élevé.");
                break;
            default:
                System.out.println("Budget inconnu.");
                break;
        }

    }

    /**
     * Méthode pour obtenir le nom du service médical
     * @return nom du service médical
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir la superficie du service médical
     * @return superficie du service médical
     */
    public double getSuperficie() {
        return superficie;
    }


    /**
     * Méthode pour obtenir la capacité maximale du service médical
     * @return capacité maximale du service
     *
     */
    public int getCapaciteMax() {
        return capaciteMax;
    }


    /**
     * Méthode pour obtenir le nombre de créatures du service médical
     * @return nombre de créatures du service médical
     */
    public int getNombreCreatures() {
        return nombreCreatures;
    }


    /**
     * Méthode pour obtenir la liste des créatures du service médical
     * @return liste des créatures du service médical
     */
    public List<Creature> getCreatures() {
        return creatures;
    }


    /**
     * Méthode pour obtenir le budget du service médical
     * @return budget du service médical
     */
    public String getBudget() {
        return budget;
    }

    /**
     * Méthode pour définir le budget du service médical
     * @param budget budget du service médical
     */
    public void setBudget(String budget) {
        if (!isValidBudget(budget)) {
            throw new IllegalArgumentException("Budget invalide.");
        }
        this.budget = budget;
    }

    /**
     * Méthode pour savoir si le budget est valide
     * @param budget budget à vérifier
     * @return true si le budget est valide, false sinon
     */
    private boolean isValidBudget(String budget) {
        return BUDGET_INEXISTANT.equals(budget) || BUDGET_MEDIOCRE.equals(budget) ||
                BUDGET_INSUFFISANT.equals(budget) || BUDGET_FAIBLE.equals(budget)|| BUDGET_MOYEN.equals(budget) || BUDGET_ELEVE.equals(budget);
    }

    /**
     * Méthode pour obtenir le type de créature du service médical
     * @return type de créature du service médical
     */
    public String getType() {
        return type;
    }

    /**
     * Méthode pour savoir si le service médical est isolé (même cas que crypte)
     * @return true si le service médical est isolé, false sinon
     */
    public boolean isIsole() {
        return isole;
    }

    /**
     * Méthode pour définir si le service médical est isolé (même cas que crypte)
     * @param isole si le service médical est isolé
     */
    public void setIsole(boolean isole) {
        this.isole = isole;
    }

    /**
     * Méthode pour définir le nom du service médical
     * @param nom nom du service médical
     */
    protected void setNom(String nom) {
        this.nom = nom;
    }
}
