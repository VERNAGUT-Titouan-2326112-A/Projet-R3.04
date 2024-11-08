public class Zombie extends Creature implements Regenerable {

    public Zombie(String nom, String sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
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
