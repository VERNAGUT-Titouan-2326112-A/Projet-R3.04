package TD4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant une meute de Lycanthropes
 */
public class Meute implements HurlementListener {
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;
    private final List<Lycanthrope> membres;
    private String nom;
    private static int DERNIER_ID = 0;
    private final int id;
    private final Colonie colonie;

    /**
     * Constructeur de la classe Meute
     * @param colonie Colonie à laquelle appartient la meute
     */
    public Meute(Colonie colonie) {
        this.colonie = colonie;
        this.membres = new ArrayList<>();
        ++DERNIER_ID;
        this.id = DERNIER_ID;
        this.nom = "Meute" + id;
    }

    /**
     * Méthode permettant d'afficher les caractéristiques de la meute
     */
    public void afficherCaracteristiques() {
        System.out.println("Couple Alpha :");
        if (maleAlpha != null) maleAlpha.afficherCaracteristiques();
        if (femelleAlpha != null) femelleAlpha.afficherCaracteristiques();
        System.out.println("Membres de la meute :");
        for (Lycanthrope membre : membres) {
            membre.afficherCaracteristiques();
        }
    }

    /**
     * Méthode permettant de définir ou modifier le lycanthrope mâle alpha de la meute
     * @param maleAlpha : lycanthrope qui va devenir le mâle alpha de la meute
     */
    public void setMaleAlpha(Lycanthrope maleAlpha) {
        this.maleAlpha = maleAlpha;
        this.maleAlpha.setRang("α");
        this.maleAlpha.calculerNiveau();
    }

    /**
     * Méthode permettant de définir ou modifier le lycanthrope femelle alpha de la meute
     * @param femelleAlpha : lycanthrope qui va devenir la femelle alpha de la meute
     */
    public void setFemelleAlpha(Lycanthrope femelleAlpha) {
        this.femelleAlpha = femelleAlpha;
        this.femelleAlpha.setRang("α");
        this.femelleAlpha.calculerNiveau();
    }

    /**
     * Méthode permettant de créer la hiérarchie de la meute
     */
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

    /**
     * Méthode permettant d'ajouter un lycanthrope à la meute
     * @param lycanthrope : lycanthrope à ajouter à la meute
     */
    public void ajouterMembre(Lycanthrope lycanthrope) {
        membres.add(lycanthrope);
        lycanthrope.ajouterListener(this);
        lycanthrope.setMeute(this);
    }

    /**
     * Méthode permettant de réagir au hurlement d'un lycanthrope parmi la meute
     * @param typeHurlement : type de hurlement émis
     * @param emetteur : lycanthrope à l'origine du hurlement
     */
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

    /**
     * Méthode permettant de récupérer le lycanthrope mAle alpha de la meute
     * @return le lycanthrope Mâle alpha de la meute
     */
    public Lycanthrope getMaleAlpha() {
        return maleAlpha;
    }

    /**
     * Méthode permettant de récupérer le lycanthrope femelle alpha de la meute
     * @return le lycanthrope Femelle alpha de la meute
     */
    public Lycanthrope getFemelleAlpha() {
        return femelleAlpha;
    }

    /**
     * Méthode permettant de récupérer les membres de la meute
     * @return la liste des membres de la meute
     */
    public List<Lycanthrope> getMembres() {
        return membres;
    }

    /**
     * Méthode permettant de retirer un lycanthrope de la meute
     * @param lycanthrope : lycanthrope à retirer de la meute
     */
    public void retirerMembre(Lycanthrope lycanthrope) {
        membres.remove(lycanthrope);
    }

    /**
     * Méthode permettant de récupérer le nom de la meute
     * @return le nom de la meute
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de définir le nom de la meute
     * @param nom : nom de la meute à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMembres(List<Lycanthrope> membres) {
        this.membres.clear();
        this.membres.addAll(membres);
    }
}

