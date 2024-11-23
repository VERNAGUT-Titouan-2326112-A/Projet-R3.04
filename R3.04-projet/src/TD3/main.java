package TD3;

import TD3.Hopital.CentreDeQuarantaine;
import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Medecin;
import TD3.Hopital.ServiceMedical;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for doctor's details
        System.out.print("Entrez le nom du médecin: ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le sexe du médecin: ");
        String sexe = scanner.nextLine();

        System.out.print("Entrez l'âge du médecin: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Create a new doctor with user input
        Medecin userMedecin = new Medecin(nom, sexe, age);

        HopitalFantastique hopital = new HopitalFantastique("Fantasy Hospital", 10);
        hopital.ajouterMedecin(userMedecin);

        // Création et ajout de services médicaux
        ServiceMedical quarantaineOrcs = new CentreDeQuarantaine("Quarantaine des Orcs", 200, 5, "médiocre", true);
        hopital.ajouterService(quarantaineOrcs);

        // Appel de la méthode pour gérer le menu
        userMedecin.gererMenu(quarantaineOrcs, quarantaineOrcs, scanner);
    }
}
