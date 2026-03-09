public class Curso {
    private int IDCurso;
    private Horario[] ConjHorarios;
    private Materia[] MateriasCurso;
    private String NomeCurso;

    public Curso(int IDCurso, Horario[] ConjHorarios, Materia[] MateriasCurso, String NomeCurso) {
        this.IDCurso = IDCurso;
        // always maintain exactly 8 horarios, filling null slots with empty Horario
        if (ConjHorarios == null) {
            this.ConjHorarios = new Horario[8];
        } else if (ConjHorarios.length != 8) {
            Horario[] tmp = new Horario[8];
            System.arraycopy(ConjHorarios, 0, tmp, 0, Math.min(ConjHorarios.length, 8));
            this.ConjHorarios = tmp;
        } else {
            this.ConjHorarios = ConjHorarios;
        }
        // make sure each position has a Horario instance
        for (int i = 0; i < this.ConjHorarios.length; i++) {
            if (this.ConjHorarios[i] == null) {
                Materia[][] vazio = new Materia[5][16];
                this.ConjHorarios[i] = new Horario(vazio);
            }
        }
        this.MateriasCurso = MateriasCurso;
        this.NomeCurso = NomeCurso;
    }

    public int getIDCurso() {
        return IDCurso;
    }

    public void setIDCurso(int IDCurso) {
        this.IDCurso = IDCurso;
    }

    public Horario[] getConjHorarios() {
        return ConjHorarios;
    }

    public void setConjHorarios(Horario[] ConjHorarios) {
        if (ConjHorarios == null) {
            this.ConjHorarios = new Horario[8];
        } else if (ConjHorarios.length != 8) {
            Horario[] tmp = new Horario[8];
            System.arraycopy(ConjHorarios, 0, tmp, 0, Math.min(ConjHorarios.length, 8));
            this.ConjHorarios = tmp;
        } else {
            this.ConjHorarios = ConjHorarios;
        }
        for (int i = 0; i < this.ConjHorarios.length; i++) {
            if (this.ConjHorarios[i] == null) {
                Materia[][] vazio = new Materia[5][16];
                this.ConjHorarios[i] = new Horario(vazio);
            }
        }
    }

    public Materia[] getMateriasCurso() {
        return MateriasCurso;
    }

    public void setMateriasCurso(Materia[] MateriasCurso) {
        this.MateriasCurso = MateriasCurso;
    }

    public String getNomeCurso() {
        return NomeCurso;
    }

    public void setNomeCurso(String NomeCurso) {
        this.NomeCurso = NomeCurso;
    }
}
