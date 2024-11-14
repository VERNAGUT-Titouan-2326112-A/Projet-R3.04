package Medecin;

import Creature.Creature;

import java.util.Scanner;

public class Medecin {
    String nom;
    String sexe;
    int age;

    public Medecin(String nom, String sexe, int age) {
        this.nom = nom;
        this.sexe = sexe;
        this.age = age;
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
    // Méthode qui gère le menu avec switch-case
    public void gererMenu(ServiceMedical service1, ServiceMedical service2, Scanner scanner) {
        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    examinerService(service1);
                    break;
                case 2:
                    soignerCreatures(service1);
                    break;
                case 3:
                    revisionBudget(service1);
                    break;
                case 4:
                    System.out.println("Entrez le nom de la créature à transférer : ");
                    String nomCreature = scanner.nextLine();
                    Creature creature = chercherCreatureParNom(nomCreature, service1, service2);
                    if (creature != null) {
                        transfererCreature(creature, service1, service2);
                    } else {
                        System.out.println("Créature non trouvée.");
                    }
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Essayez à nouveau.");
            }
        }
    }

    private void afficherMenu() {
        System.out.println("Menu:");
        System.out.println("1. Examiner un service médical");
        System.out.println("2. Soigner les créatures d'un service médical");
        System.out.println("3. Réviser le budget d'un service médical");
        System.out.println("4. Transférer une créature");
        System.out.println("5. Quitter");
    }

    public void examinerService(ServiceMedical service) {
        System.out.println("Examen du service médical : " + service.getNom());
        System.out.println("Liste des créatures dans ce service :" + service.getCreatures());
        for (Creature creature : service.getCreatures()) {
            System.out.println("- " + creature.getNom());
        }
    }

    public void soignerCreatures(ServiceMedical service) {
        for (Creature creature : service.getCreatures()) {
            System.out.println("Le médecin " + nom + " soigne " + creature.getNom());
        }
    }

    public void revisionBudget(ServiceMedical service) {
        System.out.println("Le budget du service " + service.getNom() + " est de " + service.getBudget() + " unités.");
    }

    public void transfererCreature(Creature creature, ServiceMedical serviceFrom, ServiceMedical serviceTo) {
        if (serviceFrom.getCreatures().contains(creature)) {
            serviceFrom.getCreatures().remove(creature);
            serviceTo.ajouterCreature(creature);
            System.out.println("La créature " + creature.getNom() + " a été transférée de " + serviceFrom.getNom() + " à " + serviceTo.getNom());
        } else {
            System.out.println("La créature n'est pas présente dans ce service.");
        }
    }

    // Chercher une créature par son nom
    public Creature chercherCreatureParNom(String nom, ServiceMedical service1, ServiceMedical service2) {
        for (Creature creature : service1.getCreatures()) {
            if (creature.getNom().equals(nom)) {
                return creature;
            }
        }
        for (Creature creature : service2.getCreatures()) {
            if (creature.getNom().equals(nom)) {
                return creature;
            }
        }
        return null;
    }
}
