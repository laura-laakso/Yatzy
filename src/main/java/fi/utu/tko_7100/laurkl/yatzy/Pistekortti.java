package fi.utu.tko_7100.laurkl.yatzy;

/**
 * Kirjaa pelaajan pisteet pistekortin taulukkoon.
 * Tarkistaa onko kohta jo täytetty ja laskee pistekortin yhteispisteet.
 */
public class Pistekortti {

    //Atribuutit

    /**
     * Taulukko, jossa pisteet säilytetään.
     */
    private int[] pisteet;

    /**
     * Taulukko, joka kertoo onko kohta jo täytetty.
     */
    private boolean[] taytetty;

    //Konstruktori

    /**
     * Luo taulukot pisteille ja täytetyille kategorioille.
     */
    public Pistekortti() {
        this.pisteet = new int[15];
        this.taytetty = new boolean [15];
    }


    //Metodit

    /**
     * Kirjaa pisteet haluttuun kategoriaan.
     * @param kategoria Kohta, johon pisteet halutaan kirjata.
     * @param pisteet Pelaajan saamat pisteet.
     */
    public void kirjaaPisteet (int kategoria, int pisteet) {

        if (taytetty[kategoria]) {
            System.out.println("Kohta on jo täytetty!");
        }

        this.pisteet[kategoria] = pisteet;
        this.taytetty[kategoria] = true;
    }

    /**
     * Palauttaa tietyn kategorian kohdalla olevat pisteet.
     * @param kategoria Kohta josta pisteet halutaan.
     * @return Pisteet kategorian kohdalta.
     */
    public int haePisteet (int kategoria) {
        return pisteet [kategoria];
    }

    /**
     * Palauttaa tiedon onko kategoria täytetty.
     * @param kategoria Kohta, josta tieto halutaan.
     * @return True jos täytetty, false jos vapaa.
     */
    public boolean onkoTaytetty (int kategoria) {
        return taytetty [kategoria];
    }

    /**
     * Laskee pelaajan pistekortin yhteispisteet.
     * @return Palauttaa pelaajan yhteispisteet.
     */
    public int laskeYhteispisteet() {
        int summa = 0;
        for (int piste : pisteet) {
            summa += piste;
        }

        return summa;
    }

}
