package fi.utu.tko_7100.laurkl.yatzy;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Tallentaa ja tulostaa pelin parhaiden pisteiden tulokset.
 */
public class HighScore {

    /**
     * Tiedoston nimi, johon tallennetaan high score tulokset.
     */
    private static final String TIEDOSTO = "highscore.json";

    /**
     * Jackson-kirjaston olio JSON-tiedoston lukemiseen ja kirjoittamiseen.
     */
    private ObjectMapper mapper =  new ObjectMapper();

    /**
     * Tallentaa parametrina annetut tiedot tiedostoon.
     * @param nimi Pelaajan nimi.
     * @param pisteet Pelaajan pisteet.
     */
    public void tallennaTulos (String nimi, int pisteet) {

        ArrayList<Tulos> tulokset = lataaTulokset();
        tulokset.add(new Tulos (nimi, pisteet));

        try {
            mapper.writeValue(new File(TIEDOSTO), tulokset);

        } catch (Exception e) {
            System.out.println ("Tuloksen tallentaminen epäonnistui.");
        }
    }

    /**
     * Lataa tallennetut tulokset tiedostosta.
     * @return Lista tallennetuista tuloksista, tai tyhjä jos tiedostoa ei ole.
     */
    public ArrayList<Tulos> lataaTulokset() {
        File tiedosto = new File (TIEDOSTO);

        if (!tiedosto.exists()) {
            return new ArrayList<>();
        }

        try {
            Tulos [] lista = mapper.readValue(tiedosto, Tulos[].class);
            return new ArrayList<>(Arrays.asList(lista));
        }catch (Exception e) {
            System.out.println ("Luku epäonnistui.");
            return new ArrayList<>();
        }
    }


    /**
     * Tulostaa kolme suurinta pistemäärää pelatuista peleistä.
     */
    public void tulostaTop3() {

        ArrayList<Tulos> tulokset = lataaTulokset();

        tulokset.sort ((a, b) -> b.pisteet - a.pisteet);

        System.out.println ("============= TOP 3 =============");

        for (int i = 0; i < tulokset.size() && i < 3; i++) {
            Tulos t = tulokset.get(i);
            System.out.println ((i + 1) + ". " + t.nimi + " - " + t.pisteet + " pistettä");
        }

        System.out.println ("=================================");
    }

}
