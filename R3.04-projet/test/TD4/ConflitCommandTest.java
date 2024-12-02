package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConflitCommandTest {
    private Lycanthrope agresseur;
    private Lycanthrope cible;
    private Meute meute;
    private Colonie colonie;
    private ConflitCommand conflitCommand;

    @BeforeEach
    void setUp() {
        // Mock des objets nécessaires
        agresseur = Mockito.mock(Lycanthrope.class);
        cible = Mockito.mock(Lycanthrope.class);
        meute = Mockito.mock(Meute.class);
        colonie = Mockito.mock(Colonie.class);

        // Création de la commande avec les mocks
        conflitCommand = new ConflitCommand(agresseur, cible, meute, colonie);
    }

    @Test
    void testExecute_CibleEstFemelleAlpha() {
        // Arrange
        when(meute.getFemelleAlpha()).thenReturn(cible);

        // Act
        conflitCommand.execute();

        // Assert
        verify(agresseur, never()).augmenterFacteurDomination();
        verify(cible, never()).baisserFacteurDomination();
        verify(agresseur, never()).hurler(anyString());
        System.out.println("Test terminé : la femelle alpha ne peut pas être attaquée.");
    }

    @Test
    void testExecute_CibleEstOmega() {
        // Arrange
        when(cible.getRang()).thenReturn("ω");

        // Act
        conflitCommand.execute();

        // Assert
        verify(agresseur).augmenterFacteurDomination();
        verify(cible).baisserFacteurDomination();
        verify(agresseur).hurler("AOOOOUHHHH");
        verify(cible).hurler("AIH");
    }

    @Test
    void testExecute_AgresseurPlusFort() {
        // Arrange
        when(agresseur.calculerNiveau()).thenReturn(10.0);
        when(cible.calculerNiveau()).thenReturn(5.0);
        when(agresseur.getRang()).thenReturn("γ");
        when(cible.getRang()).thenReturn("β");

        List<Lycanthrope> membres = new ArrayList<>();
        membres.add(agresseur);
        membres.add(cible);
        when(meute.getMembres()).thenReturn(membres);

        // Act
        conflitCommand.execute();

        // Assert
        verify(agresseur).augmenterFacteurDomination();
        verify(cible).baisserFacteurDomination();
        verify(agresseur).hurler("AOUH");
        verify(cible).hurler("AIH");
        verify(agresseur).setRang("β");
        verify(cible).setRang("γ");
    }

    @Test
    void testExecute_AgresseurMoinsFort() {
        // Arrange
        when(agresseur.calculerNiveau()).thenReturn(5.0);
        when(cible.calculerNiveau()).thenReturn(10.0);

        // Act
        conflitCommand.execute();

        // Assert
        verify(agresseur).baisserFacteurDomination();
        verify(cible).augmenterFacteurDomination();
        verify(agresseur).hurler("AIH");
        verify(cible).hurler("AOUH");
    }

    @Test
    void testExecute_AgresseurQuitteMeute() {
        // Arrange
        when(agresseur.calculerNiveau()).thenReturn(5.0);
        when(cible.calculerNiveau()).thenReturn(10.0);
        when(cible).thenReturn(meute.getMaleAlpha());
        when(Math.random()).thenReturn(0.1); // Probabilité que l'agresseur quitte la meute

        // Act
        conflitCommand.execute();

        // Assert
        verify(agresseur).quitterMeute();
        verify(colonie).ajouterSolitaire(agresseur);
    }
}
