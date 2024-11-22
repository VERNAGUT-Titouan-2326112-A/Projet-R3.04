package TD3;

import TD3.Hopital.CentreDeQuarantaine;
import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Medecin;
import TD3.Hopital.ServiceMedical;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        HopitalFantastique hopital = new HopitalFantastique("Fantasy Hospital", 10);
        Medecin drAbe = new Medecin("Dr. Abe", "homme", 45);
        Medecin drZoey = new Medecin("Dr. Zoey", "femme", 35);

        hopital.ajouterMedecin(drAbe);
        hopital.ajouterMedecin(drZoey);

        // Création et ajout de services médicaux
        ServiceMedical quarantaineOrcs = new CentreDeQuarantaine("Quarantaine des Orcs", 200, 5, "médiocre", true);
        hopital.ajouterService(quarantaineOrcs);

        // Création d'une instance de Scanner
        Scanner scanner = new Scanner(System.in);

        // Appel de la méthode pour gérer le menu
        drAbe.gererMenu(quarantaineOrcs, quarantaineOrcs, scanner);
    }
}
