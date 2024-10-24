package com.meteorological.engine;

import com.meteorological.engine.core.processor.DataProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class EngineApplication implements CommandLineRunner {

    @Autowired
    private DataProcessor dataProcessor;

    private final int THREAD_COUNT = 3;

    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String filePath = "src/csv";
        File folder = new File(filePath);

        if (folder.exists() && folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {

                long startTime = System.currentTimeMillis();


                ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);


                List<Future<Void>> futures = new ArrayList<>();

                for (File file : listOfFiles) {
                    if (file.isFile()) {

                        Callable<Void> task = () -> {
                            System.out.println("Processando e inserindo no banco: " + file.getName());
                            dataProcessor.loadToDatabase(file.getAbsolutePath());
                            return null;
                        };

                        Future<Void> future = executorService.submit(task);
                        futures.add(future);
                    }
                }

                for (Future<Void> future : futures) {
                    try {
                        future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.err.println("Erro ao processar o arquivo: " + e.getMessage());
                    }
                }

                executorService.shutdown();

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;

                System.out.println("O processamento paralelizado levou: " + duration + " milissegundos");
            } else {
                System.out.println("A pasta está vazia ou não pôde ser lida.");
            }
        } else {
            System.out.println("A pasta especificada não existe ou não é um diretório.");
        }
    }
}
