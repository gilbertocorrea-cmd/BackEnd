package com.lab.jpa.sisbiblioteca.config;

import com.lab.jpa.sisbiblioteca.model.Autor;
import com.lab.jpa.sisbiblioteca.model.Livro;
import com.lab.jpa.sisbiblioteca.repository.AutorRepository;
import com.lab.jpa.sisbiblioteca.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Scanner;

@Component // O Spring cria e administra esta classe automaticamente.
public class DataInitializer implements CommandLineRunner {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    // Injeção pelo construtor: o Spring fornece os repositórios prontos para uso.
    public DataInitializer(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // CommandLineRunner executa este método após o contexto Spring iniciar.
        var scanner = new Scanner(System.in);
        var continuar = true;

        System.out.println("==========================================");
        System.out.println("   SISTEMA DE GESTÃO DE BIBLIOTECA JPA    ");
        System.out.println("==========================================");

        while (continuar) {
            System.out.println("\nMENU DE OPÇÕES:");
            System.out.println("1 - Cadastrar Autor");
            System.out.println("2 - Listar Autores");
            System.out.println("3 - Cadastrar Livro");
            System.out.println("4 - Listar Livros");
            System.out.println("5 - Buscar livros pelo título");
            System.out.println("6 - Listar livros por autor");
            System.out.println("7 - Atualizar livro");
            System.out.println("8 - Excluir livro");
            System.out.println("9 - Estatísticas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // Encerra o menu quando não há mais entrada disponível.
            // Evita tentar ler uma linha após o fim da entrada.
            if (!scanner.hasNextLine()) {
                break;
            }
            var opcao = scanner.nextLine();

            // O switch retorna um boolean; yield fornece o resultado de cada bloco.
            continuar = switch (opcao) {
                case "1" -> {
                    cadastrarAutor(scanner);
                    yield true;
                }
                case "2" -> {
                    listarAutores();
                    yield true;
                }
                case "3" -> {
                    cadastrarLivro(scanner);
                    yield true;
                }
                case "4" -> {
                    listarLivros();
                    yield true;
                }
                case "5" -> {
                    buscarLivrosPorTitulo(scanner);
                    yield true;
                }
                case "6" -> {
                    listarLivrosPorAutor(scanner);
                    yield true;
                }
                case "7" -> {
                    atualizarLivro(scanner);
                    yield true;
                }
                case "8" -> {
                    excluirLivro(scanner);
                    yield true;
                }
                case "9" -> {
                    mostrarEstatisticas();
                    yield true;
                }
                case "0" -> {
                    System.out.println("Encerrando aplicação...");
                    yield false;
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                    yield true;
                }
            };
        }
        // A opção sair encerra o menu. O servidor web pode continuar ativo
        // para acessar o H2 Console; use Ctrl+C no terminal para parar o processo.
        System.out.println("Aplicação finalizada.");
    }

    private void cadastrarAutor(Scanner scanner) {
        System.out.print("Digite o nome do autor: ");
        var nome = scanner.nextLine();

        // isBlank também rejeita uma entrada contendo somente espaços.
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            return;
        }
        var autor = new Autor(nome);
        autorRepository.save(autor); // O JPA insere o autor e preenche seu ID.
        System.out.println(">>> Autor '" + autor.getNome() + "' cadastrado com ID: " + autor.getId());
    }

    private void listarAutores() {
        var autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE AUTORES ---");
        // %d imprime um número inteiro, %s um texto e %n uma quebra de linha.
        autores.forEach(a -> System.out.printf("ID: %d | Nome: %s%n", a.getId(), a.getNome()));
        System.out.println("------------------------");
    }

    private void cadastrarLivro(Scanner scanner) {
        listarAutores();
        System.out.print("Informe o ID do autor do livro: ");
        var idStr = scanner.nextLine();

        try {
            var autorId = Long.parseLong(idStr);
            // Optional representa um resultado que pode não existir no banco.
            Optional<Autor> autorOpt = autorRepository.findById(autorId);
            if (autorOpt.isEmpty()) {
                System.out.println("Autor não encontrado com o ID informado!");
                return;
            }
            System.out.print("Digite o título do livro: ");
            var titulo = scanner.nextLine();
            System.out.print("Digite o ano de publicação: ");
            var ano = Integer.parseInt(scanner.nextLine());

            // Associamos o livro a um autor existente antes de salvar.
            var livro = new Livro(titulo, ano, autorOpt.get());
            livroRepository.save(livro);
            System.out.println(">>> Livro '" + livro.getTitulo() + "' cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            // Trata ID ou ano que não puder ser convertido para um número inteiro.
            System.out.println("Valor numérico inválido informado.");
        }
    }

    private void listarLivros() {
        exibirLivros(livroRepository.findAll());
    }

    // Reutiliza a mesma apresentação nas listagens e nas buscas.
    private void exibirLivros(List<Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE LIVROS ---");
        livros.forEach(l -> System.out.printf("ID: %d | Título: %s | Ano: %d | Autor: %s%n",
                l.getId(), l.getTitulo(), l.getAnoPublicacao(), l.getAutor().getNome()));
        System.out.println("-----------------------");
    }

    private void buscarLivrosPorTitulo(Scanner scanner) {
        System.out.print("Digite parte do título: ");
        var titulo = scanner.nextLine().trim();
        if (titulo.isBlank()) {
            System.out.println("Informe um título para buscar.");
            return;
        }
        // Containing busca um trecho; IgnoreCase ignora maiúsculas/minúsculas.
        exibirLivros(livroRepository.findByTituloContainingIgnoreCase(titulo));
    }

    private void listarLivrosPorAutor(Scanner scanner) {
        listarAutores();
        System.out.print("Informe o ID do autor: ");
        try {
            var autorId = Long.parseLong(scanner.nextLine());
            if (!autorRepository.existsById(autorId)) {
                System.out.println("Autor não encontrado!");
                return;
            }
            exibirLivros(livroRepository.findByAutorId(autorId));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Digite um número inteiro.");
        }
    }

    private void atualizarLivro(Scanner scanner) {
        System.out.print("Informe o ID do livro a atualizar: ");
        try {
            var livroOpt = livroRepository.findById(Long.parseLong(scanner.nextLine()));
            if (livroOpt.isEmpty()) {
                System.out.println("Livro não encontrado!");
                return;
            }
            var livro = livroOpt.get();
            System.out.print("Novo título (Enter mantém o atual): ");
            var titulo = scanner.nextLine().trim();
            if (titulo.length() > 150) {
                System.out.println("O título deve ter no máximo 150 caracteres.");
                return;
            }
            System.out.print("Novo ano (Enter mantém o atual): ");
            var anoTexto = scanner.nextLine().trim();
            var ano = anoTexto.isEmpty() ? livro.getAnoPublicacao() : Integer.valueOf(anoTexto);
            listarAutores();
            System.out.print("Novo ID do autor (Enter mantém o atual): ");
            var autorTexto = scanner.nextLine().trim();
            var autor = livro.getAutor();
            if (!autorTexto.isEmpty()) {
                var autorOpt = autorRepository.findById(Long.parseLong(autorTexto));
                if (autorOpt.isEmpty()) {
                    System.out.println("Autor não encontrado! Alteração cancelada.");
                    return;
                }
                autor = autorOpt.get();
            }
            // Só altera após validar todas as entradas, evitando atualização parcial.
            if (!titulo.isEmpty()) {
                livro.setTitulo(titulo);
            }
            livro.setAnoPublicacao(ano);
            livro.setAutor(autor);
            // Com um ID existente, save atualiza o registro em vez de cadastrar outro.
            livroRepository.save(livro);
            System.out.println("Livro atualizado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Valor numérico inválido! Alteração cancelada.");
        }
    }

    private void excluirLivro(Scanner scanner) {
        System.out.print("Informe o ID do livro a excluir: ");
        try {
            var livroOpt = livroRepository.findById(Long.parseLong(scanner.nextLine()));
            if (livroOpt.isEmpty()) {
                System.out.println("Livro não encontrado!");
                return;
            }
            System.out.printf("Excluir '%s'? (s/n): ", livroOpt.get().getTitulo());
            if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
                System.out.println("Exclusão cancelada.");
                return;
            }
            // Exclui somente o livro. O autor continua disponível para outros livros.
            livroRepository.deleteById(livroOpt.get().getId());
            System.out.println("Livro excluído com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Digite um número inteiro.");
        }
    }

    private void mostrarEstatisticas() {
        var livros = livroRepository.findAll();
        System.out.println("\n--- ESTATÍSTICAS ---");
        // Uma Stream percorre dados em memória sem modificar a lista original.
        System.out.println("Total de livros: " + livros.stream().count());
        System.out.println("Total de autores: " + autorRepository.count());
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado para calcular estatísticas.");
            return;
        }
        // filter ignora anos ausentes; mapToInt permite resumir valores numéricos.
        var anos = livros.stream()
                .filter(l -> l.getAnoPublicacao() != null)
                .mapToInt(Livro::getAnoPublicacao)
                .summaryStatistics();
        if (anos.getCount() > 0) {
            System.out.println("Ano mais antigo: " + anos.getMin());
            System.out.println("Ano mais recente: " + anos.getMax());
            System.out.printf("Média dos anos de publicação: %.2f%n", anos.getAverage());
        } else {
            System.out.println("Nenhum ano de publicação informado.");
        }
        // Agrupa pelo ID (autores podem ter nomes iguais) e conta os livros do grupo.
        Map<Long, Long> porAutor = livros.stream().collect(Collectors.groupingBy(
                l -> l.getAutor().getId(), Collectors.counting()));
        System.out.println("Livros por autor:");
        autorRepository.findAll().forEach(a -> System.out.printf("ID: %d | %s: %d livro(s)%n",
                a.getId(), a.getNome(), porAutor.getOrDefault(a.getId(), 0L)));
    }
}
