public class Horario {
    private Materia[][] MatrizMateria;

    public Horario(Materia[][] MatrizMateria) {
        this.MatrizMateria = MatrizMateria;
    }

    public Materia[][] getMatrizMateria() {
        return MatrizMateria;
    }

    public void setMatrizMateria(Materia[][] MatrizMateria) {
        this.MatrizMateria = MatrizMateria;
    }
}