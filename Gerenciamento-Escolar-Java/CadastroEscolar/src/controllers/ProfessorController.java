package controllers;

import models.Professor;

import java.util.List;

import crud.ProfessorCrud;

public class ProfessorController{
    private ProfessorCrud professorCrud;

    public ProfessorController(ProfessorCrud professorCrud) {
        this.professorCrud = professorCrud;
    }

    public void cadastrarProfessor(Professor professor) throws Exception{
        professorCrud.cadastrarProfessor(professor);
    }

    public void atualizarProfessor(Professor professor) throws Exception{
        professorCrud.atualizarProfessor(professor);
    }

    public void deletarProfessor(int id) throws Exception{
        professorCrud.deletarProfessor(id);
    }

    public Professor buscarProfessorPorId(int id) throws Exception{
        return professorCrud.buscarProfessorPorId(id);
    }

    public List<Professor> listarProfessores() throws Exception{
        return professorCrud.listarProfessores();
    }
}

