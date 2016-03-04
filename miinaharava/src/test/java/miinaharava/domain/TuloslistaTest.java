package miinaharava.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.jfr.events.FileWriteEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TuloslistaTest {

    public TuloslistaTest() {
    }
    private static Tuloslista tuloslista;
    private static FileWriter kirjoittaja;

    @BeforeClass
    public static void setUpClass() {
        try {
            kirjoittaja = new FileWriter("files/Testi.txt");
            for (int i = 0; i < 15; i++) {
                kirjoittaja.write("Jaska Jokunen - 1:14" + System.getProperty("line.separator"));
            }
            kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(TuloslistaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        tuloslista = new Tuloslista(new File("files/Testi.txt"));
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
    public void uudenTuloksenLisaaminenPoistaaYlimaaraisen() {
        tuloslista.lisaaTulos(new Tulos("Jaska Jokunen", 30));
        assertEquals(15, tuloslista.getTuloslista().size());
    }

    @Test
    public void poistetaanOikea() {
        for (int i = 0; i < 15; i++) {
            tuloslista.lisaaTulos(new Tulos("Jaska Jokunen", 10 + i));
        }
        assertEquals(tuloslista.getTuloslista().get(14).getAika(), 24);
    }

    @Test
    public void tyhjaTuloslistaTulostetaanOikein() {
        try {
            kirjoittaja = new FileWriter("files/Testi.txt");
            kirjoittaja.write("");
            kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(TuloslistaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        tuloslista = new Tuloslista(new File("files/Testi.txt"));
        assertEquals(tuloslista.tulostaLista(), "");
    }

    @Test
    public void listaltaEiPoistetaLiianAjoissa() {
        try {
            kirjoittaja = new FileWriter("files/Testi.txt");
            for (int i = 0; i < 10; i++) {
                kirjoittaja.write("Jaska Jokunen - 1:14" + System.getProperty("line.separator"));
            }
            kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(TuloslistaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        tuloslista = new Tuloslista(new File("files/Testi.txt"));
        tuloslista.lisaaTulos(new Tulos("Jaska Jokunen", 30));
        assertEquals(tuloslista.getTuloslista().size(), 11);
    }

    @Test
    public void listaTulostetaanOikein() {
        try {
            kirjoittaja = new FileWriter("files/Testi.txt");
            kirjoittaja.write("");
            kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(TuloslistaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        tuloslista = new Tuloslista(new File("files/Testi.txt"));
        tuloslista.lisaaTulos(new Tulos("Jaska Jokunen", 30));
        assertEquals("Jaska Jokunen - 0:30\n", tuloslista.tulostaLista());
    }

    @Test
    public void sijoitusAnnetaanOikein() {
        try {
            kirjoittaja = new FileWriter("files/Testi.txt");
            for (int i = 0; i < 10; i++) {
                kirjoittaja.write("Jaska Jokunen - 1:14" + System.getProperty("line.separator"));
            }
            kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(TuloslistaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        tuloslista = new Tuloslista(new File("files/Testi.txt"));
        Tulos tulos = new Tulos("Jaska Jokunen", 30);
        tuloslista.lisaaTulos(tulos);
        assertEquals(tuloslista.sijoitus(tulos), 1);
    }

}
