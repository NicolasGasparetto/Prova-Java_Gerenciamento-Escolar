package crud;

import interfaces.Registravel;
import models.Aluno;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class AlunoCrud implements Registravel {
    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(Aluno aluno) throws Exception {
        if (buscarAlunoPorNome(aluno.getNome()) != null) {
            throw new Exception("Aluno com o nome " + aluno.getNome() + " já está cadastrado.");
        }
        alunos.add(aluno);
        registrarLog("Aluno cadastrado: " + aluno.getNome());
    }

    private Aluno buscarAlunoPorNome(String nome) {
        return alunos.stream()
                .filter(a -> a.getNome().trim().equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElse(null);
    }

    public List<Aluno> listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Não há nenhum aluno cadastrado.");
            return new ArrayList<>();
        }
        registrarLog("Listagem de alunos realizada.");
        return alunos;
    }

    public List<Aluno> listarAlunos(String filtroNome) {
        List<Aluno> alunosFiltrados = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(filtroNome.trim())) {
                alunosFiltrados.add(aluno);
            }
        }
        if (alunosFiltrados.isEmpty()) {
            System.out.println("Não há nenhum aluno cadastrado com o nome " + filtroNome + ".");
        } else {
            registrarLog("Listagem de alunos filtrada por nome realizada.");
        }
        return alunosFiltrados;
    }
   // public Aluno buscarAlunoPorId(int id) busca um aluno pelo ID sem log
   // public Aluno buscarAlunoPorId(int id, boolean logarBusca) busca um aluno pelo ID e opcionalmente registra a busca no log, se logarBusca for true
  // forma mais simples de interligar os dois
    
    public Aluno buscarAlunoPorId(int id) throws Exception {
        return buscarAlunoPorId(id, true);
    }

   public Aluno buscarAlunoPorId(int id, boolean logarBusca) throws Exception {
    Aluno alunoEncontrado = alunos.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElse(null);
    if (alunoEncontrado == null) {
        throw new Exception("Aluno com o id: " + id + " não foi encontrado.");
    }
    if (logarBusca) {
        registrarLog("Busca de aluno realizada: " + id);
    }
    return alunoEncontrado;
}
    public void atualizarAluno(Aluno alunoAtualizado) throws Exception {
        Aluno aluno = buscarAlunoPorId(alunoAtualizado.getId());
        if (aluno != null) {
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setIdade(alunoAtualizado.getIdade());
            aluno.setMatricula(alunoAtualizado.getMatricula());
            aluno.setTurma(alunoAtualizado.getTurma());
            registrarLog("Aluno atualizado: " + aluno.getNome());
        } else {
            throw new Exception("Aluno com o id: " + alunoAtualizado.getId() + " não foi encontrado.");
        }
    }

    public void deletarAluno(int id) throws Exception {
        Aluno aluno = buscarAlunoPorId(id);
        if (aluno != null) {
            alunos.remove(aluno);
            registrarLog("Aluno removido: " + aluno.getNome());
        } else {
            throw new Exception("Aluno com o id: " + id + " não foi encontrado.");
        }
    }

    private void atualizarProximoId() {
        int maxId = 0;
        for (Aluno a : alunos) {
            if (a.getId() > maxId) {
                maxId = a.getId();
            }
        }
        Aluno.setProximoId(maxId + 1);
    }

    private int proximoIdDisponivel() {
        return Aluno.getProximoId();
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
