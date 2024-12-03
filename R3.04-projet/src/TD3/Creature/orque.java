package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;

import java.util.List;

public class orque extends Creature {
    private int id;
    private static int DERNIER_ID = 0;

    public orque(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Orque" + id);
    }

    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
        // Démoraliser les autres créatures
    }

    @Override
    protected void contaminer() {

    }
    public void Triage(){

    }

    @Override
    public void demoraliser(HopitalFantastique hopital) {

    }
}
