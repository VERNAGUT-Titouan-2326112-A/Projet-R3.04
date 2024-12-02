package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeuteTest {
    private Meute meute;
    private Colonie colonie;
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;
    private Lycanthrope membre;

    @BeforeEach
    void setUp() {
        colonie = new Colonie(); // Crée une colonie réelle
        meute = new Meute(colonie);

        maleAlpha = new Lycanthrope("Mâle", "Adulte", 100, 0.2);
        femelleAlpha = new Lycanthrope("Femelle", "Adulte", 90, 0.3);
        membre = new Lycanthrope("Mâle", "Adulte", 60, 0.4);
    }

    @Test
    void testAjouterMembre() {
        // Act
        meute.ajouterMembre(membre);

        // Assert
        assertTrue(meute.getMembres().contains(membre));
        assertEquals(meute, membre.getMeute()); // Vérifie que le membre est bien associé à la meute
    }

    @Test
    void testRetirerMembre() {
        // Arrange
        meute.ajouterMembre(membre);

        // Act
        meute.retirerMembre(membre);

        // Assert
        assertFalse(meute.getMembres().contains(membre));
    }

    @Test
    void testSetMaleAlpha() {
        // Act
        meute.setMaleAlpha(maleAlpha);

        // Assert
        assertEquals(maleAlpha, meute.getMaleAlpha());
        assertEquals("α", maleAlpha.getRang()); // Le rang doit être 'α'
    }

    @Test
    void testSetFemelleAlpha() {
        // Act
        meute.setFemelleAlpha(femelleAlpha);

        // Assert
        assertEquals(femelleAlpha, meute.getFemelleAlpha());
        assertEquals("α", femelleAlpha.getRang()); // Le rang doit être 'α'
    }

    @Test
    void testCreerHierarchie() {
        // Arrange
        Lycanthrope beta = new Lycanthrope("Mâle", "Adulte", 50, 0.2);
        Lycanthrope gamma = new Lycanthrope("Femelle", "Adulte", 60, 0.4);

        meute.ajouterMembre(beta);
        meute.ajouterMembre(gamma);

        // Act
        meute.creerHierarchie();

        // Assert
        assertEquals(beta, meute.getMaleAlpha());
        assertEquals(gamma, meute.getFemelleAlpha());
    }

    @Test
    void testReagirAuHurlement_domination() {
        // Arrange
        meute.ajouterMembre(membre);

        // Act
        meute.reagirAuHurlement("domination", membre);

        // Assert
        // Ici, il faudrait que la logique de réaction soit explicitée et testée.
        // Par exemple, s'assurer que les membres réagissent ou que le conflit est lancé.
    }

    @Test
    void testAfficherCaracteristiques() {
        // Arrange
        meute.setMaleAlpha(maleAlpha);
        meute.setFemelleAlpha(femelleAlpha);
        meute.ajouterMembre(membre);

        // Act
        meute.afficherCaracteristiques();

        // Assert
        // On vérifie que les caractéristiques de chaque membre sont bien affichées.
        assertDoesNotThrow(() -> maleAlpha.afficherCaracteristiques());
        assertDoesNotThrow(() -> femelleAlpha.afficherCaracteristiques());
        assertDoesNotThrow(() -> membre.afficherCaracteristiques());
    }

    @Test
    void testGetNomEtSetNom() {
        // Act
        String nouveauNom = "NouvelleMeute";
        meute.setNom(nouveauNom);

        // Assert
        assertEquals(nouveauNom, meute.getNom());
    }
}
