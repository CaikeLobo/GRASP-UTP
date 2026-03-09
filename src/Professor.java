public class Professor {
    private int IDProf;
    private String Nome;
    private int[][] PrefProf;

    public Professor(int IDProf, String Nome, int[][] PrefProf) {
        this.IDProf = IDProf;
        this.Nome = Nome;
        this.PrefProf = PrefProf;
    }

    public int getIDProf() {
        return IDProf;
    }

    public void setIDProf(int IDProf) {
        this.IDProf = IDProf;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int[][] getPrefProf() {
        return PrefProf;
    }

    public void setPrefProf(int[][] PrefProf) {
        this.PrefProf = PrefProf;
    }
}
