package TD3.Hopital;

import TD3.Creature.Creature;

public class CentreDeQuarantaine<T> extends ServiceMedical {
    private boolean isole;
    private String nom;
    private int id;
    private static int DERNIER_ID = 0;

    public CentreDeQuarantaine( double superficie, int capaciteMax, String budget, boolean isole, String type) {
        super(superficie, capaciteMax, budget,type);
        this.isole = isole;
        this.id = DERNIER_ID++;
        setNom("Centre de Quarantaine " + id);
    }

    @Override
    public void ajouterCreature(Creature creature) {
        if (creature.estMalade()) {
            super.ajouterCreature(creature);
        } else {
            System.out.println(creature.getNom() + " ne peut pas être ajouté(e) au centre de quarantaine car il/elle n'est pas contagieux(se).");
        }
    }

    @Override
    public void revisionBudget() {
        super.revisionBudget();
        System.out.println("Isolation : " + (isole ? "Oui" : "Non"));
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
