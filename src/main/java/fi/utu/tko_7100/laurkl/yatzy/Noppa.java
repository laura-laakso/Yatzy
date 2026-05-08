package fi.utu.tko_7100.laurkl.yatzy;

import java.util.Random;

/**
 * Määrittää yhden nopan toiminnan.
 */
public class Noppa {

    //Attribuutit

    /**
     * Nopan silmäluvun arvo.
     */
    private int arvo;

    /**
     * True, jos noppa on lukittu, false jos vapaa.
     */
    private boolean lukittu;

    /**
     * Satunnaisluvun generoiva olio.
     */
    private static final Random random = new Random();

    //Konstruktori

    /**
     * Alustaa nopan silmäluvun arvon.
     * Alustaa nopan vapaaksi.
     */
    public Noppa() {
        heita();
        lukittu = false;
    }

    //Metodit

    /**
     * Satunnaisesti arpoo nopan silmäluvulle 1-6 arvon.
     */
    public void heita() {
        if (!lukittu) {
            arvo = random.nextInt(6) + 1;
        }
    }

    /**
     * Muuttaa nopan lukituksen vapaaksi.
     */
    public void vapauta() {
        lukittu = false;
    }

    /**
     * Vaihtaa nopan lukituksen.
     */
    public void vaihdaLukitus() {
        lukittu = !lukittu;
    }

    /**
     * Palauttaa tiedon siitä, onko noppa lukittu.
     * @return True, jos noppa on lukittu, false jos noppa on vapaa.
     */
    public boolean onLukittu() {
        return lukittu;
    }

    /**
     * Palauttaa nopan silmäluvun arvon.
     * @return Nopan silmäluvun arvon.
     */
    public int getArvo() {
        return arvo;
    }
}
