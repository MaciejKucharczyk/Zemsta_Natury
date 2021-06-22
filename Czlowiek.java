package com.Speedqber;

public class Czlowiek extends Osobnik{

    public Czlowiek(int indeks, Pole pole){
        max_zdrowie=400;
        zdrowie=max_zdrowie;
        this.indeks=indeks;
        this.PoziomSily = 40;
        this.pole=pole;
        this.szybkosc=3;
        this.ZakresWidzenia=9;

    }

    public Czlowiek(int indeks){
        max_zdrowie=400;
        zdrowie=max_zdrowie;
        szybkosc_bazowa=3;
        this.rodzaj=1;
        this.indeks=indeks;
        this.PoziomSily = 40;
        this.pole=pole;
        this.szybkosc=szybkosc_bazowa;
        this.ZakresWidzenia=9;

    }

    int IloscZabitychNiedzwiedzi;

    public boolean Oswajanie(){
        boolean Oswojenie;
        return Oswojenie= generator.nextBoolean();
    }

    public void PozeranieNiedzwiedzia(Osobnik osobnik){
    }
}
