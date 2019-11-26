package com.bursagalactica;

public abstract class Actiune {
    private double pret;
    private volatile int cantitate;
    private volatile boolean inTranzactie = false;


    public Actiune(int cantitate,double pret) {
        this.pret = pret;
        this.cantitate = cantitate;
    }
    
    public double getPrice() {
        return this.pret;
    }

    public int getCount() {
        return this.cantitate;
    }

    public boolean getInTransaction() {
        return this.inTranzactie;
    }

    public void setInTransaction(boolean statusTranzactie) {
        this.inTranzactie = statusTranzactie;
    }

    public void tranzactioneaza(int cantitate){
        if(cantitate> this.cantitate){
            System.out.println("Nu poti tranzactiona mai multe actiuni decat exista pe piata");
            return;
        }
        this.cantitate -= cantitate;
    }

}
