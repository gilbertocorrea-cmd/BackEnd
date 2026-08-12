package ProjetoVinil.repositorio;

import ProjetoVinil.model.Vinil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* 
Estou criando uma classe REPOSITORIO " PRATELEIRA" apenar para guardar e consultar os vinil
Com essa fatoração eu e implementação de novas funçoes eu consigo tratar melhor o sistema,
Alem de ser melhor para futuras feetuers 

AQUI SERVIRA APENAS PARA GUARDAR DADOS, CASO EU QUERIA IMPLEMETAR UMA INTERFACE GRAFICA, FICARA MELHOR.

*/

// utilizarei UMA #ARRAY LIST 
// utilizarei tambem uma função de java  #FINAL ( ela impede que uma variavel receba outra referencia depois de inicializada.)

public class Prateleira {
    private final List<Vinil> discos = new ArrayList<>(); // NAO POSSO ESQUECER DE IMPORTAR #VINIL DE #MODEL
    // Vinil dentro de < > é o disco que sera salvo dentro da prateleira de discos
    // de Vinil

    // função de adicionar discos de Vinil na prateleira:
    public void adicionar(Vinil vinil) {
        discos.add(vinil); // aqui eu peço para adicionar um vinil, pois foi esse o parametro que eu criei
                          // para a funçao ( Vinil vinil).
    }

    /*
     * Usarei uma nova função de JAVA a função #COPYOF
     * isso impede que outro dado subistitua o vinil ja salvo dentro da #ARRAYLIST
     * 
     * Ele criara apenas uma lista para leitura caso o usuario deseje ver TODOS os
     * discos ou algum disco.
     * isso torna o sistema mais seguro.
     * 
     * talvez isso ajude quando a um banco de dados implementado.
     * 
     */

    // FUNÇÃO DE LISTAR TODOS OS DISCOS COM #COPYOF e #RETURN
    public List<Vinil> listarTodos() {
        return List.copyOf(discos); // COPYOF protege os dados nao deixar que eles sejam alteral externamente.
                                    // (APPSEC)
    }

    /*
     * Farei agora uma funçao de BUSCA.
     * Vou utilizar algumas funções do JAVA:
     * #STREAM() - ELA TRANFORMA A ARRAY EM UMA ESPECIE DE BANCO DE DADOS ONDE VC
     * CONSEGUE DAR UM SELEC NO DADO DESEJADO.
     * #FILTER() - ELE FILTRA SOMENTE OS ELEMENTOS QUE FOREM INSERIDOS PELO USUARIO
     * (UMA ESPECIE DE SELECT DO MYSQL)
     * #FINDFIRST() - ELE RETORNARA O PRIMEIRO ITEM ENCONTRADO DE ACORDO COM OS
     * DADOS INSERIDOS PELO USUARIO.
     * #OPTIONAL ELA REPRESENTA UM VALOR QUE PODE OU NAO EXISTIR
     */

    // alem das funçoes de manipulaçao de LISTA utilizarei RETURN e TRIM
    // return para quando encontrar o disco e TRIM para limpar espaços em branco no
    // inicio e no final da entrada do usuario.
    // equalsIgnoreCase - para comparar texto ignorando a forma que eles estao
    // salvos ( maiusculo ou minusculo)

    // FUNÇÃO de BUSCAR por TITULO:

    public Optional<Vinil> buscarPorTitulo(String titulo) {
        return discos.stream()
                .filter(vinil -> vinil.titulo().equalsIgnoreCase(titulo.trim()))
                .findFirst();
        // tradução: procure em vinil com esse titulo ignorando maiuscula ou minuscula e
        // espaços no inicio e no final. ACHOU? retorne o resultado
    }
}
