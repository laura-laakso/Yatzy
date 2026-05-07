package fi.utu.tko_7100.laurkl.yatzy;

public class Pistekortti {

    //Atribuutit

    private int[] pisteet;
    private boolean[] taytetty;

    //Konstruktori
    public Pistekortti() {
        this.pisteet = new int[15];
        this.taytetty = new boolean [15];
    }


    //Metodit

    public void kirjaaPisteet (int kategoria, int pisteet) {

        if (taytetty[kategoria]) {
            System.out.println("Kohta on jo täytetty!");
        }

        this.pisteet[kategoria] = pisteet;
        this.taytetty[kategoria] = true;
    }

    public int haePisteet (int kategoria) {
        return pisteet [kategoria];
    }

    public boolean onkoTaytetty (int kategoria) {
        return taytetty [kategoria];
    }

    public boolean onkoTaynna() {
        for (boolean kategoria : taytetty) {
            if (!kategoria) {
                return false;
            }
        }

        return true;
    }

    public int laskeYhteispisteet() {
        int summa = 0;
        for (int piste : pisteet) {
            summa += piste;
        }

        return summa;
    }


}
