public class Professor {
    private int idProf;
    private String nome;
    private int TabelaProf;

    public Professor(int idProf, String nome) {
        this.idProf = idProf;
        this.nome = nome;
        
    }

    // Getters
    public int getIdProf() {
        return idProf;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "ID: " + idProf + ", Nome: " + nome;
    }
}
