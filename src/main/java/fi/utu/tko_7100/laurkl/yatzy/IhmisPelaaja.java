package fi.utu.tko_7100.laurkl.yatzy;

import java.util.Scanner;

/**
 * Hoitaa ihmispelaajan pelivuoron.
 * Laskee ihmispelaajan pisteet, ottaa vastaan käyttäjän syötteitä.
 * Tulostaa käyttäjälle valinnat.
 */
public class IhmisPelaaja implements Pelaaja {

    /**
     * Ihmispelaajan pistekortti.
     */
    private Pistekortti pistekortti;

    /**
     * Lukee käyttäjän syötteen.
     */
    private Scanner lukija = new Scanner (System.in);

    /**
     * Noppien heittämiseen ja lukitsemiseen käytettävä logiikka.
     */
    private NoppaLogiikka noppaLogiikka = new NoppaLogiikka();

    /**
     * Pisteiden laskemiseen käytettävä laskuri.
     */
    private PisteLaskuri pisteLaskuri = new PisteLaskuri();

    //Värit

    private static final String RESET = "\u001B[0m";
    private static final String VIHREA = "\u001B[32m";
    private static final String PUNAINEN = "\u001B[31m";
    private static final String KELTAINEN = "\u001B[33m";

    /**
     * Luo ihmispelaajan pistekortin.
     */
    public IhmisPelaaja() {
        this.pistekortti = new Pistekortti();
    }

    /**
     * Tulostaa vuoron tilanteen, heitetyt nopat ja valinnat käyttäjälle.
     * Käsittelee käyttäjän antaman valinnan.
     * @return Palauttaa true jos peli jatkuu, false jos käyttäjä lopettaa pelin.
     */
    @Override
    public boolean pelaaVuoro() {

        int heittojaJaljella = 3;
        boolean vuoroOhi = false;

        System.out.println ("=================================");
        System.out.println ("========= Sinun vuorosi =========");
        System.out.println ("=================================\n");

        heitaKaikkiNopat();
        heittojaJaljella--;

        while (!vuoroOhi) {
            tulostaVuoronTilanne (heittojaJaljella);

            if (heittojaJaljella > 0) {
                tulostaNormaalitValinnat();
            } else {
                tulostaViimeinenValinta();
            }

            int valinta = kysyValinta();

            if (valinta == 1) {
                if (heittojaJaljella > 0) {
                    System.out.println();
                    heitaLukitsemattomatNopat();
                    heittojaJaljella--;
                }
                else {
                    System.out.println (punainen("\nEt voi heittää enää. Kaikki Heitot käytetty.\n"));
                }
            }

            else if (valinta == 2) {
                if (heittojaJaljella > 0) {
                    lukitseTaiVapautaNoppia();
                }
                else {
                    System.out.println (punainen("\nNoppia ei voi enää lukita tai vapauttaa, koska heittoja ei ole jäljellä.\n"));
                }
            }

            else if (valinta == 3) {
                kirjaaPisteetPistekorttiin();
                vuoroOhi = true;
            }

            else if (valinta == 0) {
                lopetaPeli();
                return false;
            }

            else {
                System.out.println (punainen("\nVirheellinen valinta. Anna numero väliltä 0-3.\n"));
            }
        }

        //Peli jatkuu
        return true;
    }


    /**
     * Palauttaa ihmispelaajan pistekortin.
     * @return Palauttaa pistekortin.
     */
    @Override
    public Pistekortti getPistekortti(){
        return pistekortti;
    }

    /**
     * Palauttaa ihmis pelaajan yhteispisteet.
     * @return Palauttaa ihmis pelaajan yhteispisteet.
     */
    @Override
    public int annaPisteet() {
        return pistekortti.laskeYhteispisteet();

    }

    /**
     * Heittää kaikki nopat ja tulostaa ne.
     */
    private void heitaKaikkiNopat () {

        noppaLogiikka.heitaKaikkiNopat();
        tulostaNopat();
    }

    /**
     * Heittaa lukitsemattomat nopat ja tulostaa kaikki nopat.
     */
    private void heitaLukitsemattomatNopat() {

        noppaLogiikka.heitaLukitsemattomatNopat();
        tulostaNopat();
    }

    private void tulostaNopat() {

        Noppa[] nopat = noppaLogiikka.getNopat();

        String [] [] kuvat = new String [nopat.length] [];
        int nopanLeveys = 9;
        String vali = "   ";

        for (int i = 0; i < nopat.length; i++) {
            kuvat [i] = noppaKuvana(nopat[i].getArvo());
        }

        //Noppien otsikot keskitettynä
        for (int i = 0; i < nopat.length; i++) {
            System.out.print (keskita("Noppa " + (i + 1), nopanLeveys) + vali);
        }

        System.out.println ();

        //Nopat vierekkäin
        for (int rivi = 0; rivi < 5; rivi++) {
            for (int noppa = 0; noppa < nopat.length; noppa++) {
                System.out.print (kuvat [noppa] [rivi] + vali);
            }

            System.out.println ();
        }

        //Lukitusrivi
        for (int i = 0; i < nopat.length; i++) {
            if (nopat[i].onLukittu()) {
                System.out.print (vihrea (keskita("LUKITTU", nopanLeveys)) + vali);
            }
            else {
                System.out.print (keskita("vapaa", nopanLeveys) + vali);
            }
        }

        System.out.println ("\n");

    }

    private String[] noppaKuvana (int arvo) {
        String yla = "┌───────┐";
        String ala = "└───────┘";

        if (arvo == 1) {
            return new String[] {
                    yla,
                    "│       │",
                    "│   ●   │",
                    "│       │",
                    ala
            };
        }
        else if (arvo == 2) {
            return new String[] {
                    yla,
                    "│ ●     │",
                    "│       │",
                    "│     ● │",
                    ala
            };
        }
        else if (arvo == 3) {
            return new String[] {
                    yla,
                    "│ ●     │",
                    "│   ●   │",
                    "│     ● │",
                    ala
            };
        }
        else if (arvo == 4) {
            return new String[] {
                    yla,
                    "│ ●   ● │",
                    "│       │",
                    "│ ●   ● │",
                    ala
            };
        }
        else if (arvo == 5) {
            return new String[] {
                    yla,
                    "│ ●   ● │",
                    "│   ●   │",
                    "│ ●   ● │",
                    ala
            };
        }
        else {
            return new String[] {
                    yla,
                    "│ ●   ● │",
                    "│ ●   ● │",
                    "│ ●   ● │",
                    ala
            };
        }

    }

    private String keskita (String teksti, int leveys) {
        int tyhjaa = leveys - teksti.length();
        int vasen = tyhjaa / 2;
        int oikea = tyhjaa - vasen;

        return " ".repeat(vasen) + teksti + " ".repeat(oikea);
    }

    /**
     * Tulostaa vuoron tilanteen.
     * @param heitot Määrä, joita heittoja on vielä jäljellä.
     */
    private void tulostaVuoronTilanne (int heitot) {

        System.out.println ("Heittoja jäljellä: " + heitot + " / 3\n");
        System.out.println ("---------------------------------\n");


    }


    /**
     * Tulostaa käyttäjän valinnat, jos heittoja on vielä jäljellä.
     */
    private void tulostaNormaalitValinnat() {
        System.out.println ("1 - Heitä lukitsemattomat nopat uudelleen");
        System.out.println ("2 - Lukitse tai vapauta noppia");
        System.out.println ("3 - Kirjaa pisteet pistekorttiin ja lopeta vuoro");
        System.out.println ("0 - Lopeta peli");
        System.out.println ("Anna valinta: ");
    }

    /**
     * Tulostaa käyttäjän valinnat, jos heittoja ei ole enää jäljellä.
     */
    private void tulostaViimeinenValinta() {
        System.out.println ("Kaikki heitot on käytetty.");
        System.out.println ("3 - Kirjaa pisteet pistekorttiin ja lopeta vuoro");
        System.out.println ("0 - Lopeta peli");
        System.out.println ("Anna valinta: ");
    }

    /**
     * Kysyy käyttäjältä valinnan
     * @return palauttaa kokonaisluvun käyttäjän valinnasta.
     */
    private int kysyValinta() {

        while (true) {
            String syote = lukija.nextLine();

            try {
                return Integer.parseInt(syote);
            }catch (NumberFormatException e) {
                System.out.println (punainen("\nVirheellinen syöte. Anna numero.\n"));
                System.out.println ("Anna valinta: ");
            }
        }
    }

    /**
     * Lukitsee tai vapauttaa käyttäjän antaman nopan.
     * Tulostaa nopat.
     */
    private void lukitseTaiVapautaNoppia() {

        System.out.println ("\nValitse noppien numerot (1-5), ei silmälukuja.\n");
        System.out.println ("Syöte: ");

        String syote = lukija.nextLine();
        String [] osat = syote.split(" ");

        for (String osa : osat) {

            try {
                int nopanNumero = Integer.parseInt(osa);

                if (nopanNumero >= 1 && nopanNumero <= 5) {
                    int indeksi = nopanNumero - 1;
                    noppaLogiikka.vaihdaLukitus(nopanNumero);
                }

                else {
                    System.out.println(punainen("\nVirheellinen nopan numero: " + nopanNumero + "\n"));
                }
            }

            catch (NumberFormatException e) {
                System.out.println (punainen("\nVirheellinen syöte: " + osa + ". Anna numero 1-5.\n"));
            }
        }

        System.out.println ();
        tulostaNopat();

    }

    /**
     * Lisää käyttäjän valitsemaan kategoriaan saadut pisteet.
     */
    private void kirjaaPisteetPistekorttiin () {

        System.out.println ("\nValitse kategoria:");

        for (int i = 0; i < 15; i++) {
            if (!pistekortti.onkoTaytetty(i)) {
                System.out.println (i + "-" + kategoriaNimi(i));
            }
        }

        int valinta;
        while (true) {
            valinta = kysyValinta();

            if (valinta >= 0 && valinta < 15 && !pistekortti.onkoTaytetty(valinta)) {
                break;
            }

            System.out.println (punainen("\nVirheellinen valinta, yritä uudelleen.\n"));
        }

        int pisteet = laskePisteet (valinta);

        pistekortti.kirjaaPisteet(valinta, pisteet);

        System.out.println (vihrea ("\nPisteet kirjattu: " + pisteet + "\n"));

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

    /**
     * Laskee halutulle kategorialle pisteet.
     * @param kategoria se kategoria, jolle pisteet lasketaan.
     * @return palauttaa pistemäärän halutulle kategorialle.
     */
    private int laskePisteet (int kategoria) {
        return pisteLaskuri.laskePisteet(noppaLogiikka.getArvot(), kategoria);

    }

    /**
     * Lopettaa pelin.
     */
    private void lopetaPeli() {
        System.out.println (punainen("\nPeli lopetettu.\n"));

    }

    /**
     * Muuttaa annetun tekstin vihreäksi.
     * @param teksti väritettävä teksti.
     * @return teksti värjättynä vihreäksi.
     */
    private String vihrea (String teksti) {
        return VIHREA + teksti + RESET;
    }

    /**
     * Muuttaa annaetun tekstin punaiseksi.
     * @param teksti väritettävä teksti.
     * @return teksti värjättynä punaiseksi.
     */
    private String punainen (String teksti) {
        return PUNAINEN + teksti + RESET;
    }

}
