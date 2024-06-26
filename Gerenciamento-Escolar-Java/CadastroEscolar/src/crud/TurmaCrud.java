package crud;

import interfaces.Registravel;
import models.Turma;
import util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaCrud implements Registravel {
    private List<Turma> turmas;
    private static final String FILE_NAME = "turmas.ser";

    public TurmaCrud() {
        carregarDados();
    }

    public void cadastrarTurma(Turma turma) throws Exception {
        if (buscarTurmaPorNome(turma.getNome()) != null) {
            throw new Exception("Turma com o nome " + turma.getNome() + " já está cadastrada.");
        }
        turma.setId(proximoIdDisponivel()); 
        turmas.add(turma);
        salvarDados();
        registrarLog("Turma cadastrada: " + turma.getNome());
    }

    private Turma buscarTurmaPorNome(String nome) {
        return turmas.stream()
                .filter(t -> t.getNome().trim().equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElse(null);
    }

    public List<Turma> listarTurmas() throws Exception {
        if (turmas.isEmpty()) {
            throw new Exception("Não há nenhuma turma cadastrada.");
        }
        registrarLog("Listagem de turmas realizada.");
        return turmas;
    }

    public Turma buscarTurmaPorId(int id) throws Exception {
        Turma turmaEncontrada = turmas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
        if (turmaEncontrada == null) {
            throw new Exception("Turma com o id: " + id + " não foi encontrada.");
        }
        registrarLog("Busca de turma realizada: " + id);
        return turmaEncontrada;
    }

    public void atualizarTurma(Turma turmaAtualizada) throws Exception {
        Turma turma = buscarTurmaPorId(turmaAtualizada.getId());
        if (turma != null) {
            turma.setNome(turmaAtualizada.getNome());
            turma.setDescricao(turmaAtualizada.getDescricao());
            salvarDados();
            registrarLog("Turma atualizada: " + turma.getNome());
        }
    }

    public void deletarTurma(int id) throws Exception {
        Turma turma = buscarTurmaPorId(id);
        if (turma != null) {
            turmas.remove(turma);
            salvarDados();
            registrarLog("Turma removida: " + turma.getNome());
        }
    }

    private void atualizarProximoId() {
        int maxId = 0;
        for (Turma t : turmas) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        Turma.setProximoId(maxId + 1); 
    }

    private int proximoIdDisponivel() {
        return Turma.getProximoId(); 
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(turmas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    private void carregarDados() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                turmas = (List<Turma>) ois.readObject();
                atualizarProximoId();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar dados: " + e.getMessage());
            }
        } else {
            turmas = new ArrayList<>();
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
