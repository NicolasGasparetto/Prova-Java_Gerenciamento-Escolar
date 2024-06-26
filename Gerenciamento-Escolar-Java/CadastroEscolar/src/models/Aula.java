package models;


import java.time.LocalTime;

public class Aula {
    private int proximoId = 1;
    private int id;
    private Turma turma;
    private Professor professor;
    private String diaDeAula;
    private LocalTime horario;
    private String descricao;
    
    public Aula(Turma turma, Professor professor, String diaDeAula, LocalTime horario, String descricao) {
        this.id = proximoId++;
        this.turma = turma;
        this.professor = professor;
        this.diaDeAula = diaDeAula;
        this.horario = horario;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }


    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDiaDeAula() {
        return diaDeAula;
    }

    public void setDiaDeAula(String diaDeAula) {
        this.diaDeAula = diaDeAula;
    }
}
