import java.util.InputMismatchException;
import java.util.Scanner;
import TD3.Menu;
import TD4.main;

public class ChoixJeu {

    public static void main(String[] args) {
        ChoixJeu main = new ChoixJeu();
        main.afficheMenu();
        main.gererSelection();
    }

    public void afficheMenu() {
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
    }

    public void gererSelection() {
        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        try {
            choix = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un chiffre valide.");
            scanner.nextLine(); // Consommer l'entrée incorrecte
            System.exit(1);
        }

        switch (choix) {
            case 1:
                clearConsole();
                lancerMenuHopital();
                break;
            case 2:
                clearConsole();
                lancerMenuLycanthrope();
                break;
            case 3:
                quitter();
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                afficheMenu();
                gererSelection();
                break;
        }
    }

    public void lancerMenuHopital() {
        Menu menu = new Menu();
        menu.afficherMenu();
        menu.gererSelection();
    }

    public void lancerMenuLycanthrope() {
        main.main(new String[]{});
    }

    private void quitter() {
        System.out.println("Au revoir!");
        System.exit(0);
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