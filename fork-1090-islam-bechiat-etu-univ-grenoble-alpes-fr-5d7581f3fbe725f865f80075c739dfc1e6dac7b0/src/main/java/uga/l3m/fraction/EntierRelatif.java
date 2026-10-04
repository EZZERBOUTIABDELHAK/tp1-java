package uga.l3m.fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntierRelatif {
    private Integer valeur;
    private List<Integer> facteursPremiers;

    public EntierRelatif(Integer valeur) {
        this.valeur = valeur;
    }

    public EntierRelatif(EntierRelatif e) {
        this.valeur = e.valeur;
        this.facteursPremiers = e.facteursPremiers;
    }

    /**
     * Renvoie la valeur de l'entier relatif
     * @return la valeur de l'entier relatif
     */
    public Integer getValeur() {
        return valeur;
    }

    /**
     * Modifie la valeur de l'entier relatif
     * @param valeur la nouvelle valeur de l'entier relatif
     */
    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    /** Renvoie les facteurs premiers de l'entier relatif */
    public List<Integer> getFacteursPremiers() {
        return facteursPremiers;
    }

    /** Modifie les facteurs premiers de l'entier relatif
     * @param facteursPremiers les nouveaux facteurs premiers de l'entier relatif
     */
    public void setFacteursPremiers(List<Integer> facteursPremiers) {
        this.facteursPremiers = facteursPremiers;
    }

    /** Renvoie un nouvel entier relatif qui est une copie de l'entier relatif actuel */
    public EntierRelatif copier() {
        return new EntierRelatif(this.valeur);
    }

    /**
     * Modifie l'entier relatif en le multipliant par un autre entier relatif
     */
    public void multiplierPar(EntierRelatif autre) {
        this.valeur *= autre.valeur;
    }

    public String asStringDecimale() {
        return "";
    }

    public String asStringFacteursPremiers() {
        return "";
    }

    /**
     * Calcule les facteurs premiers de l'entier relatif `valeur`
     * et les stocke dans la liste `facteursPremiers`.
     */
    private void calculerFacteursPremiers() {
    }
}
