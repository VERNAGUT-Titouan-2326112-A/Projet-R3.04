package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ReproductionCommandTest {
    private Meute meute;
    private Lycanthrope maleAlpha;
    private Lycanthrope femelleAlpha;
    private Lycanthrope betaMember;
    private Colonie colonie;

    @BeforeEach
    void setUp() {
        colonie = new Colonie();
        meute = new Meute(colonie);

        maleAlpha = new Lycanthrope("Mâle","Adulte",62, 1.8);
        femelleAlpha = new Lycanthrope("Femelle","Adulte",58, 1.7);
        betaMember = new Lycanthrope("Mâle","Adulte",60, 1.75);

        // Ajout des Lycanthropes alpha à la meute
        meute.setMaleAlpha(maleAlpha);
        meute.setFemelleAlpha(femelleAlpha);

        // Ajout d'un membre bêta pour les tests où c'est nécessaire
        meute.getMembres().clear();  // S'assurer que la liste des membres est vide avant l'ajout
    }

    @Test
    void testExecute_withAlphaCouple_noBetaMembers() {
        // Arrange
        ReproductionCommand command = new ReproductionCommand(meute);

        // Act
        command.execute();

        // Assert
        int addedMembers = meute.getMembres().size();
        assertTrue(addedMembers >= 1 && addedMembers <= 7, "Le nombre de jeunes ajoutés doit être entre 1 et 7.");
    }

    @Test
    void testExecute_withAlphaCouple_withBetaMembers() {
        // Arrange
        meute.getMembres().add(betaMember); // Ajout d'un membre bêta

        ReproductionCommand command = new ReproductionCommand(meute);

        // Act
        int tailleAvant = meute.getMembres().size();
        command.execute();
        int tailleApres = meute.getMembres().size();

        // Assert
        for (int i = tailleApres-tailleAvant - 1; i > tailleAvant; i--) {
            if (!meute.getMembres().get(i-1).getRang().equals("β")) {
                assertEquals("γ", meute.getMembres().get(i-1).getRang(), "Les nouveaux membres sans rang β devraient être γ.");
            }
        }
    }


    @Test
    void testExecute_alphaCoupleAddsCorrectRanks() {
        // Arrange
        ReproductionCommand command = new ReproductionCommand(meute);

        // Act
        command.execute();

        // Assert
        for (Lycanthrope lycan : meute.getMembres()) {
            String rang = lycan.getRang();
            assertTrue(rang.equals("β") || rang.equals("γ"), "Les nouveaux membres doivent avoir un rang correct.");
        }
    }
}
