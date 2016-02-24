package miinaharava.logiikka;

import java.util.Scanner;
import miinaharava.domain.Pelialusta;
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
    String syote = muodostaSyote("1", "1");
    Miinaharava peli = new Miinaharava(alusta);

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
        peli.avaaRuutu(alusta.getAlusta()[1][1]);
        assertFalse(alusta.getAlusta()[1][1].onKiinni());
    }


    @Test
    public void miinanSisältävänRuudunAvaaminen() {
        alusta = new Pelialusta(3, 3, 9);
        peli = new Miinaharava(alusta);
        assertFalse(peli.avaaRuutu(alusta.getAlusta()[0][0]));
    }

    @Test
    public void avaaNollanViereisetOikein() {
        peli.avaaRuutu(alusta.getAlusta()[0][0]);
        assertTrue(!alusta.getAlusta()[1][2].onKiinni());
    }

    @Test
    public void aukiOlevanRuudunAvaaminen() {
        peli.avaaRuutu(alusta.getAlusta()[0][0]);
        assertTrue(peli.avaaRuutu(alusta.getAlusta()[0][0]));
    }

    @Test
    public void kierroksetKasvaaOikein() {
        peli.kasvataKierroksia();
        assertEquals(2, peli.getKierroksia());
    }

    @Test
    public void getPelialustTesti() {
        assertEquals(alusta, peli.getPelialusta());
    }

    @Test
    public void nollanViereisetKasvaa() {
        alusta = new Pelialusta(5, 5, 0);
        peli = new Miinaharava(alusta);
        peli.avaaRuutu(alusta.getAlusta()[2][2]);
        assertTrue(peli.getNollanViereiset().size() > 0);
    }

    private String muodostaSyote(String... rivit) {
        String syote = "";
        for (String rivi : rivit) {
            syote += rivi + "\n";
        }
        return syote;
    }
}
