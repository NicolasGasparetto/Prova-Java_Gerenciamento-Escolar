package crud;

import interfaces.Registravel;
import models.Professor;
import util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorCrud implements Registravel {
    private List<Professor> professores;
    private static final String FILE_NAME = "professores.ser";

    public ProfessorCrud() {
        carregarDados();
    }

    public void cadastrarProfessor(Professor professor) throws Exception {
        if (buscarProfessorPorNome(professor.getNome()) != null) {
            throw new Exception("Professor com o nome " + professor.getNome() + " já está cadastrado.");
        }
        professor.setId(proximoIdDisponivel()); 
        professores.add(professor);
        salvarDados();
        registrarLog("Professor cadastrado: " + professor.getNome());
    }

    private Professor buscarProfessorPorNome(String nome) {
        return professores.stream()
            .filter(p -> p.getNome().trim().equalsIgnoreCase(nome.trim()))
            .findFirst()
            .orElse(null);
    }

    public List<Professor> listarProfessores() throws Exception {
        if (professores.isEmpty()) {
            throw new Exception("Não há nenhum professor cadastrado.");
        }
        registrarLog("Listagem de professores realizada.");
        return professores;
    }

    public Professor buscarProfessorPorId(int id) throws Exception {
        Professor professorEncontrado = professores.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
        if (professorEncontrado == null) {
            throw new Exception("Professor com o id: " + id + " não foi encontrado.");
        }
        registrarLog("Busca de professor realizada: " + id);
        return professorEncontrado;
    }

    public void atualizarProfessor(Professor professorAtualizado) throws Exception {
        Professor professor = buscarProfessorPorId(professorAtualizado.getId());
        if (professor != null) {
            professor.setNome(professorAtualizado.getNome());
            professor.setIdade(professorAtualizado.getIdade());
            professor.setMateria(professorAtualizado.getMateria());
            salvarDados();
            registrarLog("Professor atualizado: " + professor.getNome());
        }
    }

    public void deletarProfessor(int id) throws Exception {
        Professor professor = buscarProfessorPorId(id);
        if (professor != null) {
            professores.remove(professor);
            salvarDados();
            registrarLog("Professor removido: " + professor.getNome());
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(professores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                professores = (List<Professor>) ois.readObject();
                atualizarProximoId();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar dados: " + e.getMessage());
            }
        } else {
            professores = new ArrayList<>();
        }
    }

    private void atualizarProximoId() {
        int maxId = 0;
        for (Professor p : professores) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        Professor.setProximoId(maxId + 1); 
    }

    private int proximoIdDisponivel() {
        return Professor.getProximoId(); 
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
