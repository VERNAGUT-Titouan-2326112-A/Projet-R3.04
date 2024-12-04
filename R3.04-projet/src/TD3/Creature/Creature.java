package TD3.Creature;

import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant une créature dans un hôpital fantastique.
 * Une créature peut avoir des caractéristiques spécifiques, des maladies et
 * interagir avec son environnement, notamment un hôpital fantastique.
 */
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
    private String type;
    private boolean VIP = false;
    private boolean triage = false;

    /**
     * Constructeur d'une créature.
     * @param nom : nom de la créature
     * @param moral : niveau de moral de la créature
     * @param sexe : sexe de la créature
     * @param poids : poids de la créature
     * @param taille : taille de la créature
     * @param age : âge de la créature
     * @param regenerable : si la créature est régénérable
     * @param contaminant : si la créature est contaminante
     * @param maladies : liste des maladies de la créature
     */
    public Creature(String nom,int moral, String sexe, double poids, double taille, int age, boolean regenerable, boolean contaminant, List<Maladie> maladies) {
        this.nom = nom;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moral = moral;
        this.maladies = maladies != null ? maladies : new ArrayList<>();
        this.regenerable = regenerable;
        this.contaminant = contaminant;

    }

    /**
     * Méthode abstraite pour attendre.
     */
    public void attendre() {
        if (this.VIP){
            VIP();
        }else if (this.triage){
            Triage();
        }
        if (this.moral > 0) {
            this.moral -= 10;
            if (this.moral <= 50) {
                hurler();
            }
        }
    }

    /**
     * Méthode pour hurler.
     */
    public void hurler() {
        System.out.println(nom + " hurle de désespoir !");
        if (moral <= -50) {
            sEmporter();
        }
    }

    /**
     * Méthode pour s'emporter.
     */
    public void sEmporter() {
        System.out.println(nom + " s'emporte !");
        setMoral(moral - 20);
    }

    /**
     * Méthode pour tomber malade.
     * @param maladie : maladie dont la créature tombe malade
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    public void tomberMalade(Maladie maladie , HopitalFantastique hopital) {
        maladies.add(maladie);
        System.out.println(nom + " tombe malade de " + maladie);
        if (maladies.size() >= 3) {
            trepasser(hopital);
        }
    }

    /**
     * Méthode pour soigner une créature.
     */
    public void soigner() {
        if (!maladies.isEmpty()) {
            maladies.removeFirst();
            this.moral += 20;
            System.out.println(nom + " a été soigné !");
        }
        if (moral>100){
            moral=100;
            System.out.println(nom + " a retrouvé son moral");
        }
    }

    /**
     * Méthode pour faire mourir une créature.
     * @param hopital : hôpital fantastique auquel appartient la créature
     */
    public void trepasser(HopitalFantastique hopital) {
        System.out.println(nom + " est mort...");
        hopital.getCreatures().remove(this);

    }

    /**
     * Méthode pour effectuer une action de créature appartenant au triage.
     */
    public void Triage(){
        System.out.println(nom + " patiente");

    }

    /**
     * Méthode pour effectuer une action de créature appartenant aux VIP.
     */
    public void VIP(){
        System.out.println(nom + " patiente mais ne va pas tarder à perdre sa patience");
    }

    /**
     * Méthode pour récuperer le type de la créature.
     * @return le type de la créature
     */
    public String getType() {
        return type;
    }

    /**
     * Méthode pour définir le type de la créature.
     * @param type : type de la créature
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Méthode pour récupérer le nom de la créature.
     * @return le nom de la créature
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour récupérer le sexe de la créature.
     * @return le sexe de la créature
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * Méthode pour récupérer le poids de la créature.
     * @return le poids de la créature
     */
    public double getPoids() {
        return poids;
    }

    /**
     * Méthode pour récupérer la taille de la créature.
     * @return la taille de la créature
     */
    public double getTaille() {
        return taille;
    }

    /**
     * Méthode pour récupérer l'âge de la créature.
     * @return l'âge de la créature
     */
    public int getAge() {
        return age;
    }

    /**
     * Méthode pour récupérer le moral de la créature.
     * @return le moral de la créature
     */
    public int getMoral() {
        return moral;
    }

    /**
     * Méthode pour récupérer les maladies de la créature.
     * @return les maladies de la créature
     */
    public List<Maladie> getMaladies() {
        return maladies;
    }

    /**
     * Méthode pour définir le nom de la créature.
     * @param nom : nom de la créature
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode pour définir le sexe de la créature.
     * @param sexe : sexe de la créature
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * Méthode pour définir le poids de la créature.
     * @param poids : poids de la créature
     */
    public void setPoids(double poids) {
        this.poids = poids;
    }

    /**
     * Méthode pour définir la taille de la créature.
     * @param taille : taille de la créature
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

    /**
     * Méthode pour définir l'âge de la créature.
     * @param age : âge de la créature
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Méthode pour définir le moral de la créature.
     * @param moral : moral de la créature
     */
    public void setMoral(int moral) {
        this.moral = moral;
        if (this.moral > 100) {
            this.moral = 100;
        }
        if (this.moral <= 0) {
            this.moral = 0;
        }
    }

    /**
     * Méthode pour définir les maladies de la créature.
     * @param maladies : maladies de la créature
     */
    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }

    /**
     * Méthode pour définir si la créature est régénérable.
     * @return si la créature est régénérable
     */
    public boolean isRegenerable() {
        return regenerable;
    }

    /**
     * Méthode pour définir si la créature est contaminante.
     * @return si la créature est contaminante
     */
    public boolean isContaminant() {
        return contaminant;
    }

    /**
     * Méthode pour définir si la créature est régénérable.
     * @param regenerable : si la créature est régénérable
     */
    public void setRegenerable(boolean regenerable) {
        this.regenerable = regenerable;
    }

    /**
     * Méthode pour définir si la créature est contaminante.
     * @param contaminant : si la créature est contaminante
     */
    public void setContaminant(boolean contaminant) {
        this.contaminant = contaminant;
    }

    /**
     * Méthode pour afficher les caractéristiques de la créature.
     */
    @Override
    public String toString() {
        return "nom=" + nom + ", sexe=" + sexe + ", poids=" + poids + ", taille=" + taille + ", age=" + age
                + ", moral=" + moral + ", maladies=" + maladies + "]";
    }

    /**
     * Méthode pour vérifier si la créature est malade.
     * @return si la créature est malade
     */
    public boolean estMalade() {
        return !maladies.isEmpty();
    }

    /**
     * Méthode pour afficher les caractéristiques de la créature.
     */
    public void afficherCaracteriques() {
        System.out.println("nom=" + nom + ", sexe=" + sexe + ", poids=" + poids + ", taille=" + taille + ", age=" + age
                + ", moral=" + moral + ", maladies=" + maladies + ", regenerable=" + regenerable + ", contaminant=" + contaminant);
    }

    /**
     * Méthode pour définir si la créature est VIP.
     * @param VIP : si la créature est VIP
     */
    public void setVIP(boolean VIP) {
        this.VIP = VIP;
    }

    /**
     * Méthode pour définir si la créature est en triage.
     * @param triage : si la créature est en triage
     */
    public void setTriage(boolean triage) {
        this.triage = triage;
    }


    /**
     * Méthode pour faire contaminer d'autres créatures par la créature.
     * @param hopital : hôpital fantastique avec lequel la créature interagit
     */
    protected void contaminer(HopitalFantastique hopital){
        List<Creature> creatures = new ArrayList<>(hopital.getCreatures());
        for (Creature creature : creatures) {
            Random random = new Random();
            Maladie maladie = this.getMaladies().get(random.nextInt(this.getMaladies().size()));
            creature.tomberMalade(maladie, hopital);
        }
    }

    /**
     * Méthode pour démoraliser les créatures de l'hôpital.
     * @param hopital : hôpital fantastique avec lequel la créature interagit
     */
    protected void demoraliser(HopitalFantastique hopital){
        List<Creature> creatures = hopital.getCreatures();
        for (Creature creature : creatures) {
            creature.attendre();
            System.out.println(creature.getNom() + " est démoralisé par la mort de " + getNom());
        }
    }

    /**
     * Méthode pour régénérer une créature après sa mort.
     * @param hopital : hôpital fantastique avec lequel la créature interagit
     */
    public void regenerer(HopitalFantastique hopital){
        if (this.isRegenerable()){
            this.setMoral(50);
            System.out.println(getNom() + " se régénère après sa mort !");
            if (!this.maladies.isEmpty()){
                this.maladies.removeLast();
            }
            hopital.getCreatures().add(this);
        }

    }

    /**
     * Méthode pour vérifier si la créature est VIP.
     */
    public boolean getVIP() {
        return VIP;
    }

    /**
     * Méthode pour vérifier si la créature est en triage.
     */
    public boolean getTriage() {
        return triage;
    }
}
