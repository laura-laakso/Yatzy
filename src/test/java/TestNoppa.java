import fi.utu.tko_7100.laurkl.yatzy.Noppa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestNoppa {

    @Test
    public void testaaLukitus() {
        Noppa noppa = new Noppa();

        noppa.vaihdaLukitus();

        assertTrue(noppa.onLukittu());
    }

    @Test
    public void testaaVapautus() {
        Noppa noppa = new Noppa();

        noppa.vaihdaLukitus();
        noppa.vapauta();

        assertFalse(noppa.onLukittu());
    }

    @Test
    public void testaaNopanArvoValilla1ja6() {
        Noppa noppa = new Noppa();

        int silmaluku = noppa.getArvo();

        assertTrue(silmaluku >= 1 && silmaluku <= 6);
    }


}
