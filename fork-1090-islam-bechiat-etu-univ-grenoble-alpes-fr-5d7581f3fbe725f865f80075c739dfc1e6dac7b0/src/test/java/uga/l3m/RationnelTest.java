package uga.l3m;

import org.junit.jupiter.api.Test;

import uga.l3m.fraction.EntierRelatif;
import uga.l3m.fraction.Rationnel;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests pour la classe Rationnel : Partie 2.
 *
 * Deux exemples sont fournis ci-dessous pour vous montrer comment structurer
 * un test. À vous d'en ajouter d'autres selon les consignes de l'exercice 13.
 */
class RationnelTest {

    /**
     * Exemple 1 : simplifier() réduit correctement une fraction simplifiable.
     *
     * 6/4 = (2 x 3) / (2 x 2) → après simplification : 3/2
     */
    @Test
    void simplifierReduitLaFraction() {
        Rationnel r = new Rationnel(new EntierRelatif(6), new EntierRelatif(4));

        r.simplifier();

        assertEquals(3, r.getNumerateur().getValeur());
        assertEquals(2, r.getDenominateur().getValeur());
    }

    /**
     * Exemple 2 : asStringDecimale() retourne la représentation "p/q".
     *
     * Le rationnel −3/10 doit s'afficher "-3/10".
     */
    @Test
    void asStringDecimaleFormateCorrectement() {
        Rationnel r = new Rationnel(new EntierRelatif(-3), new EntierRelatif(10));

        assertEquals("-3/10", r.asStringDecimale());
    }

    // -----------------------------------------------------------------------
    // À vous de jouer : ajoutez vos tests en dessous de cette ligne
    // -----------------------------------------------------------------------

}
