package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;
import TD3.Interfaces.Demoralisant;
import TD3.Interfaces.Regenerable;

import java.util.List;

/**
 * Classe Vampire sous-classe de Creature
 */
public class Vampire extends Creature implements Demoralisant, Regenerable {
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Vampire
     * @param nom Nom du Vampire
     * @param moral Moral du Vampire
     * @param sexe Sexe du Vampire
     * @param poids Poids du Vampire
     * @param taille Taille du Vampire
     * @param age Age du Vampire
     * @param maladies Liste des maladies du Vampire
     */
    public Vampire(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Vampire" + id);
        setVIP(true);
    }

    /**
     * Méthode pour faire trepasser un Vampire en respectant ses caractéristiques
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
        demoraliser(hopital);
        regenerer(hopital);
    }

    /**
     * Méthode pour contaminer les autres créatures de l'hôpital
     * @param hopital : hôpital fantastique avec lequel la créature interagit
     */
    @Override
    protected void contaminer(HopitalFantastique hopital) {
      super.contaminer(hopital);
    }

    /**
     * Méthode pour demoraliser les autres créatures de l'hôpital
     * @param hopital : hôpital fantastique avec lequel la créature interagit
     */
    @Override
    public void demoraliser(HopitalFantastique hopital) {
        super.demoraliser(hopital);
    }

    /**
     * Méthode pour regenerer les autres créatures de l'hôpital
     * @param hopital : hôpital fantastique avec lequel la créature interagit
     */
    @Override
    public void regenerer(HopitalFantastique hopital) {
        super.regenerer(hopital);
    }
}
