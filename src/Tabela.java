public class Tabela {
    private Materia[][] Grade;
 private static final int DIAS = 5; // Segunda a Sexta
    private static final int HORARIOS = 16; // Exemplo: 08:00, 10:00, 14:00, 16:00

    public Tabela() {
        this.Grade = new Materia[DIAS][HORARIOS];
        inicializarTabela();
    }

    private void inicializarTabela() {
        for (int i = 0; i < DIAS; i++) {
            for (int j = 0; j < HORARIOS; j++) {
                Grade[i][j] = null;
            }
        }
    }
    
    public boolean inserirAula(int dia, int horario, Materia materia) {
        
       VerificacaoBasica(dia, horario);

        // Verifica se o horário está livre
        if (Grade[dia][horario] != null)  {
            System.out.println("Erro: Horário já ocupado por: " + Grade[dia][horario]);
            return false;
        }
        
        Grade[dia][horario] = materia;
        return true;
    }

    public boolean retirarAula(int dia, int horario) {
        // Validação básica
        VerificacaoBasica(dia, horario);

        // Verifica se há uma aula para ser removida
        if (Grade[dia][horario] != null) {
            System.out.println("Erro: Não há aula neste horário para remover.");
            return false;
        }

        System.out.println("Aula de " + Grade[dia][horario] + " removida.");
        Grade[dia][horario] = null;
        return true;
    }

   public void mostrarGradeAtual() {
        String[] diasDaSemana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        String[] horarios = {"07:00", "07:50", "08:40", "09:40","10:20", "11:00", "13:20", "14:20","15:10","16:10", "17:00", "18:20", "19:10","20:10", "21:00", "21:50"};

       // System.out.println("\n--- Grade de Horários do " + this.nomeCurso + " ---");
        // Cabeçalho da tabela
        System.out.print("           ");
        for (String d : diasDaSemana) {
            System.out.printf("%-25s", d);
        }
        System.out.println();

        // Conteúdo da tabela
        for (int i = 0; i < HORARIOS; i++) {
            System.out.printf("%-10s", horarios[i] + " | ");
            for (int j = 0; j < DIAS; j++) {
                if (Grade[j][i] != null){
                    System.out.printf("%-25s", Grade[j][i].getNomeMat());
                }
                else{
                     System.out.printf("%-25s", "NADA");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }

    private boolean VerificacaoBasica(int dia, int horario){

        if (dia < 0 || dia >= DIAS || horario < 0 || horario >= HORARIOS) {
            System.out.println("Erro: Dia ou horário inválido.");
            return false;
        }
        else{
            return true;
        }
    }
}
