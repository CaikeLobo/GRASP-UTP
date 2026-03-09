import java.util.*;

public class Master {
    private static Master instance;

    private GerenteTxt gerente;
    private List<Professor> professores;
    private List<Materia> materias;
    private List<Curso> cursos;

    private Master() {
        gerente = new GerenteTxt();
        professores = gerente.lerProfessores("Professores.txt");
        materias = gerente.lerMaterias("Materias.txt", professores);
        cursos = gerente.lerCursos("Cursos.txt", materias);
    }

    /**
     * Retorna a instância única de Master (singleton).
     */
    public static synchronized Master getInstance() {
        if (instance == null) {
            instance = new Master();
        }
        return instance;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    // busca um curso pelo seu ID
    private Curso findCurso(int idCurso) {
        for (Curso c : cursos) {
            if (c.getIDCurso() == idCurso) {
                return c;
            }
        }
        return null;
    }

    // busca uma matéria pelo seu ID
    private Materia findMateria(String idMateria) {
        for (Materia m : materias) {
            if (m.getIDMateria().equals(idMateria)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Insere uma matéria na grade de horários de um curso.
     *
     * @param idCurso      identificador do curso
     * @param horarioIndex índice dentro do array de horários desse curso (0‑based)
     * @param linha        linha da matriz (0 a 4)
     * @param coluna       coluna da matriz (0 a 15)
     * @param idMateria    ID da matéria a ser colocada
     * @return true se colocou com sucesso, false caso contrário
     */
    public boolean adicionarMateriaEmHorario(int idCurso, int horarioIndex, int linha, int coluna, String idMateria) {
        Curso curso = findCurso(idCurso);
        if (curso == null) {
            System.err.println("Curso não encontrado: " + idCurso);
            return false;
        }

        Horario[] conj = curso.getConjHorarios();
        if (horarioIndex < 0 || horarioIndex >= conj.length) {
            System.err.println("Índice de horário inválido: " + horarioIndex);
            return false;
        }

        if (linha < 0 || linha >= 5 || coluna < 0 || coluna >= 16) {
            System.err.println("Posição fora dos limites: (" + linha + "," + coluna + ")");
            return false;
        }

        Materia mat = findMateria(idMateria);
        if (mat == null) {
            System.err.println("Matéria não encontrada: " + idMateria);
            return false;
        }

        Horario h = conj[horarioIndex];
        Materia[][] matriz = h.getMatrizMateria();
        
        // verifica se a posição já está ocupada
        if (matriz[linha][coluna] != null) {
            System.err.println("Posição já ocupada por: " + matriz[linha][coluna].getIDMateria());
            return false;
        }
        
        matriz[linha][coluna] = mat;
        return true;
    }

    /**
     * Imprime de forma legível todos os horários de todos os cursos.
     */
    public void imprimirTodosHorarios() {
        for (Curso curso : cursos) {
            System.out.println("Curso " + curso.getIDCurso() + " - " + curso.getNomeCurso());
            Horario[] conj = curso.getConjHorarios();
            for (int hIndex = 0; hIndex < conj.length; hIndex++) {
                System.out.println("  Horário #" + hIndex + ":");
                Materia[][] mat = conj[hIndex].getMatrizMateria();
                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[i].length; j++) {
                        String cell = mat[i][j] != null ? mat[i][j].getIDMateria() : "----";
                        System.out.print(cell + "\t");
                    }
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    /**
     * Imprime de forma legível as preferências de horário de todos os professores.
     */
    public void imprimirPreferenciasProfessores() {
        for (Professor prof : professores) {
            System.out.println("Professor " + prof.getIDProf() + " - " + prof.getNome());
            int[][] pref = prof.getPrefProf();
            for (int i = 0; i < pref.length; i++) {
                for (int j = 0; j < pref[i].length; j++) {
                    System.out.print(pref[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Função de conveniência que utiliza o verificador singleton para checar
     * consistência das listas carregadas pelo Master.
     *
     * @return Lista de mensagens de erro (vazia se não houver problemas)
     */
    public List<String> validarDados() {
        Verificador verf = Verificador.getInstance();
        verf.setDados(professores, materias, cursos);
        return verf.validarTudo();
    }

    public void PreencherHorariosRandonicamente() {
        for (int i = 0; i < 500; i++) {
            int idCurso = 1; //(int) (Math.random() * 10) + 1; // IDs de 1 a 10
            int horarioIndex = (int) (Math.random() * 8); // índices de 0 a 7
            int linha = (int) (Math.random() * 5); // linhas de 0 a 4
            int coluna = (int) (Math.random() * 16); // colunas de 0 a 15
            String idMateria = "MAT" + ((int) (Math.random() * 47) + 1); // MAT1 a MAT47

            if (adicionarMateriaEmHorario(idCurso, horarioIndex, linha, coluna, idMateria) == false){
                i--;
            }
        }
    }
}