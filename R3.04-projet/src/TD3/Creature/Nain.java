package TD3.Creature;

public class Nain extends Creature{
    public Nain(String nom, String sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age,false,false);
    }
    @Override
    public void trepasser() {
        super.trepasser();
        // Démoraliser les autres créatures
    }

    @Override
    protected void contaminer() {

    }
    public void VIP(){

    }
}