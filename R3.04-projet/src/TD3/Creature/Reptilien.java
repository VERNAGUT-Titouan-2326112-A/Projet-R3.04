package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;

import java.util.List;

/**
 * Classe Reptilien sous-classe de Creature
 */
public class Reptilien extends Creature{
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Reptilien
     * @param nom Nom du Reptilien
     * @param moral Moral du Reptilien
     * @param sexe Sexe du Reptilien
     * @param poids Poids du Reptilien
     * @param taille Taille du Reptilien
     * @param age Age du Reptilien
     * @param maladies Liste des maladies du Reptilien
     */
    public Reptilien(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("Reptilien" + id);
        setVIP(true);
    }

    /**
     * Méthode pour faire trepasser un Reptilien en respectant ses caractéristiques
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    @Override
    public void trepasser(HopitalFantastique hopital) {
        super.trepasser(hopital);
    }
}
