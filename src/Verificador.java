import java.util.*;

/**
 * Classe singleton responsável por realizar verificações sobre
 * listas de Professores, Materias e Cursos.
 */
public class Verificador {
    private static Verificador instance;

    private List<Professor> professores;
    private List<Materia> materias;
    private List<Curso> cursos;

    private Verificador() {
        // construtor privado para singleton
    }

    public static synchronized Verificador getInstance() {
        if (instance == null) {
            instance = new Verificador();
        }
        return instance;
    }

    /**
     * Inicializa ou atualiza as listas que serão checadas.
     */
    public void setDados(List<Professor> professores, List<Materia> materias, List<Curso> cursos) {
        this.professores = professores;
        this.materias = materias;
        this.cursos = cursos;
    }

    /**
     * Verifica se não existem IDs duplicados entre os professores.
     * @return lista de mensagens de erro (vazia se tudo OK)
     */
    public List<String> verificarIdsProfessores() {
        List<String> erros = new ArrayList<>();
        if (professores == null) {
            erros.add("Lista de professores não foi inicializada");
            return erros;
        }
        Set<Integer> vistos = new HashSet<>();
        for (Professor p : professores) {
            if (vistos.contains(p.getIDProf())) {
                erros.add("ID de professor duplicado: " + p.getIDProf());
            } else {
                vistos.add(p.getIDProf());
            }
        }
        return erros;
    }

    /**
     * Verifica se não existem IDs duplicados entre as materias.
     */
    public List<String> verificarIdsMaterias() {
        List<String> erros = new ArrayList<>();
        if (materias == null) {
            erros.add("Lista de materias não foi inicializada");
            return erros;
        }
        Set<String> vistos = new HashSet<>();
        for (Materia m : materias) {
            if (vistos.contains(m.getIDMateria())) {
                erros.add("ID de materia duplicado: " + m.getIDMateria());
            } else {
                vistos.add(m.getIDMateria());
            }
        }
        return erros;
    }

    /**
     * Verifica se não existem IDs duplicados entre os cursos.
     */
    public List<String> verificarIdsCursos() {
        List<String> erros = new ArrayList<>();
        if (cursos == null) {
            erros.add("Lista de cursos não foi inicializada");
            return erros;
        }
        Set<Integer> vistos = new HashSet<>();
        for (Curso c : cursos) {
            if (vistos.contains(c.getIDCurso())) {
                erros.add("ID de curso duplicado: " + c.getIDCurso());
            } else {
                vistos.add(c.getIDCurso());
            }
        }
        return erros;
    }

    /**
     * Verifica se todas as materias atribuídas aos cursos realmente existem
     */
    public List<String> verificarMateriasEmCursos() {
        List<String> erros = new ArrayList<>();
        if (cursos == null || materias == null) {
            erros.add("Listas de cursos ou materias não foram inicializadas");
            return erros;
        }
        Set<String> ids = new HashSet<>();
        for (Materia m : materias) {
            ids.add(m.getIDMateria());
        }
        for (Curso c : cursos) {
            Materia[] mcs = c.getMateriasCurso();
            if (mcs == null) continue;
            for (Materia m : mcs) {
                if (m != null && !ids.contains(m.getIDMateria())) {
                    erros.add("Materia '" + m.getIDMateria() + "' do curso " + c.getIDCurso() + " não existe na lista global");
                }
            }
        }
        return erros;
    }

    /**
     * Verifica se as preferências dos professores têm dimensão 5x16.
     */
    public List<String> verificarDimensoesPreferencias() {
        List<String> erros = new ArrayList<>();
        if (professores == null) {
            erros.add("Lista de professores não foi inicializada");
            return erros;
        }
        for (Professor p : professores) {
            int[][] pref = p.getPrefProf();
            if (pref == null || pref.length != 5) {
                erros.add("Preferências inválidas para professor " + p.getIDProf());
                continue;
            }
            for (int i = 0; i < pref.length; i++) {
                if (pref[i] == null || pref[i].length != 16) {
                    erros.add("Preferências inválidas para professor " + p.getIDProf() + " na linha " + i);
                }
            }
        }
        return erros;
    }

    /**
     * Examina todos os horários de todos os cursos e garante que nenhum professor
     * esteja escalado para duas matérias na mesma célula (mesma linha/coluna) em
     * horários diferentes. Isso detecta casos onde um professor já tem aula no
     * slot [i][j] de um horário e também aparece em outro horário qualquer no
     * mesmo slot.
     *
     * @return lista de mensagens de conflito encontradas (vazia se não houver)
     */
    public List<String> verificarConflitosProfessores() {
    List<String> erros = new ArrayList<>();
    Set<String> agenda = new HashSet<>();

    if (cursos == null) {
        erros.add("Lista de cursos não foi inicializada");
        return erros;
    }

    for (Curso c : cursos) {
        Horario[] horarios = c.getConjHorarios();
        if (horarios == null) continue;

        for (int hIndex = 0; hIndex < horarios.length; hIndex++) {
            Horario h = horarios[hIndex];
            if (h == null) continue;

            Materia[][] matriz = h.getMatrizMateria();
            if (matriz == null) continue;

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {

                    Materia m = matriz[i][j];
                    if (m == null) continue;

                    Professor p = m.getProfessorResponsavel();
                    if (p == null) continue;

                    int profId = p.getIDProf();

                    String chave = profId + "-" + i + "-" + j;

                    if (!agenda.add(chave)) {
                        erros.add("Conflito: professor " + profId +
                                " está em dois cursos no mesmo horário (linha "
                                + i + ", coluna " + j + ")");
                    }
                }
            }
        }
    }

    return erros;
}

    /**
     * Executa todas as verificações e retorna a lista completa de mensagens de erro.
     */
    public List<String> validarTudo() {
        List<String> todos = new ArrayList<>();
        todos.addAll(verificarIdsProfessores());
        todos.addAll(verificarIdsMaterias());
        todos.addAll(verificarIdsCursos());
        todos.addAll(verificarMateriasEmCursos());
        todos.addAll(verificarDimensoesPreferencias());
        todos.addAll(verificarConflitosProfessores());
        return todos;
    }
}
