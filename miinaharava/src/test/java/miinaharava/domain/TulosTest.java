package miinaharava.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TulosTest {

    public TulosTest() {
    }

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
    public void pelaajaOikein() {
        Tulos tulos = new Tulos("Jaska Jokunen", 30);
        assertEquals("Jaska Jokunen", tulos.getPelaaja());
    }

    @Test
    public void aikaOikein() {
        Tulos tulos = new Tulos("Jaska Jokunen", 330);
        assertEquals(330, tulos.getAika());
    }

    @Test
    public void tulostusOikein() {
        Tulos tulos = new Tulos("Jaska Jokunen", 330);
        assertEquals("Jaska Jokunen - 5:30", tulos.toString());
    }

    @Test
    public void vertailuToimiiOikein() {
        Tulos tulos1 = new Tulos("Jaska Jokunen", 330);
        Tulos tulos2 = new Tulos("Jaska Jokunen", 30);
        assertEquals(tulos1.compareTo(tulos2), 1);
    }

    @Test
    public void vertailuToimiiOikein2() {
        Tulos tulos1 = new Tulos("Jaska Jokunen", 330);
        Tulos tulos2 = new Tulos("Jaska Jokunen", 30);
        assertEquals(tulos2.compareTo(tulos1), -1);
    }

    @Test
    public void vertailuToimiiOikein3() {
        Tulos tulos1 = new Tulos("Jaska Jokunen", 330);
        Tulos tulos2 = new Tulos("Jaska Jokunen", 330);
        assertEquals(tulos1.compareTo(tulos2), 0);
    }

}
