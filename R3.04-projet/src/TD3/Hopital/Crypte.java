package TD3.Hopital;

import TD3.Creature.Creature;

/**
 * Classe Crypte, sous-classe de ServiceMedical
 */
public class Crypte extends ServiceMedical {
    private int ventilation;
    private int temperature;
    private String type;
    private String nom;
    private final int id;
    private static int DERNIER_ID = 0;
    private boolean isole = false;

    /**
     * Constructeur de Crypte
     * @param superficie superficie de la crypte
     * @param capaciteMax capacité maximale de la crypte
     * @param budget budget de la crypte
     * @param ventilation ventilation de la crypte
     * @param temperature température de la crypte
     * @param type type de créature contenues dans la crypte
     */
    public Crypte(double superficie, int capaciteMax, String budget, int ventilation, int temperature, String type) {
        super(superficie, capaciteMax, budget,type);
        this.ventilation = ventilation;
        this.temperature = temperature;
        this.type = type;
        this.id = DERNIER_ID++;
        super.setNom("Crypte " + id);
    }

    /**
     * Méthode pour ajouter une créature à la crypte
     * @param creature créature à ajouter
     */
    @Override
    public void ajouterCreature(Creature creature) {
        if (creature.isRegenerable()) {
            super.ajouterCreature(creature);
        } else {
            System.out.println(creature.getNom() + " ne peut pas être ajouté(e) à la crypte car il/elle n'est pas régénérant(e).");
        }
    }

    /**
     * réviser le budget de la crypte
     */
    @Override
    public void revisionBudget() {
        System.out.println("Révision du budget de la crypte : " + getNom());
        System.out.println("Ventilation : " + ventilation);
        System.out.println("Température : " + temperature);
    }

    /**
     * Méthode pour obtenir la température de la crypte
     * @return température de la crypte
     */
    public int getTemperature() {
        return this.temperature;
    }

    /**
     * Méthode pour définir la température de la crypte
     * @param tmp température de la crypte
     */
    public void setTemperature(int tmp){
        this.temperature = tmp;
    }

    /**
     * Méthode pour obtenir la ventilation de la crypte
     * @return ventilation de la crypte
     */
    public int getVentilation(){
        return this.ventilation;
    }

    /**
     * Méthode pour définir la ventilation de la crypte
     * @param vnt ventilation de la crypte
     */
    public void setVentilation(int vnt){
        this.ventilation = vnt ;
    }

    /**
     * Méthode pour définir le nom de la crypte
     * @param nom nom de la crypte
     */
    public void setNom(String nom){
        this.nom = nom;
    }

    /**
     * Méthode pour définir le type de créature contenues dans la crypte
     * @param type type de créature contenues dans la crypte
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Méthode pour obtenir le type de créature contenues dans la crypte
     * @return type de créature contenues dans la crypte
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Méthode pour savoir, si la crypte est isolée ou non (toujours false ici car seul les centre de quarantaine peuvent être isolés, on s'en sert juste pour la gestion de l'aléatoire sur les ervices médicaux)
     * @return true si la crypte est isolée, false sinon
     */
    public boolean isIsole() {
        return isole;
    }

    /**
     * Méthode pour définir si la crypte est isolée ou non (même chose que pour isIsole)
     * @param isole si la crypte est isolée ou non
     */
    public void setIsole(boolean isole) {
        this.isole = isole;
    }
}