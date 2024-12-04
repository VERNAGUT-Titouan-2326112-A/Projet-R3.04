package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;

import java.util.List;

/**
 * Classe Nain sous-classe de Creature
 */
public class Nain extends Creature{
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Nain
     * @param nom Nom du Nain
     * @param moral Moral du Nain
     * @param sexe Sexe du Nain
     * @param poids Poids du Nain
     * @param taille Taille du Nain
     * @param age Age du Nain
     * @param maladies Liste des maladies du Nain
     */
    public Nain(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Nain" + id);
        setVIP(true);
    }

    /**
     * Méthode pour faire trepasser un Nain en respectant ses caractéristiques
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
    }
}
