import crud.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controllers.*;
import views.*;
import util.*;

public class Main {
    private static final String PROFESSOR_USUARIO = "professor";
    private static final String PROFESSOR_SENHA = "prof123";

    private static final String FUNCIONARIO_USUARIO = "funcionario";
    private static final String FUNCIONARIO_SENHA = "func123";

    public static void main(String[] args) throws Exception {
        
        AlunoCrud alunoCRUD = new AlunoCrud();
        ProfessorCrud professorCRUD = new ProfessorCrud();
        TurmaCrud turmaCRUD = new TurmaCrud();
        MateriaCrud materiaCrud = new MateriaCrud();
        OutroFuncionarioCrud outroFuncionarioCrud = new OutroFuncionarioCrud();
        AulaCrud aulaCrud = new AulaCrud();

        AlunoController alunoController = new AlunoController(alunoCRUD);
        AlunoView alunoView = new AlunoView(alunoController);
        ProfessorController professorController = new ProfessorController(professorCRUD);
        ProfessorView professorView = new ProfessorView(professorController);
        TurmaController turmaController = new TurmaController(turmaCRUD);
        TurmaView turmaView = new TurmaView(turmaController);
        MateriaController materiaController = new MateriaController(materiaCrud);
        MateriaView materiaView = new MateriaView(materiaController);
        OutroFuncionarioController outroFuncionarioController = new OutroFuncionarioController(outroFuncionarioCrud);
        OutroFuncionarioView outroFuncionarioView = new OutroFuncionarioView(outroFuncionarioController);
        AulaController aulaController = new AulaController(aulaCrud);
        AulaView aulaView = new AulaView(aulaController);

        Scanner scanner = new Scanner(System.in);

        boolean sair = false;
        while (!sair) {
            String tipoUsuario = login(scanner);

            if (tipoUsuario != null) {
                registrarLog("LOGOU COM " + tipoUsuario);

                System.out.println("Login bem-sucedido!");

                boolean voltarParaLogin = false;

                while (!voltarParaLogin) {
                    System.out.println("GERENCIAMENTO ESCOLAR");
                    System.out.println("Digite o número da opção correspondente");

                    if ("FUNCIONARIO".equals(tipoUsuario)) {
                        System.out.println("1. Funcionários");
                        System.out.println("2. Turmas");
                        System.out.println("3. Alunos");
                        System.out.println("4. Matérias");
                        System.out.println("5. Professores");
                        System.out.println("6. Aulas");
                        System.out.println("9. Calcular Potência"); 
                    } else if ("PROFESSOR".equals(tipoUsuario)) {
                        System.out.println("2. Turmas");
                        System.out.println("3. Alunos");
                        System.out.println("4. Matérias");
                    }

                    System.out.println("7. Voltar para Login");
                    System.out.println("8. Sair");

                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao) {
                        case 1:
                            if ("FUNCIONARIO".equals(tipoUsuario)) {
                                outroFuncionarioView.menuFuncionarios(outroFuncionarioController, outroFuncionarioView, scanner);
                            } else {
                                System.out.println("Opção inválida para seu tipo de usuário.");
                            }
                            break;
                        case 2:
                            turmaView.menuTurmas(turmaController, turmaView, scanner);
                            break;
                        case 3:
                            alunoView.menuAlunos(alunoController, alunoView, turmaController, turmaView, scanner);
                            break;
                        case 4:
                            materiaView.menuMaterias(materiaController, materiaView, scanner);
                            break;
                        case 5:
                            if ("FUNCIONARIO".equals(tipoUsuario)) {
                                professorView.menuProfessores(professorController, professorView, materiaController, materiaView, scanner);
                            } else {
                                System.out.println("Opção inválida para seu tipo de usuário.");
                            }
                            break;
                        case 6:
                            if ("FUNCIONARIO".equals(tipoUsuario)) {
                                aulaView.menuAulas(aulaController, aulaView, professorController, professorView, turmaController, turmaView, scanner);
                            } else {
                                System.out.println("Opção inválida para seu tipo de usuário.");
                            }
                            break;
                        case 9:
                            if ("FUNCIONARIO".equals(tipoUsuario)) {
                                calculadoraPotencia(scanner);
                            } else {
                                System.out.println("Opção inválida para seu tipo de usuário.");
                            }
                            break;
                        case 7:
                            voltarParaLogin = true;
                            break;
                        case 8:
                            System.out.println("Saindo...");
                            sair = true;
                            voltarParaLogin = true;
                            break;
                        default:
                            System.out.println("Digite uma opção válida!");
                    }
                }
            }
        }

        scanner.close();
    }

    private static void calculadoraPotencia(Scanner scanner) {
        System.out.println("Digite a base: ");
        int base = scanner.nextInt();
        
        System.out.println("Digite o expoente: ");
        int expoente = scanner.nextInt();

        int resultado = 1;
        for (int i = 0; i < expoente; i++) {
            resultado *= base;
        }

        System.out.println("O resultado de " + base + " elevado a " + expoente + " é: " + resultado);
    }

    private static String login(Scanner scanner) {
        while (true) {
            System.out.println("Tela de Login");
            System.out.println("Usuário: ");
            String usuario = scanner.nextLine();
            System.out.println("Senha: ");
            String senha = scanner.nextLine();

            if (PROFESSOR_USUARIO.equals(usuario) && PROFESSOR_SENHA.equals(senha)) {
                return "PROFESSOR";
            } else if (FUNCIONARIO_USUARIO.equals(usuario) && FUNCIONARIO_SENHA.equals(senha)) {
                return "FUNCIONARIO";
            } else {
                System.out.println("Credenciais inválidas. Tente novamente.");
            }
        }
    }

    private static void registrarLog(String mensagem) {
        try {
            List<String> logs = new ArrayList<>();
            logs.add(mensagem);
            Log.salvar(logs, "log");
        } catch (Exception e) {
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }
}
