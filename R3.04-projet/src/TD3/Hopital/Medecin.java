package TD3.Hopital;

import TD3.Creature.Creature;
import TD3.Hopital.Maladie;
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

    public void gererMenu(ServiceMedical service1, ServiceMedical service2, Scanner scanner) {
        while (true) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    examinerService(service1, service2, scanner);
                    break;
                case 2:
                    service1.soignerCreatures();

                    break;
                case 3:
                    service1.revisionBudget();
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
        System.out.println("_______________________________________________");
        System.out.println("|                   Menu:                     |");
        System.out.println("|1. Examiner un service médical               |");
        System.out.println("|_____________________________________________|");
        System.out.println("|2. Soigner les créatures d'un service médical|");
        System.out.println("|_____________________________________________|");
        System.out.println("|3. Réviser le budget d'un service médical    |");
        System.out.println("|_____________________________________________|");
        System.out.println("|4. Transférer une créature                   |");
        System.out.println("|_____________________________________________|");
        System.out.println("|5. Quitter                                   |");
        System.out.println("|_____________________________________________|");
    }

    public void examinerService(ServiceMedical service1, ServiceMedical service2, Scanner scanner) {
        while (true) {
            System.out.println("____________________________________________");
            System.out.println("|Choisissez un service médical à examiner :|");
            System.out.println("|__________________________________________|");
            System.out.println("|1. " + service1.getNom() + "                     |");
            System.out.println("|__________________________________________|");
            System.out.println("|2. " + service2.getNom() + "                    |");
            System.out.println("|__________________________________________|");
            System.out.println("|3. Retour au menu principal               |");
            System.out.println("|__________________________________________|");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choix) {
                case 1:
                    afficherCreatures(service1);
                    break;
                case 2:
                    afficherCreatures(service2);
                    break;
                case 3:
                    return; // Retour au menu principal
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void afficherCreatures(ServiceMedical service) {
        System.out.println("Examen du service médical : " + service.getNom());
        System.out.println("Liste des créatures dans ce service :");
        for (Creature creature : service.getCreatures()) {
            System.out.println("Nom: " + creature.getNom());
            System.out.println("Moral: " + creature.getMoral());
            System.out.println("Sexe: " + creature.getSexe());
            System.out.println("Poids: " + creature.getPoids() + " kg");
            System.out.println("Taille: " + creature.getTaille() + " m");
            System.out.println("Age: " + creature.getAge() + " ans");
            System.out.println("Maladies: ");
            for (Maladie maladie : creature.getMaladies()) {
                System.out.println("  - " + maladie.getNomComplet() + " (" + maladie.getNomAbrege() + ") - Niveau: " + maladie.getNiveauActuel() + "/" + maladie.getNiveauMax());
            }
            System.out.println("-----------------------------------");
        }
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