package TD3.Hopital;


public class Maladie {
    private String nomComplet;
    private String nomAbrege;
    private int niveauActuel;
    private int niveauMax;

    public Maladie(String nomComplet, String nomAbrege, int niveauMax) {
        this.nomComplet = nomComplet;
        this.nomAbrege = nomAbrege;
        this.niveauActuel = 4;
        this.niveauMax = niveauMax;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public String getNomAbrege() {
        return nomAbrege;
    }

    public int getNiveauActuel() {
        return niveauActuel;
    }

    public int getNiveauMax() {
        return niveauMax;
    }

    // Méthode pour augmenter le niveau actuel
    public void augmenterNiveau(int increment) {
        niveauActuel += increment;
        if (niveauActuel > niveauMax) {
            niveauActuel = niveauMax;
        }
    }

    // Méthode pour diminuer le niveau actuel
    public void diminuerNiveau(int decrement) {
        niveauActuel -= decrement;
        if (niveauActuel < 0) {
            niveauActuel = 0;
        }
    }

    // Méthode pour changer directement le niveau actuel
    public void changerNiveau(int nouveauNiveau) {
        if (nouveauNiveau < 0) {
            niveauActuel = 0;
        } else if (nouveauNiveau > niveauMax) {
            niveauActuel = niveauMax;
        } else {
            niveauActuel = nouveauNiveau;
        }
    }

    // Vérifie si la maladie est létale
    public boolean estLetale() {
        return niveauActuel >= niveauMax;
    }


    @Override
    public String toString() {
        return nomComplet + " (" + nomAbrege + ") - Niveau: " + niveauActuel + "/" + niveauMax;
    }
}
