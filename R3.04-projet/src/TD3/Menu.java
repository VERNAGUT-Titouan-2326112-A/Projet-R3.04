package TD3;


import TD3.Creature.Creature;
import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Medecin;
import TD3.Hopital.ServiceMedical;
import com.sun.tools.javac.Main;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

public class Menu {
    public void afficherMenu() {
        System.out.println("___________________________________");
        System.out.println("|         TD3.Menu Principal:     |");
        System.out.println("|_________________________________|");
        System.out.println("|Bienvenue dans le jeu!           |");
        System.out.println("|_________________________________|");
        System.out.println("|1. Démarrer le jeu               |");
        System.out.println("|_________________________________|");
        System.out.println("|2. Options                       |");
        System.out.println("|_________________________________|");
        System.out.println("|3. Quitter                       |");
        System.out.println("|_________________________________|");
    }

    public void gererSelection() {
        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                demarrerJeu();
                break;
            case 2:
                afficherExplication();
                break;
            case 3:
                quitter();
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                afficherMenu();
                gererSelection();
                break;
        }
    }


    private void demarrerJeu() {
        System.out.println("Le jeu commence...");
        // Logique pour démarrer le jeu
        Scanner scanner = new Scanner(System.in);

        // Prompt user for doctor's details
        System.out.print("Entrez le nom du médecin: ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le sexe du médecin (Male ou Femelle) : ");
        String sexe = scanner.nextLine();

        System.out.print("Entrez l'âge du médecin: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        // Create the hospital and generate random creatures
        HopitalFantastique hopital = new HopitalFantastique("Fantasy Hospital", 10);
        HopitalFantastique hopital2 = new HopitalFantastique("Fantasy Hospital", 10);
        List<Creature> creatures = hopital.getCreatures();
        List<Creature> creatures2 = hopital2.getCreatures();
        ServiceMedical service1 = new ServiceMedical("Quarantaine du sud", creatures);
        ServiceMedical service2 = new ServiceMedical("Quarantaine du nord", creatures2);

        hopital.genererCreaturesAleatoires(5); // Ajouter 5 créatures aléatoires
        hopital2.genererCreaturesAleatoires(5); // Ajouter 5 créatures aléatoires

        Random random = new Random();
        Creature randomCreature = creatures.get(random.nextInt(creatures.size()));


    }

    private void afficherExplication() {
        clearConsole();
        System.out.println("Aide du jeu...");
        menuAide();
        gererAide();

    }

    private void quitter() {
        System.out.println("Merci d'avoir joué!");
        System.exit(0);
    }



    private void menuAide() {
        clearConsole();
        System.out.println("____________________________________");
        System.out.println("|  Bienvenue dans le menu d'aide!  |");
        System.out.println("|__________________________________|");
        System.out.println("|1. Principe du jeu                |");
        System.out.println("|__________________________________|");
        System.out.println("|2. Comment jouer                  |");
        System.out.println("|__________________________________|");
        System.out.println("|3. Retour au menu principal       |");
        System.out.println("|__________________________________|");
    }

    public void gererAide() {
        Scanner scanner = new Scanner(System.in);
        int choixA = scanner.nextInt();

        switch (choixA) {
            case 1:
                principeJeu();
                break;
            case 2:
                commentJoueur();
                break;
            case 3:
                clearConsole();
                afficherMenu();
                gererSelection();
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                menuAide();
                gererAide();
                break;
        }
    }

    private void principeJeu() {
        clearConsole();
        System.out.println("____________________________________________________________________________________________");
        System.out.println("|                           Bienvenue dans les explications du jeu                         |");
        System.out.println("|__________________________________________________________________________________________|");
        System.out.println("|Le but du jeu est simple                                                                  |");
        System.out.println("|__________________________________________________________________________________________|");
        System.out.println("|Vous aller incarner différents médecin dans un hopital Fantastique                        |");
        System.out.println("|__________________________________________________________________________________________|");
        System.out.println("|Ces médecin auront le devoir de soigner différents montres qui viendront dans l'hopital   |");
        System.out.println("|__________________________________________________________________________________________|");
        System.out.println("|Cependant vous aurait un nombre de coups maximum avec chaque médecin                      |");
        System.out.println("|__________________________________________________________________________________________|");
        System.out.println("|Certains montres viendront avec des maladies particulière                                 |");
        System.out.println("|__________________________________________________________________________________________|");
        System.out.println("|Bonne chance à vous jeune médecin !                                                       |");
        System.out.println("|__________________________________________________________________________________________|");
        try {
            Thread.sleep(10000); // Attendre 10 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menuAide();
        gererAide();
    }



    private void commentJoueur() {
        clearConsole();
        System.out.println("_________________________________________________________________________________________________________________________________________");
        System.out.println("|Bienvenue dans le menu de comment Jouer!                                                                                               |");
        System.out.println("|_______________________________________________________________________________________________________________________________________|");
        System.out.println("|Pour cela, il faut examiner les services médicaux, soigner les créatures, réviser le budget et transférer des créatures entre services.|");
        System.out.println("|_______________________________________________________________________________________________________________________________________|");
        System.out.println("|Le jeu se termine lorsque le joueur décide de quitter.                                                                                 |");
        System.out.println("|_______________________________________________________________________________________________________________________________________|");
        try {
            Thread.sleep(8000); // Attendre 8 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        menuAide();
        gererAide();
    }



    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Pour Windows, imprimer plusieurs lignes vides
                for (int i = 0; i <= 50; i++) {
                    System.out.println();
                }
            } else {
                // Pour Unix/Linux/MacOS, utiliser les séquences d'échappement ANSI
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception ex) {
            System.out.println("Erreur lors de l'effacement de la console.");

        }
    }
}
