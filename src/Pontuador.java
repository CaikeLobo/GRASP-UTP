import java.util.*;

public class Pontuador {
    private static Pontuador instance;

    private List<Professor> professores;
    private List<Materia> materias;
    private List<Curso> cursos;
    private Map<Integer, Integer> pontuacaoProfessores;

    private Pontuador() {
        Master master = Master.getInstance();
        this.professores = master.getProfessores();
        this.materias = master.getMaterias();
        this.cursos = master.getCursos();
        this.pontuacaoProfessores = new HashMap<>();
        
        // Inicializar pontuação de cada professor
        for (Professor prof : professores) {
            pontuacaoProfessores.put(prof.getIDProf(), 0);
        }
    }

    /**
     * Retorna a instância única de Pontuador (singleton).
     */
    public static synchronized Pontuador getInstance() {
        if (instance == null) {
            instance = new Pontuador();
        }
        return instance;
    }

    /**
     * Calcula a pontuação de todos os professores baseado nos horários preenchidos
     * e suas preferências.
     * - Preferência 0: -1 ponto (não gosta daquele horário)
     * - Preferência 1: 0 pontos (neutro)
     * - Preferência 2: +1 ponto (gosta daquele horário)
     */
    public void calcularPontuacao() {
        // Resetar pontuações
        for (Integer idProf : pontuacaoProfessores.keySet()) {
            pontuacaoProfessores.put(idProf, 0);
        }

        // Iterar por cada curso
        for (Curso curso : cursos) {
            Horario[] conjHorarios = curso.getConjHorarios();
            
            // Iterar por cada período
            for (int periodoIdx = 0; periodoIdx < conjHorarios.length; periodoIdx++) {
                Materia[][] matriz = conjHorarios[periodoIdx].getMatrizMateria();
                
                // Iterar por cada posição da matriz (linha e coluna)
                for (int linha = 0; linha < matriz.length; linha++) {
                    for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                        // Se há uma matéria nessa posição
                        if (matriz[linha][coluna] != null) {
                            Materia mat = matriz[linha][coluna];
                            Professor prof = mat.getProfessorResponsavel();
                            
                            if (prof != null) {
                                // Obter matriz de preferências do professor
                                int[][] prefMatriz = prof.getPrefProf();
                                
                                // Verificar a preferência naquela posição
                                if (prefMatriz != null && linha < prefMatriz.length && coluna < prefMatriz[0].length) {
                                    int preferencia = prefMatriz[linha][coluna];
                                    int pontuacaoAtual = pontuacaoProfessores.get(prof.getIDProf());
                                    
                                    if (preferencia == 0) {
                                        // Não gosta: desconta 1 ponto
                                        pontuacaoProfessores.put(prof.getIDProf(), pontuacaoAtual - 5);
                                    } else if (preferencia == 2) {
                                        // Gosta: adiciona 1 ponto
                                        pontuacaoProfessores.put(prof.getIDProf(), pontuacaoAtual + 5);
                                    }
                                    // Se preferência == 1, não faz nada (neutro)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Retorna a pontuação de um professor específico.
     *
     * @param idProf ID do professor
     * @return pontuação do professor
     */
    public int getPontuacaoProfessor(int idProf) {
        return pontuacaoProfessores.getOrDefault(idProf, 0);
    }

    /**
     * Imprime a pontuação de todos os professores.
     */
    public void imprimirPontuacoes() {
        System.out.println("=== PONTUAÇÃO DOS PROFESSORES ===");
        for (Professor prof : professores) {
            int pontuacao = pontuacaoProfessores.get(prof.getIDProf());
            System.out.println("Professor " + prof.getIDProf() + " - " + prof.getNome() + ": " + pontuacao + " pontos");
        }
        System.out.println("TOTAL: " + obterPontuacaoTotal() + " pontos");
        System.out.println("==================================");
    }

    /**
     * Retorna uma cópia do mapa de pontuações.
     */
    public Map<Integer, Integer> obterTodasPontuacoes() {
        return new HashMap<>(pontuacaoProfessores);
    }

    /**
     * Retorna a pontuação total de todos os professores.
     */
    public int obterPontuacaoTotal() {
        int total = 0;
        for (int pontuacao : pontuacaoProfessores.values()) {
            total += pontuacao;
        }
        return total;
    }
}
