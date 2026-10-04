package uga.l3m;

import org.junit.jupiter.api.Test;

import uga.l3m.fraction.EntierRelatif;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for Entier.
 */
class EntierRelatifTest {
    
    @Test
    void testEntierPremier() {
        EntierRelatif e = new EntierRelatif(13);

        assertEquals(13, e.getValeur());
        assertEquals(1, e.getFacteursPremiers().size());
        assertEquals(13, e.getFacteursPremiers().get(0));
    }

    @Test
    void testEntierComposé() {
        EntierRelatif e = new EntierRelatif(30);

        assertEquals(30, e.getValeur());
        assertEquals(3, e.getFacteursPremiers().size());
        assertEquals(2, e.getFacteursPremiers().get(0));
        assertEquals(3, e.getFacteursPremiers().get(1));
        assertEquals(5, e.getFacteursPremiers().get(2));
    }

    @Test
    void testEntierNégatif() {
        EntierRelatif e = new EntierRelatif(-27);
        assertEquals(-27, e.getValeur());
        assertEquals(4, e.getFacteursPremiers().size());
        assertEquals(-1, e.getFacteursPremiers().get(0));
        assertEquals(3, e.getFacteursPremiers().get(1));
        assertEquals(3, e.getFacteursPremiers().get(2));
        assertEquals(3, e.getFacteursPremiers().get(3));
    }

    @Test
    void testEntierUn() {
        EntierRelatif e = new EntierRelatif(1);
        assertEquals(1, e.getValeur());
        assertEquals(1, e.getFacteursPremiers().size());
        assertEquals(1, e.getFacteursPremiers().get(0));
    }
    
    @Test
    void valeurMiseAJourApresModificationFacteursPremiers() {
        EntierRelatif e = new EntierRelatif(1);
        e.setFacteursPremiers(List.of(2, 3, 5));
        assertEquals(30, e.getValeur());
        assertEquals(3, e.getFacteursPremiers().size());
        assertEquals(2, e.getFacteursPremiers().get(0));
        assertEquals(3, e.getFacteursPremiers().get(1));
        assertEquals(5, e.getFacteursPremiers().get(2));
    }

    @Test
    void encapsulationPreservéeApresModificationFacteursPremiers() {
        EntierRelatif e = new EntierRelatif(1);
        List<Integer> facteurs = new ArrayList<>(List.of(2, 3, 5));
        e.setFacteursPremiers(facteurs);
        facteurs.add(7); // Modification de la liste après l'avoir passée à l'entier
        assertEquals(30, e.getValeur());
        assertEquals(3, e.getFacteursPremiers().size());
        assertEquals(2, e.getFacteursPremiers().get(0));
        assertEquals(3, e.getFacteursPremiers().get(1));
        assertEquals(5, e.getFacteursPremiers().get(2));
    }

    @Test
    void encapsulationPreservéeApresGetFacteursPremiers() {
        EntierRelatif e = new EntierRelatif(17);
        List<Integer> facteurs = e.getFacteursPremiers();
        facteurs.add(7); // Modification de la liste obtenue via getFacteursPremiers
        assertEquals(17, e.getValeur());
        assertEquals(1, e.getFacteursPremiers().size());
        assertEquals(17, e.getFacteursPremiers().get(0));
    }

}
