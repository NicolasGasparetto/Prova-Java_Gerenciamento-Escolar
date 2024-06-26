package models;

import java.io.Serializable;

public class Aluno extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    private int matricula;
    private Turma turma;

    public Aluno(String nome, int idade, int matricula, Turma turma) {
        super(nome, idade);
        this.matricula = matricula;
        this.turma = turma;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String exibirInformacoes() {
        if (turma == null) {
            return "ID: " + getId() +
                   "\nNome: " + getNome() +
                   "\nIdade: " + getIdade() +
                   "\nMatrícula: " + matricula +
                   "\nTurma: Não associada";
        }
        return "ID: " + getId() +
               "\nNome: " + getNome() +
               "\nIdade: " + getIdade() +
               "\nMatrícula: " + matricula +
               "\nTurma: " + turma.getNome();
    }
}
