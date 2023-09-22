package Banka;


import Utility.Klient;
import Utility.Stav;

/**
 * Trieda Bankomat na starosti overovanie spravne zadaneho klienskeho cisla a pinu a nasledne operacie nad uctom
 */
public class Bankomat
{

    private Klient vybranyKlient;
    private BankovyUcet vybranyUcet;
    private Banka banka;
    private String klientskeCislo;


    /**
     * Vytvara novy objekt Bankomt ako vstupnu parameter prijma objekt typu banka po inicializovany
     *spusta metodu reset ktora nastavy premmene vybranyKlient,vybranyUcet na hodnotu null a
     * stav na hodnotu start
     * @param banka
     */
    public Bankomat(Banka banka)
    {
        this.banka = new Banka();
        reset();
    }

    /**
     * Metoda astavy premmene vybranyKlient,vybranyUcet na hodnotu null a
     *      * stav na hodnotu start
     */
    public void reset()
    {
        vybranyKlient = null;
        vybranyUcet = null;
        Stav.setAktualnyStav("start");
    }

    /**
     * Metoda prijma parameter cislo a nastavy jeho hodnotu na premmenu klientskeCislo
     * @param cislo
     */
    public void nastavKlientskeCislo(String cislo)
    {
        assert Stav.getAktualnyStav() == "START";
        this.klientskeCislo = cislo;
        Stav.setAktualnyStav("PIN");
    }

    /**
     * Metoda prijma parametre klienstkeCislo a pin a vola metodu objektu bnaka ktory kontrolu je ci dany klient existuje,
     * ak ano nastavy premmenu vybranyKlient na hodnotu vybraneho klienta
     * @param klienstkeCislo klienstkeCislo uctu
     * @param pin pin kod k danemu ucutu
     */
    public void nastavKlienta(String klienstkeCislo,String pin)
    {
        assert Stav.getAktualnyStav() == "PIN";
        this.vybranyKlient = banka.vyhladanieKlienta(klienstkeCislo, pin);
        if (vybranyKlient == null)
        {
            Stav.setAktualnyStav("START");
        }
        else
        {
            Stav.setAktualnyStav("UCET");
        }
    }


    /**
     * Metoda kotroluje aktualny stak ak je spravny prijma parameter nazov ucutu a na
     * @param ucet
     */
    public void nastavTypUctu(String ucet)
    {
        assert Stav.getAktualnyStav() == "UCET" || Stav.getAktualnyStav() == "TRANSAKCIA";
        if (ucet.equalsIgnoreCase("bezny"))
        {
            this.vybranyUcet = vybranyKlient.getBeznyUcet();
        }
        else if (ucet.equalsIgnoreCase("sporiaci"))
        {
            this.vybranyUcet = vybranyKlient.getSporiaciUcet();
        }
        Stav.setAktualnyStav("TRANSAKCIA");
    }


    /**
     * Metoda vracia aktualny zozstatok uzivatelom vybraneho ucutu
     * @return vracia aktualny zozstatok uzivatelom vybraneho ucutu
     */
    public double getAktualnyZostatok()
    {
        assert Stav.getAktualnyStav() == "TRANSAKCIA";
        return this.vybranyUcet.getAktualnyZostatok();
    }

    /**
     * Metoda zavola zavola metodu vloz na ucet na uzivatelom vybrany ucet
     * vrati true ak sa transakcia vykonala(na vybranom ucte bolo dostatok prostriedkov)
     * v opacnom pripade vrati false
     * @param ciastka suma penazi ktora sa ma z ucutu vybrat
     * @return vrati true ak sa transakcia vykonala(na vybranom ucte bolo dostatok prostriedkov)
     *      * v opacnom pripade vrati false
     */
    public boolean vyber(double ciastka)
    {
        assert Stav.getAktualnyStav() == "TRANSAKCIA";
        return this.vybranyUcet.vyber(ciastka);
    }

    /**
     * Metoda zavola metodu vloz na ucet na uzivatelom vybrany ucet
     * @param ciastka suma penazi ktora sa ma na ucet vlozit
     */
    public void vloz(double ciastka)
    {
        assert Stav.getAktualnyStav() == "TRANSAKCIA";
        this.vybranyUcet.vloz(ciastka);
    }


    /**
     * metoda kontroluje aktualny stav a nastavy jeho predchadzajucu hodnotu
     */
    public void nastavPredchadzajuciStav()
    {
        if (Stav.getAktualnyStav().equalsIgnoreCase("TRANSAKCIA"))
        {
            Stav.setAktualnyStav("UCET");
        }
        else if (Stav.getAktualnyStav().equalsIgnoreCase("UCET"))
        {
            Stav.setAktualnyStav("PIN");
        }
        else if (Stav.getAktualnyStav().equalsIgnoreCase("PIN"))
        {
            Stav.setAktualnyStav("START");
        }
    }


}
