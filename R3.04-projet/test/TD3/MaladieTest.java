package TD3;

import TD3.Hopital.Maladie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaladieTest {

    private Maladie maladie;

    @BeforeEach
    public void setUp() {
        maladie = new Maladie("Grippe A", "GA", 10);
    }

    @Test
    public void testConstructeur() {
        assertEquals("Grippe A", maladie.getNomComplet(), "Le nom complet de la maladie est incorrect.");
        assertEquals("GA", maladie.getNomAbrege(), "Le nom abrégé de la maladie est incorrect.");
        assertEquals(1, maladie.getNiveauActuel(), "Le niveau actuel de la maladie doit être 1 au départ.");
        assertEquals(10, maladie.getNiveauMax(), "Le niveau maximal de la maladie doit être 10.");
    }

    @Test
    public void testAugmenterNiveau() {
        maladie.augmenterNiveau();
        assertEquals(2, maladie.getNiveauActuel(), "Le niveau de la maladie devrait être augmenté à 2.");
        for (int i = 0; i < 8; i++) {
            maladie.augmenterNiveau();
        }
        assertEquals(10, maladie.getNiveauActuel(), "Le niveau de la maladie devrait être égal au niveau maximal de 10.");
    }

    @Test
    public void testDiminuerNiveau() {
        maladie.augmenterNiveau();
        maladie.diminuerNiveau();
        assertEquals(1, maladie.getNiveauActuel(), "Le niveau de la maladie devrait être revenu à 1 après la diminution.");
        maladie.diminuerNiveau();
        assertEquals(0, maladie.getNiveauActuel(), "Le niveau de la maladie ne peut pas être inférieur à 0.");
    }

    @Test
    public void testChangerNiveau() {
        maladie.changerNiveau(5);
        assertEquals(5, maladie.getNiveauActuel(), "Le niveau de la maladie devrait être changé à 5.");
        maladie.changerNiveau(12);
        assertEquals(10, maladie.getNiveauActuel(), "Le niveau de la maladie ne peut pas dépasser le niveau maximal.");
        maladie.changerNiveau(-2);
        assertEquals(0, maladie.getNiveauActuel(), "Le niveau de la maladie ne peut pas être inférieur à 0.");
    }

    @Test
    public void testEstLetale() {
        assertFalse(maladie.estLetale(), "La maladie ne devrait pas être létale avec un niveau actuel de 1.");
        maladie.changerNiveau(10);
        assertTrue(maladie.estLetale(), "La maladie devrait être létale lorsque son niveau atteint le niveau maximal.");
    }

    @Test
    public void testToString() {
        String expected = "Grippe A (GA) - Niveau: 1/10";
        assertEquals(expected, maladie.toString(), "La représentation textuelle de la maladie est incorrecte.");
    }

    @Test
    public void testGetNom() {
        String expected = "Grippe A (GA)";
        assertEquals(expected, maladie.getNom(), "Le nom complet et abrégé de la maladie est incorrect.");
    }
}

