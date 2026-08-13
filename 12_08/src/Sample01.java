
// ArrayList e uma lista que pode aumentar, diminuir e ter valores removidos.
import java.util.ArrayList;

// List e a interface usada para trabalhar com listas.
import java.util.List;

public class Sample01 {
    public static void main(String[] args) {
        // List.of() cria uma lista imutavel, ou seja, uma lista que nao pode
        // ser alterada depois de criada.
        var lista = List.of("Banana", "Pera", "Morango", "Abacate");

        // Se tirar os comentarios desta linha, vai dar erro em tempo de
        // execucao, porque a lista criada com List.of() nao aceita alteracao.
        // lista.add("Pera2");

        // O for-each passa por cada fruta da lista.
        // A variavel fruta recebe uma fruta por vez.
        for (String fruta : lista) {
            System.out.println(fruta);
        }

        // Aqui criamos uma lista mutavel, que pode ser alterada normalmente.
        var frutas = new ArrayList<String>();

        // add() adiciona novos valores na lista.
        frutas.add("Fruta do conde");
        frutas.add("Rocambole");
        frutas.add("Melão");

        // remove() remove o valor informado da lista.
        frutas.remove("Rocambole");

        // Mostra as frutas que sobraram depois da remocao.
        for (String fruta : frutas) {
            System.out.println(fruta);
        }
    }
}
