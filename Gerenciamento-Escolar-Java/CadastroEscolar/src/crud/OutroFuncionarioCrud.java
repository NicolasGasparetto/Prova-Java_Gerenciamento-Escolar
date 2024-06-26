package crud;

import interfaces.Registravel;
import models.OutroFuncionario;
import util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OutroFuncionarioCrud implements Registravel {
    private List<OutroFuncionario> outrosFuncionarios;
    private static final String FILE_NAME = "outrosFuncionarios.ser";

    public OutroFuncionarioCrud() {
        carregarDados();
    }

    public void cadastrarOutroFuncionario(OutroFuncionario funcionario) throws Exception {
        if (buscarOutroFuncionarioPorNome(funcionario.getNome()) != null) {
            throw new Exception("Funcionário com o nome " + funcionario.getNome() + " já está cadastrado.");
        }
        funcionario.setId(proximoIdDisponivel()); 
        outrosFuncionarios.add(funcionario);
        salvarDados();
        registrarLog("Funcionário cadastrado: " + funcionario.getNome());
    }

    private OutroFuncionario buscarOutroFuncionarioPorNome(String nome) {
        return outrosFuncionarios.stream()
                .filter(c -> {
                    String funcionarioNome = c.getNome();
                    return funcionarioNome != null && funcionarioNome.trim().equalsIgnoreCase(nome.trim());
                })
                .findFirst()
                .orElse(null);
    }

    public List<OutroFuncionario> listarOutrosFuncionarios() throws Exception {
        if (outrosFuncionarios.isEmpty()) {
            throw new Exception("Não há nenhum funcionário cadastrado.");
        }
        registrarLog("Listagem de funcionários realizada.");
        return outrosFuncionarios;
    }

    public OutroFuncionario buscarOutroFuncionarioPorId(int id) throws Exception {
        OutroFuncionario funcionarioEncontrado = outrosFuncionarios.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
        if (funcionarioEncontrado == null) {
            throw new Exception("Funcionário com o id: " + id + " não foi encontrado.");
        }
        registrarLog("Busca de funcionário realizada: " + id);
        return funcionarioEncontrado;
    }

    public void atualizarOutroFuncionario(OutroFuncionario outroFuncionarioAtualizado) throws Exception {
        OutroFuncionario funcionario = buscarOutroFuncionarioPorId(outroFuncionarioAtualizado.getId());
        if (funcionario != null) {
            funcionario.setNome(outroFuncionarioAtualizado.getNome());
            salvarDados();
            registrarLog("Funcionário atualizado: " + funcionario.getNome());
        }
    }

    public void deletarOutroFuncionario(int id) throws Exception {
        OutroFuncionario funcionario = buscarOutroFuncionarioPorId(id);
        if (funcionario != null) {
            outrosFuncionarios.remove(funcionario);
            salvarDados();
            registrarLog("Funcionário removido: " + funcionario.getNome());
        }
    }

    private void atualizarProximoId() {
        int maxId = 0;
        for (OutroFuncionario o : outrosFuncionarios) {
            if (o.getId() > maxId) {
                maxId = o.getId();
            }
        }
        OutroFuncionario.setProximoId(maxId + 1); 
    }

    private int proximoIdDisponivel() {
        return OutroFuncionario.getProximoId(); 
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(outrosFuncionarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                outrosFuncionarios = (List<OutroFuncionario>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar dados: " + e.getMessage());
            }
        } else {
            outrosFuncionarios = new ArrayList<>();
        }
    }

    @Override
    public void registrarLog(String mensagem) {
        try {
            List<String> logs = new ArrayList<>();
            logs.add(mensagem);
            Log.salvar(logs, "log");
        } catch (Exception e) {
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}
