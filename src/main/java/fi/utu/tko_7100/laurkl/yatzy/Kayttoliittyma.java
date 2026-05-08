package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Tulostaa kahden pelaajan pistekortin rinnakkain.
 */
public class Kayttoliittyma {

    //Värit
    private static final String RESET = "\u001B[0m";
    private static final String VIHREA = "\u001B[32m";

    /**
     * Tulostaa kahden pelaajan pistekortin rinnakkain.
     * @param oma Käyttäjän pistekortti.
     * @param vastustaja Vastustajan pistekortti.
     */
    public void tulostaPistekortitRinnakkain (Pistekortti oma, Pistekortti vastustaja) {

        String[] kategoriat = {
                "Ykköset", "Kakkoset", "Kolmoset", "Neloset", "Viitoset", "Kutoset", "Pari", "Kaksi paria", "Kolmoset", "Neloset", "Täyskäsi", "Pieni suora", "Iso suora", "Sattuma", "Yatzy" };

        System.out.printf("%-15s %-10s %-10s%n", "KATEGORIA", "SINÄ", "VASTUSTAJA");

        for (int i = 0; i < kategoriat.length; i++) {

            String omaArvo = "-";
            if (oma.onkoTaytetty(i)) {
                omaArvo = String.valueOf(oma.haePisteet(i));
            }

            String vastustajaArvo = "-";
            if (vastustaja.onkoTaytetty(i)) {
                vastustajaArvo = String.valueOf((vastustaja.haePisteet(i)));
            }

            System.out.printf ("%-15s %-10s %-10s%n", kategoriat[i], omaArvo, vastustajaArvo);

        }

        System.out.printf("%-15s %-10d %-10d%n", "Yhteensä", oma.laskeYhteispisteet(), vastustaja.laskeYhteispisteet());
    }

    /**
     * Tulostaa nopat kuvina rinnakkain ja kertoo onko ne lukittu vai ei.
     * @param nopat Tulostettavat nopat.
     */
    public void tulostaNopat(Noppa [] nopat) {

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

    /**
     * Palauttaa halutun nopan silmäluvun ascii kuvana.
     * @param arvo Haluttu nopan silmäluku.
     * @return Ascii kuva nopasta.
     */
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

    /**
     * Keskittää tekstin annetun leveyden mukaan.
     * @param teksti Keskitettävä teksti.
     * @param leveys Haluttu leveys.
     * @return Keskitetty teksti.
     */
    private String keskita (String teksti, int leveys) {
        int tyhjaa = leveys - teksti.length();
        int vasen = tyhjaa / 2;
        int oikea = tyhjaa - vasen;

        return " ".repeat(vasen) + teksti + " ".repeat(oikea);
    }

    /**
     * Muuttaa annetun tekstin vihreäksi.
     * @param teksti väritettävä teksti.
     * @return teksti värjättynä vihreäksi.
     */
    private String vihrea (String teksti) {
        return VIHREA + teksti + RESET;
    }
}
