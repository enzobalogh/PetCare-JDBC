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
            System.out.println("\n===== PETCARE =====");
            System.out.println("1 - Gerenciar Proprietários");
            System.out.println("2 - Gerenciar Animais");
            System.out.println("3 - Gerenciar Veterinários");
            System.out.println("4 - Gerenciar Consultas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

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
                    System.out.println("\n--- MENU VETERINÁRIO ---");
                    System.out.println("1 - Cadastrar");
                    System.out.println("2 - Listar");
                    System.out.println("3 - Atualizar");
                    System.out.println("4 - Deletar");
                    System.out.print("Escolha: ");
                    int opVet = Integer.parseInt(sc.nextLine());

                    if (opVet == 1) {
                        vetDAO.inserir(lerDadosVeterinario());
                    } else if (opVet == 2) {
                        System.out.println("== LISTA VETERINÁRIOS ==");
                        for(Veterinario v : vetDAO.listarTodos()) {
                            System.out.println("ID: " + v.getId() + " | Nome: " + v.getNome() + " | CRMV: " + v.getCrmv());
                        }
                    } else if (opVet == 3) {
                        System.out.print("ID para atualizar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Veterinario v = lerDadosVeterinario();
                        v.setId(id);
                        vetDAO.atualizar(v);
                    } else if (opVet == 4) {
                        System.out.print("ID para deletar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        vetDAO.deletar(id);
                    }
                    break;

                case 4:
                    System.out.println("\n--- MENU CONSULTAS ---");
                    System.out.println("1 - Agendar");
                    System.out.println("2 - Listar");
                    System.out.println("3 - Atualizar");
                    System.out.println("4 - Deletar");
                    System.out.print("Escolha: ");
                    int opCon = Integer.parseInt(sc.nextLine());

                    if (opCon == 1) {
                        consDAO.inserir(lerDadosConsulta());
                    } else if (opCon == 2) {
                        System.out.println("== HISTÓRICO CONSULTAS ==");
                        for(Consulta c : consDAO.listarTodas()) {
                            System.out.println("ID: " + c.getId() + " | Data: " + c.getData_hora() + " | Diag: " + c.getDiagnostico());
                        }
                    } else if (opCon == 3) {
                        System.out.print("ID para atualizar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Consulta c = lerDadosConsulta();
                        c.setId(id);
                        consDAO.atualizar(c);
                    } else if (opCon == 4) {
                        System.out.print("ID para deletar: ");
                        int id = Integer.parseInt(sc.nextLine());
                        consDAO.deletar(id);
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