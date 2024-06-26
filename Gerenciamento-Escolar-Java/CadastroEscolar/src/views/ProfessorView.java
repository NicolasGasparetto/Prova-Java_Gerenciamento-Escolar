package views;

import models.Materia;
import models.Professor;
import controllers.ProfessorController;
import controllers.MateriaController;
import java.util.List;
import java.util.Scanner;

public class ProfessorView {
    private ProfessorController professorController;

    public ProfessorView(ProfessorController professorController) {
        this.professorController = professorController;
    }

    public void exibirDetalhesProfessor(Professor professor) {
        System.out.println("Detalhes do professor:");
        System.out.println("ID: " + professor.getId());
        System.out.println("Nome: " + professor.getNome());
        System.out.println("Idade: " + professor.getIdade());
        System.out.println("Matéria que dá aula : " + professor.getMateria().getNome());
    }

    public void exibirListaProfessores(List<Professor> professores) {
        System.out.println("Lista de professores:");
        for (Professor professor : professores) {
            System.out.println("ID: " + professor.getId() + ", Nome: " + professor.getNome());
        }
    }

    public void menuProfessores(ProfessorController professorController, ProfessorView professorView, MateriaController materiaController, MateriaView materiaView, Scanner scanner) throws Exception{
        System.out.println("Menu de Professores");
        System.out.println("1. Cadastrar Professor");
        System.out.println("2. Buscar Professor por ID");
        System.out.println("3. Atualizar Professor");
        System.out.println("4. Deletar Professor");
        System.out.println("5. Listar Professores");
        System.out.println("6. Voltar");

        int opcaoProfessores = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoProfessores) {
            case 1:
                System.out.println("Cadastro de Professor");
                System.out.print("Digite o nome do professor: ");
                String nomeProfessor = scanner.nextLine();
                System.out.print("Digite a idade do professor: ");
                int idadeProfessor = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Selecione uma matéria para o professor:");
                for (Materia materia : materiaController.listarMaterias()) {
                    materiaView.exibirDetalhesMateria(materia);
                }
                System.out.print("Digite o ID da matéria: ");
                int idMateria = scanner.nextInt();
                scanner.nextLine();

                Materia materiaSelecionada = materiaController.buscarMateriaPorId(idMateria);
                if (materiaSelecionada == null) {
                    System.out.println("Matéria não encontrada! Professor não cadastrado.");
                    break;
                }

                Professor novoProfessor = new Professor(nomeProfessor, idadeProfessor, materiaSelecionada);
                professorController.cadastrarProfessor(novoProfessor);
                System.out.println("Professor cadastrado com sucesso!");
                break;
            case 2:
                System.out.println("Buscar Professor por ID");
                System.out.print("Digite o ID do Professor: ");
                int idBuscaProfessores = scanner.nextInt();
                scanner.nextLine();

                Professor professorBuscado = professorController.buscarProfessorPorId(idBuscaProfessores);

                if (professorBuscado != null) {
                    professorView.exibirDetalhesProfessor(professorBuscado);
                } else {
                    System.out.println("Professor não encontrado!");
                }
                break;
            case 3:
                System.out.println("Atualizar Professor");
                System.out.print("Digite o ID do Professor que deseja atualizar: ");
                int idAtualizarProfessor = scanner.nextInt();
                scanner.nextLine();

                Professor professorAtualizado = professorController.buscarProfessorPorId(idAtualizarProfessor);

                if (professorAtualizado != null) {
                    System.out.print("Digite o novo nome do Professor: ");
                    professorAtualizado.setNome(scanner.nextLine());
                    System.out.print("Digite a nova idade do Professor: ");
                    professorAtualizado.setIdade(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Selecione a nova matéria do professor:");
                    for (Materia materia : materiaController.listarMaterias()) {
                        materiaView.exibirDetalhesMateria(materia);
                    }
                    System.out.print("Digite o ID da matéria: ");
                    int novoIdMateria = scanner.nextInt();
                    scanner.nextLine();

                    Materia novaMateriaSelecionada = materiaController.buscarMateriaPorId(novoIdMateria);
                    if (novaMateriaSelecionada != null) {
                        professorAtualizado.setMateria(novaMateriaSelecionada);
                        professorController.atualizarProfessor(professorAtualizado);
                        System.out.println("Professor atualizado com sucesso!");
                    } else {
                        System.out.println("Matéria não encontrada! Professor não atualizado.");
                    }
                } else {
                    System.out.println("Professor não encontrado!");
                }
                break;
            case 4:
                System.out.println("Deletar Professor");
                System.out.print("Digite o ID do Professor que deseja deletar: ");
                int idDeletarProfessores = scanner.nextInt();
                scanner.nextLine();

                professorController.deletarProfessor(idDeletarProfessores);
                System.out.println("Professor deletado com sucesso!");
                break;
            case 5:
                System.out.println("Listar Professores");
                for (Professor professor : professorController.listarProfessores()) {
                    professorView.exibirDetalhesProfessor(professor);
                }
                break;
            case 6:
                break;
            default:
                System.out.println("Digite uma opção válida!");
        }
    }
}
