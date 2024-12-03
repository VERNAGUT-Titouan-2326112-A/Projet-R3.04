package TD3.Creature;

import TD3.Hopital.Maladie;
import TD3.Hopital.ServiceMedecin;
import TD3.Interfaces.Demoralisant;
import TD3.Interfaces.Regenerable;

import java.util.List;

public class Vampire extends Creature implements Demoralisant, Regenerable {
    private int id;
    private static int DERNIER_ID = 0;

    public Vampire(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Vampire" + id);
    }

    @Override
    public void trepasser() {
        super.trepasser();
        demoraliser(ServiceMedecin.getServiceCreatures());
    }

    @Override
    protected void contaminer() {
        //les vampires ne contaminent pas
    }

    @Override
    public void demoraliser(List<Creature> creatures) {
        for (Creature creature : creatures) {
            creature.attendre();
            System.out.println(creature.getNom() + " est démoralisé par la mort de " + getNom());
        }
    }
    public void VIP(){

    }

    @Override
    public void regenerer() {

    }
}
