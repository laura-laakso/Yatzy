import fi.utu.tko_7100.laurkl.yatzy.Pistekortti;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPistekortti {


    @Test
    public void testaaPisteidenKirjaus() {
        Pistekortti pistekortti = new Pistekortti();
        pistekortti.kirjaaPisteet(0,5);

        assertEquals(5, pistekortti.haePisteet(0));
    }

    @Test
    public void testaaKategoriaTaytetty() {
        Pistekortti pistekortti = new Pistekortti();
        pistekortti.kirjaaPisteet(3,12);

        assertTrue (pistekortti.onkoTaytetty(3));
    }

    @Test
    public void testaaKategoriaEiTaytetty() {
        Pistekortti pistekortti = new Pistekortti();

        assertFalse (pistekortti.onkoTaytetty(5));
    }

    @Test
    public void testaaYhteispisteet() {
        Pistekortti pistekortti = new Pistekortti();

        pistekortti.kirjaaPisteet(0,3);
        pistekortti.kirjaaPisteet(1, 6);
        pistekortti.kirjaaPisteet(2, 9);

        assertEquals(18, pistekortti.laskeYhteispisteet());
    }


}
