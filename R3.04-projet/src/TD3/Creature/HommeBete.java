package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;

import java.util.List;

/**
 * Classe HommeBete sous-classe de Creature
 */
public class HommeBete extends Creature{
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe HommeBete
     * @param nom Nom de l'HommeBete
     * @param moral Moral de l'HommeBete
     * @param sexe Sexe de l'HommeBete
     * @param poids Poids de l'HommeBete
     * @param taille Taille de l'HommeBete
     * @param age Age de l'HommeBete
     * @param maladies Liste des maladies de l'HommeBete
     */
    public HommeBete(String nom, int moral, String sexe, double poids, double taille, int age, List<Maladie> maladies) {
        super(nom, moral, sexe, poids, taille, age, true, true, maladies);
        this.id = ++DERNIER_ID;
        setNom("HommeBete" + id);
        setTriage(true);
    }

    /**
     * Méthode pour faire trepasser un HommeBete en respectant ses caractéristiques
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
    public void Triage(){

    }
}
