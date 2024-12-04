package TD3.Hopital;

import TD3.Creature.Creature;

/**
 * Classe CentreDeQuarantaine, sous-classe de ServiceMedical
 */
public class CentreDeQuarantaine<T> extends ServiceMedical {
    private String nom;
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de CentreDeQuarantaine
     * @param superficie superficie du centre de quarantaine
     * @param capaciteMax capacité maximale du centre de quarantaine
     * @param budget budget du centre de quarantaine
     * @param isole si le centre de quarantaine est isolé ou non
     * @param type type de créature contenues dans le centre de quarantaine
     */
    public CentreDeQuarantaine( double superficie, int capaciteMax, String budget, boolean isole, String type) {
        super(superficie, capaciteMax, budget,type);
        setIsole(isole);
        this.id = DERNIER_ID++;
        super.setNom("Centre de Quarantaine " + id);
    }

    /**
     * Méthode pour ajouter une créature au centre de quarantaine
     * @param creature créature à ajouter
     */
    @Override
    public void ajouterCreature(Creature creature) {
        if (creature.isContaminant()) {
            super.ajouterCreature(creature);
        } else {
            System.out.println(creature.getNom() + " ne peut pas être ajouté(e) au centre de quarantaine car il/elle n'est pas contagieux(se).");
        }
    }

    /**
     * réviser le budget du centre de quarantaine
     */
    @Override
    public void revisionBudget() {
        super.revisionBudget();
        System.out.println("Isolation : " + (isIsole() ? "Oui" : "Non"));
    }

    /**
     * Méthode pour définir le nom du centre de quarantaine
     * @param nom nom du centre de quarantaine
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
