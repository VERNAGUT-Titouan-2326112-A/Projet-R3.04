package TD4;

import java.util.ArrayList;
import java.util.List;

public class Lycanthrope {
    private String sexe;
    private String categorieAge;
    private int force;
    private int facteurDomination;
    private String rang;
    private double niveau;
    private double facteurImpetuosite;
    private Meute meute;
    private String etat;
    private String nom;
    private static int DERNIER_ID = 0;
    private final int id ;
    private final List<HurlementListener> listeners = new ArrayList<>(); // Liste des observateurs

    public Lycanthrope(String sexe, String categorieAge, int force, double facteurImpetuosite) {
        this.sexe = sexe;
        this.categorieAge = categorieAge;
        this.force = force;
        this.facteurImpetuosite = facteurImpetuosite;
        this.facteurDomination = 0;
        this.rang = "ω";
        this.niveau = calculerNiveau();
        this.meute = null;
        this.etat = "normal";
        ++DERNIER_ID;
        this.id = DERNIER_ID;
        this.nom = "Lycanthrope" + id;
    }

    public double calculerNiveau() {
        return force * 0.5 + facteurDomination * 0.3 + (rang.charAt(0) - 'ω') * 0.2 + facteurImpetuosite * 0.1;
    }


    public void seTransformerEnHumain() {
        System.out.println(this.getNom() + " se transforme en humain.");
        setEtat("humain");
    }

    public void afficherCaracteristiques() {
        System.out.printf("Nom: %s Sexe: %s, Âge: %s, Force: %d, Rang: %s, Niveau: %.2f\n",
                nom, sexe, categorieAge, force, rang, niveau);
    }

    // Méthode pour ajouter un observateur
    public void ajouterListener(HurlementListener listener) {
        listeners.add(listener);
    }

    // Méthode pour notifier les observateurs
    public void notifierListeners(String typeHurlement) {
        for (HurlementListener listener : listeners) {
            listener.reagirAuHurlement(typeHurlement, this);
        }
    }

    // Émet un hurlement
    public void hurler(String typeHurlement) {
        System.out.println(nom + " hurle : " + typeHurlement);
    }

    public void setRang(String rang) {
        this.rang = rang;
        this.niveau = calculerNiveau();
    }

    public void setFacteurDomination(int facteurDomination) {
        this.facteurDomination = facteurDomination;
        this.niveau = calculerNiveau();
    }

    public void setMeute(Meute meute) {
        this.meute = meute;
    }

    public String getSexe() {
        return sexe;
    }

    public String getRang() {
        return rang;
    }

    public double getNiveau() {
        return niveau;
    }

    public void setNiveau(double niveau) {
        this.niveau = niveau;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setCategorieAge(String categorieAge) {
        this.categorieAge = categorieAge;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getForce() {
        return force;
    }

    public int getFacteurDomination() {
        return facteurDomination;
    }

    public String getCategorieAge() {
        return categorieAge;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Lycanthrope{" + "sexe='" + sexe + '\'' + ", rang='" + rang + '\'' + '}';
    }

    public void augmenterFacteurDomination() {
        setFacteurDomination(getFacteurDomination()+1);
        setNiveau(calculerNiveau());
    }

    public void baisserFacteurDomination() {
        setFacteurDomination(getFacteurDomination()-1);
        setNiveau(calculerNiveau());
    }

    public void choisirRangAleatoire() {
        double proba = Math.random();
        if(proba < 0.1) {
            setRang("β");
        }
        else if (proba < 0.2) {
            setRang("γ");
        }
        else if (proba < 0.3) {
            setRang("δ");
        }
        else if (proba < 0.4) {
            setRang("ε");
        }
        else if (proba < 0.5) {
            setRang("ζ");
        }
        else if (proba < 0.6) {
            setRang("η");
        }
        else if (proba < 0.7) {
            setRang("θ");
        }
        else {
            setRang("ω");
        }


    }

    public void quitterMeute() {
        meute.retirerMembre(this);
        meute = null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getFacteurImpetuosite() {
        return facteurImpetuosite;
    }

    public void setFacteurImpetuosite(double facteurImpetuosite) {
        this.facteurImpetuosite = facteurImpetuosite;
    }
}

