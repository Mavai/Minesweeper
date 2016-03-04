package miinaharava.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TulostenLukijaTest {

    private static Tulostenlukija tulostenlukija;
    private static FileWriter kirjoittaja;
    private static Tuloslista tuloslista;

    public TulostenLukijaTest() {
        try {
            kirjoittaja = new FileWriter("files/Testi.txt");
            kirjoittaja.write("Jaska Jokunen - 1:10\n");
            kirjoittaja.close();
        } catch (IOException ex) {
            Logger.getLogger(TuloslistaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        tuloslista = new Tuloslista(new File("files/Testi.txt"));
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
    public void tulostenLukuOikein() {
        tulostenlukija = new Tulostenlukija(new File("files/Testi.txt"));
        assertEquals(tulostenlukija.lueTulokset().get(0).getAika(), 70);
    }

}
