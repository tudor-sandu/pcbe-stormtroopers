package com.bursagalactica;

public abstract class Actiune {
    private double pret;
    private volatile int cantitate;


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
 

    public abstract boolean getInTransaction();
    public abstract void setInTransaction(boolean b);

    public void tranzactioneaza(int cantitate){
        if(cantitate> this.cantitate){
            System.out.println("Nu poti tranzactiona mai multe actiuni decat exista pe piata");
            return;
        }
        
        this.cantitate -= cantitate;
    }

}
