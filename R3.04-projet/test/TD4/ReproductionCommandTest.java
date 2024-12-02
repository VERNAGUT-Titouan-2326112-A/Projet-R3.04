package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReproductionCommandTest {
    private Meute meuteMock;
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;
    private Lycanthrope betaMember;

    @BeforeEach
    void setUp() {
        meuteMock = mock(Meute.class);
        maleAlpha = mock(Lycanthrope.class);
        femelleAlpha = mock(Lycanthrope.class);
        betaMember = mock(Lycanthrope.class);

        when(maleAlpha.getRang()).thenReturn("α");
        when(femelleAlpha.getRang()).thenReturn("α");
        when(betaMember.getRang()).thenReturn("β");
        when(meuteMock.getMaleAlpha()).thenReturn(maleAlpha);
        when(meuteMock.getFemelleAlpha()).thenReturn(femelleAlpha);
        when(meuteMock.getMembres()).thenReturn(new LinkedList<>());
    }

    @Test
    void testExecute_withAlphaCouple_noBetaMembers() {
        // Arrange
        ReproductionCommand command = new ReproductionCommand(meuteMock);

        // Act
        command.execute();

        // Assert
        verify(meuteMock, atLeast(1)).ajouterMembre(any(Lycanthrope.class));
        int addedMembers = meuteMock.getMembres().size();
        assertTrue(addedMembers >= 1 && addedMembers <= 7, "Le nombre de jeunes ajoutés doit être entre 1 et 7.");
    }

    @Test
    void testExecute_withAlphaCouple_withBetaMembers() {
        // Arrange
        LinkedList<Lycanthrope> membres = new LinkedList<>();
        membres.add(betaMember);
        when(meuteMock.getMembres()).thenReturn(membres);

        ReproductionCommand command = new ReproductionCommand(meuteMock);

        // Act
        command.execute();

        // Assert
        verify(meuteMock, atLeast(1)).ajouterMembre(any(Lycanthrope.class));
        for (Lycanthrope lycan : meuteMock.getMembres()) {
            if (!lycan.getRang().equals("β")) {
                assertEquals("γ", lycan.getRang(), "Les nouveaux membres sans rang β devraient être γ.");
            }
        }
    }

    @Test
    void testExecute_noAlphaCouple() {
        // Arrange
        when(meuteMock.getMaleAlpha()).thenReturn(null);
        ReproductionCommand command = new ReproductionCommand(meuteMock);

        // Act
        command.execute();

        // Assert
        verify(meuteMock, never()).ajouterMembre(any(Lycanthrope.class));
    }

    @Test
    void testExecute_noAlphaFemale() {
        // Arrange
        when(meuteMock.getFemelleAlpha()).thenReturn(null);
        ReproductionCommand command = new ReproductionCommand(meuteMock);

        // Act
        command.execute();

        // Assert
        verify(meuteMock, never()).ajouterMembre(any(Lycanthrope.class));
    }

    @Test
    void testExecute_alphaCoupleAddsCorrectRanks() {
        // Arrange
        ReproductionCommand command = new ReproductionCommand(meuteMock);

        // Act
        command.execute();

        // Assert
        for (Lycanthrope lycan : meuteMock.getMembres()) {
            String rang = lycan.getRang();
            assertTrue(rang.equals("β") || rang.equals("γ"), "Les nouveaux membres doivent avoir un rang correct.");
        }
    }
}

