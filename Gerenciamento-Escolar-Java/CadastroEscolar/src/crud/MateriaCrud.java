package crud;

import interfaces.Registravel;
import models.Materia;
import util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaCrud implements Registravel {
    private List<Materia> materias = new ArrayList<>();
    private static final String FILE_NAME = "materias.ser"; 

    public MateriaCrud() {
        carregarMaterias(); 
    }

    public void cadastrarMateria(Materia materia) throws Exception {
        if (buscarMateriaPorNome(materia.getNome()) != null) {
            throw new Exception("Matéria com o nome " + materia.getNome() + " já está cadastrada.");
        }
        if (buscarMateriaPorId(materia.getId()) != null) {
            throw new Exception("Matéria com o ID " + materia.getId() + " já está cadastrada.");
        }
        materia.setId(proximoIdDisponivel()); 
        materias.add(materia);
        salvarMaterias(); 
        registrarLog("Matéria cadastrada: " + materia.getNome());
    }

    private Materia buscarMateriaPorNome(String nome) {
        return materias.stream()
                .filter(m -> m.getNome().trim().equalsIgnoreCase(nome.trim()))
                .findFirst()
                .orElse(null);
    }

    public List<Materia> listarMaterias() throws Exception {
        if (materias.isEmpty()) {
            throw new Exception("Não há nenhuma matéria cadastrada.");
        }
        registrarLog("Listagem de matérias realizada.");
        return materias;
    }

    public Materia buscarMateriaPorId(int id) {
        return materias.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void atualizarMateria(Materia materiaAtualizada) throws Exception {
        Materia materia = buscarMateriaPorId(materiaAtualizada.getId());
        if (materia != null) {
            materia.setNome(materiaAtualizada.getNome());
            materia.setDescricao(materiaAtualizada.getDescricao());
            salvarMaterias(); 
            registrarLog("Matéria atualizada: " + materia.getNome());
        }
    }

    public void deletarMateria(int id) throws Exception {
        Materia materia = buscarMateriaPorId(id);
        if (materia != null) {
            materias.remove(materia);
            salvarMaterias(); 
            registrarLog("Matéria removida: " + materia.getNome());
        }
    }

    private void atualizarProximoId() {
        int maxId = 0;
        for (Materia m : materias) {
            if (m.getId() > maxId) {
                maxId = m.getId();
            }
        }
        Materia.setProximoId(maxId + 1); 
    }

    private int proximoIdDisponivel() {
        return Materia.getProximoId(); 
    }

    private void salvarMaterias() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(materias);
        } catch (IOException e) {
            System.err.println("Erro ao salvar matérias: " + e.getMessage());
        }
    }

    private void carregarMaterias() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                materias = (List<Materia>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao carregar matérias: " + e.getMessage());
            }
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
