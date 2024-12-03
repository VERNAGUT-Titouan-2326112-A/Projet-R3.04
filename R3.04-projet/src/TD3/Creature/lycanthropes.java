package TD3.Creature;

import TD3.Hopital.Maladie;

import java.util.List;

public class lycanthropes extends Creature{
    private int id;
    private static int DERNIER_ID = 0;

    public lycanthropes(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("lycanthropes" + id);
    }
    @Override
    public void trepasser() {
        super.trepasser();
        // Démoraliser les autres créatures
    }
    @Override
    protected void contaminer() {

    }
    public void Triage(){

    }
}
