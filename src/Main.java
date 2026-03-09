import java.util.List;

public class Main {
    public static void main(String[] args) {
        Master master = Master.getInstance();

        System.out.println("Professores:");
        for (Professor p : master.getProfessores()) {
            System.out.println(p.getIDProf() + " - " + p.getNome());
        }

        System.out.println("\nMaterias:");
        for (Materia m : master.getMaterias()) {
            System.out.println(m.getIDMateria() + " - " + m.getNomeMateria() + " (Professor: " + (m.getProfessorResponsavel() != null ? m.getProfessorResponsavel().getNome() : "N/A") + ")");
        }

        System.out.println("\nCursos:");
        for (Curso c : master.getCursos()) {
            System.out.println(c.getIDCurso() + " - " + c.getNomeCurso());
        }

    
        // exemplo de inserção de matéria no horário
        System.out.println("\nInserindo MAT1 no curso 1, horário 0, posição (0,0)...");
        //boolean ok = master.adicionarMateriaEmHorario(1, 0, 0, 0, "MAT1");


        master.adicionarMateriaEmHorario(1, 0, 0, 0, "MAT1");
        master.adicionarMateriaEmHorario(1, 1, 0, 0, "MAT1");

        master.PreencherHorariosRandonicamente();
        // imprime todos os horários legíveis
        System.out.println("\nImprimindo todos os horários:");
        master.imprimirTodosHorarios();

        // imprime preferências dos professores
        //System.out.println("Imprimindo preferências dos professores:");
        //master.imprimirPreferenciasProfessores();



        System.out.println("\nValidando dados carregados:");
        List<String> erros = master.validarDados();
        if (erros.isEmpty()) {
            System.out.println("Nenhum problema encontrado.");
        } else {
            for (String e : erros) {
                System.err.println(e);
            }
        }

    }
}