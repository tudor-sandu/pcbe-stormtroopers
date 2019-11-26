package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;


public class Cumparator{
    private Bursa b;
    private double contBancar;
    private int cantitateActiuni=0;
    private String nume;

    public Cumparator(String nume, Bursa b, double contBancar) {
        this.nume = nume;
        this.b = b;
        this.contBancar = contBancar;
    }
    
    public boolean comparaCuSold(double pretActiuni){
        return contBancar>pretActiuni;
    }

    public void adaugaCerere(int cantitate, double pret) {
        CumparaActiune ca = new CumparaActiune(cantitate, pret, this);
        this.b.adaugaCerere(ca);
    }


    public void actiuneCumparata(int cantitate, double pret) {
        this.cantitateActiuni += cantitate;
        contBancar -= cantitate*pret;
        System.out.println("Cumparatorul " + this.nume + " a cumparat" + 
                            cantitate + " actiuni cu " + pret*cantitate + " si acuma detine"+cantitateActiuni+
                            " si mai are " +contBancar + " bani.");
    }




   
}
