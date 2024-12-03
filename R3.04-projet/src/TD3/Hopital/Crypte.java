package TD3.Hopital;

import TD3.Creature.Creature;

public class Crypte<T> extends ServiceMedical {
    private int ventilation;
    private int temperature;
    String type;

    public Crypte(double superficie, int capaciteMax, String budget, int ventilation, int temperature, String type) {
        super(superficie, capaciteMax, budget,type);
        this.ventilation = ventilation;
        this.temperature = temperature;
        this.type = type;
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

    public int getTemperature() {
        return this.temperature;
    }

    public void setTemperature(int tmp){
        this.temperature = tmp;
    }

    public int getVentilation(){
        return this.ventilation;
    }

    public void setVentilation(int vnt){
        this.ventilation = vnt ;
    }
}