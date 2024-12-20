package TD4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LycanthropeFactoryImplTest {
    private LycanthropeFactory factory;

    @BeforeEach
    void setUp() {
        factory = new LycanthropeFactoryImpl();
    }

    @Test
    void testCreerLycanthrope_Valide() {
        // Arrange
        String sexe = "Mâle";
        String categorieAge = "Adulte";

        // Act
        Lycanthrope lycanthrope = factory.creerLycanthrope(sexe, categorieAge);

        // Assert
        assertNotNull(lycanthrope);
        assertEquals(sexe, lycanthrope.getSexe());
        assertEquals(categorieAge, lycanthrope.getCategorieAge());
        assertTrue(lycanthrope.getForce() >= 50 && lycanthrope.getForce() <= 100,
                "La force devrait être entre 50 et 100.");
        assertTrue(lycanthrope.getFacteurImpetuosite() >= 0 && lycanthrope.getFacteurImpetuosite() <= 1,
                "L'impétuosité devrait être entre 0 et 1.");
    }

    @Test
    void testCreerLycanthrope_FemelleJeune() {
        // Arrange
        String sexe = "Femelle";
        String categorieAge = "Jeune";

        // Act
        Lycanthrope lycanthrope = factory.creerLycanthrope(sexe, categorieAge);

        // Assert
        assertNotNull(lycanthrope);
        assertEquals(sexe, lycanthrope.getSexe());
        assertEquals(categorieAge, lycanthrope.getCategorieAge());
    }

    @Test
    void testCreerLycanthrope_ForceAleatoire() {
        // Arrange
        String sexe = "Mâle";
        String categorieAge = "Adulte";
        int iterations = 100;
        boolean forceMinRespectee = true;
        boolean forceMaxRespectee = true;

        // Act
        for (int i = 0; i < iterations; i++) {
            Lycanthrope lycanthrope = factory.creerLycanthrope(sexe, categorieAge);
            if (lycanthrope.getForce() == 49) {
                forceMinRespectee = false;
            }
            if (lycanthrope.getForce() == 101) {
                forceMaxRespectee = false;
            }
        }

        // Assert
        assertTrue(forceMinRespectee, "La force minimale (50) n'a pas été dépassé après 100 itérations.");
        assertTrue(forceMaxRespectee, "La force maximale (100) n'a pas été dépassé après 100 itérations.");
    }

    @Test
    void testCreerLycanthrope_ImpetuositeAleatoire() {
        // Arrange
        String sexe = "Femelle";
        String categorieAge = "Adulte";
        int iterations = 100;
        boolean impetuositeMinRespectee = true;
        boolean impetuositeMaxRespectee = true;

        // Act
        for (int i = 0; i < iterations; i++) {
            Lycanthrope lycanthrope = factory.creerLycanthrope(sexe, categorieAge);
            if (lycanthrope.getFacteurImpetuosite() == -0.1) {
                impetuositeMinRespectee = false;
            }
            if (lycanthrope.getFacteurImpetuosite() == 1.1) {
                impetuositeMaxRespectee = false;
            }
        }

        // Assert
        assertTrue(impetuositeMinRespectee, "L'impétuosité minimale (0.0) n'a pas été dépassé après 100 itérations.");
        assertTrue(impetuositeMaxRespectee, "L'impétuosité maximale (1.0) n'a pas été dépassé après 100 itérations.");
    }
}
