package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireDeSaisonsTest {
    private GestionnaireDeSaisons gestionnaire;
    private List<Meute> meutes;
    private Colonie colonie;

    @BeforeEach
    void setUp() {
        meutes = new ArrayList<>();
        colonie = new Colonie();

        Meute meute1 = new Meute(colonie);
        Meute meute2 = new Meute(colonie);

        Lycanthrope lycan1 = new Lycanthrope("Mâle", "Jeune", 30, 0.4);
        Lycanthrope lycan2 = new Lycanthrope("Femelle", "Adulte", 50, 0.6);

        meute1.ajouterMembre(lycan1);
        meute2.ajouterMembre(lycan2);

        meutes.add(meute1);
        meutes.add(meute2);

        gestionnaire = new GestionnaireDeSaisons(1000, meutes, colonie);
    }

    @Test
    void testInitialisation() {
        assertNotNull(gestionnaire);
        assertEquals(2, meutes.size());
        assertEquals("Meute9", meutes.get(0).getNom());
        assertEquals("Meute10", meutes.get(1).getNom());
    }

    @Test
    void testDemarrerEtArreterCycle() {
        gestionnaire.demarrer();
        gestionnaire.arreter();
    }

    @Test
    void testChangerSaison_Printemps() {
        assertEquals("Printemps", gestionnaire.getSaisonActuelle());
    }

    @Test
    void testChangerSaison_Ete() {
        gestionnaire.changerSaison(); // Passe a l'été
        assertEquals("Été", gestionnaire.getSaisonActuelle());
    }

    @Test
    void testChangerSaison_Automne() {
        gestionnaire.changerSaison(); // Passe a l'été
        gestionnaire.changerSaison(); // Passe à l'automne
        assertEquals("Automne", gestionnaire.getSaisonActuelle());

        // Vérifier les transformations humaines
        for (Meute meute : meutes) {
            for (Lycanthrope lycanthrope : meute.getMembres()) {
                lycanthrope.seTransformerEnHumain();
                assertEquals("humain", lycanthrope.getEtat());
            }
        }
    }

    @Test
    void testChangerSaison_Hiver() {
        gestionnaire.changerSaison(); // Passe a l'été
        gestionnaire.changerSaison(); // Passe à l'autome
        // Vérifier les changements dans les catégories d'âge et les décès
        Meute meute = meutes.getFirst();
        Lycanthrope lycanthrope = meute.getMembres().getFirst();
        lycanthrope.setCategorieAge("Vieux");
        gestionnaire.changerSaison(); // Passe à l'hiver
        assertEquals("Hiver", gestionnaire.getSaisonActuelle());
        gestionnaire.changerSaison();
        assertFalse(meute.getMembres().contains(lycanthrope));
    }

    @Test
    void testSetDureeSaison() {
        gestionnaire.setDureeSaison(2000);
        assertEquals(2000, gestionnaire.getDureeSaison());
    }
}
