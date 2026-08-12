package ProjetoVinil.model;

public enum EstadoDeConservacao { // ENUM PARA DEFINIR UM CONJUNTO FIXO DE OPÇÕES NUMERICAS.
    NOVO,
    EXCELENTE,
    USADO,
    RARO,
    DANIFICADO;

    /*
     * O ENUM É USADO QUANDO QUEREMOS UM CONJUNTO FIXO DE VALORES
     * ASSIM NÃO É POSSIVEL INSERIR VALORES DE OUTRO TIPO A NAO SER NUMERICO
     */
    public static EstadoDeConservacao aPartirDaOpcao(int opcao) {
        // O SWITCH É USADO PARA RETORNAR O VALOR DO ENUM CORRESPONDENTE A OPÇÃO
        // NUMERICA INFORMADA PELO USUARIO.
        return switch (opcao) {
            case 1 -> NOVO;
            case 2 -> EXCELENTE;
            case 3 -> USADO;
            case 4 -> RARO;
            case 5 -> DANIFICADO;
            /*
             * USAREI THROW EM DEFAULT PARA CASO O USUARIO DIGITE UM
             * VALOR INVALIDO
             */
            default -> throw new IllegalArgumentException("Opção de conservação inválida:");
        };
    }
}