package TD3;

import TD3.Creature.Creature;
import TD3.Creature.Elfe;
import TD3.Creature.Vampire;
import TD3.Hopital.HopitalFantastique;
import TD3.Hopital.Maladie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {
    private Creature creature;
    private HopitalFantastique hopital;
    private Maladie maladie;
    private List<Maladie> maladies;

    @BeforeEach
    void setUp() {
        maladie = new Maladie("Syndrome fear of missing out", "FOMO", 10);
        maladies = new ArrayList<>();
        maladies.add(maladie);
        creature = new Elfe("Test Creature", 50, "Male", 70.0, 1.75, 25, maladies);
        hopital = new HopitalFantastique("test",100);
    }


    @Test
    void testHurler() {
        creature.setMoral(40);
        creature.hurler();
        assertTrue(creature.getMoral() < 50);
    }

    @Test
    void testSEmporter() {
        creature.setMoral(60);
        creature.sEmporter();
        assertTrue(creature.getMoral() < 60);
    }

    @Test
    void testTomberMalade() {
        Maladie mdc = new Maladie("Maladie débilitante chronique", "MDC", 10);
        creature.tomberMalade(mdc, hopital);
        assertTrue(creature.getMaladies().contains(mdc));
    }

    @Test
    void testSoigner() {
        creature.soigner();
        assertTrue(creature.getMaladies().isEmpty());
    }

    @Test
    void testTrepasser() {

        creature.trepasser(hopital);
        assertFalse(hopital.getCreatures().contains(creature));
    }

    @Test
    void testContaminer() {
        Creature creature2 = new Elfe("Creature 2", 80, "Female", 65.0, 1.60, 30, maladies);
        creature.contaminer(hopital);
        assertTrue(creature2.getMaladies().contains(maladie));
    }

    @Test
    void testDemoraliser() {
        Creature creature2 = new Elfe("Creature 2", 80, "Female", 65.0, 1.60, 30,maladies);
        hopital.getCreatures().add(creature2); // On ajoute la créature à l'hôpital
        creature.demoraliser(hopital);
        assertTrue(creature2.getMoral() < 80);
    }

    @Test
    void testRegenerer() {
        creature.regenerer(hopital);
        assertTrue(hopital.getCreatures().contains(creature));
    }

    @Test
    void testGetAndSetVIP() {
        creature.setVIP(true);
        assertTrue(creature.getVIP());
    }

    @Test
    void testGetAndSetTriage() {
        creature.setTriage(true);
        assertTrue(creature.getTriage());
    }

    @Test
    void testEstMalade() {
        assertTrue(creature.estMalade());
    }
}
