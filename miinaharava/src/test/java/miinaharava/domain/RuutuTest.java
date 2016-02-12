package miinaharava.domain;

import miinaharava.domain.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RuutuTest {
    Pelialusta alusta = new Pelialusta(3, 0);
    
    public RuutuTest() {
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
    
    @ Test
    public void viereisetRuudutKulmassaOikein1() {
        assertEquals(3, alusta.getAlusta()[0][0].viereisetRuudut(alusta.getAlusta(), 0, 0).size());
    }
    
    @Test
    public void viereisetRuudutKulmassaOikein2() {
        assertEquals(3, alusta.getAlusta()[0][2].viereisetRuudut(alusta.getAlusta(), 0, 2).size());
    }
    
    @ Test
    public void viereisetRuudutKulmassaOikein3() {
        assertEquals(3, alusta.getAlusta()[2][0].viereisetRuudut(alusta.getAlusta(), 2, 0).size());
    }
    
    @Test
    public void viereisetRuudutKulmassaOikein4() {
        assertEquals(3, alusta.getAlusta()[2][2].viereisetRuudut(alusta.getAlusta(), 2, 2).size());
    }
    
    @Test
    public void viereisetRuudutReunassaOikein1() {
        assertEquals(5, alusta.getAlusta()[0][1].viereisetRuudut(alusta.getAlusta(), 0, 1).size());
    }
    
    @Test
    public void viereisetRuudutReunassaOikein2() {
        assertEquals(5, alusta.getAlusta()[2][1].viereisetRuudut(alusta.getAlusta(), 2, 1).size());
    }
    
    @Test
    public void viereisetRuudutReunassaOikein3() {
        assertEquals(5, alusta.getAlusta()[1][0].viereisetRuudut(alusta.getAlusta(), 1, 0).size());
    }
    
    @Test
    public void viereisetRuudutReunassaOikein4() {
        assertEquals(5, alusta.getAlusta()[1][2].viereisetRuudut(alusta.getAlusta(), 1, 2).size());
    }
    
    @Test
    public void viereisetRuudutKeskellaOikein() {
        assertEquals(8, alusta.getAlusta()[1][1].viereisetRuudut(alusta.getAlusta(), 1, 1).size());
    }
    
    @Test
    public void kiinniOlevanRuudunTulostus() {
        Ruutu ruutu = alusta.getAlusta()[1][1];
        assertEquals("@", ruutu.toString());
    }
    
    @Test
    public void aukiOlevanRuudunTulostus() {
        Ruutu ruutu = alusta.getAlusta()[1][1];
        ruutu.avaa();
        assertEquals(0 + "", ruutu.toString());
    }
    
    @Test
    public void miinanSijoittaminenToimii() {
        assertFalse(alusta.getAlusta()[0][0].sisaltaaMiinan());
    }
    
    @Test
    public void miinanSijoittaminenToimii2() {
        alusta = new Pelialusta(3, 9);
        assertTrue(alusta.getAlusta()[0][0].sisaltaaMiinan());
    }
    
    @Test
    public void miinanSisaltavanRuudunTulostusToimii() {
        alusta = new Pelialusta(3, 9);
        alusta.getAlusta()[0][0].avaa();
        assertEquals("*", alusta.getAlusta()[0][0].toString());
    }
    
    @Test
    public void viereisetMiinatLasketaanOikein() {
        alusta = new Pelialusta(3, 9);
        assertEquals(8, alusta.getAlusta()[1][1].getViereisetMiinat());
    }
    
    @Test
    public void equalsToimii() {
        assertEquals(new Ruutu(1, 1), new Ruutu(1, 1));
    }
}
