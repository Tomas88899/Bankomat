package Bankomat_Main;

import Banka.Banka;
import Banka.Bankomat;
import Utility.SpravaNaDispleji;
import Utility.Stav;

import java.util.Scanner;

public class Klavesnica
{
    /**
     * vytvara novy objekt konzoloveho rozhrania bankomat
     */
private boolean running;
    public Klavesnica(){

        running = true;
        Bankomat bankomat = new Bankomat(new Banka());
        Scanner vstup = new Scanner(System.in);

        String klientskeCislo ="";
        String pin="";


        while(running)
        {

            String stav = Stav.getAktualnyStav();
            System.out.println(stav);
            if(stav.equalsIgnoreCase("Start"))
            {
                System.out.println(SpravaNaDispleji.zozbrazSpravu());
                klientskeCislo = vstup.nextLine();
               bankomat.nastavKlientskeCislo(klientskeCislo);
            }
            else if (stav.equalsIgnoreCase("pin"))
            {
                System.out.println(SpravaNaDispleji.zozbrazSpravu());
                pin = vstup.nextLine();
                bankomat.nastavKlienta(klientskeCislo,pin);

            }
            else if (stav.equalsIgnoreCase("ucet"))
            {
                System.out.print(SpravaNaDispleji.zozbrazSpravu());
                String volba = vstup.next();

                if (volba.equalsIgnoreCase("A"))
                {
                    bankomat.nastavTypUctu("bezny");

                }
                else if (volba.equalsIgnoreCase("B"))
                {
                    bankomat.nastavTypUctu("sporiaci");
                }
                else if (volba.equalsIgnoreCase("C"))
                {
                    bankomat.reset();
                }
                else
                {
                    System.out.println("Nespravna volba!");
                }
            }
            else if (stav.equalsIgnoreCase("TRANSAKCIA"))
            {
                SpravaNaDispleji.zostatok(bankomat.getAktualnyZostatok());
                System.out.println(SpravaNaDispleji.zozbrazSpravu());
                String volba = vstup.next();

                if (volba.equalsIgnoreCase("A"))
                {
                    System.out.print("Ciastka: ");
                    double ciastka = vstup.nextDouble();

                    if(!bankomat.vyber(ciastka))
                    {
                        System.out.println("Nedostatok prostriedkov na ucte");
                        bankomat.nastavPredchadzajuciStav();
                    }else
                    {
                        bankomat.nastavPredchadzajuciStav();
                    }


                }
                else if (volba.equalsIgnoreCase("B"))
                {
                    System.out.print("Ciastka: ");
                    double ciastka = vstup.nextDouble();

                    bankomat.vloz(ciastka);
                    bankomat.nastavPredchadzajuciStav();

                }
                else if (volba.equalsIgnoreCase("C"))
                {
                    bankomat.nastavPredchadzajuciStav();
                }
                else
                {
                    System.out.println("Nespravna volba!");
                }
            }

        }
    }
}
