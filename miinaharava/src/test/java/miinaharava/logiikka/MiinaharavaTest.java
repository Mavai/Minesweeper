package miinaharava.logiikka;

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
    
    @Test
    public void kentanKokoOikeinHelpollaVaikeusasteella() {
        peli = new Miinaharava(Vaikeusaste.HELPPO);
        assertEquals(8, peli.getPelialusta().getLeveys());
    }
    
    @Test
    public void kentanKokoOikeinHaastavallaVaikeusasteella() {
        peli = new Miinaharava(Vaikeusaste.HAASTAVA);
        assertEquals(16, peli.getPelialusta().getLeveys());
    }
    
    @Test
    public void kentanKokoOikeinVaikeallaVaikeusasteella() {
        peli = new Miinaharava(Vaikeusaste.VAIKEA);
        assertTrue(peli.getPelialusta().getLeveys() == 30 && peli.getPelialusta().getKorkeus() == 16);
    }
    
    @Test
    public void jaljellaOlevienMiinojenMaaraVaheneeOikein() {
        peli = new Miinaharava(Vaikeusaste.HELPPO);
        peli.merkkaaRuutu(peli.getPelialusta().getAlusta()[0][0]);
        assertTrue(peli.getMiinojaJaljella() == 9 && peli.getPelialusta().getAlusta()[0][0].onMerkattu());
    }
    
    @Test
    public void jaljellaOlevienMiinojenMaaraKasvaaOikein() {
        peli = new Miinaharava(Vaikeusaste.HELPPO);
        peli.merkkaaRuutu(peli.getPelialusta().getAlusta()[0][0]);
        peli.postaRuudunMerkinta(peli.getPelialusta().getAlusta()[0][0]);
        assertTrue(peli.getMiinojaJaljella() == 10 && (!peli.getPelialusta().getAlusta()[0][0].onMerkattu()));
    }
    
    @Test
    public void kaikkiAvattuToimiiOikeinKunKaikkiOnAvattu() {
        peli = new Miinaharava(new Pelialusta(5, 5, 0));
        peli.avaaRuutu(peli.getPelialusta().getAlusta()[2][2]);
        assertTrue(peli.kaikkiAvattu());
    }
    
    @Test
    public void kaikkiAvattuToimiiOikeinKunKaikkiEiAvattu() {
        peli = new Miinaharava(new Pelialusta(5, 5, 0));
        assertTrue(!peli.kaikkiAvattu());
    }

    private String muodostaSyote(String... rivit) {
        String syote = "";
        for (String rivi : rivit) {
            syote += rivi + "\n";
        }
        return syote;
    }
}
