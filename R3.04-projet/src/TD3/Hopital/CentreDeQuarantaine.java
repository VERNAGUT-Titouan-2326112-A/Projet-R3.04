package TD3.Hopital;

import TD3.Creature.Creature;

public class CentreDeQuarantaine<T> extends ServiceMedical {
    private boolean isole;

    public CentreDeQuarantaine( double superficie, int capaciteMax, String budget, boolean isole, String type) {
        super(superficie, capaciteMax, budget,type);
        this.isole = isole;
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

    public void setIsole(boolean isole){
        this.isole = isole ;
    }

    public boolean isIsolation(){
        return this.isole;
    }
}
