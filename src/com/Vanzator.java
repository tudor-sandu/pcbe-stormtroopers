package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;

public class Vanzator {
    private Bursa b;
    private double contBancar=0.0;
    private String nume;

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
        System.out.println(
                "Vanzatorul " + nume + " a primi " + pret + " lei pe actiunile sale. Total in cont: " 
                + contBancar + " lei.");
    }
}
