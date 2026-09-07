package com.lab.jpa.sisbiblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Ativa a configuração automática e descobre componentes neste pacote e nos subpacotes.
@SpringBootApplication
public class SisbibliotecaApplication {

    // Ponto de entrada: execute este método para iniciar o sistema.
    public static void main(String[] args) {
        SpringApplication.run(SisbibliotecaApplication.class, args);
    }
}
