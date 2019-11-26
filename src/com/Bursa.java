package com.bursagalactica;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class Bursa {

    private volatile ArrayList<VindeActiune> vindeActiuni ;
    private volatile HashMap<CumparaActiune, Thread> oferteActiuni ;


    public Bursa(){
        vindeActiuni = new ArrayList<>();
        oferteActiuni = new HashMap<>();
    }

    public synchronized void punePeBursa(VindeActiune actiune) {
        vindeActiuni.add(actiune);
    }

    public synchronized void scoateDePeBursa(VindeActiune actiune) {
        vindeActiuni.remove(actiune);
    }

    public synchronized void adaugaCerere(CumparaActiune cumparaActiune) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (VindeActiune vindeActiune : vindeActiuni) {
                        vindeActiune.tryTransaction(cumparaActiune);
                        if (vindeActiune.getCount() == 0) {
                            scoateDePeBursa(vindeActiune);
                        }
                        if (cumparaActiune.getCount() == 0) {
                            scoateCerere(cumparaActiune);
                        }
                    }
                }
            }
        });
        thread.start();
        this.oferteActiuni.put(cumparaActiune, thread);
    }

    public synchronized void scoateCerere(CumparaActiune cumparaActiune) {
        Thread thread = this.oferteActiuni.get(cumparaActiune);
        if(thread == null) {
            return;
        }

        thread.interrupt();
        this.oferteActiuni.remove(cumparaActiune);
    }
}