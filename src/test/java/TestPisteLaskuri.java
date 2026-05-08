import fi.utu.tko_7100.laurkl.yatzy.PisteLaskuri;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPisteLaskuri {

    @Test
    public void testaaYkkoset () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {1, 1, 3, 4, 6};

        assertEquals(2, laskuri.laskePisteet(nopat,0));
    }

    @Test
    public  void testaaPari () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {5, 4, 2, 5, 1};

        assertEquals(10, laskuri.laskePisteet(nopat, 6));
    }

    @Test
    public  void testaaKaksiParia () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {2, 4, 2, 5, 4};

        assertEquals(12, laskuri.laskePisteet(nopat, 7));
    }

    @Test
    public  void testaaKolmoset () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {1, 1, 2, 5, 1};

        assertEquals(3, laskuri.laskePisteet(nopat, 8));
    }

    @Test
    public  void testaaTayskasi () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {5, 5, 1, 5, 1};

        assertEquals(17, laskuri.laskePisteet(nopat, 10));
    }

    @Test
    public  void testaaPieniSuora () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {4, 3, 2, 5, 1};

        assertEquals(15, laskuri.laskePisteet(nopat, 11));
    }
    @Test
    public  void testaaIsoSuora () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {4, 3, 6, 5, 2};

        assertEquals(20, laskuri.laskePisteet(nopat, 12));
    }

    @Test
    public  void testaaSattuma () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {1, 3, 4, 5, 6};

        assertEquals(19, laskuri.laskePisteet(nopat, 13));
    }

    @Test
    public  void testaaYatzy () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {5, 5, 5, 5, 5};

        assertEquals(50, laskuri.laskePisteet(nopat, 14));
    }

    @Test
    public  void testaaKunKategoriaEIOnnistu () {
        PisteLaskuri laskuri = new PisteLaskuri();
        int[] nopat = {1, 2, 3, 4, 5};

        assertEquals(0, laskuri.laskePisteet(nopat, 14));
    }
}
