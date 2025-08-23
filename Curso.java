import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int idCurso;
    private String nomeCurso;
    private List<Materia> materiasCurso;
    private List<Professor> professoresCurso;
    private Tabela gradeDeAulas; // Nova variável para a instância de Tabela

    public Curso(int idCurso, String nomeCurso) {
        this.idCurso = idCurso;
        this.nomeCurso = nomeCurso;
        this.materiasCurso = new ArrayList<>();
        this.professoresCurso = new ArrayList<>();
        this.gradeDeAulas = new Tabela(); // Instanciando a Tabela aqui // TEM QUE VER A INICIALIZACAO CONSISTENTE ENTRE TABELAS
    }

    // Métodos para gerenciar as listas de professores e matérias
    public void adicionarProfessor(Professor p) {
        this.professoresCurso.add(p);
    }
    
    public void adicionarMateria(Materia m) {
        this.materiasCurso.add(m);
    }

    // Métodos para acessar a funcionalidade da Tabela
    public void inserirAula(int dia, int horario, Materia materia) {
        this.gradeDeAulas.inserirAula(dia, horario, materia);
    }

    public void retirarAula(int dia, int horario) {
        this.gradeDeAulas.retirarAula(dia, horario);
    }

    public void mostrarGradeAtual() {
        System.out.println("\n--- Grade de Horários do Curso de " + this.nomeCurso + " ---");
        this.gradeDeAulas.mostrarGradeAtual();
    }

    public List<Materia> getMateriasCurso() {
        return materiasCurso;
    }
}