package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
public class Cumparator{
    private Bursa b;
    private double contBancar;
    private int cantitateActiuni=0;
    public String nume;
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    public Cumparator(String nume, Bursa b, double contBancar) {
        this.nume = nume;
        this.b = b;
        this.contBancar = contBancar;
    }
    
    public boolean comparaCuSold(double pretActiuni){
        return contBancar>pretActiuni;
    }

    public void adaugaCerere(int cantitate, double pret) {
        // System.out.println("adaugaCerere cantitate:"+cantitate+" pret:"+pret);
        CumparaActiune ca = new CumparaActiune(cantitate, pret, this);
        this.b.adaugaCerere(ca);
    }


    public void actiuneCumparata(int cantitate, double pret) {
        this.cantitateActiuni += cantitate;
        contBancar -= cantitate*pret;
        System.out.println( this.nume + " cumpara " + 
                            cantitate + " actiuni cu " + numberFormat.format(pret*cantitate) + ". Detine "+cantitateActiuni+
                            " actiuni si " + numberFormat.format(contBancar) + " lei.");
    }




   
}
