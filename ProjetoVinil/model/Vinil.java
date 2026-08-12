package ProjetoVinil.model;

/* Vou usar RECORD porque ele cria automaticamente metodos de acesso
" Titulo, Artista, Ano, EstadoDeConservacao, ano.. etc."
essa função é nativa do JAVA e esta na documentação da linguagem  */

public record Vinil(
        String titulo,
        String artista,
        int ano,
        EstadoDeConservacao estadoDoVinil)
/*
 * AGORA CRIAREI UM CONSTRUTOR COMPACTO QUE VALIDA AS ENTRADAS DO USUARIO
 * REMOVENDO ESPAÇOS EM BRANCO COM:
 * " IS.BLANK()"
 * E USAREI THROW PARA APRENSETAR UMA MENSAGEM DE ERRO CASO O USUARIO ENTRE UM
 * DADO INVALIDO
 * OU NAO ENTRE COM NENHUM DADO
 * 
 */
{
    public Vinil {
        // aqui eu valido o #TITULO E se a uma entrada ou se o é apenas um espaço com
        // ISBLANK " /t"
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Titulo do vinil VAZIO");
            // aqui eu valido o #ARTISTA E se a uma entrada ou se o é apenas um espaço com
            // ISBLANK " /t"
        }
        if (artista == null || artista.isBlank()) {
            throw new IllegalArgumentException("Campo de artista VAZIO");
            // Valido o #ANO do vinil, ele nao pode ser menor que 0 MAS NAO USO ISBLANK
            // VALIDA APENAS STRINGS
        }
        if (ano <= 0) {
            throw new IllegalArgumentException("Ano do vinil INVALIDO");
            // VALIDO O ESTADO DO VINIL POIS É UMA FUNÇÃO OBRIGATORIO DO SISTEMA.
        }
        if (estadoDoVinil == null) {
            throw new IllegalArgumentException("É obrigatorio informar o estado do vinil");
        }

        /*
         * TRIM É UMA FUNÇÃO DO JAVA QUE ESTA NA DOCUMENTAÇÃO TAMBEM.
         * ELE REMOVE ESPAÇOS EM BRANCO NO INICIO E NO FIM DAS ENTRADAS(TEXTO/STRING)
         */
        titulo = titulo.trim();
        artista = artista.trim();

    }
}