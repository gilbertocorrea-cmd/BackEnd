# Sisbiblioteca — Avaliação P1

Nome completo: Gilberto A. Corrêa

Sistema de gestão de biblioteca com cadastro de autores e livros, consultas, atualização, exclusão e estatísticas. O projeto organiza as entidades em `model`, os repositórios em `repository` e o menu em `config/DataInitializer.java`. A classe principal fica no pacote `com.lab.jpa.sisbiblioteca`.

## Tecnologias e execução

O projeto está configurado para Java 25, Spring Boot 4.1.1, Maven Wrapper, Spring Data JPA, Lombok e H2 em memória. Use um JDK 25 ao compilar e executar.


## Cinco modificações

| Opção | Modificação | Funcionamento |
| --- | --- | --- |
| 5 | Busca de livros pelo título | Solicita parte do título e usa `findByTituloContainingIgnoreCase`, sem diferenciar maiúsculas de minúsculas. Rejeita pesquisa em branco. |
| 6 | Listagem de livros por autor | Solicita um ID, verifica a existência do autor e usa `findByAutorId`. Informa quando a lista está vazia. |
| 7 | Atualização de livro | Localiza pelo ID e permite alterar título, ano e autor. Enter mantém o valor atual. Valida os dados antes de salvar; um autor inexistente ou número inválido cancela a alteração. |
| 8 | Exclusão de livro | Localiza pelo ID e pede confirmação com `s`. Remove somente o livro, preservando o autor. |
| 9 | Estatísticas usando Streams | Mostra total de livros e autores, menor e maior ano, média dos anos e quantidade de livros por autor, incluindo autores sem livros. Trata acervo vazio e anos ausentes. |

As funções do menu ficam em `DataInitializer`. `exibirLivros` centraliza a impressão usada pela listagem e pelas buscas. Os repositórios fornecem as operações de persistência sem SQL manual.

Nas estatísticas, `stream().count()` conta os livros; `filter` remove anos ausentes do cálculo; `mapToInt` e `summaryStatistics` calculam mínimo, máximo e média. `Collectors.groupingBy` agrupa por ID do autor e `Collectors.counting` conta os livros de cada grupo. Agrupar pelo ID evita misturar autores com o mesmo nome. O total de autores vem de `autorRepository.count()`.
