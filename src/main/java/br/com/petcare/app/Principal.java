package br.com.petcare.app;

import br.com.petcare.dao.AnimalDAO;
import br.com.petcare.dao.ConsultaDAO;
import br.com.petcare.dao.ProprietarioDAO;
import br.com.petcare.dao.VeterinarioDAO;
import br.com.petcare.model.Animal;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Proprietario;
import br.com.petcare.model.Veterinario;
import br.com.petcare.view.Menu;

import java.util.List;
import java.util.Scanner;

public class Principal extends Menu {
    public static void main(String[] args) {

        AnimalDAO animalDAO = new AnimalDAO();
        ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
        VeterinarioDAO vetDAO = new VeterinarioDAO();
        ConsultaDAO consDAO = new ConsultaDAO();

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== PETCARE =====");
            System.out.println("1 - Gerenciar Proprietários");
            System.out.println("2 - Gerenciar Animais");
            System.out.println("3 - Gerenciar Veterinários");
            System.out.println("4 - Gerenciar Consultas");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {

                case 1:
                    Proprietario p = lerDadosProprietario();
                    proprietarioDAO.inserir(p);
                    System.out.println("\n== LISTA DE PROPRIETÁRIOS ==");
                    List<Proprietario> proprietarios = proprietarioDAO.listarTodos();
                    for(Proprietario dono : proprietarios){
                        System.out.println("ID: "+dono.getId() + " | Nome: "+dono.getNome() + " | CPF: "+dono.getCpf());
                    }
                    break;

                case 2:
                    Animal a = lerDadosAnimal();
                    animalDAO.inserir(a);
                    System.out.println("\n== LISTA DE ANIMAIS ==");
                    List<Animal> animais = animalDAO.listarTodos();
                    for(Animal bicho : animais){
                        System.out.println("ID: " + bicho.getId() + " | Nome: " + bicho.getNome() + " | Dono ID: " + bicho.getId_proprietario());
                    }
                    break;

                case 3:
                    System.out.println("--- VETERINÁRIOS ---");
                    System.out.println("1-Cadastrar | 2-Listar | 3-Atualizar | 4-Deletar");
                    int opVet = sc.nextInt();
                    sc.nextLine();

                    switch(opVet) {
                        case 1:
                            vetDAO.inserir(lerDadosVeterinario());
                            break;
                        case 2:
                            for(Veterinario v : vetDAO.listarTodos()) {
                                System.out.println("ID: " + v.getId() + " | Nome: " + v.getNome() + " | CRMV: " + v.getCrmv());
                            }
                            break;
                        case 3:
                            System.out.print("ID para atualizar: ");
                            int idV = sc.nextInt();
                            sc.nextLine();
                            Veterinario vUp = lerDadosVeterinario();
                            vUp.setId(idV);
                            vetDAO.atualizar(vUp);
                            break;
                        case 4:
                            System.out.print("ID para deletar: ");
                            int idDelV = sc.nextInt();
                            sc.nextLine();
                            vetDAO.deletar(idDelV);
                            break;
                    }
                    break;

                case 4:
                    System.out.println("--- CONSULTAS ---");
                    System.out.println("1-Agendar | 2-Listar | 3-Atualizar | 4-Deletar");
                    int opCon = sc.nextInt();
                    sc.nextLine();

                    switch(opCon) {
                        case 1:
                            consDAO.inserir(lerDadosConsulta());
                            break;
                        case 2:
                            for(Consulta c : consDAO.listarTodas()) {
                                System.out.println("ID: " + c.getId() + " | Data: " + c.getData_hora() + " | Diag: " + c.getDiagnostico());
                            }
                            break;
                        case 3:
                            System.out.print("ID para atualizar: ");
                            int idC = sc.nextInt();
                            sc.nextLine();
                            Consulta cUp = lerDadosConsulta();
                            cUp.setId(idC);
                            consDAO.atualizar(cUp);
                            break;
                        case 4:
                            System.out.print("ID para deletar: ");
                            int idDelC = sc.nextInt();
                            sc.nextLine();
                            consDAO.deletar(idDelC);
                            break;
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}