import java.util.List;

public class Elfe extends Creature implements Demoralisant {

    public Elfe(String nom, String sexe, double poids, double taille, int age) {
        super(nom, sexe, poids, taille, age);
    }

    @Override
    public void trepasser() {
        super.trepasser();
        demoraliser(ServiceMedecin.getServiceCreatures());
    }

    @Override
    protected void contaminer() {
        //les elfes ne contaminent pas
    }

    @Override
    public void demoraliser(List<Creature> creatures) {
        for (Creature creature : creatures) {
            creature.attendre();
            System.out.println(creature.getNom() + " est démoralisé par la mort de " + getNom());
        }
    }
}