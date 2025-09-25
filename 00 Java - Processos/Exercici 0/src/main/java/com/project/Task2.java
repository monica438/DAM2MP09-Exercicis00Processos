package com.project;

class Task2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Comprovant l'estat de la xarxa...");
        try{
            Thread.sleep(1000); // Simulem una tasca que triga 1 segon
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Xarxa en funcionament.");
    }
    
}
