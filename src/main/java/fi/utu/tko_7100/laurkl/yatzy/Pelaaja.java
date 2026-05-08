package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Rajapinta pelaajille.
 * Määrittää metodit, jotka jokaisen pelaajaluokan tulee toteuttaa.
 */
public interface Pelaaja {

    /**
     * Suorittaa pelaajan vuoron.
     * @return True jos peli jatkuu, false jos peli lopetetaan.
     */
    boolean pelaaVuoro();

    /**
     * Palauttaa pelaajan pistekortin.
     * @return Pelaajan pistekortti.
     */
    Pistekortti getPistekortti();

    /**
     * Palauttaa pelaajan kokonaispisteet.
     * @return Pelaajan kokonaispisteet.
     */
    int annaPisteet();
}
