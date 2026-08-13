public class Sample02 {
    public static void main(String[] args) {
        // Cria um vetor com alguns valores inteiros.
        int[] valores = { 1, 3, 5, 7, 9 };

        // A variavel soma começa com zero porque ainda nao somamos nada.
        int soma = 0;

        // Esse e o jeito tradicional de percorrer um vetor.
        // O contador i começa em 0, porque a primeira posicao do vetor e 0.
        // valores.length informa quantos elementos existem no vetor.
        for (int i = 0; i < valores.length; i++) {
            // Pega o valor da posicao atual e adiciona na soma.
            soma += valores[i];
        }

        // Depois que o for termina, mostra o resultado final da soma.
        System.out.println("Resultado da soma: " + soma);
    }
}
