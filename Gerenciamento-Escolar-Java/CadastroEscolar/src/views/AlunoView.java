package views;

import models.Aluno;
import models.Turma;
import controllers.AlunoController;
import controllers.TurmaController;

import java.util.List;
import java.util.Scanner;

public class AlunoView {
    private Aluno aluno;
    private AlunoController alunoController;

    public AlunoView(AlunoController alunoController) {
        this.alunoController = alunoController;
    }

    public void exibirDetalhesAluno(Aluno aluno) {
        System.out.println("Detalhes do Aluno:");
        System.out.println("ID: " + aluno.getId());
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Idade: " + aluno.getIdade());
        System.out.println("Número de matrícula: " + aluno.getMatricula());
    
        Turma turma = aluno.getTurma(); 
        if (turma != null) {
            System.out.println("Turma: " + turma.getNome());
        } else {
            System.out.println("Turma não associada");
        }
    }

    public void exibirListaAlunos(List<Aluno> alunos) {
        System.out.println("Lista de Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getNome());
        }
    }

    public void menuAlunos(AlunoController alunoController, AlunoView alunoView, TurmaController turmaController, TurmaView turmaView, Scanner scanner) throws Exception{
    System.out.println("Menu de Alunos");
    System.out.println("1. Cadastrar Aluno");
    System.out.println("2. Buscar Aluno por ID");
    System.out.println("3. Atualizar Aluno");
    System.out.println("4. Deletar Aluno");
    System.out.println("5. Listar Alunos");
    System.out.println("6. Voltar");

    int opcaoAluno = scanner.nextInt();
    scanner.nextLine();

    switch (opcaoAluno) {
        case 1:
            System.out.println("Cadastro de Aluno");
            System.out.print("Digite o nome do aluno: ");
            String nomeAluno = scanner.nextLine();
            System.out.print("Digite a idade do aluno: ");
            int idadeAluno = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o número de matricula: ");
            int numMatricula = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Selecione uma turma para o aluno:");
            for (Turma turma : turmaController.listarTurma()) {
                turmaView.exibirDetalhesTurma(turma);
            }
            System.out.print("Digite o ID da turma: ");
            int idTurma = scanner.nextInt();
            scanner.nextLine();

            Turma turmaSelecionada = turmaController.buscarTurmaPorId(idTurma);
            if (turmaSelecionada == null) {
                System.out.println("Turma não encontrada! Aluno não cadastrado.");
                break;
            }

            Aluno novoAluno = new Aluno(nomeAluno, idadeAluno, numMatricula, turmaSelecionada);
            alunoController.cadastrarAluno(novoAluno);
            System.out.println("Aluno cadastrado com sucesso!");
            break;
        case 2:
            System.out.println("Buscar Aluno por ID");
            System.out.print("Digite o ID do aluno: ");
            int idBuscaAluno = scanner.nextInt();
            scanner.nextLine();

            Aluno alunoBuscado = alunoController.buscarAlunoPorId(idBuscaAluno);

            if (alunoBuscado != null) {
                alunoView.exibirDetalhesAluno(alunoBuscado);
            } else {
                System.out.println("Aluno não encontrado!");
            }
            break;
        case 3:
            System.out.println("Atualizar Aluno");
            System.out.print("Digite o ID do aluno que deseja atualizar: ");
            int idAtualizarAluno = scanner.nextInt();
            scanner.nextLine();

            Aluno alunoAtualizado = alunoController.buscarAlunoPorId(idAtualizarAluno);

            if (alunoAtualizado != null) {
                System.out.print("Digite o novo nome do aluno: ");
                alunoAtualizado.setNome(scanner.nextLine());
                System.out.print("Digite a nova idade do aluno: ");
                alunoAtualizado.setIdade(scanner.nextInt());
                scanner.nextLine();

                System.out.println("Selecione a nova turma do aluno:");
                for (Turma turma : turmaController.listarTurma()) {
                    turmaView.exibirDetalhesTurma(turma);
                }
                System.out.print("Digite o ID da turma: ");
                int novaIdTurma = scanner.nextInt();
                scanner.nextLine();

                Turma novaTurmaSelecionada = turmaController.buscarTurmaPorId(novaIdTurma);
                if (novaTurmaSelecionada != null) {
                    alunoAtualizado.setTurma(novaTurmaSelecionada);
                    alunoController.atualizarAluno(alunoAtualizado);
                    System.out.println("Aluno atualizado com sucesso!");
                } else {
                    System.out.println("Turma não encontrada! Aluno não atualizado.");
                }
            } else {
                System.out.println("Aluno não encontrado!");
            }
            break;
        case 4:
            System.out.println("Deletar Aula");
            System.out.print("Digite o ID do aluno que deseja deletar: ");
            int idDeletarAluno = scanner.nextInt();
            scanner.nextLine();

            alunoController.deletarAluno(idDeletarAluno);
            System.out.println("Aluno deletado com sucesso!");
            break;
        case 5:
            System.out.println("Listar Alunos");
            for (Aluno aluno : alunoController.listarAlunos()) {
                alunoView.exibirDetalhesAluno(aluno);
            }
            break;
        case 6:
            break; 
        default:
            System.out.println("Digite uma opção válida!");
    }
}

}
