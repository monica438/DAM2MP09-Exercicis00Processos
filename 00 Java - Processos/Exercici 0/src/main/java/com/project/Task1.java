package com.project;

class Task1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Enregistrant esdeveniments del sistema...");
        try{
            Thread.sleep(2000); // Simulem una tasca que triga 2 segons
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Esdeveniments del sistema enregistrats.");
    }
    
}
