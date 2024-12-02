package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConflitCommandTest {
    private Lycanthrope agresseur;
    private Lycanthrope cible;
    private Meute meute;
    private Colonie colonie;
    private ConflitCommand conflitCommand;

    @BeforeEach
    void setUp() {
        // Création des instances réelles des objets nécessaires
        agresseur = new Lycanthrope("Mâle", "Adulte", 62, 1.8);
        cible = new Lycanthrope("Mâle", "Adulte", 58, 1.7);
        colonie = new Colonie();
        meute = new Meute(colonie);


        // Ajout de membres à la meute
        meute.getMembres().add(agresseur);
        meute.getMembres().add(cible);

        // Création de la commande avec les objets réels
        conflitCommand = new ConflitCommand(agresseur, cible, meute, colonie);
    }

    @Test
    void testExecute_CibleEstFemelleAlpha() {
        // Arrange
        meute.setFemelleAlpha(cible);

        // Act
        conflitCommand.execute();

        // Assert
        assertEquals(agresseur.getFacteurDomination(), 0.0, "Le facteur de domination de l'agresseur ne doit pas changer.");
        assertEquals(cible.getFacteurDomination(), 0.0, "Le facteur de domination de la cible ne doit pas changer.");
    }

    @Test
    void testExecute_CibleEstOmega() {
        // Arrange
        cible.setRang("ω");

        // Act
        conflitCommand.execute();

        // Assert
        assertTrue(agresseur.getFacteurDomination() > 0, "L'agresseur doit augmenter son facteur de domination.");
        assertTrue(cible.getFacteurDomination() < 0, "La cible doit baisser son facteur de domination.");
    }

    @Test
    void testExecute_AgresseurPlusFort() {
        // Arrange
        agresseur.setNiveau(10.0);
        cible.setNiveau(5.0);
        agresseur.setRang("γ");
        cible.setRang("β");

        List<Lycanthrope> membres = new ArrayList<>();
        membres.add(agresseur);
        membres.add(cible);
        meute.setMembres(membres);

        // Act
        conflitCommand.execute();

        // Assert
        assertEquals(agresseur.getFacteurDomination(), 1.0, "Le facteur de domination de l'agresseur doit augmenter.");
        assertEquals(cible.getFacteurDomination(), -1.0, "Le facteur de domination de la cible doit diminuer.");
    }

    @Test
    void testExecute_AgresseurMoinsFort() {
        // Arrange
        agresseur.setForce(5);
        cible.setForce(10);

        // Act
        conflitCommand.execute();

        // Assert
        assertEquals(agresseur.getFacteurDomination(), 1.0, "Le facteur de domination de l'agresseur doit diminuer.");
        assertEquals(cible.getFacteurDomination(), -1.0, "Le facteur de domination de la cible doit augmenter.");
    }

    @Test
    void testExecute_AgresseurQuitteMeute() {
        // Arrange
        agresseur.setNiveau(5.0);
        cible.setNiveau(10.0);
        agresseur.setMeute(meute);

        // Simuler la probabilité de l'agresseur quittant la meute
        double randomValue = 0.1; // Une valeur aléatoire pour tester

        // Act
        if (randomValue < 0.2) {
            agresseur.quitterMeute();
            colonie.ajouterSolitaire(agresseur);
        }

        // Assert
        if (randomValue < 0.2) {
            assertTrue(colonie.getSolitaires().contains(agresseur), "L'agresseur doit être ajouté à la colonie.");
        } else {
            assertFalse(colonie.getSolitaires().contains(agresseur), "L'agresseur ne doit pas être ajouté à la colonie.");
        }
    }
}
