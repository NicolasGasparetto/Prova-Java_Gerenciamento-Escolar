package views;

import java.util.List;
import java.util.Scanner;

import controllers.OutroFuncionarioController;
import models.OutroFuncionario;

public class OutroFuncionarioView {
    private OutroFuncionarioController outroFuncionarioController;

    public OutroFuncionarioView(OutroFuncionarioController outroFuncionarioController) {
        this.outroFuncionarioController = outroFuncionarioController;
    }

    public void exibirDetalhesOutroFuncionario(OutroFuncionario outroFuncionario) {
        System.out.println("Detalhes do Funcionário");
        System.out.println("ID: " + outroFuncionario.getId());
        System.out.println("Nome: " + outroFuncionario.getNome());
        System.out.println("Cargo: " + outroFuncionario.getCargo());
    }

    public void exibirListaOutrosFuncionarios(List<OutroFuncionario> outrosFuncionarios) {
        System.out.println("Lista de Funcionários:");
        for (OutroFuncionario funcionario : outrosFuncionarios) {
            System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome() + "Cargo: " + funcionario.getCargo());
        }
    }

      public void menuFuncionarios(OutroFuncionarioController funcionarioController, OutroFuncionarioView funcionarioView, Scanner scanner) throws Exception{
        System.out.println("Menu de Funcionários");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Buscar Funcionário por ID");
        System.out.println("3. Atualizar Funcionário");
        System.out.println("4. Deletar Funcionário");
        System.out.println("5. Listar Funcionários");
        System.out.println("6. Voltar");

        int opcaoFuncionario = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoFuncionario) {
            case 1:
                System.out.println("Cadastro de Funcionário");
                System.out.print("Digite o nome do funcionário: ");
                String nomeFuncionario = scanner.nextLine();
                System.out.print("Digite a idade do funcionário: ");
                int idadeFuncionario = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Digite o cargo do funcionário: ");
                String cargoFuncionario = scanner.nextLine();

                OutroFuncionario novoFuncionario = new OutroFuncionario(nomeFuncionario, idadeFuncionario, cargoFuncionario);
                funcionarioController.cadastrarOutroFuncionario(novoFuncionario);
                System.out.println("Funcionário cadastrado com sucesso!");
                break;
            case 2:
                System.out.println("Buscar Funcionário por ID");
                System.out.print("Digite o ID do funcionário: ");
                int idBuscaFuncionario = scanner.nextInt();
                scanner.nextLine();

                OutroFuncionario funcionarioBuscado = funcionarioController.buscarOutroFuncionarioPorId(idBuscaFuncionario);

                if (funcionarioBuscado != null) {
                    funcionarioView.exibirDetalhesOutroFuncionario(funcionarioBuscado);
                } else {
                    System.out.println("Funcionário não encontrado!");
                }
                break;
            case 3:
                System.out.println("Atualizar Funcionário");
                System.out.print("Digite o ID do funcionário que deseja atualizar: ");
                int idAtualizarFuncionario = scanner.nextInt();
                scanner.nextLine();

                OutroFuncionario funcionarioAtualizado = funcionarioController.buscarOutroFuncionarioPorId(idAtualizarFuncionario);

                if (funcionarioAtualizado != null) {
                    System.out.print("Digite o novo nome do funcionário: ");
                    funcionarioAtualizado.setNome(scanner.nextLine());
                    System.out.print("Digite a nova idade do funcionário: ");
                    funcionarioAtualizado.setIdade(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Digite o novo cargo do funcionário: ");
                    funcionarioAtualizado.setCargo(scanner.nextLine());
                    funcionarioController.atualizarOutroFuncionario(funcionarioAtualizado);
                    System.out.println("Funcionário atualizado com sucesso!");
                } else {
                    System.out.println("Funcionário não encontrado!");
                }
                break;
            case 4:
                System.out.println("Deletar Funcionário");
                System.out.print("Digite o ID do funcionário que deseja deletar: ");
                int idDeletarFuncionario = scanner.nextInt();
                scanner.nextLine();

                funcionarioController.deletarOutroFuncionario(idDeletarFuncionario);
                System.out.println("Funcionário deletado com sucesso!");
                break;
            case 5:
                System.out.println("Listar Funcionários");
                for (OutroFuncionario funcionario : funcionarioController.listarOutrosFuncionarios()) {
                    funcionarioView.exibirDetalhesOutroFuncionario(funcionario);
                }
                break;
            case 6:
                break; 
            default:
                System.out.println("Digite uma opção válida!");
        }
    }

}
