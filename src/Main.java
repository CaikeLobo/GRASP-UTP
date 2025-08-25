import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    // Método para criar os arquivos de teste
    private static void criarArquivosDeTeste() {
        try {
            // Cria o arquivo professores.txt
            FileWriter fwProf = new FileWriter("professores.txt");
            fwProf.write("1;João Silva;02000000000000000000000000000000000000000000000000000000000000000000000000000000\n");
            fwProf.write("2;Maria Oliveira;00000000000000000000000000000000000000000000000000000000000000000000000000000000\n");
            fwProf.write("3;Pedro Santos;00000000000000000000000000000000000000000000000000000000000000000000000000000000\n");
            fwProf.close();

            // Cria o arquivo materias.txt
            FileWriter fwMat = new FileWriter("materias.txt");
            fwMat.write("101;Algoritmos;1;60\n");
            fwMat.write("102;Estrutura de Dados;2;80\n");
            fwMat.write("103;Programacao Orientada a Objetos;1;80\n");
            fwMat.write("104;Banco de Dados;3;60\n");
            fwMat.close();

        } catch (IOException e) {
            System.err.println("Erro ao criar os arquivos de teste: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        
        System.out.println("Criando arquivos de teste...");
        criarArquivosDeTeste();
        System.out.println("Arquivos criados com sucesso.");

        // 1. Ler dados dos arquivos e criar listas de objetos
        System.out.println("\nLendo dados dos arquivos...");
        List<Professor> professoresDoArquivo = Gerente_Txt.lerProfessores("professores.txt");
        List<Materia> materiasDoArquivo = Gerente_Txt.lerMaterias("materias.txt", professoresDoArquivo);
        
        // 2. Criar um objeto Curso e popular suas listas
        Curso engenhariaSoftware = new Curso(1, "Engenharia de Software");
        
        System.out.println("\nAdicionando professores e matérias ao curso...");
        // Adicionando os professores lidos do arquivo
        for (Professor p : professoresDoArquivo) {
            engenhariaSoftware.adicionarProfessor(p);
        }

        // Adicionando as matérias lidas do arquivo
        for (Materia m : materiasDoArquivo) {
            engenhariaSoftware.adicionarMateria(m);
        }
        
        // 3. Demonstrar a manipulação da grade de horários
        engenhariaSoftware.mostrarGradeAtual(); // Exibe a grade vazia inicialmente

        // Adicionar algumas aulas
        // Materia 101 - Algoritmos (Professor João Silva)
        Materia algoritmos = engenhariaSoftware.getMateriasCurso().get(0);
        engenhariaSoftware.inserirAula(0, 0, algoritmos); // Segunda, 08:00
        engenhariaSoftware.inserirAula(2, 2, algoritmos); // Quarta, 14:00

        // Materia 102 - Estrutura de Dados (Professor Maria Oliveira)
        //Materia estruturaDados = engenhariaSoftware.getMateriasCurso().get(1);
        engenhariaSoftware.inserirAula(2, 6, engenhariaSoftware.getMateriasCurso().get(1)); // Terça, 10:00

        //engenhariaSoftware.retirarAula(1, 1); // Terça, 10:00
        
        engenhariaSoftware.mostrarGradeAtual(); // Exibe a grade após a remoção

        engenhariaSoftware.getProfessorCurso().get(0).ExibirTabelaPrefProf();
    }
}
