package TD3;

import TD3.Hopital.CentreDeQuarantaine;
import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Medecin;
import TD3.Hopital.ServiceMedical;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        HopitalFantastique hopital = new HopitalFantastique("Hopital Fantastique",1000);
        hopital.menu();
    }
}
