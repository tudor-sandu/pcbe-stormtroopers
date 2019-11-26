package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
public class Vanzator {
    private Bursa b;
    private double contBancar=0.0;
    private String nume;
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    public Vanzator(String n,Bursa b) {
        nume = n;
        this.b = b;
    }

    public void punePeBursa(int c, double p) {
        VindeActiune va = new VindeActiune(c, p, this);
        this.b.punePeBursa(va);
    }

    public void actiuneVanduta(double pret) {
        this.contBancar += pret;
        System.out.println( nume + " a vandut actiuni in valoare de " +  numberFormat.format(pret) +
                 " lei. Sold cont: " + numberFormat.format(contBancar) + " lei.");
    }
}
