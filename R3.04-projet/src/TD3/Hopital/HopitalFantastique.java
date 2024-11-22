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

    public HopitalFantastique(String nom, int capaciteMaxServices) {
        this.nom = nom;
        this.capaciteMaxServices = capaciteMaxServices;
        this.services = new ArrayList<>();
        this.medecins = new ArrayList<>();
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

    public void simulation() {
        Random random = new Random();
        int intervalle = 0;

        while (true) {  // Boucle infinie pour la simulation
            System.out.println("Intervalle de temps : " + (++intervalle));

            // Modification aléatoire de l'état des créatures
            for (ServiceMedical service : services) {
                for (Creature creature : service.getCreatures()) {
                    if (random.nextBoolean()) {
                    //    creature.tomberMalade();
                        System.out.println(creature.getNom() + " est tombé malade.");
                    }
                    if (random.nextBoolean()) {
                       // creature.se();
                        System.out.println("Évolution de la maladie pour " + creature.getNom());
                    }
                    if (random.nextBoolean()) {
                        creature.setMoral(random.nextInt(10) - 5);
                    }
                }
            }

            // Modification aléatoire de l'état des services médicaux
            for (ServiceMedical service : services) {
                if (random.nextBoolean()) {
                    service.revisionBudget();
                }
                if (service instanceof CentreDeQuarantaine) {
                    CentreDeQuarantaine<?> quarantaine = (CentreDeQuarantaine<?>) service;
                    if (random.nextBoolean()) {
                        quarantaine.setIsole(!quarantaine.isIsolation());
                        System.out.println("Modification de l'isolation pour " + quarantaine.getNom());
                    }
                } else if (service instanceof Crypte) {
                    Crypte crypte = (Crypte) service;
                    if (random.nextBoolean()) {
                        crypte.setTemperature((crypte.getTemperature() + (random.nextInt() * 2 - 1)));
                        System.out.println("Modification de la température pour " + crypte.getNom());
                    }
                }
            }

            // Passage de TD3.main à un médecin (interaction utilisateur)
            for (Medecin medecin : medecins) {
                int actionsRestantes = 3;
                while (actionsRestantes > 0) {
                    // Ici, on pourrait interroger l'utilisateur pour réaliser une action
                    System.out.println("Médecin " + medecin.getNom() + " a " + actionsRestantes + " actions disponibles.");
                    // Exemple d'action : le médecin soigne une créature aléatoire
                    ServiceMedical service = services.get(random.nextInt(services.size()));
                    if (!service.getCreatures().isEmpty()) {
                        medecin.soignerCreatures(service);
                        System.out.println("Le médecin " + medecin.getNom() + " soigne le service " + service.getNom());
                        actionsRestantes--;
                    }
                }
            }

            // Pause dans la boucle pour simuler le passage du temps (remplaçable par Thread.sleep si nécessaire)
            try {
                Thread.sleep(2000);  // Attendre 2 secondes avant le prochain intervalle
            } catch (InterruptedException e) {
                System.out.println("Simulation interrompue.");
                break;
            }
        }
    }
}
