package fi.utu.tko_7100.laurkl.yatzy;

import java.util.Scanner;

public class Yatzy {

    //Atribuutit

    private Scanner lukija = new Scanner (System.in);
    private Pelaaja ihmisPelaaja;
    private Pelaaja tekoalyPelaaja;
    private Kayttoliittyma kortti;
    private boolean peliKaynnissa;
    private HighScore highScore;

    //Värit
    private static final String RESET = "\u001B[0m";
    private static final String VIHREA = "\u001B[32m";
    private static final String PUNAINEN = "\u001B[31m";
    private static final String KELTAINEN = "\u001B[33m";

    //Konstruktori

    public Yatzy() {
        this.ihmisPelaaja = new IhmisPelaaja();
        this.tekoalyPelaaja = new TekoalyPelaaja();
        this.kortti = new Kayttoliittyma();
        this.peliKaynnissa = true;
        this.highScore = new HighScore();
    }

    //Metodit

    public void Pelaa() {

        TervetuloaViesti();
        highScore.tulostaTop3();

        odotaEnter("\nPaina Enter aloittaaksesi pelin...");
        tyhjennaRuutu();

        System.out.println (keltainen("\n=========== PELI ALKAA ==========\n"));

        for (int kierros = 1; kierros <= 15 && peliKaynnissa; kierros ++) {

            System.out.println(keltainen("\n========== Kierros " + kierros + " ==========="));

            peliKaynnissa = ihmisPelaaja.pelaaVuoro();

            if (!peliKaynnissa) {
                break;
            }

            kortti.tulostaPistekortitRinnakkain(ihmisPelaaja.getPistekortti(), tekoalyPelaaja.getPistekortti());

            odotaEnter("\nPaina Enter lopettaaksesi vuoron...");
            tyhjennaRuutu();

            tekoalyPelaaja.pelaaVuoro();

            kortti.tulostaPistekortitRinnakkain(ihmisPelaaja.getPistekortti(), tekoalyPelaaja.getPistekortti());

            odotaEnter("\nPaina enter jatkaaksesi seuraavaan kierrokseen...");
            tyhjennaRuutu();
        }


        naytaLopputulos();
    }

    private void tyhjennaRuutu() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private void odotaEnter (String viesti) {
        System.out.println (keltainen(viesti));
        lukija.nextLine();
    }


    private void TervetuloaViesti() {
        System.out.println (keltainen("=================================\n"
                        +   "============= YATZY =============\n"
                        +   "=================================\n"));
    }

    private void naytaLopputulos() {
        System.out.println (keltainen("\n========== PELI PÄÄTTYI =========\n"));

        int ihmisenPisteet = ihmisPelaaja.annaPisteet();
        int tekoalynPisteet = tekoalyPelaaja.annaPisteet();

        highScore.tallennaTulos("Ihminen", ihmisenPisteet);
        highScore.tallennaTulos("Tekoäly", tekoalynPisteet);

        System.out.println ("Sinun pisteet: " + ihmisenPisteet);
        System.out.println ("Vastustajan pisteet: " + tekoalynPisteet);
        System.out.println ();

        if (ihmisenPisteet > tekoalynPisteet) {
            System.out.println ("Voitit Pelin!");
        }
        else if (tekoalynPisteet > ihmisenPisteet) {
            System.out.println ("Vastustaja voitti!");
        }
        else {
            System.out.println ("Tasapeli!");
        }

        System.out.println (keltainen("\n=================================\n"));
    }

    private String vihrea (String teksti) {
        return VIHREA + teksti + RESET;
    }

    private String punainen (String teksti) {
        return PUNAINEN + teksti + RESET;
    }

    private String keltainen (String teksti) {
        return KELTAINEN + teksti + RESET;
    }
}
