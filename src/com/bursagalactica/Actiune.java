package com.bursagalactica;

public class Actiune {
    private String nume;
    private double cantitate;
    private double valoare;
    private Agent proprietar;

    public Actiune(String nume, double cantitate, double valoare, Agent proprietar) {
        this.nume = nume;
        this.cantitate = cantitate;
        this.valoare = valoare;
        this.proprietar = proprietar;
    }

    public String getJSON() {
        return "{ \"name\":\"" + nume + "\", \"value\":" + valoare + ",\"volume\":" + cantitate + ",\"owner\":\"" + proprietar.getNumeAgent() + "\"}";
    }

    public void getDetalii() {
        System.out.println(nume);
        System.out.println(cantitate);
        System.out.println(valoare);
        System.out.println(proprietar);
    }
}
