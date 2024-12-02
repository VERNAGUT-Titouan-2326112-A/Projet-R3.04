package TD4;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un lycanthrope, ses caractéristiques et ses comportements
 */
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

    /**
     * Constructeur de la classe Lycanthrope
     * @param sexe : sexe du Lycanthrope
     * @param categorieAge : permet de définir si le Lycanthrope est un enfant, un adulte ou un vieux
     * @param force : force du Lycanthrope
     * @param facteurImpetuosite : facteur d'impétuosité du Lycanthrope
     */
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

    /**
     * Méthode permettant de calculer le niveau d'un lycanthrope
     * @return le niveau du lycanthrope
     */
    public double calculerNiveau() {
        return force * 0.5 + facteurDomination * 0.3 + (rang.charAt(0) - 'ω') * 0.2 + facteurImpetuosite * 0.1;
    }


    /**
     * Méthode permettant de se transformer en humain
     */
    public void seTransformerEnHumain() {
        System.out.println(this.getNom() + " se transforme en humain.");
        setEtat("humain");
    }

    /**
     * Méthode permettant d'afficher les caractéristiques d'un lycanthrope
     */
    public void afficherCaracteristiques() {
        System.out.printf("Nom: %s Sexe: %s, Âge: %s, Force: %d, Rang: %s, Niveau: %.2f\n",
                nom, sexe, categorieAge, force, rang, niveau);
    }

    /**
     * Méthode permettant d'ajouter un observateur à la liste des observateurs
     * @param listener : observateur à ajouter à la liste des observateurs
     */
    public void ajouterListener(HurlementListener listener) {
        listeners.add(listener);
    }

    /**
     * Méthode permettant de notifier les observateurs
     * @param typeHurlement : type du hurlement émis par un lycanthrope
     */
    public void notifierListeners(String typeHurlement) {
        for (HurlementListener listener : listeners) {
            listener.reagirAuHurlement(typeHurlement, this);
        }
    }

    /**
     * Méthode permettant à un lycanthrope de hurler
     * @param typeHurlement : type de hurlement émis par un lycanthrope
     */
    public void hurler(String typeHurlement) {
        System.out.println(nom + " hurle : " + typeHurlement);
    }

    /**
     * Méthode permettant de modifier le rang d'un lycanthrope
     * @param rang : rang du lycanthrope à définir
     */
    public void setRang(String rang) {
        this.rang = rang;
        this.niveau = calculerNiveau();
    }

    /**
     * Méthode permettant de modifier le facteur de domination d'un lycanthrope
     * @param facteurDomination : facteur de domination du lycanthrope à définir
     */
    public void setFacteurDomination(int facteurDomination) {
        this.facteurDomination = facteurDomination;
        this.niveau = calculerNiveau();
    }

    /**
     * Méthode permettant de modifier la meute d'un lycanthrope
     * @param meute : meute du lycanthrope à définir
     */
    public void setMeute(Meute meute) {
        this.meute = meute;
    }

    /**
     * Méthode permettant de récupérer le sexe d'un lycanthrope
     * @return le sexe du lycanthrope
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * Méthode permettant de récupérer le rang d'un lycanthrope
     * @return le rang du lycanthrope
     */
    public String getRang() {
        return rang;
    }

    /**
     * Méthode permettant de récupérer le niveau d'un lycanthrope
     * @return le niveau du lycanthrope
     */
    public double getNiveau() {
        return niveau;
    }

    /**
     * Méthode permettant de modifier le niveau d'un lycanthrope
     * @param niveau : niveau du lycanthrope à définir
     */
    public void setNiveau(double niveau) {
        this.niveau = niveau;
    }

    /**
     * Méthode permettant de modifier le sexe d'un lycanthrope
     * @param sexe : sexe du lycanthrope à définir
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * Méthode permettant de modifier la catégorie d'âge d'un lycanthrope
     * @param categorieAge : catégorie d'âge à définir (Jeune / Adulte / Vieux)
     */
    public void setCategorieAge(String categorieAge) {
        this.categorieAge = categorieAge;
    }

    /**
     * Méthode permettant de modifier la force d'un lycanthrope
     * @param force : force à définir
     */
    public void setForce(int force) {
        this.force = force;
    }

    /**
     * Méthode permettant de récupérer la force d'un lycanthrope
     * @return la force du lycanthrope
     */
    public int getForce() {
        return force;
    }

    /**
     * Méthode permettant de récupérer le facteur de domination d'un lycanthrope
     * @return le facteur de domination du lycanthrope
     */
    public int getFacteurDomination() {
        return facteurDomination;
    }

    /**
     * Méthode permettant de récupérer la catégorie d'âge d'un lycanthrope
     * @return la catégorie d'âge du lycanthrope
     */
    public String getCategorieAge() {
        return categorieAge;
    }

    /**
     * Méthode permettant de modifier l"état d'un lycanthrope
     * @param etat : état du lycanthrope à définir
     */
    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * Méthode permettant d'afficher un Lycanthrope et ses attributs sous forme de String
     * @return le Lycanthrope et ses attributs en format String
     */
    @Override
    public String toString() {
        return "Lycanthrope{" + "sexe='" + sexe + '\'' + ", rang='" + rang + '\'' + '}';
    }

    /**
     * Méthode permettant d'incrémenter le facteur de domination d'un lycanthrope
     */
    public void augmenterFacteurDomination() {
        setFacteurDomination(getFacteurDomination()+1);
        setNiveau(calculerNiveau());
    }

    /**
     * Méthode permettant de décrémenter le facteur de domination d'un Lycanthrope
     */
    public void baisserFacteurDomination() {
        setFacteurDomination(getFacteurDomination()-1);
        setNiveau(calculerNiveau());
    }

    /**
     * Méthode permettant d'affecter un rang aléatoire à un Lycanthrope
     */
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

    /**
     * Méthode permettant à un lycanthrope de quitter sa meute
     */
    public void quitterMeute() {
        meute.retirerMembre(this);
        meute = null;
    }

    /**
     * Méthode permettant de récupérer le nom d'un lycanthrope
     * @return le nom du lycanthrope
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de modifier le nom d'un lycanthrope
     * @param nom : nom du lycanthrope à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode permettant de récupérer le facteur d'impétuosité d'un lycanthrope
     * @return le facteur d'impétuosité du lycanthrope
     */
    public double getFacteurImpetuosite() {
        return facteurImpetuosite;
    }

    /**
     * Méthode permettant de modifier le facteur d'impétuosité d'un lycanthrope
     * @param facteurImpetuosite : facteur d'impétuosité du lycanthrope à définir
     */
    public void setFacteurImpetuosite(double facteurImpetuosite) {
        this.facteurImpetuosite = facteurImpetuosite;
    }

    /**
     * Méthode permettant de récupérer l'état d'un lycanthrope
     * @return l'état du lycanthrope
     */
    public String getEtat() {
        return etat;
    }

    /**
     * Méthode permettant de récupérer la meute d'un lycanthrope
     * @return la meute du lycanthrope
     */
    public Meute getMeute() {
        return meute;
    }
}

