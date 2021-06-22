package com.Speedqber;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pole {

    int IloscLudzi;
    int IloscNiedzwiedzi;   //Czy to potrzebne skoro mamy listy i mozemy wyciagnac ilosc :/ ?
    int IloscKawalerii;     //

    /*List<Czlowiek> LudzieNaPolu = new ArrayList<>();
    List<Niedzwiedz> NiedzwiedzieNaPolu = new ArrayList<>();
    List<Kawaleria> KawaleriaNaPolu = new ArrayList<>();*/

    public  int WspX;
    public  int WspY;
    public int ID;
    //  public final int ID_pola;
    //  private static int ile_juz_jest=0;

    int numerTerenu;
    double SpowolnienieNiedzwiedzia;
    double SpowolnienieCzlowieka;

    int IloscZwlokLudzi;
    int IloscZwlokNiedzwiedzi;

    public Pole(int WspX, int WspY){
        this.WspX = WspX;
        this.WspY = WspY;
        ID=WspY*Symulacja.SzerokoscMapy+WspX;
        // ile_juz_jest=ile_juz_jest+1;
        //ID_pola=ile_juz_jest;

    }

    public void ustawienie_szybkosci()
    {
        switch (numerTerenu)
        {
            case 1://miasto
                SpowolnienieCzlowieka=1.4;
                SpowolnienieNiedzwiedzia=0.6;
                break;

            case 2://wieś
                SpowolnienieCzlowieka=1.2;
                SpowolnienieNiedzwiedzia=0.8;
                break;

            case 3://łąka
                SpowolnienieCzlowieka=1;
                SpowolnienieNiedzwiedzia=1;
                break;

            case 4://las
                SpowolnienieCzlowieka=0.8;
                SpowolnienieNiedzwiedzia=1.2;
                break;

            case 5://góry
                SpowolnienieCzlowieka=0.6;
                SpowolnienieNiedzwiedzia=1.4;
                break;
        }
    }

}
