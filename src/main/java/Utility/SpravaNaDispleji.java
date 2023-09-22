package Utility;

import Utility.Stav;

public class SpravaNaDispleji
{

    private String sprava;
    private static double akutualnyZostatok;

    /**
     * Staticka metoda kontroluje aktulny stav z triedy Stav a podla toho pri zavolani zobrazi spravu na displeji
     * alebo na konzole
     * @return vracia spravu podla aktualneho stavu bankomatu
     */
    public static String zozbrazSpravu()
    {

           String aktualnyStav = Stav.getAktualnyStav();

           if(aktualnyStav.equalsIgnoreCase("start"))
           {
               if(Stav.isGui())
               {
                   return "Zadaj klientské čislo \nA = OK";
               }
               return "Zadaj klientské čislo: ";
           }
           else if (aktualnyStav.equalsIgnoreCase("pin"))
           {
               if(Stav.isGui())
               {
                   return "Zadaj PIN: \nA = OK";
               }
               return "Zadaj PIN: ";
           }
           else if (aktualnyStav.equalsIgnoreCase("ucet"))
           {
               return "Zvoľ typ účtu: \n" +
                       "A = Bežný\n" +
                       "B = Sporiaci\n" +
                       "C = Koniec";
           }
           else if (aktualnyStav.equalsIgnoreCase("transakcia"))
           {



               return "Aktualny zostatok: " + akutualnyZostatok
                       + " EUR\nZadaj ciastku a zvol operaciu:\nA = Vyber\nB = Vklad\nC = Krok spat";
           }
           return null;
       }

    /**
     * metoda prijma aktualny zostatok aby mohol bit zobrazeny v sprave
     * @param zostatok prijma zostatok na ucte
     */
    public static void zostatok(double zostatok)
       {
           akutualnyZostatok = zostatok;
       }



}
