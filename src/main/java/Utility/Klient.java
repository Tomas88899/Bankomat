package Utility;

import Banka.BankovyUcet;

public class Klient
{
    private  String klientskeCislo;
    private  String pin;

    private BankovyUcet beznyUcet;
    private  BankovyUcet sporiaciUcet;

    /**
     * Vytvara novy objekt typu klient prijam parametre klientskeCislo a pin
     * a vytvara nove objekty typu BankovyUcet
     * @param klientskeCislo
     * @param pin
     */
    public Klient(String klientskeCislo, String pin) {
        this.klientskeCislo = klientskeCislo;
        this.pin = pin;
        this.beznyUcet = new BankovyUcet();
        this.sporiaciUcet = new BankovyUcet();
    }

    /**
     * vrati true ak prijate parametre id a pin sa z hoduju z klientskeCislom a pinom daneho objetku typu klient
     * @param id
     * @param zadanyPin
     * @return
     */
    public boolean ucetExistuje(String id,String zadanyPin )
    {
        return (this.klientskeCislo.equals(id) && this.pin.equals(zadanyPin));
    }

    /**
     * vracia bezeny Bankovy ucet klienta
     * @return vracia bezeny Bankovy ucet klienta
     */
    public BankovyUcet getBeznyUcet()
    {
        return beznyUcet;
    }

    /**
     * vracia sporiaci Bankovy ucet klienta
     * @return vracia sporiaci Bankovy ucet klienta
     */
    public BankovyUcet getSporiaciUcet()
    {
        return sporiaciUcet;
    }
}
