package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Ohjelman käynnistysluokka.
 * Luo Yatzy-peliolion ja käynnistää pelin.
 */

public class Main {
    public static void main(String [] args) {

        Yatzy y = new Yatzy();
        y.pelaa();

    }
}
