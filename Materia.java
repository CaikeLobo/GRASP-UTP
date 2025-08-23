public class Materia {
    private int idMat;
    private String nomeMat;
    private Professor professor;
    private int cargaHoraria;

    public Materia(int idMat, String nomeMat, Professor professor, int cargaHoraria) {
        this.idMat = idMat;
        this.nomeMat = nomeMat;
        this.professor = professor;
        this.cargaHoraria = cargaHoraria;
    }

    // Getters
    public int getIdMat() {
        return idMat;
    }

    public String getNomeMat() {
        return nomeMat;
    }

    public Professor getProfessor() {
        return professor;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }
    
    @Override
    public String toString() {
        return "ID: " + idMat + ", Nome: " + nomeMat + ", Professor: " + professor.getNome();
    }
}