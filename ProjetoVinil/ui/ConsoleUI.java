/*
AQUI É A INTERAÇÃO COM O USUARIO.
ISOLADA E COM TRATAMENTO FICA MUITO MELHOR DE VISUALISAR.
*/

package ProjetoVinil.ui;

import java.util.Scanner;

import java.util.List;
import java.util.Optional;

import ProjetoVinil.model.EstadoDeConservacao;
import ProjetoVinil.model.Vinil;
import ProjetoVinil.service.ColecaoService;

//aqui onde conversamos com o usuario
public class ConsoleUI {
    private final Scanner scanner;
    private final ColecaoService service;

    // FUNÇÃO QUE VAI GUARDAR AS DEPENDENCIAS QUE A INTERFACE VAI USAR
    public ConsoleUI(Scanner scanner, ColecaoService service) {
        this.scanner = scanner;
        this.service = service;
    }

    // MENU DE INTERAÇÃO COM O USUARIO:
    public void iniciar() {
        int opcao;

        // do - while executa pelo menos uma vez ou até o usuario digitar 9 para sair.
        do {
            exibirMenu();
            // Metodo de ler interno para converter entradas de texto para INT
            opcao = lerInteiro("Digite sua Opção");

            // Switch de opções para o usuario
            switch (opcao) {
                case 1 -> cadastrarVinil();
                case 2 -> procurarVinil();
                case 3 -> listarVinis();
                case 9 -> System.out.println("Fim do programa.");
                default -> System.out.println("Opção invalida.");
            }
        } while (opcao != 9);

    }

    // FUNÇÂO MOSTRAR MENSAGENS DE OPÇÕES DISPONIVEIS:
    private void exibirMenu() {
        System.out.println("\n======= COLEÇÃO DE VINIS =======");
        System.out.println("1 - Cadastrar vinil");
        System.out.println("2 - Procurar vinil");
        System.out.println("3 - Mostrar todos os vinis");
        System.out.println("9 - Sair");
    }

    // Função para ler todos os dados para cadastro de VINIL.
    private void cadastrarVinil() {
        // CADA METODO VAI LER SOLICITAR E VALIDAR CADA PARTE DOS DADOS.
        String titulo = lerTexto("Digite o Titulo: ");
        String artista = lerTexto("Digite o Artista");
        int ano = lerInteiro("Digite o ano");
        EstadoDeConservacao estado = lerEstado();

        // tratando as entradas com try - catch
        try {
            service.cadastrar(titulo, artista, ano, estado);
            System.out.println("Vinil cadastrado com sucesso.");
        } catch (IllegalArgumentException erro) {
            System.out.println("Não foi possivel cadastrar: " + erro.getMessage());
        }

    }

    // FUNÇÃO PARA LER UM TITULO E EXIBIR O RESULTADO DA PROCURA.
    private void procurarVinil() {
        String titulo = lerTexto("Digite o titulo procurado: ");
        Optional<Vinil> resultado = service.procurarPorTitulo(titulo);

        // IF PRESENTE VERIFICA SE O OPTIONAL CONTEM UM VINIL.
        if (resultado.isPresent()) {
            System.out.println("\nVinil encontrado");
            // GET PEGA O OBJETO QUE ESTA DENTRO DO #OPTIONAL
            exibirVinil(resultado.get());
        } else {
            System.out.println("Titulo não encontrado. :( ");
        }
    }

    // IMPRIMIR CADA VINIL CADASTRADO
    private void listarVinis() {
        List<Vinil> vinis = service.listar();
        // IF EMPTY RETORNAR VERDADEIRO QUANDO A LISTA NAO POSSUI NENHUM ELEMENTO.
        if (vinis.isEmpty()) {
            System.out.println("Nenhum Vinil cadastrado :(");
            return;
        }

        // FOR-EACH PERCORRE CADA VINIL DA LISTA UM A UM....

        for (Vinil vinil : vinis) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            exibirVinil(vinil);
        }
    }

    // METODO PARA EXIBIR UM UNICO DISCO DE VINIL
    private void exibirVinil(Vinil vinil) {
        System.out.println("Titulo: " + vinil.titulo());
        System.out.println("Artista: " + vinil.artista());
        System.out.println("Ano: " + vinil.ano());
        System.out.println("Estado do Disco: " + vinil.estadoDoVinil());
    }

    // METODO DE SUBMENU para transformar a escolha em um subMenu
    private EstadoDeConservacao lerEstado() {
        System.out.println("Estado de conservação:");
        System.out.println("1 - Novo");
        System.out.println("2 - Exelente");
        System.out.println("3 - Usado");
        System.out.println("4 - Raro");
        System.out.println("5 - Danificado");

        while (true) {
            // QUANDO FOR VERDADE WHILE PASSA PARA PARA A FUNÇÃO QUE CONNVERTE OS DADOS EM
            // UM ENUM.
            int opcao = lerInteiro("Escolha uma opção");
            try {
                // convertendo para o estado de converservação em forma de ENUM
                return EstadoDeConservacao.aPartirDaOpcao(opcao);
            } catch (IllegalArgumentException ERRO) {
                System.out.println(ERRO.getMessage());
            }
        }
    }

    // TRATAMENTO DE ERRO: LE UM TEXTO E NÃO PERMITE QUE ELE FIQUE VAZIO;

    private String lerTexto(String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String texto = scanner.nextLine().trim();

            // se o texto nao esta vazio retorne o texto
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println(" Esse campo não pode ficar vazio.");
        }
    }

    // le um numero Interido caso o usuario digite um texto.
    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException ERRO) {
                System.out.println("Digite um numero inteiro valido");
            }
        }

    }
}
