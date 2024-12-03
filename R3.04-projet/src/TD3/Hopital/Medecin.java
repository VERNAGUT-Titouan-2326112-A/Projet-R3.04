package TD3.Hopital;

import TD3.Creature.Creature;


public class Medecin {
    String nom;
    String sexe;
    int age;
    String type ;

    public Medecin(String sexe, int age, String type) {
        this.sexe = sexe;
        this.age = age;
        this.type = type ;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public int getAge() {
        return age;
    }

    public void afficherDetails() {
        System.out.println("Nom: " + nom +", Sexe: " + sexe+", Age: " + age + ", Type: " + type);
    }

    public String getType(){
        return this.type;
    }

    public void inspecterService(ServiceMedical service){
        service.afficherDetails();
        for (Creature creature : service.getCreatures()) {
            creature.afficherCaractériques();
        }
    }

    public void transfererCreature(Creature creature, ServiceMedical service1, ServiceMedical service2){
        service1.enleverCreature(creature);
        service2.ajouterCreature(creature);
    }
}