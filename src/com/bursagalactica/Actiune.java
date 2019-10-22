package com.bursagalactica;

public class Actiune {
    private String nume;
    private double cantitate;
    private double valoare;


    public Actiune(String nume, double cantitate, double valoare) {
        this.nume = nume;
        this.cantitate = cantitate;
        this.valoare = valoare;
    }
    public String getInfo() {
        return "{ name:"+nume+", value:"+valoare+"volume:"+cantitate+"}";
    }

    public void getDetalii() {
        System.out.println(nume);
        System.out.println(cantitate);
        System.out.println(valoare);
    }
}
