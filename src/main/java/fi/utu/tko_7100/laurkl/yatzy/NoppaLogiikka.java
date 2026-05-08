package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Hallitsee pelin noppia.
 * Luo nopat, heittää niitä, palauttaa noppien arvot, vaihtaa noppien lukitukset.
 */
public class NoppaLogiikka {

    /**
     * Taulukko, jossa pelin nopat.
     */
    private Noppa[] nopat;

    /**
     * Luo viisi Noppa-oliota.
     */
    public NoppaLogiikka() {
        nopat = new Noppa[5];

        for (int i = 0; i < nopat.length; i++) {
            nopat[i] = new Noppa();
        }
    }

    /**
     * Heittää kaikki nopat.
     */
    public void heitaKaikkiNopat() {
        for (Noppa noppa : nopat) {
            noppa.vapauta();
            noppa.heita();
        }
    }

    /**
     * Heittää vain lukitsemattomat nopat.
     */
    public void heitaLukitsemattomatNopat() {
        for (Noppa noppa : nopat) {
            noppa.heita();
        }
    }

    /**
     * Lukitsee tai vapauttaa parametrina annetun nopan
     * @param nopanNumero Noppa, jonka lukitusta halutaan vaihtaa.
     */
    public void vaihdaLukitus (int nopanNumero) {
        if (nopanNumero >= 1 && nopanNumero <= nopat.length) {
            nopat[nopanNumero - 1]. vaihdaLukitus();
        }
    }

    /**
     * Palauttaa kaikki nopat.
     * @return Taulukko nopista.
     */
    public Noppa[] getNopat() {
        return nopat;
    }

    /**
     * Palauttaa noppien silmälukujen arvot.
     * @return Taulukko noppien silmäluvuista.
     */
    public int[] getArvot() {
        int[] arvot = new int [nopat.length];

        for (int i = 0; i < nopat.length; i++) {
            arvot[i] = nopat[i].getArvo();
        }

        return arvot;
    }

}
