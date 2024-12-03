package TD3.Hopital;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import TD3.Creature.*;

public class HopitalFantastique {
    private final String nom;
    private final int capaciteMaxServices;
    private final List<ServiceMedical> services;
    private final List<Medecin> medecins;
    private final List<Creature> creatures;
    private final List<Maladie> maladiesDisponibles;
    Maladie mdc = new Maladie("Maladie débilitante chronique", "MDC", 10);
    Maladie fomo = new Maladie("Syndrome fear of missing out", "FOMO", 10);
    Maladie drs = new Maladie("Dépendance aux réseaux sociaux", "DRS", 10);
    Maladie pec = new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 10);
    Maladie zpl = new Maladie("Zoopathie paraphrénique lycanthropique", "ZPL", 10);

    public HopitalFantastique(String nom, int capaciteMaxServices) {
        this.nom = nom;
        this.capaciteMaxServices = capaciteMaxServices;
        this.services = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.maladiesDisponibles = new ArrayList<>();
        initialiserMaladies();
    }

    private void initialiserMaladies() {
        maladiesDisponibles.add(new Maladie("Maladie débilitante chronique", "MDC", 10));
        maladiesDisponibles.add(new Maladie("Syndrome fear of missing out", "FOMO", 10));
        maladiesDisponibles.add(new Maladie("Dépendance aux réseaux sociaux", "DRS", 10));
        maladiesDisponibles.add(new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 10));
        maladiesDisponibles.add(new Maladie("Zoopathie paraphrénique lycanthropique", "ZPL", 10));
    }


    public void genererCreaturesAleatoires(int nombre) {
        for (int i = 0; i < nombre; i++) {
            int type = new Random().nextInt(8);
            Random random = new Random();
            List<Maladie> maladies = new ArrayList<>();
            int nombreMaladies = random.nextInt(maladiesDisponibles.size()) + 1;
            for (int j = 0; j < nombreMaladies; j++) {
                int maladieIndex = random.nextInt(maladiesDisponibles.size());
                Maladie maladie = switch (maladieIndex) {
                    case 0 -> mdc;
                    case 1 -> fomo;
                    case 2 -> drs;
                    case 3 -> pec;
                    case 4 -> zpl;
                    default -> throw new IllegalStateException("Unexpected value: " + maladieIndex);
                };
                maladies.add(maladie);
            }
            switch (type) {
                case 0:
                    creatures.add(new Elfe("Elfe", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100), maladies));
                    break;
                case 1:
                    creatures.add(new HommeBete("HommeBete", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100),maladies));
                    break;
                case 2:
                    creatures.add(new lycanthropes("Lycanthrope", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100),maladies));
                    break;
                case 3:
                    creatures.add(new Nain("Nain", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100),maladies));
                    break;
                case 4:
                    creatures.add(new orque("Orque", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100),maladies));
                    break;
                case 5:
                    creatures.add(new Reptilien("Reptilien", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100),maladies));
                    break;
                case 6:
                    creatures.add(new Vampire("Vampire", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100),maladies));
                    break;
                case 7:
                    creatures.add(new Zombie("Zombie", (int) (Math.random() * 100), Math.random() < 0.5 ? "Mâle" : "Femelle", Math.random() * 100, Math.random() + 1, (int) (Math.random() * 100),maladies));
                    break;
            }

        }
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public String getNom() {
        return nom;
    }

    public int getCapaciteMaxServices() {
        return capaciteMaxServices;
    }

    public List<ServiceMedical> getServices() {
        return services;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void ajouterService(ServiceMedical service) {
        if (services.size() < capaciteMaxServices) {
            services.add(service);
            System.out.println("Service médical ajouté : " + service.getNom());
        } else {
            System.out.println("Capacité maximale de services atteinte.");
        }
    }

    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
        System.out.println("Médecin ajouté : " + medecin.getNom());
    }

    public int compterCreatures() {
        int total = 0;
        for (ServiceMedical service : services) {
            total += service.getNombreCreatures();
        }
        return total;
    }

    public void afficherCreaturesDansServices() {
        System.out.println("Créatures présentes dans l'hôpital :");
        for (Creature creature : creatures) {
            creature.afficherCaractériques();
        }
    }

    public void menuCreature(){
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 4) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Ajouter une créature");
            System.out.println("2. Générer des créatures aléatoires");
            System.out.println("3. Afficher les créatures");
            System.out.println("4. Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Quel type de créature voulez-vous ajouter ?");
                    System.out.println("1. Elfe");
                    System.out.println("2. Hommebete");
                    System.out.println("3. Lycanthrope");
                    System.out.println("4. Nain");
                    System.out.println("5. Orque");
                    System.out.println("6. Reptilien");
                    System.out.println("7. Vampire");
                    System.out.println("8. Zombie");
                    int type = scanner.nextInt();
                    System.out.println("Combien de maladies voulez-vous ajouter à votre créature ?");
                    Random random = new Random();
                    int nombreMaladies = scanner.nextInt();
                    List<Maladie> maladies = new ArrayList<>();
                    for (int j = 0; j < nombreMaladies; j++) {
                        int maladieIndex = random.nextInt(maladiesDisponibles.size());
                        Maladie maladie = switch (maladieIndex) {
                            case 0 -> mdc;
                            case 1 -> fomo;
                            case 2 -> drs;
                            case 3 -> pec;
                            case 4 -> zpl;
                            default -> throw new IllegalStateException("Unexpected value: " + maladieIndex);
                        };
                        maladies.add(maladie);
                    }
                    switch (type) {
                        case 1:
                            System.out.println("Ajout d'un elfe");
                            String sexe = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age = (int) (Math.random() * 100);
                            int moral = (int) (Math.random() * 100);
                            double poids =  (Math.random() * 100);
                            double taille = (Math.random() ) + 1;
                            creatures.add(new Elfe("Elfe", moral, sexe, poids, taille, age, maladies));
                            break;
                        case 2:
                            System.out.println("Ajout d'un hommebete");
                            String sexe2 = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age2 = (int) (Math.random() * 100);
                            int moral2 = (int) (Math.random() * 100);
                            double poids2 = Math.random() * 100;
                            double taille2 =Math.random()  + 1;
                            creatures.add(new HommeBete("HommeBete", moral2, sexe2, poids2, taille2, age2, maladies));
                            break;
                        case 3:
                            System.out.println("Ajout d'un lycanthrope");
                            String sexe3 = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age3 = (int) (Math.random() * 100);
                            int moral3 = (int) (Math.random() * 100);
                            double poids3 = Math.random() * 100;
                            double taille3 = (Math.random()+1) ;
                            creatures.add(new lycanthropes("Lycanthrope", moral3, sexe3, poids3, taille3, age3, maladies));
                            break;
                        case 4:
                            System.out.println("Ajout d'un nain");
                            String sexe4 = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age4 = (int) (Math.random() * 100);
                            int moral4 = (int) (Math.random() * 100);
                            double poids4 = (Math.random() * 100);
                            double taille4 =  (Math.random() ) + 1;
                            creatures.add(new Nain("Nain", moral4, sexe4, poids4, taille4, age4, maladies));
                            break;
                        case 5:
                            System.out.println("Ajout d'un orque");
                            String sexe5 = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age5 = (int) (Math.random() * 100);
                            int moral5 = (int) (Math.random() * 100);
                            double poids5 = (Math.random() * 100);
                            double taille5 =  (Math.random() ) + 1;
                            creatures.add(new orque("Orque", moral5, sexe5, poids5, taille5, age5, maladies));
                            break;
                        case 6:
                            System.out.println("Ajout d'un reptilien");
                            String sexe6 = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age6 = (int) (Math.random() * 100);
                            int moral6 = (int) (Math.random() * 100);
                            double poids6 =  (Math.random() * 100);
                            double taille6 = (Math.random() ) + 1;
                            creatures.add(new Reptilien("Reptilien", moral6, sexe6, poids6, taille6, age6, maladies));
                            break;
                        case 7:
                            System.out.println("Ajout d'un vampire");
                            String sexe7 = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age7 = (int) (Math.random() * 100);
                            int moral7 = (int) (Math.random() * 100);
                            double poids7 =  (Math.random() * 100);
                            double taille7 =  (Math.random() ) + 1;
                            creatures.add(new Vampire("Vampire", moral7, sexe7, poids7, taille7, age7, maladies));
                            break;
                        case 8:
                            System.out.println("Ajout d'un zombie");
                            String sexe8 = Math.random() < 0.5 ? "Mâle" : "Femelle";
                            int age8 = (int) (Math.random() * 100);
                            int moral8 = (int) (Math.random() * 100);
                            double poids8 =  (Math.random() * 100);
                            double taille8 =  (Math.random() ) + 1;
                            creatures.add(new Zombie("Zombie", moral8, sexe8, poids8, taille8, age8, maladies));
                            break;
                        default:
                            System.out.println("Type de créature invalide");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Génération de créatures aléatoires");
                    System.out.println("Combien de créatures voulez-vous générer ?");
                    int nombre = scanner.nextInt();
                    genererCreaturesAleatoires(nombre);
                    break;
                case 3:
                    System.out.println("Affichage des créatures");
                    for (Creature creature : creatures) {
                        System.out.println(creature);
                    }
                    break;
                case 4:
                    System.out.println("Retour au menu principal");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    public void menuService() {
        int choix = 0;
        while (choix != 9){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Ajouter un service");
            System.out.println("2. Afficher les services");
            System.out.println("3. Supprimer un service");
            System.out.println("4. Afficher les caractéristiques d'un service");
            System.out.println("5. Ajouter des créatures à un service");
            System.out.println("6. Retirer des créatures d'un service");
            System.out.println("7. Soigner les créatures d'un service");
            System.out.println("8. Réviser le budget d'un service");
            System.out.println("9. Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Ajout d'un service");
                    System.out.println("Quel type de service voulez-vous ajouter ?");
                    System.out.println("1. Centre de quarantaine");
                    System.out.println("2. Crypte");
                    System.out.println("3. Service de médical classique");
                    int type = scanner.nextInt();
                    System.out.println("Quel type de créature peuvent intégrer le service ? ");
                    System.out.println("1. Elfes");
                    System.out.println("2. Hommebete");
                    System.out.println("3. Lycanthrope");
                    System.out.println("4. Nain");
                    System.out.println("5. Orque");
                    System.out.println("6. Reptilien");
                    System.out.println("7. Vampire");
                    System.out.println("8. Zombie");
                    String typeCreature = scanner.next();
                    double superficie = Math.random() * 1000;
                    int capaciteMax = (int) (Math.random() * 100);
                    int ventilation = (int) (Math.random() * 100);
                    int temperature = (int) (Math.random() * 100);
                    double proba = Math.random();
                    String budget;
                    if (proba < 0.3) {
                        budget = "faible";
                    } else if (proba < 0.6) {
                        budget = "moyen";
                    } else {
                        budget = "élevé";
                    }
                    switch (type) {
                        case 1:
                            CentreDeQuarantaine service = new CentreDeQuarantaine(superficie, capaciteMax, budget, true,typeCreature);
                            ajouterService(service);
                            break;
                        case 2:
                            Crypte service2 = new Crypte(superficie, capaciteMax, budget, ventilation, temperature, typeCreature);
                            ajouterService(service2);
                            break;
                        case 3:
                            ServiceMedical service3 = new ServiceMedical(superficie, capaciteMax, budget,typeCreature);
                            ajouterService(service3);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Affichage des services");
                    for (ServiceMedical service : services) {
                        System.out.println(service.getNom());
                    }
                    break;
                case 3:
                    System.out.println("Suppression d'un service");
                    System.out.println("Quel service voulez-vous supprimer ?");
                    for (int i = 1; i < services.size()+1; i++) {
                        System.out.println(i + ". " + services.get(i-1).getNom());
                    }
                    int index = scanner.nextInt();
                    services.remove(index-1);
                    break;
                case 4:
                    System.out.println("Affichage des caractéristiques d'un service");
                    System.out.println("Quel service voulez-vous afficher ?");
                    for (int i = 1; i < services.size()+1; i++) {
                        System.out.println(i + ". " + services.get(i-1).getNom());
                    }
                    int index2 = scanner.nextInt();
                    services.get(index2-1).afficherDetails();
                    System.out.println("Voulez vous aficher les caractéristiques des créatures du service ? \n 1. Oui \n 2. Non");
                    int choix2 = scanner.nextInt();
                    if (choix2 == 1) {
                        for (Creature creature : services.get(index2-1).getCreatures()) {
                            creature.afficherCaractériques();
                        }
                    }
                    break;
                case 5:
                    System.out.println("Ajout de créatures à un service");
                    System.out.println("Dans quel service voulez-vous ajouter des créatures ?");
                    for (int i = 1; i < services.size()+1; i++) {
                        System.out.println(i + ". " + services.get(i-1).getNom());
                    }
                    int index3 = scanner.nextInt();
                    System.out.println("Combien de créatures voulez-vous ajouter ?");
                    int nombre = scanner.nextInt();
                    for (int i = 0; i < nombre; i++) {
                        System.out.println("Quelle créature voulez-vous ajouter ?");
                        int p = 0 ;
                        for (int j = 0; j < creatures.size(); j++) {
                            if (creatures.get(j).getType().equals(services.get(index3).getType())) {
                                System.out.println(p+1 + ". " + creatures.get(j).getNom());
                                p++;
                            }
                        }
                        int index4 = scanner.nextInt();
                        services.get(index3).ajouterCreature(creatures.get(index4));
                    }
                    break;
                case 6:
                    System.out.println("Retrait de créatures d'un service");
                    System.out.println("De quel service voulez-vous retirer des créatures ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i + ". " + services.get(i).getNom());
                    }
                    int index5 = scanner.nextInt();
                    System.out.println("Combien de créatures voulez-vous retirer ?");
                    int nombre2 = scanner.nextInt();
                    for (int i = 0; i < nombre2; i++) {
                        System.out.println("Quelle créature voulez-vous retirer ?");
                        for (int j = 0; j < services.get(index5).getNombreCreatures(); j++) {
                            System.out.println(j+1 + ". " + services.get(index5).getCreatures().get(j).getNom());
                        }
                        int index6 = scanner.nextInt();
                        services.get(index5).enleverCreature(services.get(index5).getCreatures().get(index6));
                    }
                    break;
                case 7:
                    System.out.println("Soigner les créatures d'un service");
                    System.out.println("Quel service voulez-vous soigner ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index7 = scanner.nextInt();
                    System.out.println("Quel médecin voulez-vous utiliser ?");
                    int k = 0;
                    for (Medecin medecin4 : medecins) {
                        if(medecin4.getType() == services.get(index7).getType()){
                            System.out.println(k+1 + ". " + medecin4.getNom());
                            k++;
                        }
                    }
                    if (k == 0) {
                        System.out.println("Aucun médecin disponible pour soigner les créatures de ce service");
                        break;
                    }
                    else {
                        services.get(index7).soignerCreatures();
                    }
                    break;
                case 8:
                    System.out.println("Révision du budget d'un service");
                    System.out.println("Quel service voulez-vous réviser ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index8 = scanner.nextInt();
                    int l = 0;
                    for (Medecin medecin3 : medecins) {
                        if(medecin3.getType() == services.get(index8).getType()){
                            System.out.println(l+1 + ". " + medecin3.getNom());
                            l++;
                        }
                    }
                    if (l == 0) {
                        System.out.println("Aucun médecin disponible pour réviser le budget de ce service");
                        break;
                    }
                    else {
                        services.get(index8).revisionBudget();
                    }
                    break;

                case 9:
                    System.out.println("Retour au menu principal");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }

    public void menuMedecin(){
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 9) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Ajouter un médecin");
            System.out.println("2. Afficher les médecins");
            System.out.println("3. Supprimer un médecin");
            System.out.println("4. Afficher les caractéristiques d'un médecin");
            System.out.println("5. Soigner les créatures d'un service");
            System.out.println("6. Réviser le budget d'un service");
            System.out.println("7. Inspecter un service");
            System.out.println("8. Transférer une créature vers un autre service");
            System.out.println("9. Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Ajout d'un médecin");
                    String sexe = Math.random() < 0.5 ? "Mâle" : "Femelle";
                    int age = (int) (Math.random() * 100);
                    List<String> types = new ArrayList<>();
                    for (ServiceMedical service : services) {
                        if (!types.contains(service.getType())) {
                            types.add(service.getType());
                        }
                    }
                    Random random = new Random();
                    String type = types.get(random.nextInt(types.size()));
                    Medecin medecin = new Medecin(sexe, age, type);
                    ajouterMedecin(medecin);
                    break;
                case 2:
                    System.out.println("Affichage des médecins");
                    for (Medecin medecin2 : medecins) {
                        System.out.println(medecin2.getNom());
                    }
                    break;
                case 3:
                    System.out.println("Suppression d'un médecin");
                    System.out.println("Quel médecin voulez-vous supprimer ?");
                    for (int i = 0; i < medecins.size(); i++) {
                        System.out.println(i + 1 + ". " + medecins.get(i).getNom());
                    }
                    int index = scanner.nextInt();
                    medecins.remove(index);
                    break;
                case 4:
                    System.out.println("Affichage des caractéristiques d'un médecin");
                    System.out.println("Quel médecin voulez-vous afficher ?");
                    for (int i = 0; i < medecins.size(); i++) {
                        System.out.println(i + 1 + ". " + medecins.get(i).getNom());
                    }
                    int index2 = scanner.nextInt();
                    medecins.get(index2).afficherDetails();
                    break;

                case 5:
                    System.out.println("Soigner les créatures d'un service");
                    System.out.println("Quel service voulez-vous soigner ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index7 = scanner.nextInt();
                    System.out.println("Quel médecin voulez-vous utiliser ?");
                    int k = 0;
                    for (Medecin medecin4 : medecins) {
                        if(medecin4.getType() == services.get(index7).getType()){
                            System.out.println(k+1 + ". " + medecin4.getNom());
                            k++;
                        }
                    }
                    if (k == 0) {
                        System.out.println("Aucun médecin disponible pour soigner les créatures de ce service");
                        break;
                    }
                    else {
                        services.get(index7).soignerCreatures();
                    }
                    break;
                case 6:
                    System.out.println("Révision du budget d'un service");
                    System.out.println("Quel service voulez-vous réviser ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index8 = scanner.nextInt();
                    int l = 0;
                    for (Medecin medecin3 : medecins) {
                        if(medecin3.getType() == services.get(index8).getType()){
                            System.out.println(l+1 + ". " + medecin3.getNom());
                            l++;
                        }
                    }
                    if (l == 0) {
                        System.out.println("Aucun médecin disponible pour réviser le budget de ce service");
                        break;
                    }
                    else {
                        services.get(index8).revisionBudget();
                    }
                    break;

                case 7:
                    System.out.println("Inspection d'un service");
                    System.out.println("Quel service voulez-vous inspecter ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index9 = scanner.nextInt();
                    System.out.println("Quel médecin voulez-vous utiliser ?");
                    int m = 0;
                    for (Medecin medecin5 : medecins) {
                        if(medecin5.getType() == services.get(index9).getType()){
                            System.out.println(m+1 + ". " + medecin5.getNom());
                            m++;
                        }
                    }
                    if (m == 0) {
                        System.out.println("Aucun médecin disponible pour inspecter ce service");
                        break;
                    }
                    else {
                        medecins.get(m).inspecterService(services.get(index9));
                    }
                    break;
                case 8 :
                    System.out.println("Quel médecin voulez-vous utiliser ?");
                    for (int i = 0; i < medecins.size(); i++) {
                        System.out.println(i+1 + ". " + medecins.get(i).getNom());
                    }
                    int index10 = scanner.nextInt();
                    System.out.println("Dans quel service voulez-vous prendre une créature à transférer?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index11 = scanner.nextInt();
                    System.out.println("Quelle créature voulez-vous transférer ?");
                    for (int i = 0; i < services.get(index11).getNombreCreatures(); i++) {
                        System.out.println(i+1 + ". " + services.get(index11).getCreatures().get(i).getNom());
                    }
                    int index12 = scanner.nextInt();
                    Creature creature = services.get(index11).getCreatures().get(index12);
                    System.out.println("Dans quel service voulez-vous transférer la créature ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index13 = scanner.nextInt();
                    medecins.get(index10).transfererCreature(creature, services.get(index11), services.get(index13));
                    break;
                case 9:
                    System.out.println("Retour au menu principal");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;


            }
        }
    }

    public void menuRpMedecin(){
        System.out.println("Bienvenue dans le RP de médecin");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est votre nom ?");
        String nom = scanner.next();
        System.out.println("Quel est votre sexe ?");
        String sexe = scanner.next();
        System.out.println("Quel est votre âge ?");
        int age = scanner.nextInt();
        System.out.println("Quel est votre type de créature ?");
        String type = scanner.next();
        Medecin medecin = new Medecin(sexe, age, type);
        medecin.setNom(nom);
        System.out.println("Bienvenue " + medecin.getNom());
        int choix = 0;
        while (choix != 4) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Soigner les créatures d'un service");
            System.out.println("2. Réviser le budget d'un service");
            System.out.println("3. Inspecter un service");
            System.out.println("4. Transférer une créature vers un autre service");
            System.out.println("5. Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Soigner les créatures d'un service");
                    System.out.println("Quel service voulez-vous soigner ?");
                    int m = 0;
                    for (int i = 0; i < services.size(); i++) {
                        if (services.get(i).getType().equals(medecin.getType())) {
                            System.out.println(m+1 + ". " + services.get(i).getNom());
                            m++;
                        }
                    }
                    int index7 = scanner.nextInt();
                    services.get(index7).soignerCreatures();
                    break;
                case 2:
                    System.out.println("Révision du budget d'un service");
                    System.out.println("Quel service voulez-vous réviser ?");
                    int n = 0;
                    for (int i = 0; i < services.size(); i++) {
                        if (services.get(i).getType().equals(medecin.getType())) {
                            System.out.println(n+1 + ". " + services.get(i).getNom());
                            n++;
                        }
                    }
                    int index8 = scanner.nextInt();
                    services.get(index8).revisionBudget();
                    break;
                case 3:
                    System.out.println("Inspection d'un service");
                    System.out.println("Quel service voulez-vous inspecter ?");
                    int o = 0;
                    for (int i = 0; i < services.size(); i++) {
                        if (services.get(i).getType().equals(medecin.getType())) {
                            System.out.println(o+1 + ". " + services.get(i).getNom());
                            o++;
                        }
                    }
                    int index9 = scanner.nextInt();
                    medecin.inspecterService(services.get(index9));
                    break;
                case 4 :
                    System.out.println("Dans quel service voulez-vous prendre une créature à transférer?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index11 = scanner.nextInt();
                    System.out.println("Quelle créature voulez-vous transférer ?");
                    for (int i = 0; i < services.get(index11).getNombreCreatures(); i++) {
                        System.out.println(i+1 + ". " + services.get(index11).getCreatures().get(i).getNom());
                    }
                    int index12 = scanner.nextInt();
                    Creature creature = services.get(index11).getCreatures().get(index12);
                    System.out.println("Dans quel service voulez-vous transférer la créature ?");
                    for (int i = 0; i < services.size(); i++) {
                        System.out.println(i+1 + ". " + services.get(i).getNom());
                    }
                    int index13 = scanner.nextInt();
                    medecin.transfererCreature(creature, services.get(index11), services.get(index13));
                    break;
                case 5:
                    System.out.println("Retour au menu principal");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        while (choix != 4) {
            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Gérer les créatures");
            System.out.println("2. Gérer les services médicaux");
            System.out.println("3. Gérer les médecins");
            System.out.println("4. Afficher les créatures présentes dans l'hôpital");
            System.out.println("5. Afficher l'ensemble des services médicaux et leurs créatures");
            System.out.println("6. Incarner un médecin");
            System.out.println("7. Quitter");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Gestion des créatures");
                    menuCreature();
                    break;
                case 2:
                    System.out.println("Gestion des services médicaux");
                    menuService();
                    break;
                case 3:
                    System.out.println("Gestion des médecins");
                    menuMedecin();
                    break;

                case 4 :
                    System.out.println("Affichage des créatures présentes dans l'hôpital");
                    afficherCreaturesDansServices();
                    break;
                case 5 :
                    System.out.println("Affichage des services médicaux et de leurs créatures");
                    for (ServiceMedical service : services) {
                        service.afficherDetails();
                        for (Creature creature : service.getCreatures()) {
                            creature.afficherCaractériques();
                        }
                    }
                    break;
                case 6:
                        System.out.println("Fin de la simulation");
                        break;
                case 7 :
                    menuRpMedecin();
                case 8:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }
    }
}