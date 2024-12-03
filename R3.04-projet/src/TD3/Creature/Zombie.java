package TD3.Creature;

import TD3.Hopital.Maladie;
import TD3.Interfaces.Regenerable;

import java.util.List;

public class Zombie extends Creature implements Regenerable {

    public Zombie(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
    }
    @Override
    public void trepasser() {
        super.trepasser();
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
}
