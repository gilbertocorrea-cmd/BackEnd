/* 
Vou criar uma uma classe apenas para serviços
Aqui eu vou lincar todas as "interfaces" e deixar a Main CLEAN
e de facil manutençao, 
Caso eu implemente uma interface grafica essa esse unico arquivo fara todo o trabalho.

Não posso esquecer de lincar todos os arquivos ja criados (EstadoDeConservacao - Vinil e Prateleiras )

*/

package ProjetoVinil.service;

import java.util.List;
import java.util.Optional;

import ProjetoVinil.model.EstadoDeConservacao;
import ProjetoVinil.model.Vinil;
import ProjetoVinil.repositorio.Prateleira;

public class ColecaoService {
    private final Prateleira prateleira;
    // FINAL impede que uma variavel Receba outra referencia depois de inicializar

    // prateleira é o repositorio da coleção de vinil

    // FUNÇÃO DA PRATELEIRA
    public ColecaoService(Prateleira prateleira) {
        this.prateleira = prateleira;
    }

    // FUNÇÃO DE CRIAR E CADASTRAR UM NOVO DISCO DE VINIL
    public void cadastrar(String titulo, String artista, int ano, EstadoDeConservacao estado) {
        Vinil vinil = new Vinil(titulo, artista, ano, estado);
        // new cria um novo disco de Vinil dentro da ARRAY VINIL

        // agora eu peço para que a função guarde o obj criado.

        prateleira.adicionar(vinil);
    }

    // FUNÇÃO PARA DELEGAR A BUSCA NA PRATELEIRA.
    public Optional<Vinil> procurarPorTitulo(String titulo) {
        // #OPTIONAL mais uma vez representa um resultado que pode ou nao existir
        return prateleira.buscarPorTitulo(titulo);

    }

    // FUNÇÃO QUE DEVOLVE TODOS OS DISCOS DE VINIL CADASTRADOS
    public List<Vinil> listar() {
        return prateleira.listarTodos();
    }

}
