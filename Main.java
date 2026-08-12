package ProjetoVinil;

import java.util.Scanner;

import ProjetoVinil.repositorio.Prateleira;
import ProjetoVinil.service.ColecaoService;
import ProjetoVinil.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        // try fecha o scanner no final.
        try (Scanner scanner = new Scanner(System.in)) {
            // Criando o obj para armazenar na preteleira;
            Prateleira prateleira = new Prateleira();

            // Entrega a prateleira usando composição entre obj
            ColecaoService service = new ColecaoService(prateleira);

            // Entrega o Scanner e o serviço para a UI do console
            ConsoleUI consoleUI = new ConsoleUI(scanner, service);

            // inicia o menu principal
            consoleUI.iniciar();
        }
    }

}
