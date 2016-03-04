package miinaharava.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tulostenlukija {

    private File tiedosto;
    private Scanner lukija;

    public Tulostenlukija(File tiedosto) {
        this.tiedosto = tiedosto;
        try {
            this.lukija = new Scanner(tiedosto);
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa " + tiedosto + " ei löydy");
        }
    }

    public List<Tulos> lueTulokset() {
        ArrayList<Tulos> tuloslista = new ArrayList<>();
        while (lukija.hasNextLine()) {
            String[] luettu = lukija.nextLine().split(" - ");
            String[] aika = luettu[1].split(":");
            long sekunnit = Integer.parseInt(aika[0]) * 60 + Integer.parseInt(aika[1]);
            tuloslista.add(new Tulos(luettu[0], sekunnit));
        }
        return tuloslista;
    }
}
