package views;

import models.Turma;
import controllers.TurmaController;
import java.util.List;
import java.util.Scanner;

public class TurmaView {
    private TurmaController turmaController;

    public TurmaView(TurmaController turmaController) {
        this.turmaController = turmaController;
    }

    public void exibirDetalhesTurma(Turma turma) {
        System.out.println("Detalhes do Turma:");
        System.out.println("ID: " + turma.getId());
        System.out.println("Nome: " + turma.getNome());
        System.out.println("Descrição: " + turma.getDescricao());
    }

    public void exibirListaTurmas(List<Turma> turmas) {
        System.out.println("Lista de Turmas:");
        for (Turma Turma : turmas) {
            System.out.println("ID: " + Turma.getId() + ", Nome: " + Turma.getNome() + "Descrição: " + Turma.getDescricao());
        }
    }

    public void menuTurmas(TurmaController turmaController, TurmaView turmaView, Scanner scanner) throws Exception{
        System.out.println("Menu de Turmas");
        System.out.println("1. Cadastrar Turma");
        System.out.println("2. Buscar Turma por ID");
        System.out.println("3. Atualizar Turma");
        System.out.println("4. Deletar Turma");
        System.out.println("5. Listar Turmas");
        System.out.println("6. Voltar");

        int opcaoTurma = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoTurma) {
            case 1:
                System.out.println("Cadastro de Turma");
                System.out.print("Digite o nome da turma: ");
                String nomeTurma = scanner.nextLine();
                System.out.print("Digite a descrição da turma (opcional): ");
                String descricaoTurma = scanner.nextLine();

                Turma novaTurma = new Turma(nomeTurma, descricaoTurma);
                turmaController.cadastrarTurma(novaTurma);
                System.out.println("Turma cadastrada com sucesso!");
                break;
            case 2:
                System.out.println("Buscar Turma por ID");
                System.out.print("Digite o ID da turma: ");
                int idBuscaTurma = scanner.nextInt();
                scanner.nextLine();

                Turma turmaBuscada = turmaController.buscarTurmaPorId(idBuscaTurma);

                if (turmaBuscada != null) {
                    turmaView.exibirDetalhesTurma(turmaBuscada);
                } else {
                    System.out.println("Turma não encontrada!");
                }
                break;
            case 3:
                System.out.println("Atualizar Turma");
                System.out.print("Digite o ID da turma que deseja atualizar: ");
                int idAtualizarTurma = scanner.nextInt();
                scanner.nextLine();

                Turma turmaAtualizada = turmaController.buscarTurmaPorId(idAtualizarTurma);

                if (turmaAtualizada != null) {
                    System.out.print("Digite o novo nome da turma: ");
                    turmaAtualizada.setNome(scanner.nextLine());
                    System.out.print("Digite a nova descrição da turma (opcional): ");
                    turmaAtualizada.setDescricao(scanner.nextLine());

                    turmaController.atualizarTurma(turmaAtualizada);
                    System.out.println("Turma atualizada com sucesso!");
                } else {
                    System.out.println("Turma não encontrada!");
                }
                break;
            case 4:
                System.out.println("Deletar Turma");
                System.out.print("Digite o ID da turma que deseja deletar: ");
                int idDeletarTurma = scanner.nextInt();
                scanner.nextLine();

                turmaController.deletarTurma(idDeletarTurma);
                System.out.println("Turma deletada com sucesso!");
                break;
            case 5:
                System.out.println("Listar Turmas");
                for (Turma turma : turmaController.listarTurma()) {
                    turmaView.exibirDetalhesTurma(turma);
                }
                break;
            case 6:
                break; 
            default:
                System.out.println("Digite uma opção válida!");
        }
    }
}
