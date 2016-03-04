/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miinaharava.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import javax.swing.*;
import miinaharava.kayttoliittyma.kuuntelijat.CustomPelinAloittaja;
import miinaharava.kayttoliittyma.kuuntelijat.CustomSliderinKuuntelija;

/**
 *
 * @author Marko Vainio
 */
public class CustomPelinLuontiIkkuna extends JFrame{
    private JSlider korkeus;
    private JSlider leveys;
    private JSlider miinat;

    public CustomPelinLuontiIkkuna() {
        super("Miinaharava");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.korkeus = new JSlider();
        this.leveys = new JSlider();
        this.miinat = new JSlider();

        luoKomponentit(getContentPane());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(luoKorkeudenValitsin());
        container.add(luoLeveydenValitsin());
        container.add(luoMiinojenValitsin());
        container.add(luoALoitusNappi());
    }
    
    private JPanel luoKorkeudenValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        korkeus = new JSlider(JSlider.HORIZONTAL, 5, 30, 8);
        JLabel teksti = new JLabel("Korkeus: " + korkeus.getValue());
        teksti.setFont(new Font("Normal", Font.BOLD, 16));
        korkeus.setMajorTickSpacing(5);
        korkeus.setPaintTicks(true);
        korkeus.addChangeListener(new CustomSliderinKuuntelija(teksti, this));
        paneeli.add(korkeus);
        paneeli.add(teksti);
        return paneeli;
    }
    
    private JPanel luoLeveydenValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        leveys = new JSlider(JSlider.HORIZONTAL, 5, 50, 8);
        JLabel teksti = new JLabel("Leveys: " + leveys.getValue());
        teksti.setFont(new Font("Normal", Font.BOLD, 16));
        leveys.setMajorTickSpacing(5);
        leveys.setPaintTicks(true);
        leveys.addChangeListener(new CustomSliderinKuuntelija(teksti, this));
        paneeli.add(leveys);
        paneeli.add(teksti);
        return paneeli;
    }
    
    private JPanel luoMiinojenValitsin() {
        JPanel paneeli = new JPanel();
        paneeli.setLayout(new BoxLayout(paneeli, BoxLayout.Y_AXIS));
        miinat = new JSlider(JSlider.HORIZONTAL, 0, korkeus.getValue() * leveys.getValue(), 8);
        JLabel teksti = new JLabel("Miinat: " + miinat.getValue());
        teksti.setFont(new Font("Normal", Font.BOLD, 16));
        miinat.setMajorTickSpacing(miinat.getMaximum() / 20);
        miinat.setPaintTicks(true);
        miinat.addChangeListener(new CustomSliderinKuuntelija(teksti, this));
        paneeli.add(miinat);
        paneeli.add(teksti);
        return paneeli;
    }
    
    public void paivitaMiinat() {
        miinat.setMaximum(korkeus.getValue() * leveys.getValue());
        miinat.setMajorTickSpacing(miinat.getMaximum() / 20);
    }
    
    private JPanel luoALoitusNappi() {
        JPanel paneeli = new JPanel();
        JButton aloita = new JButton("Aloita");
        aloita.addActionListener(new CustomPelinAloittaja(leveys, korkeus, miinat, this));
        paneeli.add(aloita);
        return paneeli;
    }
    
    
    
}
