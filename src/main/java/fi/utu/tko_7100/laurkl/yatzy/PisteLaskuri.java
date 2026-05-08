package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Laskee pisteet kategorioille.
 */
public class PisteLaskuri {

    /**
     * Laskee pisteet annetulle kategorialle noppien silmälukujen perusteella..
     * @param nopat Pelaajan noppien silmäluvut.
     * @param kategoria Kategorian numero, joka määrää miten pisteet lasketaan.
     * @return Kategoriasta saadut pisteet.
     */
    public int laskePisteet (int[] nopat, int kategoria) {
        int [] lkm = laskeLukumaarat(nopat);

        //0-5 eli ykköset-kutoset
        if (kategoria >= 0 && kategoria <= 5) {
            int numero = kategoria + 1;
            return lkm[numero] * numero;
        }

        //6: Pari
        if (kategoria == 6) {
            for (int i = 6; i >= 1; i--) {
                if (lkm[i] >= 2) {
                    return i * 2;
                }
            }

            return 0;
        }

        //7: Kaksi paria
        if (kategoria == 7) {
            int parit = 0;
            int summa = 0;

            for (int i = 6; i >= 1; i--) {
                if (lkm[i] >= 2) {
                    parit++;
                    summa += i * 2;
                }
            }

            if (parit >= 2) {
                return summa;
            }
            else {
                return 0;
            }
        }

        //8: Kolmoset
        if (kategoria == 8) {
            for(int i = 6; i >= 1; i--) {
                if (lkm[i] >= 3) {
                    return i * 3;
                }
            }
            return 0;
        }

        //9: Neloset
        if (kategoria == 9) {
            for (int i = 6; i >= 1; i--) {
                if (lkm[i] >= 4) {
                    return i * 4;
                }
            }
            return 0;
        }

        //10: Täyskäsi
        if (kategoria == 10) {
            boolean pari = false;
            boolean kolmoset = false;

            for (int i = 1; i <= 6; i++) {
                if (lkm[i] == 2) pari = true;
                if (lkm[i] == 3) kolmoset = true;
            }

            if (pari && kolmoset) {
                return summa(nopat);
            } else {
                return 0;
            }
        }


        //11: Pieni suora (1-5)
        if (kategoria == 11) {
            for (int i = 1; i <= 5; i++) {
                if (lkm[i] != 1) {
                    return 0;
                }
            }
            return 15;
        }

        //12: Iso suora (2-6)
        if (kategoria == 12) {
            for (int i = 2; i <= 6; i++) {
                if (lkm[i] != 1) {
                    return 0;
                }
            }
            return 20;
        }

        //13: Sattuma
        if (kategoria == 13) {
            return summa(nopat);
        }

        //14: Yatzy
        if (kategoria == 14) {
            for (int i = 1; i <= 6; i++) {
                if (lkm[i] == 5) {
                    return 50;
                }
            }
            return 0;
        }

        return 0;

    }

    /**
     * Laskee silmälukujen esiintymisen määrät.
     * @param nopat Taulukko noppien silmäluvuista.
     * @return Palauttaa taulukon silmälukujen määristä.
     */
    private int[] laskeLukumaarat(int[] nopat) {

        int [] lkm = new int [7];

        for (int noppa : nopat) {
            lkm[noppa]++;
        }

        return lkm;
    }

    /**
     * Laskee noppien silmälukujen summan.
     * @param nopat Taulukko noppien silmäluvuista.
     * @return Noppien silmälukujen summa.
     */
    private int summa(int[] nopat) {
        int summa = 0;
        for (int noppa : nopat) {
            summa += noppa;
        }
        return summa;
    }

}
