package fi.utu.tko_7100.laurkl.yatzy;

public class Kayttoliittyma {

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
}
