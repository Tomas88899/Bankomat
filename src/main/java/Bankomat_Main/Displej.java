package Bankomat_Main;

import Banka.Banka;
import Banka.Bankomat;
import Utility.SpravaNaDispleji;
import Utility.Stav;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 */
public class Displej extends JFrame {

    private static final int SIRKA = 500;
    private static final int VYSKA = 200;
    private Bankomat bankomat;
    private JTextArea obrazovka;
    private JPanel panelKlavesnica;
    private JPanel panelTlacitok;
    private JTextField textovePole;
    private String klienstkeCislo;

    /**
     * vytvara novy objekt GUI rozhrania bankomat
     */
    public Displej()
    {
        this.bankomat = new Bankomat(new Banka());
        Stav.setGui(true);

        pridajNumerickuKlavesnicu();
        pridajObrazovku();
        pridajobsluzneTlacitka();


        setLayout(new FlowLayout());
        add(panelKlavesnica);
        add(obrazovka);

        add(panelTlacitok);


        setTitle("Bankomat_Main");
        setSize(SIRKA, VYSKA);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


    }

    /**
     * vytvara textove pole a numericke tlacitka s listenerom
     */
    private void pridajNumerickuKlavesnicu()
    {
        // vytvorenie ciselnej klavesnice
        panelKlavesnica = new JPanel();
        panelKlavesnica.setLayout(new BorderLayout());
        textovePole = new JTextField(12);
        panelKlavesnica.add(textovePole, BorderLayout.NORTH);
        JPanel numerickaKlavesnica = new JPanel(new GridLayout(4, 3));
        panelKlavesnica.add(numerickaKlavesnica);

        // vytvorenie cislic od 0 - 9
        for (int i = 1; i <= 10; i++) {
            if (i < 10) {
                JButton tlacitkonum = new JButton(i + "");
                tlacitkonum.addActionListener((f) -> textovePole.setText(textovePole.getText() + tlacitkonum.getText()));
                numerickaKlavesnica.add(tlacitkonum);
            } else {
                JButton tlacitkonum = new JButton("0");
                tlacitkonum.addActionListener((f) -> textovePole.setText(textovePole.getText() + tlacitkonum.getText()));
                numerickaKlavesnica.add(tlacitkonum);
            }

        }
        // vytvorenie tlacitka vymaz
        JButton tlacitkoCE = new JButton("CE");
        tlacitkoCE.addActionListener((e) -> textovePole.setText(""));

        numerickaKlavesnica.add(tlacitkoCE);

        // vytvorenie tlacitka bodka
        JButton tlacidkoBodka = new JButton(".");

        tlacidkoBodka.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textovePole.getText().contains(".") && !textovePole.getText().isEmpty())
                    textovePole.setText(textovePole.getText() + tlacidkoBodka.getText());
            }
        });
        numerickaKlavesnica.add(tlacidkoBodka);

    }
    /**
     * vytvara textovu plochu (obrazovku)
     */
    private void pridajObrazovku()
    {

        obrazovka = new JTextArea(8, 20);
        obrazovka.setEditable(false);
        obrazovka.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());
    }

    /**
     * vytvara obsluzne tlacitka a nastavuje listenery na zaklade stavu v ktorom je aktualne
     * bankomat
     */
    private void pridajobsluzneTlacitka() {


    JButton tlacitkoA = new JButton("A");

    tlacitkoA.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Stav.getAktualnyStav().equalsIgnoreCase("start")) {
                bankomat.nastavKlientskeCislo(textovePole.getText());
                klienstkeCislo = textovePole.getText();
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());


            } else if (Stav.getAktualnyStav().equalsIgnoreCase("pin")) {
                bankomat.nastavKlienta(klienstkeCislo, textovePole.getText());
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());

            } else if (Stav.getAktualnyStav().equalsIgnoreCase("ucet")) {
                bankomat.nastavTypUctu("bezny");
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());

            } else if (Stav.getAktualnyStav().equalsIgnoreCase("TRANSAKCIA")) {
                double ciastka = Double.parseDouble(textovePole.getText());

                if (!bankomat.vyber(ciastka)) {
                    System.out.println("Nedostatok prostriedkov na ucte");
                    bankomat.nastavPredchadzajuciStav();
                } else {
                    bankomat.nastavPredchadzajuciStav();
                }
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());
            }

        }
    });

    JButton tlacitkoB = new JButton("B");

    tlacitkoB.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (Stav.getAktualnyStav().equalsIgnoreCase("ucet")) {
                bankomat.nastavTypUctu("sporiaci");
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());

            } else if (Stav.getAktualnyStav().equalsIgnoreCase("TRANSAKCIA")) {
                bankomat.vloz(Double.parseDouble(textovePole.getText()));
                bankomat.nastavPredchadzajuciStav();
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());
            }

        }
    });

    JButton tlacitkoC = new JButton("C");

    tlacitkoC.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (Stav.getAktualnyStav().equalsIgnoreCase("ucet")) {
                bankomat.reset();
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());
            } else if (Stav.getAktualnyStav().equalsIgnoreCase("ucet")) {
                bankomat.nastavPredchadzajuciStav();
                textovePole.setText("");
                obrazovka.setText(SpravaNaDispleji.zozbrazSpravu());
            }
        }
    });

    panelTlacitok = new JPanel();

    panelTlacitok.setLayout(new GridLayout(3, 1));
    panelTlacitok.add(tlacitkoA);
    panelTlacitok.add(tlacitkoB);
    panelTlacitok.add(tlacitkoC);

    }




}
