package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Hoitaa tekoäly pelaajan vuoron.
 * Laskee tekoälypelaajan pisteet.
 * Valitsee mistä kategoriasta kierroksella saa parhaat pisteet.
 */
public class TekoalyPelaaja implements Pelaaja{

    //Atribuutit

    /**
     * Tekoälypelaajan pistekortti.
     */
    private Pistekortti pistekortti;

    /**
     * Noppien heittämiseen ja lukitsemiseen käytettävä logiikka.
     */
    private NoppaLogiikka noppaLogiikka = new NoppaLogiikka();

    /**
     * Pisteiden laskemiseen käytettävä laskuri.
     */
    private PisteLaskuri pisteLaskuri = new PisteLaskuri();

    //Konstruktori

    /**
     * Luo tekoälypelaajan pistekortin.
     */
    public TekoalyPelaaja () {
        this.pistekortti = new Pistekortti();
    }

    /**
     * Ohjaa tekoälypelaajan vuoron.
     * Laskee tekoälypelaajan nopista parhaimman kategorian.
     * @return Palauttaa true, koska tekoälypelaaja ei koskaan lopeta peliä.
     */
    @Override
    public boolean pelaaVuoro() {

        System.out.println ("=================================");
        System.out.println ("======= Vastustajan vuoro =======");
        System.out.println ("=================================\n");

        noppaLogiikka.heitaKaikkiNopat();
        System.out.println ("Vastustaja heitti nopat.");

        for (int i = 0; i < 2; i++) {
            int tavoite = yleisinSilmäluku();
            lukitseVainSilmäluku(tavoite);

            noppaLogiikka.heitaLukitsemattomatNopat();

            System.out.println ("Vastustaja heitti lukitsemattomat nopat uudelleen.");
        }

        int parasKategoria = valitseParasKategoria();
        int pisteet = pisteLaskuri.laskePisteet(noppaLogiikka.getArvot(), parasKategoria);

        pistekortti.kirjaaPisteet(parasKategoria, pisteet);

        System.out.println ("Vastustaja valitsi kategorian: " + kategoriaNimi(parasKategoria));
        System.out.println ("Vastustaja sai pisteitä: " + pisteet);
        System.out.println ();

        return true;
    }

    /**
     * Palauttaa tekoälypelaajan pistekortin.
     * @return Tekoälypelaajan pistekortti.
     */
    @Override
    public Pistekortti getPistekortti(){
        return pistekortti;
    }

    /**
     * Palauttaa tekoälypelaajan yhteispisteet.
     * @return Tekoälypelaajan yhteispisteet.
     */
    @Override
    public int annaPisteet() {
        return pistekortti.laskeYhteispisteet();
    }

    /**
     * Palauttaa nopista yleisimmän silmäluvun.
     * @return Noppien yleisin silmäluku.
     */
    private int yleisinSilmäluku() {
        int[] arvot = noppaLogiikka.getArvot();
        int[] lkm = new int [7];

        for (int arvo : arvot) {
            lkm[arvo]++;
        }

        int parasArvo = 1;
        int parasLkm = 0;

        for (int i = 1; i <= 6; i++) {
            if (lkm[i] > parasLkm) {
                parasLkm = lkm[i];
                parasArvo = i;
            }
        }

        return parasArvo;
    }

    /**
     * Lukitsee tietyn silmäluvun nopista.
     * @param tavoite Silmäluku, joka halutaan lukita.
     */
    private void lukitseVainSilmäluku (int tavoite) {
        Noppa[] nopat = noppaLogiikka.getNopat();

        for (Noppa noppa : nopat) {
            boolean pitaisiOllaLukittu = noppa.getArvo() == tavoite;

            if (pitaisiOllaLukittu && !noppa.onLukittu()) {
                noppa.vaihdaLukitus();
            }
            else if (!pitaisiOllaLukittu && noppa.onLukittu()) {
                noppa.vaihdaLukitus();
            }
        }
    }

    /**
     * Laskee mistä kategoriasta pelaaja saa arhaat pisteet nykyisillä nopilla.
     * @return Kategoria, josta saa parhaat pisteet.
     */
    private int valitseParasKategoria() {
        int parasKategoria = -1;
        int parhaatPisteet = -1;

        for (int i = 0; i < 15; i++) {
            if (!pistekortti.onkoTaytetty(i)) {
                int pisteet = pisteLaskuri.laskePisteet(noppaLogiikka.getArvot(), i);

                if (pisteet > parhaatPisteet) {
                    parhaatPisteet = pisteet;
                    parasKategoria = i;
                }
            }
        }

        return parasKategoria;
    }

    /**
     * Palauttaa parametrina olevan arvon kohdalta kategorian nimi.
     * @param i Taulukon arvo, jossa haluttu nimi on.
     * @return Palauttaa kategorian nimen.
     */
    private String kategoriaNimi (int i) {
        String[] kategoriat = {
                "Ykköset", "Kakkoset", "Kolmoset", "Neloset", "Viitoset",
                "Kutoset", "Pari", "Kaksi paria", "Kolmoset", "Neloset",
                "Täyskäsi", "Pieni suora", "Iso suora", "Sattuma", "Yatzy"
        };

        return kategoriat[i];
    }
}
