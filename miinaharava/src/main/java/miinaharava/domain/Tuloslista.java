package miinaharava.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import miinaharava.logiikka.Vaikeusaste;

public class Tuloslista {

    private List<Tulos> tuloslista;
    private Tulostenlukija lukija;
    private Vaikeusaste vaikeusaste;

    public Tuloslista(Vaikeusaste vaikeusaste) {
        this.vaikeusaste = vaikeusaste;
        if (vaikeusaste == Vaikeusaste.HELPPO) {
            lukija = new Tulostenlukija(new File("files/Helppo.txt"));
            tuloslista = lukija.lueTulokset();
        }
        if (vaikeusaste == Vaikeusaste.HAASTAVA) {
            lukija = new Tulostenlukija(new File("files/Haastava.txt"));
            tuloslista = lukija.lueTulokset();
        }
        if (vaikeusaste == Vaikeusaste.VAIKEA) {
            lukija = new Tulostenlukija(new File("files/Vaikea.txt"));
            tuloslista = lukija.lueTulokset();
        }
    }

    public void lisaaTulos(Tulos tulos) {
        tuloslista.add(tulos);
        Collections.sort(tuloslista);
        if (tuloslista.size() > 15) {
            tuloslista.remove(15);
        }
    }

    public String tulostaLista() {
        String tulokset = "";
        if (!tuloslista.isEmpty()) {
            for (Tulos tulos : tuloslista) {
                tulokset += tulos.toString() + "\n";
            }
        }
        return tulokset;
    }

    public boolean tallenna() {
        FileWriter kirjoittaja = null;
        try {
            if (vaikeusaste == Vaikeusaste.HELPPO) {
                kirjoittaja = new FileWriter(new File("files/Helppo.txt"));
            } else if (vaikeusaste == Vaikeusaste.HAASTAVA) {
                kirjoittaja = new FileWriter(new File("files/Haastava.txt"));
            } else if (vaikeusaste == Vaikeusaste.VAIKEA) {
                kirjoittaja = new FileWriter(new File("files/Vaikea.txt"));
            }
            for (Tulos tulos : tuloslista) {
                kirjoittaja.write(tulos.toString());
                kirjoittaja.write(System.getProperty("line.separator"));
            }
            kirjoittaja.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
