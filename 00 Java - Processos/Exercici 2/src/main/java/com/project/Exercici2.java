package com.project;

import java.util.concurrent.CompletableFuture;

public class Exercici2 {
    public static void main(String[] args) {
        System.out.println("Iniciant processament de sol·licitud...");

        CompletableFuture<Void> processament = CompletableFuture
            // Primera etapa: Validar dades (simula una entrada vàlida)
            .supplyAsync(() -> {
                System.out.println("Validant dades de la sol·licitud...");
                simulateDelay(1000);
                String dades = "Usuari123";
                System.out.println("Dades validades: " + dades);
                return dades;
            })

            // Segona etapa: Processar les dades (simula càlculs o accés a BBDD)
            .thenApply(dades -> {
                System.out.println("Processant dades per a usuari: " + dades);
                simulateDelay(1500);
                String resultat = "Saldo disponible: 950.75 euros";
                return resultat;
            })

            // Tercera etapa: Mostrar el resultat (resposta a l’usuari)
            .thenAccept(resultat -> {
                System.out.println("Resposta enviada a l’usuari:");
                System.out.println(">>> " + resultat);
            });

        // Esperar que totes les operacions asíncrones acabin
        processament.join();

        System.out.println("Processament complet.");
    }

    // Mètode utilitari per simular retard
    private static void simulateDelay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
