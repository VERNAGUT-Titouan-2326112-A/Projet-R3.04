import java.util.List;

public class Vampire extends Creature implements Demoralisant, Contaminant, Regenerable {

    public Vampire(String nom, String sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
    }

    @Override
    public void trepasser() {
        super.trepasser();
        demoraliser(ServiceMedecin.getServiceCreatures());
        regenerer();
    }

    @Override
    protected void contaminer() {

    }

    @Override
    public void contaminer(Creature creature) {
        if (!this.getMaladies().isEmpty()) {
            Maladie maladie = this.getMaladies().get(0);
            creature.tomberMalade(maladie);
            System.out.println(creature.getNom() + " a été contaminé par " + getNom());
        }
    }

    @Override
    public void regenerer() {
        this.setMoral(50);
        System.out.println(getNom() + " se régénère après sa mort !");
    }

    @Override
    public void demoraliser(List<Creature> creatures) {

    }
}
