package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Tallentaa pelaajan nimen ja pistemäärän samaan olioon.
 */
public class Tulos {

    /**
     * Pelaajan nimi.
     */
    public String nimi;

    /**
     * Pelaajan pistemäärä.
     */
    public int pisteet;

    /**
     * Tyhjä konstruktori JSON-käsittelyä varten.
     */
    public Tulos() {}

    /**
     * Luo uusen tulos-olion.
     * @param nimi Pelaajan nimi.
     * @param pisteet Pelaajan pisteet.
     */
    public Tulos(String nimi, int pisteet) {
        this.nimi = nimi;
        this.pisteet = pisteet;
    }
}
