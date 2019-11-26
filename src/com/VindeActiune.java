package com.bursagalactica;

public class VindeActiune extends Actiune{
        private Vanzator vanzator;
        private volatile boolean inTranzactie = false;

        public boolean getInTransaction() {
            return this.inTranzactie;
        }
        public void setInTransaction(boolean b){
            this.inTranzactie=b;
        }
    public VindeActiune(int cantitate, double pret, Vanzator vanzator){
        super(cantitate, pret);
        this.vanzator = vanzator;
    }

    public void tranzactioneaza(int cantitate){
        super.tranzactioneaza(cantitate);
        this.vanzator.actiuneVanduta(cantitate * super.getPrice());
    }
  

    public void tryTransaction(CumparaActiune cumparaActiune) {
        if (this.getInTransaction() || cumparaActiune.getInTransaction()) {
            return;
        }
        synchronized (this) {//pentru a nu putea accesa aceeasi actiune de mai multe persoane
            this.setInTransaction(true);
            cumparaActiune.setInTransaction(true);
            if (cumparaActiune.getPrice() != this.getPrice() || this.getCount() == 0 || cumparaActiune.getCount() == 0) {
                if(cumparaActiune.getPrice() != this.getPrice())
                    // System.out.println(cumparaActiune.getPrice()+" - "+this.getPrice()+" Cererea nu coincide cu oferta. Tranzactie esuata!");
                return;
            }
            //System.out.println("tranzactionam!!!!!");

            int min = Math.min(this.getCount(), cumparaActiune.getCount());
            if(cumparaActiune.verificaDacaDepasimBuget(min * cumparaActiune.getPrice())){
                this.tranzactioneaza(min);
                cumparaActiune.tranzactioneaza(min);
                }
            else System.out.println(cumparaActiune.getNumeCumparator()+ " nu are destui bani sa cumperi actiunile respective");
        }

        this.setInTransaction(false);
        cumparaActiune.setInTransaction(false);

    }



}