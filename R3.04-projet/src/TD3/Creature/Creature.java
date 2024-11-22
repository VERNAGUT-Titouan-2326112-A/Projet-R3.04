package TD3.Creature;

import TD3.Hopital.Maladie;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature {
    private String nom;
    private String sexe;
    private double poids;
    private double taille;
    private int age;
    private int moral;
    private List<Maladie> maladies;
    private boolean regenerable;
    private boolean contaminant;

    public Creature(String nom, String sexe, double poids, double taille, int age, boolean regenerable, boolean contaminant) {
        this.nom = nom;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moral = 100;
        this.maladies = new ArrayList<>();
        this.regenerable = regenerable;
        this.contaminant = contaminant;
    }

    public void attendre() {
        this.moral -= 10;
        if (this.moral <= 0) {
            hurler();
        }
    }

    public void hurler() {
        System.out.println(nom + " hurle de désespoir !");
        if (moral <= -50) {
            sEmporter();
        }
    }

    public void sEmporter() {
        System.out.println(nom + " s'emporte !");
        contaminer();
    }

    public void tomberMalade(Maladie maladie) {
        maladies.add(maladie);
        System.out.println(nom + " tombe malade de " + maladie);
        if (maladies.size() >= 3) {
            trepasser();
        }
    }

    public void soigner() {
        if (!maladies.isEmpty()) {
            maladies.remove(0);
            this.moral += 20;
            System.out.println(nom + " a été soigné !");
        }
    }

    public void trepasser() {
        System.out.println(nom + " est mort...");
    }
    public void Triage(){
        System.out.println(nom + " patiente");

    }
    public void VIP(){
        System.out.println(nom + " patiente mais ne va pas tarder à perdre sa patiente");
    }
    // Méthode abstraite pour spécialiser le comportement de contamination dans les sous-classes
    protected abstract void contaminer();

    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public double getPoids() {
        return poids;
    }

    public double getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getMoral() {
        return moral;
    }

    public List<Maladie> getMaladies() {
        return maladies;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }

    public boolean isRegenerable() {
        return regenerable;
    }

    public boolean isContaminant() {
        return contaminant;
    }

    public void setRegenerable(boolean regenerable) {
        this.regenerable = regenerable;
    }

    public void setContaminant(boolean contaminant) {
        this.contaminant = contaminant;
    }

    @Override
    public String toString() {
        return "TD3.Creature.TD3.Creature [nom=" + nom + ", sexe=" + sexe + ", poids=" + poids + ", taille=" + taille + ", age=" + age
                + ", moral=" + moral + ", maladies=" + maladies + "]";
    }

    public boolean estMalade() {
        return !maladies.isEmpty();
    }
}
