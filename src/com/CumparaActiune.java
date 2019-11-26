package com.bursagalactica;

public class CumparaActiune extends Actiune{
        private final Cumparator cumparator;
        private volatile boolean inTranzactie = false;

        public boolean getInTransaction() {
            return this.inTranzactie;
        }
        public void setInTransaction(boolean b){
            this.inTranzactie=b;
        }

    public CumparaActiune(final int cantitate, final double pret, final Cumparator cumparator) {
        super(cantitate, pret);
        this.cumparator = cumparator;
    }
        public String getNumeCumparator(){
            return cumparator.nume;
        }
    public void tranzactioneaza(final int cantitate) {
        super.tranzactioneaza(cantitate);
        this.cumparator.actiuneCumparata(cantitate, super.getPrice());
    }

    public boolean verificaDacaDepasimBuget(final double suma) {
        return cumparator.comparaCuSold(suma);
    }

}
