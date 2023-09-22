package Banka;

/**
 * Trieda bankovy ucet predstavuje zjednoduseny bankovy ucet z realneho sveta.
 * Je mozne aktualny zostatok zvysit, znizit a ziskat jeho hodnotu.
 */
public class BankovyUcet {

    private double aktualnyZostatok;

    /**
     * Vytvara novy bankovy ucet s nulovym pociatocnym zostatkom.
     */
    public BankovyUcet() {
        aktualnyZostatok = 0;
    }


    /**
     * Vklada danu sumu penazi na bankovy ucet.
     *
     * @param ciastka suma penazi, ktora sa vlozi na ucet
     */
    public void vloz(double ciastka) {
        aktualnyZostatok = aktualnyZostatok + ciastka;
    }

    /**
     * ybera danu sumu penazi z bankoveho uctu a zaroven kontroluje ci je dostatok prostriedkov na ucte.
     * @param ciastka suma penazi ktora sa ma z uctu vybrat
     * @return tracia true ak je na ucte dostatok prostriedkov
     */
    public boolean vyber(double ciastka)
    {
        if(aktualnyZostatok - ciastka >= 0 )
        {
            aktualnyZostatok = aktualnyZostatok - ciastka;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Vracia aktualny zostatok na bankovom ucte.
     *
     * @return aktualny zostatok na bankovom ucte
     */
    public double getAktualnyZostatok() {
        return aktualnyZostatok;
    }

}
