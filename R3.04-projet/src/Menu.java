import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public void afficherMenu() {
        System.out.println("Bienvenue dans le jeu!");
        System.out.println("1. Démarrer le jeu");
        System.out.println("2. Options");
        System.out.println("3. Quitter");
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
        System.out.println("Bienvenue dans le menu d'aide!");
        System.out.println("1. Principe du jeu");
        System.out.println("2. Comment jouer");
        System.out.println("3. Retour au menu principal");
    }

    public void gererAide() {
        Scanner scanner = new Scanner(System.in);
        int choixA = scanner.nextInt();

        switch (choixA) {
            case 1:
                principeJeu();
                break;
            case 2:
                commandeJoueur();
                break;
            case 3:
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
        System.out.println("Bienvenue dans les explications du jeu");
        System.out.println("Le but du jeu est simple");


    }



    private void commandeJoueur() {
        clearConsole();
        System.out.println("Bienvenue dans le menu de commande!");

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

