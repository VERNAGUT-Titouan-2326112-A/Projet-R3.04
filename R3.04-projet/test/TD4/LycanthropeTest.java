package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LycanthropeTest {
    private Lycanthrope lycanthrope;

    @BeforeEach
    void setUp() {
        lycanthrope = new Lycanthrope("Mâle", "Adulte", 50, 0.5);
    }

    @Test
    void testInitialisation() {
        assertEquals("Mâle", lycanthrope.getSexe());
        assertEquals("Adulte", lycanthrope.getCategorieAge());
        assertEquals(50, lycanthrope.getForce());
        assertEquals(0, lycanthrope.getFacteurDomination());
        assertEquals("ω", lycanthrope.getRang());
        assertNotNull(lycanthrope.getNom());
    }

    @Test
    void testCalculerNiveau() {
        lycanthrope.setFacteurDomination(5);
        lycanthrope.setRang("γ");
        double expected = 50 * 0.5 + 5 * 0.3 + ('γ' - 'ω') * 0.2 + 0.5 * 0.1;
        assertEquals(expected, lycanthrope.calculerNiveau(), 0.001);
    }

    @Test
    void testAugmenterFacteurDomination() {
        lycanthrope.augmenterFacteurDomination();
        assertEquals(1, lycanthrope.getFacteurDomination());
    }

    @Test
    void testBaisserFacteurDomination() {
        lycanthrope.setFacteurDomination(3);
        lycanthrope.baisserFacteurDomination();
        assertEquals(2, lycanthrope.getFacteurDomination());
    }

    @Test
    void testSetRang() {
        lycanthrope.setRang("β");
        assertEquals("β", lycanthrope.getRang());
    }

    @Test
    void testSetForce() {
        lycanthrope.setForce(70);
        assertEquals(70, lycanthrope.getForce());
    }

    @Test
    void testSetFacteurImpetuosite() {
        lycanthrope.setFacteurImpetuosite(0.8);
        assertEquals(0.8, lycanthrope.getFacteurImpetuosite());
    }

    @Test
    void testAjouterListener() {
        HurlementListener listener = (typeHurlement, lycan) -> System.out.println("Réaction au hurlement.");
        lycanthrope.ajouterListener(listener);
        assertDoesNotThrow(() -> lycanthrope.notifierListeners("test"));
    }

    @Test
    void testNotificateurs() {
        HurlementListener listener = (typeHurlement, lycan) -> assertEquals("AOUH", typeHurlement);
        lycanthrope.ajouterListener(listener);
        lycanthrope.notifierListeners("AOUH");
    }

    @Test
    void testQuitterMeute() {
        Colonie colonie = new Colonie();
        Meute meute = new Meute(colonie);
        lycanthrope.setMeute(meute);
        meute.ajouterMembre(lycanthrope);
        assertNotNull(lycanthrope.getMeute());

        lycanthrope.quitterMeute();
        assertNull(lycanthrope.getMeute());
        assertFalse(meute.getMembres().contains(lycanthrope));
    }

    @Test
    void testTransformationHumain() {
        lycanthrope.seTransformerEnHumain();
        assertEquals("humain", lycanthrope.getEtat());
    }

    @Test
    void testChoisirRangAleatoire() {
        lycanthrope.choisirRangAleatoire();
        String rang = lycanthrope.getRang();
        assertTrue(rang.matches("[βγδεζηθω]"));
    }

    @Test
    void testToString() {
        String toStringResult = lycanthrope.toString();
        assertTrue(toStringResult.contains("sexe='Mâle'"));
        assertTrue(toStringResult.contains("rang='ω'"));
    }
}
