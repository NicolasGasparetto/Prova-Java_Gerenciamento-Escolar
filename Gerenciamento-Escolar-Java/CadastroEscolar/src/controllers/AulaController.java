package controllers;

import java.util.List;

import crud.AulaCrud;
import models.Aula;

public class AulaController {
    private AulaCrud aulaCrud;

    public AulaController(AulaCrud aulaCrud) {
        this.aulaCrud = aulaCrud;
    }

    public void cadastrarAula(Aula aula) throws Exception{
        aulaCrud.cadastrarAula(aula);
    }

    public void atualizarAula(Aula aula) throws Exception{
        aulaCrud.atualizarAula(aula);
    }

    public void deletarAula(int id) throws Exception{
        aulaCrud.deletarAula(id);
    }

    public Aula buscarAulaPorId(int id) throws Exception{
        return aulaCrud.buscarAulaPorId(id);
    }

    public List<Aula> listarAulas() throws Exception{
        return aulaCrud.listarAulas();
    }
}
