package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;

import java.util.List;

/**
 * Classe Lycanthropes sous-classe de Creature
 */
public class Lycanthropes extends Creature{
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Lycanthropes
     * @param nom Nom de l'Lycanthropes
     * @param moral Moral de l'Lycanthropes
     * @param sexe Sexe de l'Lycanthropes
     * @param poids Poids du Lycanthropes
     * @param taille Taille du Lycanthropes
     * @param age Age de l'Lycanthropes
     * @param maladies Liste des maladies de l'Lycanthropes
     */
    public Lycanthropes(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Lycanthropes" + id);
        setTriage(true);
    }

    /**
     * Méthode pour faire trepasser un Lycanthropes en respectant ses caractéristiques
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
        contaminer(hopital);
    }
    /**
     * Méthode pour contaminer les autres créatures de l'hôpital
     * @param hopital : hôpital fantastique avec lequel la créature interagit
     */
    @Override
    public void contaminer(HopitalFantastique hopital) {
        super.contaminer(hopital);

    }
}
