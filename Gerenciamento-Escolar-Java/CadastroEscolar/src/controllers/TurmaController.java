package controllers;

import models.Turma;

import java.util.List;

import crud.TurmaCrud;

public class TurmaController{
    private TurmaCrud turmaCrud;

    public TurmaController(TurmaCrud turmaCrud) {
        this.turmaCrud = turmaCrud;
    }

    public void cadastrarTurma(Turma turma) throws Exception{
        turmaCrud.cadastrarTurma(turma);
    }

    public void atualizarTurma(Turma turma) throws Exception{
        turmaCrud.atualizarTurma(turma);
    }

    public void deletarTurma(int id) throws Exception{
        turmaCrud.deletarTurma(id);
    }

    public Turma buscarTurmaPorId(int id) throws Exception{
        return turmaCrud.buscarTurmaPorId(id);
    }

    public List<Turma> listarTurma() throws Exception{
        return turmaCrud.listarTurmas();
    }
}

