public class Materia {
    private String IDMateria;
    private String NomeMateria;
    private Professor ProfessorResponsavel;
    private int CargaHoraria;
    private int Periodo;

    public Materia(String IDMateria, String NomeMateria, Professor ProfessorResponsavel, int CargaHoraria, int Periodo) {
        this.IDMateria = IDMateria;
        this.NomeMateria = NomeMateria;
        this.ProfessorResponsavel = ProfessorResponsavel;
        this.CargaHoraria = CargaHoraria;
        this.Periodo = Periodo;
    }

    public String getIDMateria() {
        return IDMateria;
    }

    public void setIDMateria(String IDMateria) {
        this.IDMateria = IDMateria;
    }

    public String getNomeMateria() {
        return NomeMateria;
    }

    public void setNomeMateria(String NomeMateria) {
        this.NomeMateria = NomeMateria;
    }

    public Professor getProfessorResponsavel() {
        return ProfessorResponsavel;
    }

    public void setProfessorResponsavel(Professor ProfessorResponsavel) {
        this.ProfessorResponsavel = ProfessorResponsavel;
    }

    public int getCargaHoraria() {
        return CargaHoraria;
    }

    public void setCargaHoraria(int CargaHoraria) {
        this.CargaHoraria = CargaHoraria;
    }

    public int getPeriodo() {
        return Periodo;
    }
}
