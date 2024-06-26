package crud;

import interfaces.Registravel;
import models.Aula;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class AulaCrud implements Registravel {
    private List<Aula> aulas = new ArrayList<>();

    public void cadastrarAula(Aula aula) throws Exception {
        aulas.add(aula);
        registrarLog("Aula cadastrada: " + aula.getId());
    }

    public List<Aula> listarAulas() throws Exception {
        if (aulas.isEmpty()) {
            throw new Exception("Não há nenhuma aula cadastrada.");
        }
        registrarLog("Listagem de aulas realizada.");
        return aulas;
    }

    public Aula buscarAulaPorId(int id) throws Exception {
        Aula aulaEncontrada = aulas.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
        if (aulaEncontrada == null) {
            throw new Exception("Aula com o id: " + id + " não foi encontrada.");
        }
        registrarLog("Busca de aula realizada: " + id);
        return aulaEncontrada;
    }

    public void atualizarAula(Aula aulaAtualizada) throws Exception {
        Aula aula = buscarAulaPorId(aulaAtualizada.getId());
        if (aula != null) {
            aula.setTurma(aulaAtualizada.getTurma());
            aula.setProfessor(aulaAtualizada.getProfessor());
            aula.setDiaDeAula(aulaAtualizada.getDiaDeAula());
            aula.setHorario(aulaAtualizada.getHorario());
            aula.setDescricao(aulaAtualizada.getDescricao());
            registrarLog("Aula atualizada: " + aula.getId());
        }
    }

    public void deletarAula(int id) throws Exception {
        Aula aula = buscarAulaPorId(id);
        if (aula != null) {
            aulas.remove(aula);
            registrarLog("Aula removida: " + aula.getId());
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
