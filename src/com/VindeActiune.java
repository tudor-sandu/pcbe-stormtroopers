package com.bursagalactica;

public class VindeActiune extends Actiune{
        private Vanzator vanzator;

    public VindeActiune(int cantitate, double pret, Vanzator vanzator){
        super(cantitate, pret);
        this.vanzator = vanzator;
    }

    public void tranzactioneaza(int cantitate){
        super.tranzactioneaza(cantitate);
        this.vanzator.actiuneVanduta(cantitate * super.getPrice());
    }
  

    public void tryTransaction(CumparaActiune cumparaActiune) {
        if (super.getInTransaction() || cumparaActiune.getInTransaction()) {
            return;
        }

        synchronized (this) {//pentru a nu putea accesa aceeasi actiune de mai multe persoane
            this.setInTransaction(true);
            cumparaActiune.setInTransaction(true);

            if (cumparaActiune.getPrice() != this.getPrice() || this.getCount() == 0 || cumparaActiune.getCount() == 0) {
                return;
            }

            int min = Math.min(this.getCount(), cumparaActiune.getCount());
            if(cumparaActiune.verificaDacaDepasimBuget(min * cumparaActiune.getPrice())){
                this.tranzactioneaza(min);
                cumparaActiune.tranzactioneaza(min);
                }
            else System.out.println("Nu ai destui bani sa cumperi actiunile respective");
        }

        this.setInTransaction(false);
        cumparaActiune.setInTransaction(false);

    }



}