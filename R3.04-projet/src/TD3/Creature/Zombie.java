package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;
import TD3.Interfaces.Regenerable;

import java.util.List;

public class Zombie extends Creature implements Regenerable {
    private int id;
    private static int DERNIER_ID = 0;

    public Zombie(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Zombie" + id);
    }
    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
        // Démoraliser les autres créatures
    }

    @Override
    protected void contaminer() {

    }

    @Override
    public void regenerer() {
        this.setMoral(50);
        System.out.println(getNom() + " se régénère après sa mort !");
    }
    public void Triage(){

    }

    @Override
    public void demoraliser(HopitalFantastique hopital) {

    }
}
