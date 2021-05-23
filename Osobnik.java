package com.Speedqber;

abstract public class Osobnik {

    final int rodzaj;
    final int indeks;
    int PoziomSily;
    int zdrowie;
    final int wiek;
    int szybkosc;
    int pole;
    int ZakresWidzenia;

    public void Atak(Osobnik osobik){}
    public void PrzyjmijAtak(Osobnik osobnik){}
    public void ZmniejszZdrowie(Osobnik osobnik){}
    public void ZwiekszZdrowie(Osobnik osobnik){}
    public void WykonajRuch(){}

    public int IleWidziWrogow(){
        int IloscWrogow;
        return IloscWrogow;
    }

    public int IleWrogowJestBlisko(){
        int IleJestBlisko;
        return IleJestBlisko;
    }

    public boolean CzyJestMartwyWrog(int rodzaj){
        boolean TakCzyNie;
        return TakCzyNie;
    }

    public int IleWidziSojusznikow(){
        int Ilosc;
        return Ilosc;
    }

    public boolean WykonajOswojenie(){
        boolean TakCzynie;
        return TakCzyNie;
    }

}
