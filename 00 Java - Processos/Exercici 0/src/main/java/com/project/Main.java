package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        // Crear un executor amb un pool de 2 fils
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Enviem les tasques a l'executor
        executor.execute(new Task1());
        executor.execute(new Task2());

        // Tanquem l'executor
        executor.shutdown();

    }
}
