package TD3;

import TD3.Creature.Creature;
import TD3.Creature.Elfe;
import TD3.Hopital.Maladie;
import TD3.Hopital.Medecin;
import TD3.Hopital.ServiceMedical;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MedecinTest {

    private Medecin medecin;
    private ServiceMedical serviceMedical1;
    private ServiceMedical serviceMedical2;
    private Creature creature;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets avant chaque test
        medecin = new Medecin("Homme", 35, "Elfe");
        Maladie md = new Maladie("Syndrome fear of missing out", "FOMO", 10);
        serviceMedical1 = new ServiceMedical(100.0, 10, ServiceMedical.BUDGET_MOYEN, "Elfe");
        serviceMedical2 = new ServiceMedical(200.0, 15, ServiceMedical.BUDGET_ELEVE, "Elfe");
        creature = new Elfe("test",50,"Mâle",45.3,1.86,75,new ArrayList<>(List.of(md)));
    }

    @Test
    public void testConstructeur() {
        // Tester que le médecin est correctement initialisé
        assertEquals("Medecin 2", medecin.getNom(), "Le nom du médecin devrait être 'Medecin 0'.");
        assertEquals("Homme", medecin.getSexe(), "Le sexe du médecin est incorrect.");
        assertEquals(35, medecin.getAge(), "L'âge du médecin est incorrect.");
        assertEquals("Elfe", medecin.getType(), "Le type de créature gérée par le médecin est incorrect.");
    }

    @Test
    public void testSetNom() {
        // Tester la méthode setNom
        medecin.setNom("Dr. Smith");
        assertEquals("Dr. Smith", medecin.getNom(), "Le nom du médecin devrait être 'Dr. Smith'.");
    }

    @Test
    public void testSetSexe() {
        // Tester la méthode setSexe
        medecin.setSexe("Femme");
        assertEquals("Femme", medecin.getSexe(), "Le sexe du médecin devrait être 'Femme'.");
    }

    @Test
    public void testSetAge() {
        // Tester la méthode setAge
        medecin.setAge(40);
        assertEquals(40, medecin.getAge(), "L'âge du médecin devrait être 40.");
    }

    @Test
    public void testAfficherDetails() {
        // Tester la méthode afficherDetails
        medecin.afficherDetails();
        // Nous ne pouvons pas tester l'affichage directement dans un test unitaire,
        // mais vous pouvez manuellement vérifier l'affichage dans la console.
    }

    @Test
    public void testGetType() {
        // Tester la méthode getType
        assertEquals("Elfe", medecin.getType(), "Le type de créature gérée par le médecin est incorrect.");
    }

    @Test
    public void testInspecterService() {
        // Tester la méthode inspecterService
        serviceMedical1.ajouterCreature(creature);
        serviceMedical1.afficherDetails();  // Vérifier l'affichage du service
        medecin.inspecterService(serviceMedical1);
        // Vérifier que la créature a bien été ajoutée et que le médecin inspecte correctement
        assertTrue(serviceMedical1.getCreatures().contains(creature), "Le service médical devrait contenir la créature.");
    }

    @Test
    public void testTransfererCreature() {
        // Tester la méthode transfererCreature
        serviceMedical1.ajouterCreature(creature);
        assertEquals(1, serviceMedical1.getNombreCreatures(), "Le service médical 1 devrait contenir 1 créature.");
        assertEquals(0, serviceMedical2.getNombreCreatures(), "Le service médical 2 devrait être vide.");

        // Transférer la créature du service 1 vers le service 2
        medecin.transfererCreature(creature, serviceMedical1, serviceMedical2);

        // Vérifier que la créature a été transférée correctement
        assertEquals(0, serviceMedical1.getNombreCreatures(), "Le service médical 1 devrait être vide.");
        assertEquals(1, serviceMedical2.getNombreCreatures(), "Le service médical 2 devrait contenir 1 créature.");
    }
}
