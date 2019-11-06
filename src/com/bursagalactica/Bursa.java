package com.bursagalactica;

import java.util.ArrayList;

public class Bursa {

    private ArrayList<Actiune> ActiuniCumparare = new ArrayList<Actiune>();
    private ArrayList<Actiune> ActiuniVanzare = new ArrayList<Actiune>();

    public synchronized void vinde(Actiune e) {
        ActiuniVanzare.add(e);
    }

    public synchronized void cumpara(Actiune e) {
        ActiuniCumparare.add(e);
    }

    public ArrayList<Actiune> getActiuniCumparare() {
        return ActiuniCumparare;
    }

    public ArrayList<Actiune> getActiuniVanzare() {
        return ActiuniVanzare;
    }

    public void verificaTranzactii() {
        System.out.println("--------------Verific tranzatiii-----------");
    }
}