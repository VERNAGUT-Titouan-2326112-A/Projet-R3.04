package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;

import java.util.List;

/**
 * Classe Orque sous-classe de Creature
 */
public class Orque extends Creature {
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Orque
     * @param nom Nom de l'Orque
     * @param moral Moral de l'Orque
     * @param sexe Sexe de l'Orque
     * @param poids Poids de l'Orque
     * @param taille Taille de l'Orque
     * @param age Age de l'Orque
     * @param maladies Liste des maladies de l'Orque
     */
    public Orque(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Orque" + id);
        setTriage(true);
    }

    /**
     * Méthode pour faire trepasser un Orque en respectant ses caractéristiques
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
