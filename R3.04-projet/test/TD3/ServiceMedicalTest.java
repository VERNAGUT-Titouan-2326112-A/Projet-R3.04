package TD3;

import TD3.Creature.*;
import TD3.Hopital.Maladie;
import TD3.Hopital.ServiceMedical;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ServiceMedicalTest {

    private ServiceMedical serviceMedical;
    private Creature creature;

    @BeforeEach
    public void setUp() {
        Maladie md = new Maladie("Syndrome fear of missing out", "FOMO", 10);
        serviceMedical = new ServiceMedical(100.0, 5, ServiceMedical.BUDGET_MOYEN, "Elfe");
        creature = new Elfe("TestCreature", 50, "M", 70, 1.80, 30, new ArrayList<>(List.of(md)));
    }

    @Test
    public void testAjouterCreature() {
        serviceMedical.ajouterCreature(creature);
        assertEquals(1, serviceMedical.getNombreCreatures(), "Le nombre de créatures devrait être 1.");
    }

    @Test
    public void testAjouterCreatureCapaciteMax() {
        for (int i = 0; i < 5; i++) {
            serviceMedical.ajouterCreature(creature);
        }
        serviceMedical.ajouterCreature(creature); // Tentative d'ajout d'une créature supplémentaire
        assertEquals(5, serviceMedical.getNombreCreatures(), "Le nombre de créatures ne devrait pas dépasser la capacité maximale.");
    }

    @Test
    public void testEnleverCreature() {
        // Ajouter et ensuite enlever une créature
        serviceMedical.ajouterCreature(creature);
        serviceMedical.enleverCreature(creature);
        assertEquals(0, serviceMedical.getNombreCreatures(), "Le nombre de créatures devrait être 0 après avoir enlevé la créature.");
    }

    @Test
    public void testEnleverCreatureNonPresente() {
        serviceMedical.enleverCreature(creature);
        assertEquals(0, serviceMedical.getNombreCreatures(), "Le nombre de créatures ne devrait pas changer.");
    }

    @Test
    public void testSoignerCreatures() {
        serviceMedical.ajouterCreature(creature);
        serviceMedical.soignerCreatures();
        assertTrue(creature.getMaladies().isEmpty(), "Les maladies de la créature devraient être guéries après le soin.");
    }

    @Test
    public void testSetBudgetValide() {
        serviceMedical.setBudget(ServiceMedical.BUDGET_ELEVE);
        assertEquals(ServiceMedical.BUDGET_ELEVE, serviceMedical.getBudget(), "Le budget devrait être modifié en 'élevé'.");
    }

    @Test
    public void testSetBudgetInvalide() {
        assertThrows(IllegalArgumentException.class, () -> {
            serviceMedical.setBudget("budgetInvalide");
        }, "Un budget invalide devrait lancer une exception.");
    }

    @Test
    public void testIsIsole() {
        assertFalse(serviceMedical.isIsole(), "Le service médical ne devrait pas être isolé par défaut.");
        serviceMedical.setIsole(true);
        assertTrue(serviceMedical.isIsole(), "Le service médical devrait être isolé après avoir modifié l'état.");
    }
}
