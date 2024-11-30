package TD4;

import java.util.ArrayList;
import java.util.Arrays;


public class ConflitCommand implements Command {
    private final Lycanthrope agresseur;
    private final Lycanthrope cible;
    private final Meute meute;
    private final Colonie colonie;

    public ConflitCommand(Lycanthrope agresseur, Lycanthrope cible, Meute meute , Colonie colonie) {
        this.agresseur = agresseur;
        this.cible = cible;
        this.meute = meute;
        this.colonie = colonie;
    }

    @Override
    public void execute() {
        ArrayList<String> rangs = new ArrayList<>(Arrays.asList("α","β", "γ", "δ", "ε", "ζ", "η", "θ", "ω"));
        System.out.println(agresseur.getNom() + " tente de dominer " + cible.getNom());
        if (cible.equals(meute.getFemelleAlpha())){ // Si la cible est la femelle alpha
            System.out.println("La femelle alpha ne peut pas être attaquée.");
        } else if (cible.getRang().equals("ω")){
            System.out.println(agresseur.getNom() + " domine " + cible.getNom());
            agresseur.augmenterFacteurDomination();
            cible.baisserFacteurDomination();
            agresseur.hurler("AOOOOUHHHH");
            agresseur.afficherCaracteristiques();
            agresseur.notifierListeners("aggression");
            cible.hurler("AIH");
            cible.afficherCaracteristiques();
            cible.notifierListeners("soumission");
            agresseur.setNiveau(agresseur.calculerNiveau());
            cible.setNiveau(cible.calculerNiveau());
        } else if (agresseur.calculerNiveau() > cible.calculerNiveau()){
            System.out.println(agresseur.getNom() + " domine " + cible.getNom());
            if (rangs.indexOf(agresseur.getRang()) < rangs.indexOf(cible.getRang())){
                String tmp1 = agresseur.getRang();
                String tmp2 = cible.getRang();
                cible.setRang(tmp1);
                agresseur.setRang(tmp2);
                if (cible.equals(meute.getMaleAlpha())){
                    Lycanthrope tmp =  meute.getFemelleAlpha();
                    Lycanthrope tmp3 = tmp;
                    for (Lycanthrope lycanthrope : meute.getMembres()){
                        if (lycanthrope.getSexe().equals("Femelle") && lycanthrope.getNiveau() > tmp3.getNiveau()){
                            tmp3 = lycanthrope;
                        }
                    }
                    meute.setFemelleAlpha(tmp3);
                    if(meute.getFemelleAlpha() != tmp){
                        System.out.println("Le couple alpha a changé.");
                        tmp.setRang(cible.getRang());
                        tmp.setNiveau(tmp.calculerNiveau());
                    }
                    meute.setMaleAlpha(agresseur);
                    System.out.println(agresseur.getNom() + "devient le nouveau mâle alpha.");
                    System.out.println(meute.getFemelleAlpha().getNom() + "devient la nouvelle femelle alpha.");
                }
            }
            agresseur.augmenterFacteurDomination();
            cible.baisserFacteurDomination();
            agresseur.hurler("AOUH");
            agresseur.afficherCaracteristiques();
            agresseur.notifierListeners("domination");
            cible.hurler("AIH");
            cible.afficherCaracteristiques();
            cible.notifierListeners("soumission");
        } else {
            System.out.println(agresseur.getNom() + " n'arrive pas à dominer " + cible.getNom());
            agresseur.baisserFacteurDomination();
            cible.augmenterFacteurDomination();
            agresseur.hurler("AIH");
            agresseur.afficherCaracteristiques();
            agresseur.notifierListeners("soumission");
            cible.hurler("AOUH");
            cible.afficherCaracteristiques();
            cible.notifierListeners("domination");
            agresseur.setNiveau(agresseur.calculerNiveau());
            cible.setNiveau(cible.calculerNiveau());
            if (cible.equals(meute.getMaleAlpha())){
               double proba =  Math.random();
                if (proba < 0.2) {
                    agresseur.quitterMeute();
                    System.out.println(agresseur.getNom() + " quitte la meute.");
                    colonie.ajouterSolitaire(agresseur);
                }
            }
        }
    }
}

