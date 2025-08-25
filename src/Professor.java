public class Professor {
    private int idProf;
    private String nome;
    private int TabelaProf[][] = new int[5][16];

    public Professor(int idProf, String nome, String PreferenciaHorario) {
        this.idProf = idProf;
        this.nome = nome;
        PreencherTabela(PreferenciaHorario);
    }

    // Getters
    public int getIdProf() {
        return idProf;
    }

    public String getNome() {
        return nome;
    }

    private void PreencherTabela(String PreferenciaH){
            int contador = 0;

        // Loop para preencher a matriz.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 16; j++) {
                // Pegando o caractere na posição 'contador'.
                char caractere = PreferenciaH.charAt(contador);
                
                // Convertendo o caractere para o seu valor numérico (ASCII) e armazenando na matriz.
                TabelaProf[i][j] = (int) caractere;
                TabelaProf[i][j] = TabelaProf[i][j] - 48;

                contador++;
            }
        }

    }

    public void ExibirTabelaPrefProf() {
       
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(TabelaProf[j][i] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "ID: " + idProf + ", Nome: " + nome;
    }
}
