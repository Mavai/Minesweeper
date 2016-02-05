package miinaharava.domain;

import miinaharava.kayttoliittyma.Pelialusta;
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
        alusta = new Pelialusta(5, 10);
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
        assertEquals(10,alusta.getMiinat().size());
    }

}
