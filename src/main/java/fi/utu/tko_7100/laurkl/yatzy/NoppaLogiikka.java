package fi.utu.tko_7100.laurkl.yatzy;

public class NoppaLogiikka {

    private Noppa[] nopat;

    public NoppaLogiikka() {
        nopat = new Noppa[5];

        for (int i = 0; i < nopat.length; i++) {
            nopat[i] = new Noppa();
        }
    }

    public void heitaKaikkiNopat() {
        for (Noppa noppa : nopat) {
            noppa.vapauta();
            noppa.heita();
        }
    }

    public void heitaLukitsemattomatNopat() {
        for (Noppa noppa : nopat) {
            noppa.heita();
        }
    }

    public void vaihdaLukitus (int nopanNumero) {
        if (nopanNumero >= 1 && nopanNumero <= nopat.length) {
            nopat[nopanNumero - 1]. vaihdaLukitus();
        }
    }

    public Noppa[] getNopat() {
        return nopat;
    }

    public int[] getArvot() {
        int[] arvot = new int [nopat.length];

        for (int i = 0; i < nopat.length; i++) {
            arvot[i] = nopat[i].getArvo();
        }

        return arvot;
    }

    public boolean[] getLukitukset() {
        boolean [] lukitukset = new boolean[nopat.length];

        for (int i = 0; i < nopat.length; i++) {
            lukitukset[i] = nopat[i].onLukittu();
        }

        return lukitukset;
    }
}
