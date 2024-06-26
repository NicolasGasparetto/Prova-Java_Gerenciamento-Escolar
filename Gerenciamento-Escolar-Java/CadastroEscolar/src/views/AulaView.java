package views;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import controllers.*;
import models.*;

public class AulaView {
    private AulaController aulaController;
    private LocalTime horario = null;
    private LocalTime horarioAtualizado = null;

    public AulaView(AulaController aulaController) {
        this.aulaController = aulaController;
    }

    public void exibirDetalhesAula(Aula aula) {
        System.out.println("Detalhes da aula:");
        System.out.println("ID: " + aula.getId());
        System.out.println("Professor: " + aula.getProfessor().getNome());
        System.out.println("Matéria que dá aula : " + aula.getTurma().getNome());
        System.out.println("Dia da aula: " + aula.getDiaDeAula());
        System.out.println("Horário : " + aula.getHorario());
    }

    public void exibirListaAulas(List<Aula> aulas) {
        System.out.println("Lista de aulas:");
        for (Aula aula : aulas) {
            exibirDetalhesAula(aula);
        }
    }

    public void menuAulas(AulaController aulaController, AulaView aulaView, ProfessorController professorController, ProfessorView professorView, TurmaController turmaController, TurmaView turmaView, Scanner scanner) throws Exception{
        System.out.println("Menu de Aulas");
        System.out.println("1. Cadastrar aula");
        System.out.println("2. Buscar aula por ID");
        System.out.println("3. Atualizar aula");
        System.out.println("4. Deletar aula");
        System.out.println("5. Listar aulas");
        System.out.println("6. Voltar");

        int opcaoAulas = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoAulas) {
            case 1:
                System.out.println("Cadastro de Aula");
                System.out.println("Selecione um professor:");
                for (Professor professor : professorController.listarProfessores()) {
                    professorView.exibirDetalhesProfessor(professor);
                }
                System.out.println("Digite o ID do professor: ");
                int idProfessor = scanner.nextInt();
                scanner.nextLine();

                Professor professorSelecionado = professorController.buscarProfessorPorId(idProfessor);
                if (professorSelecionado == null) {
                    System.out.println("Professor não encontrado! Aula não cadastrada.");
                    break;
                }
                System.out.println("Selecione uma turma:");
                for (Turma turma : turmaController.listarTurma()) {
                    turmaView.exibirDetalhesTurma(turma);
                }
                System.out.println("Digite o ID da turma: ");
                int idTurma = scanner.nextInt();
                scanner.nextLine();


                Turma turmaSelecionada = turmaController.buscarTurmaPorId(idTurma);
                if (turmaSelecionada == null) {
                    System.out.println("Turma não encontrada! Aula não cadastrada.");
                    break;
                }
                System.out.println("Digite o dia da aula: ");
                String diaAula = scanner.nextLine();
                System.out.println("Digite o horário da aula (formato HH:mm): ");
                String horarioString = scanner.nextLine();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    horario = LocalTime.parse(horarioString, formatter);
                    System.out.println("Horário da aula: " + horario);
                } catch (DateTimeParseException e) {
                    System.out.println("Horário informado em formato inválido! Aula não cadastrada.");
                }
                System.out.print("Digite a descrição da aula: ");
                String descricao = scanner.nextLine();

                Aula novaAula = new Aula(turmaSelecionada, professorSelecionado, diaAula, horario, descricao);
                aulaController.cadastrarAula(novaAula);
                System.out.println("Aula cadastrada com sucesso!");
                break;
            case 2:
                System.out.println("Buscar Aula por ID");
                System.out.print("Digite o ID da Aula: ");
                int idBuscaAulas = scanner.nextInt();
                scanner.nextLine();

                Aula aulaBuscada = aulaController.buscarAulaPorId(idBuscaAulas);

                if (aulaBuscada != null) {
                    aulaView.exibirDetalhesAula(aulaBuscada);
                } else {
                    System.out.println("Aula não encontrada!");
                }
                break;
            case 3:
            System.out.println("Atualizar Aula");
            System.out.print("Digite o ID da aula que deseja atualizar: ");
            int idAtualizarAula = scanner.nextInt();
            scanner.nextLine();
    
            Aula aulaAtualizada = aulaController.buscarAulaPorId(idAtualizarAula);
    
            if (aulaAtualizada != null) {
                System.out.println("Aula encontrada:");
                exibirDetalhesAula(aulaAtualizada);
    
                System.out.println("Selecione um novo professor:");
                for (Professor professor : professorController.listarProfessores()) {
                    professorView.exibirDetalhesProfessor(professor);
                }
                System.out.print("Digite o ID do novo professor: ");
                int novoIdProfessor = scanner.nextInt();
                scanner.nextLine();
    
                Professor novoProfessor = professorController.buscarProfessorPorId(novoIdProfessor);
                if (novoProfessor == null) {
                    System.out.println("Professor não encontrado! Aula não atualizada.");
                    return;
                }

                System.out.println("Selecione uma nova turma:");
                for (Turma turma : turmaController.listarTurma()) {
                    turmaView.exibirDetalhesTurma(turma);
                }

                System.out.println("Digite o ID da nova turma: ");
                int novoIdTurma = scanner.nextInt();
                scanner.nextLine();
    
                Turma novaTurma = turmaController.buscarTurmaPorId(novoIdTurma);
                if (novaTurma == null) {
                    System.out.println("Turma não encontrada! Aula não atualizada.");
                    return;
                }

                System.out.println("Digite o novo dia da aula: ");
                String novoDiaAula = scanner.nextLine();

    
    
                System.out.print("Digite o novo horário da aula (formato HH:mm): ");
                String horarioAulaString = scanner.nextLine();
        
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    horarioAtualizado = LocalTime.parse(horarioAulaString, formatter);
                    System.out.println("Horário da aula: " + horarioAtualizado);
                } catch (DateTimeParseException e) {
                    System.out.println("Horário informado em formato inválido! Aula não cadastrada.");
                }
    
                System.out.print("Digite a nova descrição da aula: ");
                String novaDescricao = scanner.nextLine();
                aulaAtualizada.setTurma(novaTurma);
                aulaAtualizada.setProfessor(novoProfessor);
                aulaAtualizada.setHorario(horarioAtualizado);
                aulaAtualizada.setDiaDeAula(novoDiaAula);
                aulaAtualizada.setDescricao(novaDescricao);
    
                aulaController.atualizarAula(aulaAtualizada);
                System.out.println("Aula atualizada com sucesso!");
            } else {
                System.out.println("Aula não encontrada!");
            }
                break;
            case 4:
                System.out.println("Deletar Aula");
                System.out.print("Digite o ID da aula que deseja deletar: ");
                int idDeletarAula = scanner.nextInt();
                scanner.nextLine();

                aulaController.deletarAula(idDeletarAula);
                System.out.println("Aula deletado com sucesso!");
                break;
            case 5:
                System.out.println("Listar Aula");
                for (Aula aula : aulaController.listarAulas()) {
                    aulaView.exibirDetalhesAula(aula);
                }
                break;
            case 6:
                break;
            default:
                System.out.println("Digite uma opção válida!");
        }
    }
}
