package TD3.Hopital;

import TD3.Creature.Creature;

/**
 * Classe Medecin représentant un médecin dans un hôpital
 */
public class Medecin {
    String nom;
    String sexe;
    int age;
    String type ;
    private int id;
    private static int DERNIER_ID = 0;

    /**
     * Constructeur de la classe Medecin
     * @param sexe le sexe du médecin
     * @param age l'âge du médecin
     * @param type le type de créature gérée par le médecin
     */
    public Medecin(String sexe, int age, String type) {
        this.id = DERNIER_ID++;
        this.nom = "Medecin " + id;
        this.sexe = sexe;
        this.age = age;
        this.type = type ;
    }

    /**
     * Méthode pour changer le nom du médecin
     * @param nom le nouveau nom du médecin
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode pour changer le sexe du médecin
     * @param sexe le nouveau sexe du médecin
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * Méthode pour changer l'âge du médecin
     * @param age le nouvel âge du médecin
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Méthode pour obtenir le nom du médecin
     * @return le nom du médecin
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir le sexe du médecin
     * @return le sexe du médecin
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * Méthode pour obtenir l'âge du médecin
     * @return l'âge du médecin
     */
    public int getAge() {
        return age;
    }

    /**
     * Méthode pour afficher les caractéristiques du médecin
     */
    public void afficherDetails() {
        System.out.println("Nom: " + nom +", Sexe: " + sexe+", Age: " + age + ", Type: " + type);
    }

    /**
     * Méthode pour obtenir le type de créature gérée par le médecin
     * @return le type de créature gérée par le médecin
     */
    public String getType(){
        return this.type;
    }

    /**
     * Méthode pour inspecter un service médical
     * @param service le service médical à inspecter
     */
    public void inspecterService(ServiceMedical service){
        service.afficherDetails();
        for (Creature creature : service.getCreatures()) {
            creature.afficherCaracteriques();
        }
    }

    /**
     * Méthode pour transférer une créature d'un service médical à un autre
     * @param creature la créature à transférer
     * @param service1 le service médical de départ
     * @param service2 le service médical d'arrivée
     */
    public void transfererCreature(Creature creature, ServiceMedical service1, ServiceMedical service2){
        service1.enleverCreature(creature);
        service2.ajouterCreature(creature);
    }
}