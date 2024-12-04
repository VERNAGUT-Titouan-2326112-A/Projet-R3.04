package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;
import TD3.Interfaces.Regenerable;

import java.util.List;

/**
 * Classe Zombie sous-classe de Creature
 */
public class Zombie extends Creature implements Regenerable {
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Zombie
     * @param nom Nom du Zombie
     * @param moral Moral du Zombie
     * @param sexe Sexe du Zombie
     * @param poids Poids du Zombie
     * @param taille Taille du Zombie
     * @param age Age du Zombie
     * @param maladies Liste des maladies du Zombie
     */
    public Zombie(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Zombie" + id);
        setTriage(true);
    }

    /**
     * Méthode pour faire trepasser un Zombie en respectant ses caractéristiques
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
        regenerer(hopital);
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
