package com.Speedqber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.Speedqber.Symulacja.*;
import static java.lang.Math.sqrt;

abstract public class Osobnik {

    Random generator = new Random();

    int max_zdrowie;
    int rodzaj;
    public int indeks;
    int PoziomSily;
    int zdrowie;
    int wiek;
    int szybkosc_bazowa;
    int szybkosc;
    int aktualneWspX;
    int aktualneWspY;
    Pole pole;
    int ZakresWidzenia;

    public static void Atak(Osobnik agresor, List<Osobnik> wrogowie){

                        //szukanie najblizszego wroga wsrod listy wrogow w polu widzenia

                        List <Osobnik> WrogowieWZasieguRuchu = new ArrayList<>();
                        for(int j=0;j<wrogowie.size();j++)   //ustalenie ile pol jest w zasiegu ruchu
                        {
                            if((wrogowie.get(j).aktualneWspX - agresor.aktualneWspX)*(wrogowie.get(j).aktualneWspX - agresor.aktualneWspX) + (wrogowie.get(j).aktualneWspY - agresor.aktualneWspY)*(wrogowie.get(j).aktualneWspY - agresor.aktualneWspX) <= (agresor.szybkosc +1)*(agresor.szybkosc +1))
                            {//wzorek:(x-centrum_x)*(x-centrum_x)+(y-centrum_y)*(y-centrum_y)<=promien*promien)

                                WrogowieWZasieguRuchu.add(wrogowie.get(j));
                            }
                        }

                        if(WrogowieWZasieguRuchu.size()>0)   //czy są wrogowie w zasięgu ruchu?
                        {
                            for (int m = 0; m < WrogowieWZasieguRuchu.size(); m++)   //posortowanie ktore z pol w zasiegu ruchu jest najblizej
                            {
                                for (int n = 0; n < WrogowieWZasieguRuchu.size()-m-1; n++)
                                {
                                    if ((WrogowieWZasieguRuchu.get(n).aktualneWspX - agresor.aktualneWspX)*(WrogowieWZasieguRuchu.get(n).aktualneWspX - agresor.aktualneWspX) + (WrogowieWZasieguRuchu.get(n).aktualneWspY - agresor.aktualneWspY)*(WrogowieWZasieguRuchu.get(n).aktualneWspY - agresor.aktualneWspY) > (WrogowieWZasieguRuchu.get(n+1).aktualneWspX - agresor.aktualneWspX)*(WrogowieWZasieguRuchu.get(n+1).aktualneWspX - agresor.aktualneWspX) + (WrogowieWZasieguRuchu.get(n+1).aktualneWspY - agresor.aktualneWspY)*(WrogowieWZasieguRuchu.get(n+1).aktualneWspY - agresor.aktualneWspY))
                                    {
                                        Osobnik zamiennik = WrogowieWZasieguRuchu.get(n);
                                        WrogowieWZasieguRuchu.set(n , WrogowieWZasieguRuchu.get(n+1));
                                        WrogowieWZasieguRuchu.set(n+1 , zamiennik);
                                    }
                                }
                            }

                            int WspX_wroga=WrogowieWZasieguRuchu.get(0).aktualneWspX;
                            int WspY_wroga=WrogowieWZasieguRuchu.get(0).aktualneWspY;
                            List <Pole> Otoczenie_wroga = new ArrayList<>();

                            for (int m = WspY_wroga-1; m <= WspY_wroga+1; m++) //dodanie otoczenia wroga
                            {
                                for (int n = WspX_wroga-1; n <= WspX_wroga+1; n++)
                                {
                                    if(n>=0 && n<SzerokoscMapy && m>=0 && m<DlugoscMapy && !(n==WspX_wroga && m==WspY_wroga))
                                    {
                                        Otoczenie_wroga.add(TablicaPol[m][n]);
                                    }
                                }
                            }

                            for (int m = 0; m < Otoczenie_wroga.size(); m++)   //posortowanie otoczenia wroga
                            {
                                for (int n = 0; n < Otoczenie_wroga.size()-m-1; n++)
                                {
                                    if ((Otoczenie_wroga.get(n).WspX - agresor.aktualneWspX)*(Otoczenie_wroga.get(n).WspX - agresor.aktualneWspX) + (Otoczenie_wroga.get(n).WspY - agresor.aktualneWspY)*(Otoczenie_wroga.get(n).WspY - agresor.aktualneWspY) > (Otoczenie_wroga.get(n+1).WspX - agresor.aktualneWspX)*(Otoczenie_wroga.get(n+1).WspX - agresor.aktualneWspX) + (Otoczenie_wroga.get(n+1).WspY - agresor.aktualneWspY)*(Otoczenie_wroga.get(n+1).WspY - agresor.aktualneWspY))
                                    {
                                        Pole zamiennik = Otoczenie_wroga.get(n);
                                        Otoczenie_wroga.set(n , Otoczenie_wroga.get(n+1));
                                        Otoczenie_wroga.set(n+1 , zamiennik);
                                    }
                                }
                            }

                            int WspX_docelowe = -5;
                            int WspY_docelowe = -5;

                            for(int m=0;m<Otoczenie_wroga.size();m++)     //sprawdzenie czy nie zajete pole przez inny rodzaj
                            {
                                if (agresor.rodzaj==1)
                                {
                                    if (TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscNiedzwiedzi == 0 && TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscKawalerii == 0)
                                    {
                                        WspX_docelowe = Otoczenie_wroga.get(m).WspX;
                                        WspY_docelowe = Otoczenie_wroga.get(m).WspY;
                                        break;
                                    }
                                }

                                if (agresor.rodzaj==2)
                                {
                                    if (TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscLudzi == 0 && TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscKawalerii == 0)
                                    {
                                        WspX_docelowe = Otoczenie_wroga.get(m).WspX;
                                        WspY_docelowe = Otoczenie_wroga.get(m).WspY;
                                        break;
                                    }
                                }

                                if (agresor.rodzaj==3)
                                {
                                    if (TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscLudzi == 0 && TablicaPol[Otoczenie_wroga.get(m).WspY][Otoczenie_wroga.get(m).WspX].IloscNiedzwiedzi == 0)
                                    {
                                        WspX_docelowe = Otoczenie_wroga.get(m).WspX;
                                        WspY_docelowe = Otoczenie_wroga.get(m).WspY;
                                        break;
                                    }
                                }
                            }

                            //TUTAJ ODPALENIE FUNKCJI PORUSZANIA
                            Ruch_na_konkretne_pole(agresor, TablicaPol[WspY_docelowe][WspX_docelowe]);

                            WrogowieWZasieguRuchu.get(0).zdrowie-=agresor.PoziomSily;


                            if(WrogowieWZasieguRuchu.get(0).zdrowie<=0)
                            {
                                if(WrogowieWZasieguRuchu.get(0).rodzaj==1){
                                    TablicaPol[WrogowieWZasieguRuchu.get(0).aktualneWspY][WrogowieWZasieguRuchu.get(0).aktualneWspX].IloscLudzi--;
                                }else if(WrogowieWZasieguRuchu.get(0).rodzaj==2){
                                    TablicaPol[WrogowieWZasieguRuchu.get(0).aktualneWspY][WrogowieWZasieguRuchu.get(0).aktualneWspX].IloscNiedzwiedzi--;
                                }else if(WrogowieWZasieguRuchu.get(0).rodzaj==3){
                                    TablicaPol[WrogowieWZasieguRuchu.get(0).aktualneWspY][WrogowieWZasieguRuchu.get(0).aktualneWspX].IloscKawalerii--;
                                }
                                osobnicy.remove(WrogowieWZasieguRuchu.get(0));
                            }

                        }

                    }


    public void PrzyjmijAtak(Osobnik osobnik){}
    public void ZmniejszZdrowie(Osobnik osobnik){}
    public void ZwiekszZdrowie(Osobnik osobnik){}

    public static void Ruch_na_konkretne_pole(Osobnik osobnik, Pole konkretne_pole){
        int p=osobnik.aktualneWspX;
        int q=osobnik.aktualneWspY;

        osobnik.aktualneWspX= konkretne_pole.WspX;
        osobnik.aktualneWspY=konkretne_pole.WspY;

        if (osobnik.rodzaj == 1) {
            TablicaPol[q][p].IloscLudzi--;
            konkretne_pole.IloscLudzi++;
        } else if (osobnik.rodzaj == 2) {
            TablicaPol[q][p].IloscNiedzwiedzi--;
            konkretne_pole.IloscNiedzwiedzi++;
        } else if (osobnik.rodzaj == 3) {
            TablicaPol[q][p].IloscKawalerii--;
            konkretne_pole.IloscKawalerii++;
        }
    }

   // public void IleWidziWrogow(Osobnik osobnik){ }

    //public int IleWrogowJestBlisko(){ return IleJestBlisko; }

    //public boolean CzyJestMartwyWrog(int rodzaj){ return TakCzyNie; }

    //public int IleWidziSojusznikow(){ return IloscSojusznikow; }

    //public boolean WykonajOswojenie(){ return TakCzyNie; }



}

