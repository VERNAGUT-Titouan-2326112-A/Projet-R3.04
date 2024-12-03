package TD3.Hopital;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import TD3.Creature.Creature;

public class HopitalFantastique {
    private String nom;
    private int capaciteMaxServices;
    private List<ServiceMedical> services;
    private List<Medecin> medecins;
    private List<Creature> creatures;
    private List<Maladie> maladiesDisponibles;
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

    public void ajouterCreature(Creature creature) {
        if (creatures.size() < capaciteMaxServices) {
            creatures.add(creature);
        } else {
            System.out.println("Capacité maximale atteinte !");
        }
    }

    public void genererCreaturesAleatoires(int nombre) {
        Random random = new Random();
        for (int i = 0; i < nombre; i++) {
            String sexe = random.nextBoolean() ? "male" : "female";
            double poids = 50 + (100 - 50) * random.nextDouble();
            double taille = 1.5 + (2.5 - 1.5) * random.nextDouble();
            int age = random.nextInt(100) + 1;
            int moral = random.nextInt(100) + 1;
            boolean regenerable = random.nextBoolean();
            boolean contaminant = random.nextBoolean();

            int randomType = random.nextInt(8) + 1;
            String nom;
            switch (randomType) {
                case 1:
                    nom = "elfe";
                    break;
                case 2:
                    nom = "Hommebete";
                    break;
                case 3:
                    nom = "lycanthropes";
                    break;
                case 4:
                    nom = "nain";
                    break;
                case 5:
                    nom = "orque";
                    break;
                case 6:
                    nom = "reptilien";
                    break;
                case 7:
                    nom = "vampire";
                    break;
                case 8:
                    nom = "zombie";
                    break;
                default:
                    nom = "inconnu";
                    break;
            }

            List<Maladie> maladies = new ArrayList<>();
            int nombreMaladies = random.nextInt(maladiesDisponibles.size()) + 1;
            for (int j = 0; j < nombreMaladies; j++) {
                int maladieIndex = random.nextInt(maladiesDisponibles.size());
                Maladie maladie;
                switch (maladieIndex) {
                    case 0:
                        maladie = mdc;
                        break;
                    case 1:
                        maladie = fomo;
                        break;
                    case 2:
                        maladie = drs;
                        break;
                    case 3:
                        maladie = pec;
                        break;
                    case 4:
                        maladie = zpl;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + maladieIndex);
                }
                maladies.add(maladie);
            }

            Creature creature = new Creature(nom, moral, sexe, poids, taille, age, regenerable, contaminant, maladies);
            ajouterCreature(creature);
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

    public boolean ajouterService(ServiceMedical service) {
        if (services.size() < capaciteMaxServices) {
            services.add(service);
            System.out.println("Service médical ajouté : " + service.getNom());
            return true;
        } else {
            System.out.println("Capacité maximale de services atteinte.");
            return false;
        }
    }

    public boolean ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
        System.out.println("Médecin ajouté : " + medecin.getNom());
        return true;
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
        for (ServiceMedical service : services) {
            service.afficherDetails();
        }
    }
}