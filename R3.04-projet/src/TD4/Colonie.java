package TD4;

import java.util.*;

import static java.lang.System.exit;

public class Colonie {
    private final List<Meute> meutes;
    private final List<Lycanthrope> solitaires;
    private final List<List<Lycanthrope>> groupeDeSolitaires;
    private final GestionnaireDeSaisons gestionnaireDeSaisons;

    /**
     *Constructeur de la classe Colonie
     *
     */
    public Colonie() {
        this.meutes = new ArrayList<>();
        this.solitaires = new ArrayList<>();
        this.groupeDeSolitaires = new ArrayList<>();
        gestionnaireDeSaisons = new GestionnaireDeSaisons(5000, meutes, this);
    }

    /**
     *Méthode permettant d'ajouter une meute à la colonie
     *
     * @param meute : Meute à ajouter à la colonie
     */
    public void ajouterMeute(Meute meute) {
        meutes.add(meute);
    }

    /**
     *Méthode permettant d'afficher les meutes de la colonie
     *
     */
    public void afficherMeutes() {
        System.out.println("Meutes de la colonie (" +meutes.size() + ") :");
        for (Meute meute : meutes) {
            System.out.println(meute.getNom());
        }
    }

    /**
     *Méthode permettant d'afficher les groupes de Lycanthropes solitaires de la colonie
     *
     */
    public void afficherGroupesDeSolitaires(){
        System.out.println("Groupes de Lycanthropes solitaires (" +groupeDeSolitaires.size()+":");
        for (int i = 0; i < groupeDeSolitaires.size(); i++){
            System.out.println("Groupe " + (i+1) + " :");
        }
    }

    /**
     *
     *
     * @param lycanthrope : Lycanthrope à ajouter à un groupe de Lycanthropes solitaires
     */
    public void ajouterSolitaire(Lycanthrope lycanthrope){
        solitaires.add(lycanthrope);
    }

    /**
     *Méthode de gestion des meutes via un menu qui s'affiche dans un terminal dans lequel on navigue avec des commandes au clavier
     *
     */
    public void menuMeute () {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 7) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Créer une meute");
            System.out.println("2. Afficher les meutes");
            System.out.println("3. Afficher les caractéristiques d'une meute");
            System.out.println("4. Regénérer la hiérarchie d'une meutes");
            System.out.println("5. Ajouter des Lycanthropes ω à une meute");
            System.out.println("6. Ajouter ou Supprimer un/des lycanthrope(s) d'une meute");
            System.out.println("7. Quitter");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                choix = 10;
            }
            else {
                choix = Integer.parseInt(input);
            }
            switch (choix) {
                case 1:
                    Meute meute = new Meute(this);
                    Random random1 = new Random();
                    int randomNumber = random1.nextInt(101);
                    for (int i = 0; i < randomNumber; i++) {
                        String sexe = Math.random() < 0.5 ? "Mâle" : "Femelle";
                        double proba = Math.random();
                        String categorieAge;
                        if (proba < 0.3) {
                            categorieAge = "Jeune";
                        } else if (proba < 0.6) {
                            categorieAge = "Adulte";
                        } else {
                            categorieAge = "Vieux";
                        }
                        meute.ajouterMembre(new LycanthropeFactoryImpl().creerLycanthrope(sexe, categorieAge));
                    }
                    meute.creerHierarchie();
                    ajouterMeute(meute);
                    break;
                case 2:
                    afficherMeutes();
                    break;

                case 3:
                    if (meutes.isEmpty()) {
                        System.out.println("Vous devez d'abord créer au moins une meute avant de pouvoir afficher ses caractéristiques.");
                        break;
                    }
                    System.out.println("Choisissez la meute dont vous voulez afficher les caractéristiques :");
                    afficherMeutes();
                    int idx = scanner.nextInt();
                    Meute meuteprint = meutes.get(idx - 1);
                    meuteprint.afficherCaracteristiques();
                    System.out.println("Voulez vous en plus de cela afficher les caractéristiques d'un lycanthrope dans cette meute ? \n 1. Oui \n 2. Non");
                    int chx = scanner.nextInt();
                    if (chx == 1) {
                        System.out.println("Lequel parmi " + meuteprint.getMembres().size() + " (son numéro)");
                        int idx2 = scanner.nextInt();
                        Lycanthrope choisi = meuteprint.getMembres().get(idx2 - 1);
                        choisi.afficherCaracteristiques();
                    }
                    break;
                case 4:
                    if (meutes.isEmpty()) {
                        System.out.println("Vous devez d'abord créer au moins une meute avant de pouvoir régénérer sa hiérarchie.");
                        break;
                    }
                    System.out.println("Choisissez la meute dont vous voulez régénérer la hiérarchie :");
                    afficherMeutes();
                    int idx3 = scanner.nextInt();
                    Meute meute2 = meutes.get(idx3 - 1);
                    meute2.creerHierarchie();
                    break;
                case 5:
                    if (meutes.isEmpty()) {
                        System.out.println("Vous devez d'abord créer au moins une meute avant de pouvoir ajouter des Lycanthropes ω.");
                        break;
                    }
                    System.out.println("Choisissez la meute à laquelle vous voulez générer des Lycanthropes ω :");
                    afficherMeutes();
                    int idx4 = scanner.nextInt();
                    Meute meute3 = meutes.get(idx4 - 1);
                    System.out.println("Combien de Lycanthropes ω voulez vous ajouter ?");
                    int nbr = scanner.nextInt();
                    Lycanthrope plusfaible = meute3.getMembres().get(0);
                    for (int i = 0; i < nbr; i++) {
                        for (Lycanthrope lycanthrope : meute3.getMembres()) {
                            if (lycanthrope.getForce() < plusfaible.getForce() && !lycanthrope.getRang().equals("ω")) {
                                plusfaible = lycanthrope;
                            }
                        }
                        plusfaible.setRang("ω");
                    }
                    break ;
                case 6:
                    if (meutes.isEmpty()) {
                        System.out.println("Vous devez d'abord créer au moins une meute avant de pouvoir ajouter ou supprimer un/des lycanthrope(s).");
                        break;
                    }
                    System.out.println("Choisissez la meute à laquelle vous voulez ajouter ou supprimer un/des lycanthrope(s) :");
                    afficherMeutes();
                    int idx5 = scanner.nextInt();
                    Meute meute4 = meutes.get(idx5 - 1);
                    System.out.println("Que voulez vous faire ?");
                    System.out.println("1. Ajouter un lycanthrope");
                    System.out.println("2. Supprimer un lycanthrope");
                    int chx2 = scanner.nextInt();
                    switch (chx2) {
                        case 1:
                            System.out.println("Ajout de Lycanthropes");
                            System.out.println("Combien de Lycanthropes voulez vous ajouter ?");
                            int nbr2 = scanner.nextInt();
                            for (int i = 0; i < nbr2; i++) {
                                String sexe = Math.random() < 0.5 ? "Mâle" : "Femelle";
                                double proba = Math.random();
                                String categorieAge;
                                if (proba < 0.3) {
                                    categorieAge = "Jeune";
                                } else if (proba < 0.6) {
                                    categorieAge = "Adulte";
                                } else {
                                    categorieAge = "Vieux";
                                }
                                meute4.ajouterMembre(new LycanthropeFactoryImpl().creerLycanthrope(sexe, categorieAge));
                            }
                            break;

                        case 2:
                            System.out.println("Suppression de Lycanthropes");
                            System.out.println("Combien de Lycanthropes voulez vous supprimer ?");
                            int nbr3 = scanner.nextInt();
                            for (int i = 0; i < nbr3; i++) {
                                System.out.println("Lequel parmi " + meute4.getMembres().size() + " (son numéro)");
                                int idx6 = scanner.nextInt();
                                Lycanthrope choisi2 = meute4.getMembres().get(idx6 - 1);
                                meute4.retirerMembre(choisi2);
                            }
                            break;
                        default:
                            System.out.println("Choix invalide");
                            break;
                    }
                    break ;
                case 7:
                    System.out.println("Retour au menu principal");
                    break;
                case 10:
                        break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    /**
     *Méthode de gestion des Lycanthropes solitaires via un menu qui s'affiche dans un terminal dans lequel on navigue avec des commandes au clavier
     *
     */
    public void menuSolitaire(){
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 4) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Afficher le nombre de Lycanthropes solitaires");
            System.out.println("2. Réunir les Lycanthropes solitaires ensemble");
            System.out.println("3. Afficher les groupes de Lycanthropes solitaires réunis");
            System.out.println("4. Quitter");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                choix = 10;
            }
            else {
                choix = Integer.parseInt(input);
            }
            switch (choix) {
                case 1:
                    System.out.println("Il y a " + solitaires.size() + " Lycanthropes solitaires.");
                    System.out.println("Voulez vous afficher leurs caractéristiques ? \n 1. Oui \n 2. Non");
                    int chx2 = scanner.nextInt();
                    if (chx2 == 1) {
                        for (Lycanthrope lycanthrope : solitaires) {
                            lycanthrope.afficherCaracteristiques();
                        }
                    }
                    break;
                case 2:
                    if (solitaires.isEmpty()) {
                        System.out.println("Il n'y a pas de Lycanthropes solitaires à réunir.");
                        break;
                    }
                    System.out.println("Réunir les Lycanthropes solitaires ensemble");
                    boolean verif1male = false;
                    boolean verif1femelle = false;
                    for (Lycanthrope lycanthrope : solitaires) {
                        if (lycanthrope.getSexe().equals("Mâle")) {
                            verif1male = true;
                        }
                        if (lycanthrope.getSexe().equals("Femelle")) {
                            verif1femelle = true;
                        }
                    }
                    if (!verif1male || !verif1femelle) {
                        System.out.println("Les lycanthrope solitaires se réunissent en attendant d'avoir un lycanthrope du sexe opposé pour former une meute.");
                    } else {
                        Meute meute1 = new Meute(this);
                        for (Lycanthrope lycanthrope : solitaires) {
                            meute1.ajouterMembre(lycanthrope);
                        }
                        meute1.creerHierarchie();
                        ajouterMeute(meute1);
                        solitaires.clear();
                    }
                    break;
                case 3:
                    if (groupeDeSolitaires.isEmpty()) {
                        System.out.println("Il n'y a pas de groupe de Lycanthropes solitaires réunis.");
                        break;
                    }
                    System.out.println("Groupes de Lycanthropes solitaires réunis :");
                    afficherGroupesDeSolitaires();
                    System.out.println("Voulez vous afficher les caractéristiques d'un groupe de Lycanthropes solitaires réunis ? \n 1. Oui \n 2. Non");
                    int chx3 = scanner.nextInt();
                    if (chx3 == 1) {
                        System.out.println("Lequel parmi " + groupeDeSolitaires.size() + " (son numéro)");
                        int idx3 = scanner.nextInt();
                        List<Lycanthrope> groupe = groupeDeSolitaires.get(idx3 - 1);
                        for (Lycanthrope lycanthrope : groupe) {
                            lycanthrope.afficherCaracteristiques();
                        }
                    }
                    break;
                case 4:
                    System.out.println("Retour au menu principal");
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    /**
     *Méthode de gestion des évènements via un menu qui s'affiche dans un terminal dans lequel on navigue avec des commandes au clavier
     *
     */
    public void menuEvent(){
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 3) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Planifier un conflit");
            System.out.println("2. Planifier une reproduction");
            System.out.println("3. Quitter");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                choix = 10;
            }
            else {
                choix = Integer.parseInt(input);
            }            switch (choix) {
                case 1:
                    if (meutes.isEmpty()){
                        System.out.println("Vous devez d'abord créer au moins une meute avant de pouvoir organiser un conflit.");
                        break;
                    }
                    System.out.println("Planification d'un conflit");
                    afficherMeutes();
                    System.out.println("Choisissez la meute où se déroulera le conflit (son numéro) :");
                    int indexMeutec = scanner.nextInt();
                    --indexMeutec;
                    Meute meute1 = meutes.get(indexMeutec);
                    if (meute1.getMembres().size() < 2){
                        System.out.println("La meute ne contient pas assez de membres pour un conflit.");
                        break;
                    }
                    Random random2 = new Random();
                    int randomIndex = random2.nextInt(meute1.getMembres().size());
                    Lycanthrope agresseur = meute1.getMembres().get(randomIndex);
                    int randomIndex2 = random2.nextInt(meute1.getMembres().size());
                    if (randomIndex2 == randomIndex){
                        while (randomIndex2 == randomIndex){
                            randomIndex2 = random2.nextInt(meute1.getMembres().size());
                        }
                    }
                    Lycanthrope cible = meute1.getMembres().get(randomIndex2);
                    ConflitCommand conflit = new ConflitCommand(agresseur,cible,meute1,this);
                    conflit.execute();
                    break;
                case 2:
                    if (meutes.isEmpty()){
                        System.out.println("Vous devez d'abord créer au moins une meute avant de pouvoir organiser une reproduction.");
                        break;
                    }
                    System.out.println("Planification d'une reproduction");
                    afficherMeutes();
                    System.out.println("Choisissez la meute (son numéro) :");
                    int indexMeuter = scanner.nextInt();
                    --indexMeuter;
                    ReproductionCommand reproduction = new ReproductionCommand(meutes.get(indexMeuter));
                    reproduction.execute();
                    break;
                case 3:
                    System.out.println("Retour au menu principal");
                    break;
                case 10 :
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    /**
     *Méthode de gestion des saisons via un menu qui s'affiche dans un terminal dans lequel on navigue avec des commandes au clavier
     *
     */
    public void menuSaison(){
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 5) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Lancer l'avancement automatique des saisons");
            System.out.println("2. Avancer d'une saison");
            System.out.println("3. Mettre en pause l'avancement des saisons");
            System.out.println("4. Changer la durée d'une saison");
            System.out.println("5. Quitter");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                choix = 10;
            }
            else {
                choix = Integer.parseInt(input);
            }
            switch (choix) {
                case 1:
                    System.out.println("Lancement de l'avancement automatique des saisons");
                    gestionnaireDeSaisons.demarrer();
                    break;
                case 2:
                    System.out.println("Avancement d'une saison");
                    gestionnaireDeSaisons.changerSaison();
                    gestionnaireDeSaisons.demarrer();
                    break;
                case 3:
                    System.out.println("Mise en pause de l'avancée des saisons");
                    gestionnaireDeSaisons.arreter();
                    break;
                case 4:
                    System.out.println("Changement de la durée d'une saison");
                    System.out.println("Entrez la nouvelle durée en seconde :");
                    int duree = scanner.nextInt();
                    gestionnaireDeSaisons.setDureeSaison(duree);
                    gestionnaireDeSaisons.demarrer();
                    break;
                case 5:
                    System.out.println("Retour au menu principal");
                    break;
                case 10:
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    /**
     *Méthode de gestion des menus via un menu qui s'affiche dans un terminal dans lequel on navigue avec des commandes au clavier. Cette méthode gère aussi les évènements aléatoires qui peuvent se produire au cours de la simulation via un Thread.
     *
     */
    public void menu(){
        GestionnaireDeSaisons GestionnaireDeSaisons = new GestionnaireDeSaisons(5000, meutes,this); // Une saison dure 5 secondes
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 6) {
                Thread thread = new Thread(() -> {
                    while (true) {
                        try {
                            if (meutes.isEmpty()) {
                                GestionnaireDeSaisons.arreter();
                                Thread.sleep(5000); // Pause de 5 secondes
                                continue;
                            }
                            //pour les conflits
                            Random random = new Random();
                            int randomIndex = random.nextInt(meutes.size());
                            Meute meute = meutes.get(randomIndex);
                            if (meute.getMembres().isEmpty()) {
                                meutes.remove(meute);
                                Thread.sleep(5000); // Pause de 5 secondes
                                continue;
                            }
                            int randomIndex2 = random.nextInt(meute.getMembres().size());
                            Lycanthrope lycanthrope = meute.getMembres().get(randomIndex2);
                            double proba = Math.random();
                            if (proba < 0.10) {
                                lycanthrope.hurler("AAOUUHHHHHHHH   " + meute.getNom());
                                lycanthrope.afficherCaracteristiques();
                                lycanthrope.notifierListeners("appartenance à la meute " + meute.getNom());
                                double proba3 = Math.random();
                                if (proba3 < 0.25){
                                    for (Meute meutereact : meutes){
                                        if( meutereact != meute){
                                            int nbr = random.nextInt(5);
                                            for (int i = 0 ; i < nbr ; ++i){
                                                randomIndex = random.nextInt(meutereact.getMembres().size());
                                                meutereact.getMembres().get(randomIndex).hurler("AAOUUHHHHHHHH   " + meutereact.getNom());
                                            }
                                        }
                                    }
                                }
                            }
                            // pour les Lycanthropes trop faibles qui quittent la meute
                            double proba2 = Math.random();
                            if (proba2 < 0.05) {
                                for (Meute meute2 : meutes){
                                    List<Lycanthrope> membres = new ArrayList<>(meute2.getMembres());
                                    for (Lycanthrope lycanthrope2 : membres){
                                        if (lycanthrope2.getRang().equals("ω")) {
                                            System.out.println(lycanthrope2.getNom() + " quitte la meute.");
                                            lycanthrope2.quitterMeute();
                                            ajouterSolitaire(lycanthrope2);
                                        }
                                    }
                                }
                            }
                            //pour les Lycanthropes qui deviennent humains
                            double proba3 = Math.random();
                            if (proba3 < 0.05) {
                                for (Meute meute1 : meutes) {
                                    List<Lycanthrope> membres = new ArrayList<>(meute1.getMembres());
                                    for (Lycanthrope lycanthrope1 : membres) {
                                        double proba4 = Math.random();
                                        if (proba4 < 0.02) {
                                            lycanthrope1.seTransformerEnHumain();
                                            double proba5 = Math.random();
                                            if (proba5 < 0.5) {
                                                System.out.println(lycanthrope1.getNom() + " quitte la meute après s'être transformé en humain.");
                                                lycanthrope1.quitterMeute();}
                                        }
                                    }
                                }

                            }
                            Thread.sleep(5000); // Pause de 5 secondes
                        } catch (InterruptedException e) {
                            System.out.println("Thread interrompu.");
                            break;
                        }
                    }
                });

            if (meutes.isEmpty()) {
                    Meute meute = new Meute(this);
                    Random random1 = new Random();
                    int randomNumber = random1.nextInt(101);
                    for (int i = 0; i < randomNumber; i++) {
                        String sexe = Math.random() < 0.5 ? "Mâle" : "Femelle";
                        double proba = Math.random();
                        String categorieAge;
                        if (proba < 0.3) {
                            categorieAge = "Jeune";
                        } else if (proba < 0.6) {
                            categorieAge = "Adulte";
                        } else {
                            categorieAge = "Vieux";
                        }
                        meute.ajouterMembre(new LycanthropeFactoryImpl().creerLycanthrope(sexe, categorieAge));
                    }
                    meute.creerHierarchie();
                    ajouterMeute(meute);
                }
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Afficher le contenu de la colonie");
            System.out.println("2. Gérer les meutes :");
            System.out.println("3. Gérer les Lycanthropes solitaires et leurs groupes");
            System.out.println("4. gérer les évènements");
            System.out.println("5. gérer les saisons");
            System.out.println("6. Quitter");
            choix = scanner.nextInt();
            thread.start();
            switch (choix) {
                case 1:
                    System.out.println("Contenu de la colonie :");
                    System.out.println("Meutes :");
                    afficherMeutes();
                    System.out.println("Lycanthropes solitaires :");
                    afficherGroupesDeSolitaires();
                    break;
                case 2:
                    menuMeute();
                    break;
                case 3:
                    menuSolitaire();
                    break;
                case 4:
                    menuEvent();
                    break;
                case 5:
                    menuSaison();
                    break;
                case 6:
                    thread.interrupt();
                    System.out.println("Fin de la simulation");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    /**
     *Méthode principale de la classe colonie permettant de lancer une simulation
     */
    public void simulation(){
        System.out.println("Bienvenue dans le zoo des Lycanthropes !");
        menu();
    }

    /**
     *Méthode permettant de récupérer les lycanthropes solitaires de la colonie
     *
     * @return : Liste des lycanthropes solitaires de la colonie
     */
    public List<Lycanthrope> getSolitaires() {
        return solitaires;
    }
}
