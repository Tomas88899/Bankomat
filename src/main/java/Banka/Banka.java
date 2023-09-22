package Banka;

import Utility.Klient;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banka
{
    private  List<Klient> zoznamKlientov;
    private final String nazovSuboru = "src\\main\\resources\\klienti.txt";

    /**
     * vytvara novy objekt banka
     */
    public Banka()
    {
        zoznamKlientov= new ArrayList<>();
        nacitanieKlientov(nazovSuboru);
    }

    /**
     * vyhlada klienta a zisti ci sa jeho udaje zhoduju s udajmi v banke
     *
     * @param id klientske cislo klienta
     * @param pin pin klienta
     * @return vrati klienta ak presiel validaciou v opacnom priprade vrati null
     */
    public  Klient vyhladanieKlienta(String id,String pin)
    {
        for (Klient vyhladanyklient : zoznamKlientov)
        {
            if (vyhladanyklient.ucetExistuje(id,pin))
            {
                return vyhladanyklient;
            }

        }

        return null;
    }

    /**
     * nacita klientske udaje zo suboru
     *
     * @param nazovSuboru subor s udajmi o klientoch

     */
    public void nacitanieKlientov(String nazovSuboru)
        {
            try {

                String line;
                FileReader citac = new FileReader(nazovSuboru);
                Scanner scanner = new Scanner(citac);

                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();

                    String [] klient = (line.split(" "));

                    zoznamKlientov.add(new Klient(klient[0],klient[1]));
                }
                scanner.close();
                citac.close();

            } catch (FileNotFoundException e) {
                System.out.println("Subor nenajdeny");
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Nepodarilo sa uzavriet citac suborov");
                System.exit(0);
            }

        }




}

