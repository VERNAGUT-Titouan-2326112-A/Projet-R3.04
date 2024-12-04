import java.util.Scanner;

import TD3.Hopital.HopitalFantastique;
import TD4.Colonie;

/**
 * Classe pour choisir le jeu à jouer
 */
public class ChoixJeu {

    /**
     * Méthode pour gérer le menu de choix du jeu
     */
    public void choixJeu() {
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        System.out.println("___________________________________");
        System.out.println("|      Bienvenue dans le menu!    |");
        System.out.println("|_________________________________|");
        System.out.println("|          Choix du Jeu:          |");
        System.out.println("|_________________________________|");
        System.out.println("|1. Hopital Fantastique           |");
        System.out.println("|_________________________________|");
        System.out.println("|2. Meute de Lycanthrope          |");
        System.out.println("|_________________________________|");
        System.out.println("|3. Quitter                       |");
        System.out.println("|_________________________________|");
        choix = scanner.nextInt();
        switch (choix) {
            case 1 :
                clearConsole();
                HopitalFantastique hopital = new HopitalFantastique("Νοσοκομείο Φαντασίας", 1000);
                hopital.simulation();
                break;
            case 2 :
                clearConsole();
                Colonie colonie = new Colonie();
                colonie.simulation();
                break;
                case 3 :
                quitter();
                break;
            default:
                System.out.println("Choix invalide. Veuillez choisir un nombre entre 1 et 3.");
                choixJeu();
                break;
        }
        choixJeu();
    }


    /**
     * Méthode pour fermer le programme
     */
    private void quitter() {
        System.out.println("Au revoir!");
        System.exit(0);
    }

    /**
     * Méthode pour effacer la console
     */
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