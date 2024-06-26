package controllers;

import java.util.List;

import crud.MateriaCrud;
import models.Materia;

public class MateriaController {
     private MateriaCrud materiaCrud;

    public MateriaController(MateriaCrud materiaCrud) {
        this.materiaCrud = materiaCrud;
    }

    public void cadastrarMateria(Materia materia) throws Exception{
        materiaCrud.cadastrarMateria(materia);
    }

    public void atualizarMateria(Materia materia) throws Exception{
        materiaCrud.atualizarMateria(materia);
    }

    public void deletarMateria(int id) throws Exception{
        materiaCrud.deletarMateria(id);
    }

    public Materia buscarMateriaPorId(int id) throws Exception{
        return materiaCrud.buscarMateriaPorId(id);
    }

    public List<Materia> listarMaterias() throws Exception{
        return materiaCrud.listarMaterias();
    }
}
