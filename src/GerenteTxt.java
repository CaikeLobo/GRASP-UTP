import java.io.*;
import java.util.*;

public class GerenteTxt {
    public List<Professor> lerProfessores(String filePath) {
        List<Professor> professores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // expecting format: id;nome;80charsOfPreferences
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String nome = parts[1].trim();
                    String prefs = parts[2].trim();

                    // build preference matrix from the 80-char string
                    int[][] pref = new int[5][16];
                    int idx = 0;
                    for (int i = 0; i < 5 && idx < prefs.length(); i++) {
                        for (int j = 0; j < 16 && idx < prefs.length(); j++) {
                            char c = prefs.charAt(idx++);
                            pref[i][j] = Character.getNumericValue(c); // converts '0','1','2' to 0,1,2
                        }
                    }

                    professores.add(new Professor(id, nome, pref));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return professores;
    }

    public List<Materia> lerMaterias(String filePath, List<Professor> professores) {
        List<Materia> materias = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String nome = parts[1].trim();
                    int profId = Integer.parseInt(parts[2].trim());
                    int carga = Integer.parseInt(parts[3].trim());
                    Professor prof = professores.stream().filter(p -> p.getIDProf() == profId).findFirst().orElse(null);
                    materias.add(new Materia(id, nome, prof, carga));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public List<Curso> lerCursos(String filePath, List<Materia> materias) {
        List<Curso> cursos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String nome = parts[1].trim();
                    String[] matIds = parts[2].split(";");
                    Materia[] mats = new Materia[matIds.length];
                    for (int i = 0; i < matIds.length; i++) {
                        String mid = matIds[i].trim();
                        mats[i] = materias.stream().filter(m -> m.getIDMateria().equals(mid)).findFirst().orElse(null);
                    }
                    // Assuming a default 5x16 empty matrix for Horario
                    Materia[][] mat = new Materia[5][16];
                    Horario hor = new Horario(mat);
                    Horario[] conjHor = {hor};
                    cursos.add(new Curso(id, conjHor, mats, nome));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}