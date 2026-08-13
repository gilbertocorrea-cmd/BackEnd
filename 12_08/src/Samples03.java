
// Importa o IntStream, que ajuda a trabalhar com numeros do vetor
// usando uma ideia mais funcional, sem precisar fazer um for tradicional.
import java.util.stream.IntStream;

public class Samples03 {
        public static void main(String[] args) {
                // Cria um vetor de numeros inteiros.
                // O int[] significa que o vetor guarda apenas valores inteiros.
                int[] valor = { 1, 2, 3, 4, 5, 7, 9 };

                // Converte o vetor em um IntStream.
                // sorted() organiza os valores em ordem crescente.
                // forEach() passa por cada numero do vetor e mostra ele na tela.
                IntStream.of(valor)
                                .sorted()
                                .forEach(numero -> System.out.printf("%d ", numero));

                // Pula uma linha depois de mostrar os valores.
                System.out.println();

                // max() procura o maior valor.
                // getAsInt() pega o resultado como um int.
                int maior = IntStream.of(valor).max().getAsInt();

                // min() procura o menor valor.
                int menor = IntStream.of(valor).min().getAsInt();

                // sum() soma todos os valores do vetor.
                int somaComum = IntStream.of(valor).sum();

                System.out.println("Maior valor: " + maior);
                System.out.println("Menor valor: " + menor);
                System.out.println("Soma comum: " + somaComum);

                // reduce() tambem pode ser usado para fazer a soma.
                // O 0 e o valor inicial da soma.
                // x e o acumulador e y e o numero atual do vetor.
                int somaComReduce = IntStream.of(valor)
                                .reduce(0, (x, y) -> x + y);
                System.out.println("Soma com reduce: " + somaComReduce);

                // Aqui cada numero e elevado ao quadrado antes de ser somado.
                // Exemplo: 1*1 + 2*2 + 3*3...
                int somaQuadrado = IntStream.of(valor)
                                .reduce(0, (x, y) -> x + y * y);
                System.out.println("Soma dos quadrados: " + somaQuadrado);

                // Pula uma linha antes de mostrar os numeros pares.
                System.out.println();

                // filter() filtra os valores do vetor.
                // numero % 2 calcula o resto da divisao por 2.
                // Se o resto for 0, o numero e par.
                // Depois, sorted() organiza os pares e forEach() mostra cada um.
                IntStream.of(valor)
                                .filter(numero -> numero % 2 == 0)
                                .sorted()
                                .forEach(numero -> System.out.printf("%d ", numero));
        }
}
