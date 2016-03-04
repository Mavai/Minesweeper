package miinaharava.domain;

public class Tulos {
    private long aika;
    private String pelaaja;

    public Tulos(long aika, String pelaaja) {
        this.aika = aika;
        this.pelaaja = pelaaja;
    }

    public long getAika() {
        return aika;
    }

    public String getPelaaja() {
        return pelaaja;
    }
    
    
    
}
