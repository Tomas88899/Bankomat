package Utility;

/**
 * Trieda stav uchovava aktualny stav bankomatu a ci bolo spustene GUI rozhranie alebo konzolove
 */
public class Stav
{
    private static String aktualnyStav = "Start";
    private static boolean gui = false;

    /**
     * Vracia aktualny stav v akom sa Bankomat nachadza
     * @return Vracia aktualny stav v akom sa Bankomat nachadza
     */
    public static String getAktualnyStav() {
        return aktualnyStav;
    }

    /**
     * Nastavuje novy stav v akom sa Bankomat nachadza
     * @param aktualnyStav nastavuje novy stav Bankomatu
     */
    public static void setAktualnyStav(String aktualnyStav) {
        Stav.aktualnyStav = aktualnyStav;
    }

    /**
     * vrati true ak bolo spustene GUI rozhranie Bankomatu v opacno pripade false
     * @return vrati true ak bolo spustene GUI rozhranie Bankomatu v opacno pripade false
     */
    public static boolean isGui() {
        return gui;
    }

    /**
     * nastavuje hodnotu premmenej boolen gui
     * @param gui prijma true alebo false
     */
    public static void setGui(boolean gui) {
        Stav.gui = gui;
    }
}
