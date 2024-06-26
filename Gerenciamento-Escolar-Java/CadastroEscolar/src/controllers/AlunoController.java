package controllers;

import models.Aluno;

import java.util.List;

import crud.AlunoCrud;

public class AlunoController {
    private AlunoCrud alunoCrud;

    public AlunoController(AlunoCrud alunoCrud) {
        this.alunoCrud = alunoCrud;
    }

    public void cadastrarAluno(Aluno aluno) throws Exception{
        alunoCrud.cadastrarAluno(aluno);
    }

    public void atualizarAluno(Aluno aluno) throws Exception{
        alunoCrud.atualizarAluno(aluno);
    }

    public void deletarAluno(int id) throws Exception{
        alunoCrud.deletarAluno(id);
    }

    public Aluno buscarAlunoPorId(int id) throws Exception{
        return alunoCrud.buscarAlunoPorId(id);
    }

    public List<Aluno> listarAlunos() throws Exception{
        return alunoCrud.listarAlunos();
    }
}
