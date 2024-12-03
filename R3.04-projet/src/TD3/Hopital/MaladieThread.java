
package TD3.Hopital;

import TD3.Creature.Creature;
import java.util.List;
import java.util.Iterator;

public class MaladieThread implements Runnable {
    private List<Creature> creatures;

    public MaladieThread(List<Creature> creatures) {
        this.creatures = creatures;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30000); // Sleep for 30 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Iterator<Creature> iterator = creatures.iterator();
            while (iterator.hasNext()) {
                Creature creature = iterator.next();
                creature.setMoral(creature.getMoral() - 10);
                for (Maladie maladie : creature.getMaladies()) {
                    maladie.augmenterNiveau(1);
                }
                if (creature.getMoral() <= 0) {
                    creature.trepasser();
                    iterator.remove();
                    System.out.println("La créature " + creature.getNom() + " est morte et a été retirée de la liste.");
                }
            }
            System.out.println("Toutes les créatures ont perdu 10 de moral et leurs niveaux de maladies ont augmenté de 1.");
        }
    }
}