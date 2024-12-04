package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;
import TD3.Interfaces.Demoralisant;
import java.util.List;


/**
 * Classe Elfe, sous-classe de Creature et implémente l'interface Demoralisant
 */
public class Elfe extends Creature implements Demoralisant {
  private final int id;
  private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Elfe
     * @param nom Nom de l'elfe
     * @param moral Moral de l'elfe
     * @param sexe Sexe de l'elfe
     * @param poids Poids de l'elfe
     * @param taille Taille de l'elfe
     * @param age Age de l'elfe
     * @param maladies Liste des maladies de l'elfe
     */
    public Elfe(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Elfe " + id);
        setVIP(true);
    }

    /**
     * Méthode permettant à l'elfe de trepasser en respectant ses caractéristiques
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
        demoraliser(hopital);
    }

    /**
     * Méthode permettant à l'elfe de démoraliser l'hôpital
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    @Override
    public void demoraliser(HopitalFantastique hopital) {
        super.demoraliser(hopital);
    }
}