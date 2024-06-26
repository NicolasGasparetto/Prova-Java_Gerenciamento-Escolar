package controllers;

import java.util.List;

import crud.OutroFuncionarioCrud;
import models.OutroFuncionario;

public class OutroFuncionarioController {
    private OutroFuncionarioCrud outroFuncionarioCrud;

    public OutroFuncionarioController(OutroFuncionarioCrud outroFuncionarioCrud) {
        this.outroFuncionarioCrud = outroFuncionarioCrud;
    }

    public void cadastrarOutroFuncionario(OutroFuncionario outroFuncionario) throws Exception{
        outroFuncionarioCrud.cadastrarOutroFuncionario(outroFuncionario);
    }

    public void atualizarOutroFuncionario(OutroFuncionario outroFuncionario) throws Exception{
        outroFuncionarioCrud.atualizarOutroFuncionario(outroFuncionario);
    }

    public void deletarOutroFuncionario(int id) throws Exception{
        outroFuncionarioCrud.deletarOutroFuncionario(id);
    }

    public OutroFuncionario buscarOutroFuncionarioPorId(int id) throws Exception{
        return outroFuncionarioCrud.buscarOutroFuncionarioPorId(id);
    }

    public List<OutroFuncionario> listarOutrosFuncionarios() throws Exception{
        return outroFuncionarioCrud.listarOutrosFuncionarios();
    }
}
