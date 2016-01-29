package miinaharava.logiikka;

import java.util.Scanner;
import miinaharava.kayttoliittyma.Pelialusta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MiinaharavaTest {

    public MiinaharavaTest() {
    }
    Pelialusta alusta = new Pelialusta(3, 3, 0);

    @BeforeClass
    public static void setUpClass() {

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
    public void ruudunAvaaminenToimii() {
        String syote = muodostaSyote("1", "1", "999");
        Miinaharava peli = new Miinaharava(alusta,new Scanner(syote));
        peli.aloita();
        assertFalse(alusta.getAlusta()[1][1].onKiinni());
    }

    private String muodostaSyote(String... lines) {
        String syote = "";
        for (String line : lines) {
            syote += line + "\n";
        }
        return syote;
    }
}
