# Novas funções e recursos usados

## Texto

- `isBlank()`: verifica se um texto está vazio ou contém apenas espaços.
- `isEmpty()`: verifica se um texto ou uma lista não possui elementos.
- `trim()`: remove espaços no início e no final do texto.
- `equalsIgnoreCase()`: compara textos ignorando maiúsculas e minúsculas.

## Listas

- `add()`: adiciona um item à lista.
- `List.copyOf()`: cria uma cópia da lista que não pode ser alterada diretamente.
- `stream()`: transforma a lista em um fluxo para pesquisas e filtros.
- `filter()`: mantém somente elementos que atendem a uma condição.
- `findFirst()`: retorna o primeiro item encontrado.

## Optional

- `Optional`: representa um valor que pode existir ou não.
- `isPresent()`: verifica se existe um valor dentro do `Optional`.
- `get()`: obtém o valor que está dentro do `Optional`.

## Entrada e conversão

- `nextLine()`: lê uma linha digitada pelo usuário.
- `Integer.parseInt()`: converte um texto para número inteiro.
- `getMessage()`: obtém a mensagem armazenada em um erro.

## Estruturas novas

- `record`: cria uma classe simples para armazenar dados e gera métodos como `titulo()` automaticamente.
- `enum`: representa um conjunto fixo de opções.
- `switch` com `->`: forma mais nova de escrever um `switch`.
- `try/catch`: tenta executar um código e captura erros.
- `try-with-resources`: fecha automaticamente recursos, como o `Scanner`.
- `final`: impede que uma variável receba outra referência depois de inicializada.
- `return`: encerra o método e pode devolver um valor.
- `throw`: cria e lança um erro manualmente.
- `||`: significa “ou” nas condições.

## Exemplos

### `isBlank()`

```java
"   ".isBlank(); // true
```

### `trim()`

```java
"  Vinil  ".trim(); // "Vinil"
```

### `equalsIgnoreCase()`

```java
"Abbey Road".equalsIgnoreCase("abbey road"); // true
```

### `Optional`

```java
Optional<Vinil> resultado;

if (resultado.isPresent()) {
    Vinil vinil = resultado.get();
}
```

### `try/catch`

```java
try {
    service.cadastrar(...);
} catch (IllegalArgumentException erro) {
    System.out.println(erro.getMessage());
}
```

### `throw`

```java
throw new IllegalArgumentException("O título não pode ficar vazio.");
```

### `||`

```java
if (titulo == null || titulo.isBlank()) {
}
```

Nesse exemplo, a condição será verdadeira se o título for `null` ou estiver vazio.
