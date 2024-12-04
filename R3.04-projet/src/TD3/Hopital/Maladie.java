package TD3.Hopital;

/**
 * Classe Maladie représentant une maladie atttrapable par une créature.
 */
public class Maladie {
    private String nomComplet;
    private String nomAbrege;
    private int niveauActuel;
    private int niveauMax;

    /**
     * Constructeur de la classe Maladie.
     * @param nomComplet Nom complet de la maladie.
     * @param nomAbrege Nom abrégé de la maladie.
     * @param niveauMax Niveau maximal de la maladie.
     */
    public Maladie(String nomComplet, String nomAbrege, int niveauMax) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauActuel = 1;
        this.niveauMax = niveauMax;
    }

    /**
     * Méthode permettant de récupérer le nom complet de la maladie.
     * @return Nom complet de la maladie.
     */
    public String getNomComplet() {
        return nomComplet;
    }

    /**
     * Méthode permettant de récupérer le nom abrégé de la maladie.
     * @return Nom abrégé de la maladie.
     */
    public String getNomAbrege() {
        return nomAbrege;
    }

    /**
     * Méthode permettant de récupérer le niveau actuel de la maladie.
     * @return Niveau actuel de la maladie.
     */
    public int getNiveauActuel() {
        return niveauActuel;
    }

    /**
     * Méthode permettant de récupérer le niveau maximal de la maladie.
     * @return Niveau maximal de la maladie.
     */
    public int getNiveauMax() {
        return niveauMax;
    }

    /**
     * Méthode permettant d'augmenter le niveau de la maladie.
     */
    public void augmenterNiveau() {
        niveauActuel += 1;
        if (niveauActuel > niveauMax) {
            niveauActuel = niveauMax;
        }
    }

    /**
     * Méthode permettant de diminuer le niveau de la maladie.
     */
    public void diminuerNiveau() {
        niveauActuel -= 1;
        if (niveauActuel < 0) {
            niveauActuel = 0;
        }
    }

    /**
     * Méthode permettant de changer le niveau de la maladie.
     * @param nouveauNiveau Nouveau niveau de la maladie.
     */
    public void changerNiveau(int nouveauNiveau) {
        if (nouveauNiveau < 0) {
            niveauActuel = 0;
        } else if (nouveauNiveau > niveauMax) {
            niveauActuel = niveauMax;
        } else {
            niveauActuel = nouveauNiveau;
        }
    }

    /**
     * Méthode permettant de savoir si la maladie est létale.
     * @return Vrai si la maladie est létale, faux sinon.
     */
    public boolean estLetale() {
        return niveauActuel >= niveauMax;
    }


    /**
     * Méthode permettant de récupérer une représentation textuelle de la maladie.
     * @return Représentation textuelle de la maladie.
     */
    @Override
    public String toString() {
        return nomComplet + " (" + nomAbrege + ") - Niveau: " + niveauActuel + "/" + niveauMax;
    }

    /**
     * Méthode permettant de récupérer le nom complet et le nom abrégé de la maladie.
     * @return Nom complet et nom abrégé de la maladie.
     */
    public String getNom() {
        return nomComplet + " (" + nomAbrege + ")";
    }
}
