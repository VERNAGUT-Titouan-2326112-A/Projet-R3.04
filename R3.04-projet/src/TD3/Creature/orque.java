package TD3.Creature;

public class orque extends Creature {
    public orque(String nom,int moral, String sexe, double poids, double taille, int age) {
        super(nom,moral, sexe, poids, taille, age,false,true);
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
