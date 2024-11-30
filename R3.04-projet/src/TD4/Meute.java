package TD4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Meute implements HurlementListener {
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;
    private final List<Lycanthrope> membres;
    private String nom;
    private static int DERNIER_ID = 0;
    private final int id;
    private final Colonie colonie;

    public Meute(Colonie colonie) {
        this.colonie = colonie;
        this.membres = new ArrayList<>();
        ++DERNIER_ID;
        this.id = DERNIER_ID;
        this.nom = "Meute" + id;
    }

    public void afficherCaracteristiques() {
        System.out.println("Couple Alpha :");
        if (maleAlpha != null) maleAlpha.afficherCaracteristiques();
        if (femelleAlpha != null) femelleAlpha.afficherCaracteristiques();
        System.out.println("Membres de la meute :");
        for (Lycanthrope membre : membres) {
            membre.afficherCaracteristiques();
        }
    }

    public void setMaleAlpha(Lycanthrope maleAlpha) {
        this.maleAlpha = maleAlpha;
        maleAlpha.setRang("α");
        maleAlpha.calculerNiveau();
    }

    public void setFemelleAlpha(Lycanthrope femelleAlpha) {
        this.femelleAlpha = femelleAlpha;
        femelleAlpha.setRang("α");
        maleAlpha.calculerNiveau();
    }

    public void creerHierarchie() {
        int i = 0;
        while (!membres.get(i).getSexe().equals("Mâle") & !membres.get(i).getCategorieAge().equals("Adulte")){
            i++;
        }
        setMaleAlpha(membres.get(i));
        i = 0;
        while (!membres.get(i).getSexe().equals("Femelle") & !membres.get(i).getCategorieAge().equals("Adulte")){
            i++;
        }
        setFemelleAlpha(membres.get(i));
        for (Lycanthrope lycanthrope : membres){
            if (lycanthrope.getCategorieAge().equals("Adulte") && lycanthrope.getSexe().equals("Mâle")&& maleAlpha.getForce() < lycanthrope.getForce()){
                setMaleAlpha(lycanthrope);
                }
                if (lycanthrope.getCategorieAge().equals("Adulte")&& lycanthrope.getSexe().equals("Femelle") && femelleAlpha.getNiveau() < lycanthrope.getNiveau()){
                    setFemelleAlpha(lycanthrope);
                }
            }
        for (Lycanthrope lycanthrope : membres) {
            if (lycanthrope.getRang().equals("α")){
                continue;
            }
            lycanthrope.choisirRangAleatoire();
            lycanthrope.setNiveau(lycanthrope.calculerNiveau());
        }
    }


    public void ajouterMembre(Lycanthrope lycanthrope) {
        membres.add(lycanthrope);
        lycanthrope.ajouterListener(this);
        lycanthrope.setMeute(this);
    }

    @Override
    public void reagirAuHurlement(String typeHurlement, Lycanthrope emetteur) {
        System.out.println("Réaction dans la meute au hurlement de " + emetteur.getNom() + ": " + typeHurlement);
        if (typeHurlement.equals("domination") || typeHurlement.equals("soumission") || typeHurlement.equals("agression")) {
            double proba = Math.random();
            if (proba < 0.2) {
                Random random = new Random();
                int index = random.nextInt(membres.size());
                Lycanthrope react = membres.get(index);
                System.out.println(react.getNom() + " a réagi au cri de " + typeHurlement + " et entre en conflit avec " + emetteur.getNom() + " à l'origine de celui-ci.");
                ConflitCommand conflitCommand = new ConflitCommand(react, emetteur, this, colonie);
                conflitCommand.execute();
            }
        }
        else {
            for (Lycanthrope lycanthrope : getMembres()){
                lycanthrope.hurler(typeHurlement);
            }
        }
    }

    public Lycanthrope getMaleAlpha() {
        return maleAlpha;
    }

    public Lycanthrope getFemelleAlpha() {
        return femelleAlpha;
    }

    public List<Lycanthrope> getMembres() {
        return membres;
    }

    public void retirerMembre(Lycanthrope lycanthrope) {
        membres.remove(lycanthrope);
    }


    public static void ajusterPourSaison(String saison) {
        switch (saison) {
            case "Printemps":
                System.out.println("La meute rentre dans la période de reproduction.");
                break;
            case "Été":
                System.out.println("La meute souffre de la chaleur estivale...");
                break;
            case "Automne":
                System.out.println("La meute se prépare pour l'hiver.");
                break;
            case "Hiver":
                System.out.println("La meute lutte contre le froid.");
                break;
            default:
                break;
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

