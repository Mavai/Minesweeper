package miinaharava.domain;

import miinaharava.logiikka.Miinaharava;
import miinaharava.logiikka.Vaikeusaste;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author markovai
 */
public class PelialustaTest {

    static Pelialusta alusta;

    public PelialustaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        alusta = new Pelialusta(5, 5, 10);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void oikeaMaaraMiinoja() {
        assertEquals(10, alusta.getMiinat().size());
    }

    @Test
    public void tavallisiaRuutujaOikeaMaara() {
        assertEquals(15, alusta.getTavallisetRuudut().size());
    }

    @Test
    public void tavallisiaRuutujaOikeaMaaraIsossaAlustassa() {
        Miinaharava peli = new Miinaharava(Vaikeusaste.VAIKEA);
        Pelialusta uusiAlusta = peli.getPelialusta();
        assertEquals(381, uusiAlusta.getTavallisetRuudut().size());
    }

    @Test
    public void leveysOikein() {
        assertEquals(5, alusta.getLeveys());
    }

    @Test(timeout = 1000)
    public void luodaanOikeaMaaraMiinoja() {
        alusta.luoMiinat();
    }
}
