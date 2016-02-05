package miinaharava.kayttoliittyma;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import miinaharava.domain.Miina;
import miinaharava.domain.Ruutu;

public class Pelialusta {
    private int leveys;
    private int miinojenLkm;
    private Ruutu[][] alusta;
    private List<Miina> miinat;

    public Pelialusta(int leveys, int miinojenLkm) {
        this.leveys = leveys;
        this.miinojenLkm = miinojenLkm;
        this.miinat = new ArrayList<>();
        luo();
    }

    public Ruutu[][] getAlusta() {
        return alusta;
    }

    public List<Miina> getMiinat() {
        return miinat;
    }
    
    public void luo() {
        this.alusta = new Ruutu[leveys][leveys];
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                alusta[j][i] = new Ruutu(j, i);
            }
        }
        luoMiinat(miinojenLkm);
        sijoitaMiinat();
        laskeViereisetMiinat();
    }
    
    public void tulosta() {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                System.out.print(alusta[j][i] + " ");
            }
            System.out.println("");
        }
    }
    
    public void luoMiinat(int maara) {
        Random r = new Random();
        for (int i = 0; i < maara; i++) {
            Miina uusiMiina = new Miina(r.nextInt(alusta.length), r.nextInt(alusta.length));
            if (miinat.contains(uusiMiina)) {
                i--;
            } else {
                miinat.add(uusiMiina);
            }
        }
    }
    
    public void sijoitaMiinat() {
        for (Miina miina : miinat) {
            alusta[miina.getX()][miina.getY()].asetaMiina();
        }
    }
    
    public void laskeViereisetMiinat() {
        for (int i = 0; i < alusta.length; i++) {
            for (int j = 0; j < alusta.length; j++) {
                alusta[j][i].laskeViereisetMiinat(alusta);
            }
        }
    }
}
