package fi.utu.tko_7100.laurkl.yatzy;

import java.util.Random;

public class Noppa {

    //Atribuutit
    private int arvo;
    private boolean lukittu;
    private static final Random random = new Random();

    //Konstruktori
    public Noppa() {
        heita();
        lukittu = false;
    }

    //Metodit
    public void heita() {
        if (!lukittu) {
            arvo = random.nextInt(6) + 1;
        }
    }

    public void vapauta() {
        lukittu = false;
    }

    public void vaihdaLukitus() {
        lukittu = !lukittu;
    }

    public boolean onLukittu() {
        return lukittu;
    }

    public int getArvo() {
        return arvo;
    }
}
