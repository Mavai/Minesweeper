package miinaharava.kayttoliittyma;

import java.util.Random;
import miinaharava.domain.Miina;
import miinaharava.domain.Ruutu;

public class Pelialusta {
    private int leveys;
    private int korkeus;
    private int miinojenLkm;
    private Ruutu[][] alusta;

    public Pelialusta(int leveys, int korkeus, int miinojenLkm) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.miinojenLkm = miinojenLkm;
        luo();
    }

    public Ruutu[][] getAlusta() {
        return alusta;
    }
    
    public void luo() {
        this.alusta = new Ruutu[leveys][korkeus];
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                alusta[i][j] = new Ruutu(i, j);
            }
        }
        sijoitaMiinat();
        laskeViereisetMiinat();
    }
    
    public void tulosta() {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                System.out.print(alusta[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public void sijoitaMiinat() {
        Random r = new Random();
        for (int i = 0; i < miinojenLkm; i++) {
            Miina miina = new Miina(r.nextInt(alusta.length), r.nextInt(alusta.length));
            alusta[miina.getX()][miina.getY()].asetaMiina();
        }
    }
    
    public void laskeViereisetMiinat() {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                alusta[i][j].laskeViereisetMiinat(alusta);
            }
        }
    }
}
