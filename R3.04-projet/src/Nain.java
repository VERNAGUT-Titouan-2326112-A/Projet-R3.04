public class Nain extends Creature{
    public Nain(String nom, String sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
    }
    @Override
    public void trepasser() {
        super.trepasser();
        // Démoraliser les autres créatures
    }

}
