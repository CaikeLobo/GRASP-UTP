import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gerente_Txt {
    
    public static List<Professor> lerProfessores(String arquivo) {
        List<Professor> professores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idProf = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();
                String Pref = partes[2].trim();
                professores.add(new Professor(idProf, nome, Pref));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de professores: " + e.getMessage());
        }
        return professores;
    }

    public static List<Materia> lerMaterias(String arquivo, List<Professor> professores) {
        List<Materia> materias = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idMat = Integer.parseInt(partes[0].trim());
                String nomeMat = partes[1].trim();
                int idProf = Integer.parseInt(partes[2].trim());
                int cargaHoraria = Integer.parseInt(partes[3].trim());
                
                // Encontrar o professor correspondente na lista
                Professor professor = null;
                for (Professor p : professores) {
                    if (p.getIdProf() == idProf) {
                        professor = p;
                        break;
                    }
                }
                
                if (professor != null) {
                    materias.add(new Materia(idMat, nomeMat, professor, cargaHoraria));
                } else {
                    System.err.println("Professor com ID " + idProf + " não encontrado para a matéria " + nomeMat);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de matérias: " + e.getMessage());
        }
        return materias;
    }
}