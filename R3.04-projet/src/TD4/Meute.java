package TD4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Meute implements HurlementListener {
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;
    private List<Lycanthrope> membres;

    public Meute() {
        this.membres = new ArrayList<>();
    }

    public void afficherCaracteristiques() {
        System.out.println("=== Meute ===");
        System.out.println("Couple Alpha :");
        if (maleAlpha != null) maleAlpha.afficherCaracteristiques();
        if (femelleAlpha != null) femelleAlpha.afficherCaracteristiques();
        System.out.println("Membres de la meute :");
        for (Lycanthrope membre : membres) {
            membre.afficherCaracteristiques();
        }
    }

    public void setMaleAlpha(Lycanthrope maleAlpha) {
        this.maleAlpha = maleAlpha;
    }

    public void setFemelleAlpha(Lycanthrope femelleAlpha) {
        this.femelleAlpha = femelleAlpha;
    }

    public void creerHierarchie() {
        if (maleAlpha != null && femelleAlpha != null) {
            System.out.println("Création de la hiérarchie...");
            maleAlpha.setRang("α");
            femelleAlpha.setRang("α");
        }
        for (Lycanthrope lycanthrope : membres) {
            if (lycanthrope.getRang() == "α"){
                continue;
            }
            lycanthrope.choisirRangAleatoire();
        }
    }

    public void reproduction() {
        if (maleAlpha != null && femelleAlpha != null) {
            System.out.println("Reproduction en cours...");
            int nbPortee = (int) (Math.random() * 7 + 1);
            for (int i = 0; i < nbPortee; i++) {
                Lycanthrope nouveau = new Lycanthrope("Mâle", "Jeune", 10, 0.5);
                ajouterMembre(nouveau);
            }
        }
    }

    public void ajouterMembre(Lycanthrope lycanthrope) {
        membres.add(lycanthrope);
        lycanthrope.ajouterListener(this);
        lycanthrope.setMeute(this);
    }

    @Override
    public void reagirAuHurlement(String typeHurlement, Lycanthrope emetteur) {
        System.out.println("Réaction dans la meute au hurlement de " + emetteur + ": " + typeHurlement);
    }

    public Object getMaleAlpha() {
        return maleAlpha;
    }

    public Object getFemelleAlpha() {
        return femelleAlpha;
    }

    public List<Lycanthrope> getMembres() {
        return membres;
    }

    public void retirerMembre(Lycanthrope lycanthrope) {
        membres.remove(lycanthrope);
    }


    public static void ajusterPourSaison(String saison) {
        switch (saison) {
            case "Printemps":
                System.out.println("La meute rentre dans la période de reproduction.");
                break;
            case "Été":
                System.out.println("La meute souffre de la chaleur estivale...");
                break;
            case "Automne":
                System.out.println("La meute se prépare pour l'hiver.");
                break;
            case "Hiver":
                System.out.println("La meute lutte contre le froid.");
                break;
            default:
                break;
        }
    }
}

