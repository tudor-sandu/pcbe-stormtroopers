package com.bursagalactica;

public class CumparaActiune extends Actiune{
        private Cumparator cumparator;

    public CumparaActiune(int cantitate,double pret, Cumparator cumparator){
        super(cantitate, pret, cumparator);
        this.cumparator = cumparator;
    }

    public void tranzactioneaza(int cantitate){
        super.tranzactioneaza(cantitate);
        super.cumparator.actiuneCumparata(cantitate);
    }
    public boolean verificaDacaDepasimBuget(double suma){
        return cumparator.comparaCuSold(suma);
    }

}
