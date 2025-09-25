package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercici1 {
    public static void main(String[] args) {

        // Creem un ConcurrentHashMap com a estructura compartida de dades concurrents
        ConcurrentHashMap<String, Double> infoBancaria = new ConcurrentHashMap<>();
        // Creem un executor amb un pool de 3 fils
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Creem les tasques i les enviem a l'executor

        Runnable t1 = () -> {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            infoBancaria.put("operacio1", 100.0); // Simulem la recepció d'una operació bancària
            System.out.println("Operació bancària de 100 euros rebuda.");
        };

        Runnable t2 = () -> {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            while(!infoBancaria.containsKey("operacio1")) {
                try {
                    Thread.sleep(100); // Esperem fins que hi hagi dades disponibles
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            infoBancaria.computeIfPresent("operacio1", (k, v) -> v * 1.05); // Simulem el càlcul d'interessos
            System.out.println("Interessos bancaris calculats.");
        };

        Callable<Double> t3 = () -> {
            try {
                // Espera fins que el càlcul d'interessos s’hagi fet
                while (!infoBancaria.containsKey("operacio1")) {
                    Thread.sleep(1000);
                }

                Thread.sleep(500); // Simula processament extra
                double operacio1 = infoBancaria.get("operacio1");
                System.out.println("Saldo final obtingut: " + operacio1 + " euros");
                return operacio1;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        };

        executor.execute(t1);
        executor.execute(t2);
        Future<Double> resultatFuture = executor.submit(t3);
        // Esperem el resultat de la tasca t3
        try {
            Double resultat = resultatFuture.get();
            System.out.println("El resultat final de l'operació és: " + resultat + " euros");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tanquem l'executor
        executor.shutdown();
    }
}
