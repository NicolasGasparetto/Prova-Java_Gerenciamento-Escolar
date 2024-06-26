package views;
import java.util.List;
import java.util.Scanner;
import controllers.MateriaController;
import models.Materia;


public class MateriaView {
    private MateriaController materiaController;

    public MateriaView(MateriaController materiaController) {
        this.materiaController = materiaController;
    }

    public void exibirDetalhesMateria(Materia materia) {
        System.out.println("Detalhes da Materia:");
        System.out.println("ID: " + materia.getId());
        System.out.println("Nome: " + materia.getNome());
        System.out.println("Descrição: " + materia.getDescricao());

    }

    public void exibirListaMaterias(List<Materia> materias) {
        System.out.println("Lista de Materiaes:");
        for (Materia materia : materias) {
            System.out.println("ID: " + materia.getId() + ", Nome: " + materia.getNome());
        }
    }

    public void menuMaterias(MateriaController materiaController, MateriaView materiaView, Scanner scanner) throws Exception{
        System.out.println("Menu de Materias");
        System.out.println("1. Cadastrar Materia");
        System.out.println("2. Buscar Materia por ID");
        System.out.println("3. Atualizar Materia");
        System.out.println("4. Deletar Materia");
        System.out.println("5. Listar Materias");
        System.out.println("6. Voltar");

        int opcaoMateria = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoMateria) {
            case 1:
                System.out.println("Cadastro de Materia");
                System.out.print("Digite o nome da Materia: ");
                String nomeMateria = scanner.nextLine();
                System.out.print("Digite a descrição da Materia (opcional): ");
                String descricaoMateria = scanner.nextLine();

                Materia novaMateria = new Materia(nomeMateria, descricaoMateria);
                materiaController.cadastrarMateria(novaMateria);
                System.out.println("Materia cadastrada com sucesso!");
                break;
            case 2:
                System.out.println("Buscar Materia por ID");
                System.out.print("Digite o ID da Materia: ");
                int idBuscaMateria = scanner.nextInt();
                scanner.nextLine();

                Materia materiaBuscada = materiaController.buscarMateriaPorId(idBuscaMateria);

                if (materiaBuscada != null) {
                    materiaView.exibirDetalhesMateria(materiaBuscada);
                } else {
                    System.out.println("Materia não encontrada!");
                }
                break;
            case 3:
                System.out.println("Atualizar Materia");
                System.out.print("Digite o ID da Materia que deseja atualizar: ");
                int idAtualizarMateria = scanner.nextInt();
                scanner.nextLine();

                Materia materiaAtualizada = materiaController.buscarMateriaPorId(idAtualizarMateria);

                if (materiaAtualizada != null) {
                    System.out.print("Digite o novo nome da Materia: ");
                    materiaAtualizada.setNome(scanner.nextLine());
                    System.out.print("Digite a nova descrição da Materia (opcional): ");
                    materiaAtualizada.setDescricao(scanner.nextLine());

                    materiaController.atualizarMateria(materiaAtualizada);
                    System.out.println("Materia atualizada com sucesso!");
                } else {
                    System.out.println("Materia não encontrada!");
                }
                break;
            case 4:
                System.out.println("Deletar Materia");
                System.out.print("Digite o ID da Materia que deseja deletar: ");
                int idDeletarMateria = scanner.nextInt();
                scanner.nextLine();

                materiaController.deletarMateria(idDeletarMateria);
                System.out.println("Materia deletada com sucesso!");
                break;
            case 5:
                System.out.println("Listar Materias");
                for (Materia Materia : materiaController.listarMaterias()) {
                    materiaView.exibirDetalhesMateria(Materia);
                }
                break;
            case 6:
                break; 
            default:
                System.out.println("Digite uma opção válida!");
        }
    }
}


