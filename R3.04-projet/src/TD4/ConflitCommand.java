package TD4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConflitCommand implements Command {
    private Lycanthrope agresseur;
    private Lycanthrope cible;
    private Meute meute;

    public ConflitCommand(Lycanthrope agresseur, Lycanthrope cible, Meute meute) {
        this.agresseur = agresseur;
        this.cible = cible;
        this.meute = meute;
    }

    @Override
    public void execute() {
        ArrayList<String> rangs = new ArrayList<>(Arrays.asList("α","β", "γ", "δ", "ε", "ζ", "η", "θ", "ω"));
        System.out.println(agresseur + " tente de dominer " + cible);
        if (cible.equals(meute.getFemelleAlpha())){ // Si la cible est la femelle alpha
            System.out.println("La femelle alpha ne peut pas être attaquée.");
        } else if (cible.getRang().equals("ω")){
            System.out.println(agresseur + "domine " + cible);
            agresseur.augmenterFacteurDomination();
            cible.baisserFacteurDomination();
        } else if (agresseur.calculerNiveau() > cible.calculerNiveau()){
            System.out.println(agresseur + "domine " + cible);
            if (rangs.indexOf(agresseur.getRang()) < rangs.indexOf(cible.getRang())){
                String tmp1 = agresseur.getRang();
                String tmp2 = cible.getRang();
                cible.setRang(tmp1);
                agresseur.setRang(tmp2);
            }
            agresseur.augmenterFacteurDomination();
            cible.baisserFacteurDomination();
        } else {
            System.out.println(agresseur + "n'arrive pas à dominer " + cible);
            agresseur.baisserFacteurDomination();
            cible.augmenterFacteurDomination();

        }

    }
}

