package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;
import TD3.Hopital.ServiceMedecin;
import TD3.Interfaces.Demoralisant;

import java.util.List;


public class Elfe extends Creature implements Demoralisant {
  private int id;
  private static int DERNIER_ID = 0;

    public Elfe(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Elfe " + id);
    }

    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
        for (Creature creature : hopital.getCreatures()) {
            creature.attendre();
            System.out.println(creature.getNom() + " est démoralisé par la mort de " + getNom());
        }
    }

    @Override
    public void demoraliser(HopitalFantastique hopital) {
        List<Creature> creatures = hopital.getCreatures();
        for (Creature creature : creatures) {
            creature.attendre();
            System.out.println(creature.getNom() + " est démoralisé par la mort de " + getNom());
        }
    }
    public void VIP(){

    }
}