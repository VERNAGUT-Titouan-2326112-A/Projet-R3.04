public class lycanthropes extends Creature{

    public lycanthropes(String nom, String sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age,false,true);
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
