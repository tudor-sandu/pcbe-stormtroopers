package com.bursagalactica;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main{

    public static void main(String[] args) {
    Bursa bursa = new Bursa();
    List<Vanzator> vanzatori = new ArrayList<>();
    List<Cumparator> cumparatori = new ArrayList<>();
    Random random = new Random();
        String[] v = {"Bob","Alice","Doe"};
        String[] c = {"Smith","Will","Ana"};
    for(int i=0;i<3;i++){
        vanzatori.add(new Vanzator(v[i],bursa));
        cumparatori.add(new Cumparator(c[i],bursa,(1000*random.nextDouble() + 1500.0)));
    }

    for (Vanzator vanzator:vanzatori){
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try{
                    vanzator.punePeBursa(random.nextInt(10) + 1, 10*random.nextDouble() + 5.5);
                    Thread.sleep(2000);
                    System.out.print("zzzz");
                    }
                    catch(InterruptedException ie ){
                        System.out.println("InterruptedException caught");
                    }
                }
            }
        }).start();
    }

    for (Cumparator cumparator: cumparatori){
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    try{
                        System.out.print("aaaaa");
                    cumparator.adaugaCerere(random.nextInt(10) + 1, 10*random.nextDouble() + 4.0);
                    Thread.sleep(2000);
                    }
                    catch(InterruptedException ie ){
                        System.out.println("InterruptedException caught");
                    }
                }
            }
        }).start();
    }
    
    

    }



}
