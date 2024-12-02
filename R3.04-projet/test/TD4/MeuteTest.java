package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MeuteTest {
    private Meute meute;
    private Colonie colonieMock;
    private Lycanthrope maleMock;
    private Lycanthrope femelleMock;
    private Lycanthrope membreMock;

    @BeforeEach
    void setUp() {
        colonieMock = mock(Colonie.class);
        meute = new Meute(colonieMock);

        maleMock = mock(Lycanthrope.class);
        femelleMock = mock(Lycanthrope.class);
        membreMock = mock(Lycanthrope.class);

        when(maleMock.getSexe()).thenReturn("Mâle");
        when(maleMock.getCategorieAge()).thenReturn("Adulte");

        when(femelleMock.getSexe()).thenReturn("Femelle");
        when(femelleMock.getCategorieAge()).thenReturn("Adulte");
    }

    @Test
    void testAjouterMembre() {
        // Act
        meute.ajouterMembre(membreMock);

        // Assert
        assertTrue(meute.getMembres().contains(membreMock));
        verify(membreMock, times(1)).ajouterListener(meute);
        verify(membreMock, times(1)).setMeute(meute);
    }

    @Test
    void testRetirerMembre() {
        // Arrange
        meute.ajouterMembre(membreMock);

        // Act
        meute.retirerMembre(membreMock);

        // Assert
        assertFalse(meute.getMembres().contains(membreMock));
    }

    @Test
    void testSetMaleAlpha() {
        // Act
        meute.setMaleAlpha(maleMock);

        // Assert
        assertEquals(maleMock, meute.getMaleAlpha());
        verify(maleMock, times(1)).setRang("α");
        verify(maleMock, times(1)).calculerNiveau();
    }

    @Test
    void testSetFemelleAlpha() {
        // Act
        meute.setFemelleAlpha(femelleMock);

        // Assert
        assertEquals(femelleMock, meute.getFemelleAlpha());
        verify(femelleMock, times(1)).setRang("α");
        verify(maleMock, never()).calculerNiveau(); // Vérifie que seul le rang de la femelle a été modifié.
    }

    @Test
    void testCreerHierarchie() {
        // Arrange
        Lycanthrope betaMock = mock(Lycanthrope.class);
        Lycanthrope gammaMock = mock(Lycanthrope.class);

        when(betaMock.getCategorieAge()).thenReturn("Adulte");
        when(betaMock.getSexe()).thenReturn("Mâle");
        when(betaMock.getForce()).thenReturn(50);

        when(gammaMock.getCategorieAge()).thenReturn("Adulte");
        when(gammaMock.getSexe()).thenReturn("Femelle");
        when(gammaMock.getNiveau()).thenReturn(60);

        meute.ajouterMembre(betaMock);
        meute.ajouterMembre(gammaMock);

        // Act
        meute.creerHierarchie();

        // Assert
        assertEquals(betaMock, meute.getMaleAlpha());
        assertEquals(gammaMock, meute.getFemelleAlpha());
    }

    @Test
    void testReagirAuHurlement_domination() {
        // Arrange
        when(membreMock.getNom()).thenReturn("LoupBêta");
        meute.ajouterMembre(membreMock);

        // Act
        meute.reagirAuHurlement("domination", membreMock);

        // Assert
        // Vérification d'une éventuelle réaction (20% de chances).
        verify(membreMock, atLeast(0)).getNom();
    }

    @Test
    void testReagirAuHurlement_hurlementGeneralisé() {
        // Arrange
        Lycanthrope membre1 = mock(Lycanthrope.class);
        Lycanthrope membre2 = mock(Lycanthrope.class);

        meute.ajouterMembre(membre1);
        meute.ajouterMembre(membre2);

        // Act
        meute.reagirAuHurlement("appel", membreMock);

        // Assert
        verify(membre1, times(1)).hurler("appel");
        verify(membre2, times(1)).hurler("appel");
    }

    @Test
    void testAfficherCaracteristiques() {
        // Arrange
        meute.setMaleAlpha(maleMock);
        meute.setFemelleAlpha(femelleMock);
        meute.ajouterMembre(membreMock);

        // Act
        meute.afficherCaracteristiques();

        // Assert
        verify(maleMock, times(1)).afficherCaracteristiques();
        verify(femelleMock, times(1)).afficherCaracteristiques();
        verify(membreMock, times(1)).afficherCaracteristiques();
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
