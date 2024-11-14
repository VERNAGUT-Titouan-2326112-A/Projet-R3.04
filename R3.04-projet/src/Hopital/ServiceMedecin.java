package Hopital;


import Creature.Creature;

import java.util.ArrayList;
import java.util.List;

public class ServiceMedecin {
    private static List<Creature> creatures = new ArrayList<>();

    public static List<Creature> getServiceCreatures() {
        return creatures;
    }

    public static void ajouterCreature(Creature creature) {
        creatures.add(creature);
        System.out.println(creature.getNom() + " a été ajouté au service médical.");
    }

    public static void afficherCreatures() {
        for (Creature creature : creatures) {
            System.out.println(creature.getNom());
        }
    }
}
