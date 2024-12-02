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
        assertEquals("Meute1", meutes.get(0).getNom());
        assertEquals("Meute2", meutes.get(1).getNom());
    }

    @Test
    void testDemarrerEtArreterCycle() {
        gestionnaire.demarrer();
        // On ne peut pas facilement vérifier directement le fonctionnement du Timer,
        // mais s'il n'y a pas d'exception ici, c'est bon signe.
        gestionnaire.arreter();
    }

    @Test
    void testChangerSaison_Printemps() {
        gestionnaire.changerSaison(); // Passe au printemps
        assertEquals("Printemps", gestionnaire.getSaisonActuelle());

        // Vérifier les événements spécifiques du printemps
        // Comme la reproduction est probabilistique, on ne peut pas tester de manière déterministe.
    }

    @Test
    void testChangerSaison_Ete() {
        gestionnaire.changerSaison(); // Passe au printemps
        gestionnaire.changerSaison(); // Passe à l'été
        assertEquals("Été", gestionnaire.getSaisonActuelle());

        // Vérifier les événements spécifiques à l'été
        // Simulation d'un conflit
    }

    @Test
    void testChangerSaison_Automne() {
        gestionnaire.changerSaison(); // Passe au printemps
        gestionnaire.changerSaison(); // Passe à l'été
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
        gestionnaire.changerSaison(); // Passe au printemps
        gestionnaire.changerSaison(); // Passe à l'été
        gestionnaire.changerSaison(); // Passe à l'automne
        gestionnaire.changerSaison(); // Passe à l'hiver
        assertEquals("Hiver", gestionnaire.getSaisonActuelle());

        // Vérifier les changements dans les catégories d'âge et les décès
        Meute meute = meutes.getFirst();
        Lycanthrope lycanthrope = meute.getMembres().getFirst();
        lycanthrope.setCategorieAge("Vieux");
        gestionnaire.changerSaison();
        assertFalse(meute.getMembres().contains(lycanthrope));
    }

    @Test
    void testSetDureeSaison() {
        gestionnaire.setDureeSaison(2000);
        assertEquals(2000, gestionnaire.getDureeSaison());
    }
}
