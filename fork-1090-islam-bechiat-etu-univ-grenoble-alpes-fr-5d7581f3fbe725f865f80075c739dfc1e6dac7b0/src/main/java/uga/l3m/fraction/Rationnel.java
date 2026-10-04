package uga.l3m.fraction;

import java.util.ArrayList;
import java.util.List;

public class Rationnel {
    private EntierRelatif numerateur;
    private EntierRelatif denominateur;

    public Rationnel(EntierRelatif numerateur, EntierRelatif denominateur) {
        // Problème : pas possible d'assurer l'encapsulation si on utilise les objets passés en paramètre sans les copier
        this.numerateur = numerateur;
        this.denominateur = denominateur;
    }

    public EntierRelatif getNumerateur() {
        return numerateur;
    }

    public EntierRelatif getDenominateur() {
        return denominateur;
    }

    /**
     * Simplifie la fraction en supprimant les facteurs premiers communs au numérateur et au dénominateur.
     * @return une liste de chaînes de caractères décrivant les étapes de la simplification.
     */
    public List<String> simplifier() {
        return null;
    }

    public Rationnel copier() {
        return new Rationnel(this.numerateur, this.denominateur);
    }
    
    public void inverser() {
        EntierRelatif temp = this.numerateur;
        this.numerateur = this.denominateur;
        this.denominateur = temp;
    }

    /**
     * Multiplie ce rationnel par un autre rationnel.
     * @param autre le rationnel à multiplier avec ce rationnel
     * @return une liste de chaînes de caractères décrivant les étapes de la multiplication.
     *         Les étapes se basent sur la manipulation des facteurs premiers.
     */
    public List<String> multiplierPar(Rationnel autre) {
        this.numerateur.multiplierPar(autre.numerateur);
        this.denominateur.multiplierPar(autre.denominateur);
        return List.of();
    }

    /**
     * Divise ce rationnel par un autre rationnel.
     * @param autre le rationnel à utiliser pour diviser ce rationnel
     * @return une liste de chaînes de caractères décrivant les étapes de la division.
     *         Les étapes se basent sur la manipulation des facteurs premiers.
     */
    public List<String> diviserPar(Rationnel autre) {
        this.numerateur.multiplierPar(autre.denominateur);
        this.denominateur.multiplierPar(autre.numerateur);
        return List.of();
    }

    public String asStringDecimale() {
        return "";
    }

    public String asStringFacteursPremiers() {
        return "";
    }
}
