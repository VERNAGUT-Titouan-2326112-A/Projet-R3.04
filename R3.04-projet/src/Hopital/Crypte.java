package Hopital;

import Creature.Creature;

public class Crypte extends ServiceMedical {
    private int ventilation;
    private int temperature;

    public Crypte(String nom, double superficie, int capaciteMax, String budget, int ventilation, int temperature) {
        super(nom, superficie, capaciteMax, budget);
        this.ventilation = ventilation;
        this.temperature = temperature;
    }

    @Override
    public void ajouterCreature(Creature creature) {
        if (creature.isContaminant()) {
            super.ajouterCreature(creature);
        } else {
            System.out.println(creature.getNom() + " ne peut pas être ajouté(e) à la crypte car il/elle n'est pas régénérant(e).");
        }
    }

    @Override
    public void revisionBudget() {
        System.out.println("Révision du budget de la crypte : " + getNom());
        System.out.println("Ventilation : " + ventilation);
        System.out.println("Température : " + temperature);
    }
}