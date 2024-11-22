package TD4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    }

    public double calculerNiveau() {
        return force * 0.5 + facteurDomination * 0.3 + (rang.charAt(0) - 'ω') * 0.2;
    }


    public void seTransformerEnHumain() {
        System.out.println(this + " se transforme en humain.");
        etat = "humain";
    }

    public void afficherCaracteristiques() {
        System.out.printf("Sexe: %s, Âge: %s, Force: %d, Rang: %s, Niveau: %.2f\n",
                sexe, categorieAge, force, rang, niveau);
    }

    // Méthode pour ajouter un observateur
    public void ajouterListener(HurlementListener listener) {
        listeners.add(listener);
    }

    // Méthode pour notifier les observateurs
    private void notifierListeners(String typeHurlement) {
        for (HurlementListener listener : listeners) {
            listener.reagirAuHurlement(typeHurlement, this);
        }
    }

    // Émet un hurlement
    public void hurler(String typeHurlement) {
        System.out.println(this + " hurle : " + typeHurlement);
        notifierListeners(typeHurlement);
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

    public Meute getMeute() {
        return meute;
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

    public double getFacteurImpetuosite() {
        return facteurImpetuosite;
    }

    public void setFacteurImpetuosite(double facteurImpetuosite) {
        this.facteurImpetuosite = facteurImpetuosite;
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


    @Override
    public String toString() {
        return "Lycanthrope{" + "sexe='" + sexe + '\'' + ", rang='" + rang + '\'' + '}';
    }

    public void augmenterFacteurDomination() {
        facteurDomination++;
        niveau = calculerNiveau();
    }

    public void baisserFacteurDomination() {
        facteurDomination--;
        niveau = calculerNiveau();
    }

    public void choisirRangAleatoire() {
        Random random = new Random();
        int choix = random.nextInt(6); // Limiter les choix à 7 (α à ζ)

        switch (choix) {
            case 0:
                setRang("β");
            case 1:
                setRang("γ");
            case 2:
                setRang("δ");
            case 3:
                setRang("ε");
            case 4:
                setRang("ζ");
            case 5:
                setRang("η");
            case 6:
                setRang("θ");
            default:
                setRang("ω");
        }
    }

    public void quitterMeute() {
        meute.retirerMembre(this);
        meute = null;
    }
}

