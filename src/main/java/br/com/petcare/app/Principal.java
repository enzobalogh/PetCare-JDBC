package br.com.petcare.app;

import br.com.petcare.dao.AnimalDAO;
import br.com.petcare.dao.ProprietarioDAO;
import br.com.petcare.model.Animal;
import br.com.petcare.model.Proprietario;
import br.com.petcare.view.Menu;

import java.util.List;
import java.util.Scanner;

public class Principal extends Menu {
    public static void main(String[] args) {

        AnimalDAO animalDAO = new AnimalDAO();
        ProprietarioDAO proprietarioDAO = new ProprietarioDAO();
        Scanner sc = new Scanner(System.in);
        int opcao;
        int opcao2;

        do {
            System.out.println("===== PETCARE =====");
            System.out.println("1 - Gerenciar Proprietários");
            System.out.println("2 - Gerenciar Animais");
            System.out.println("3 - Gerenciar Veterinários");
            System.out.println("4 - Gerenciar Consultas");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("O que deseja fazer:");
                    System.out.println("1 - Inserir proprietário");
                    System.out.println("2 - Excluir proprietário");
                    System.out.println("3 - Atualizar proprietário");
                    System.out.println("4 - Voltar");
                    opcao2 = sc.nextInt();
                    switch (opcao2) {
                        case 1:
                            Proprietario p = lerDadosProprietario();
                            proprietarioDAO.inserir(p);
                            System.out.println("\n== LISTA DE PROPRIETÁRIOS ==");
                            List<Proprietario> proprietarios = proprietarioDAO.listarTodos();
                            for (Proprietario dono : proprietarios) {
                                System.out.println(
                                        "ID: " + dono.getId() +
                                                ", Nome: " + dono.getNome() +
                                                ", CPF: " + dono.getCpf() +
                                                ", Telefone: " + dono.getTelefone() +
                                                ", Endereço: " + dono.getEndereco() +
                                                ", Email: " + dono.getEmail()
                                );
                            }
                            break;
                        case 2:
                            //EXCLUIR PROPRIETARIO
                            System.out.println("Digite o CPF do proprietário:");
                            String cpf = sc.nextLine();
                            excluirProprietario(cpf);
                            proprietarios = proprietarioDAO.listarTodos();
                            for (Proprietario dono : proprietarios) {
                                System.out.println(
                                        "ID: " + dono.getId() +
                                                ", Nome: " + dono.getNome() +
                                                ", CPF: " + dono.getCpf() +
                                                ", Telefone: " + dono.getTelefone() +
                                                ", Endereço: " + dono.getEndereco() +
                                                ", Email: " + dono.getEmail()
                                );
                            }
                            break;

                        case 3:
                            //ATUALIZAR PROPRIETÁRIO
                            cpf = sc.nextLine();
                            atualizarProprietario(cpf);
                            proprietarios = proprietarioDAO.listarTodos();
                            for (Proprietario dono : proprietarios) {
                                System.out.println(
                                        "ID: " + dono.getId() +
                                                ", Nome: " + dono.getNome() +
                                                ", CPF: " + dono.getCpf() +
                                                ", Telefone: " + dono.getTelefone() +
                                                ", Endereço: " + dono.getEndereco() +
                                                ", Email: " + dono.getEmail()
                                );
                            }

                    }
                    break;

                case 2:
                    System.out.println("O que deseja fazer:");
                    System.out.println("1 - Inserir animal");
                    System.out.println("2 - Excluir animal");
                    System.out.println("3 - Atualizar animal");
                    System.out.println("4 - Voltar");
                    opcao2 = sc.nextInt();
                    switch (opcao2) {
                        case 1:
                            Animal a = lerDadosAnimal();
                            animalDAO.inserir(a);
                            System.out.println("\n== LISTA DE ANIMAIS ==");
                            List<Animal> animais = animalDAO.listarTodos();
                            for (Animal bicho : animais) {
                                System.out.println(
                                        "ID: " + bicho.getId() +
                                                ", Nome: " + bicho.getNome() +
                                                ", Espécie: " + bicho.getEspecie() +
                                                ", Raça: " + bicho.getRaca() +
                                                ", Nascimento: " + bicho.getData_de_nascimento() +
                                                ", Peso: " + bicho.getPeso() +
                                                ", Proprietário ID: " + bicho.getId_proprietario()
                                );
                            }
                            break;

                        case 2:
                            //EXCLUIR ANIMAL
                            System.out.println("Digite o ID do Animal:");
                            int id = sc.nextInt();
                            excluirAnimal(id);
                            System.out.println("\n== LISTA DE ANIMAIS ==");
                            animais = animalDAO.listarTodos();
                            for (Animal bicho : animais) {
                                System.out.println(
                                        "ID: " + bicho.getId() +
                                                ", Nome: " + bicho.getNome() +
                                                ", Espécie: " + bicho.getEspecie() +
                                                ", Raça: " + bicho.getRaca() +
                                                ", Nascimento: " + bicho.getData_de_nascimento() +
                                                ", Peso: " + bicho.getPeso() +
                                                ", Proprietário ID: " + bicho.getId_proprietario()
                                );
                            }
                            break;
                        case 3:
                            //ATUALIZAR ANIMAL
                            System.out.println("Digite o ID do Animal:");
                             id = sc.nextInt();
                             atualizarAnimal(id);
                            System.out.println("\n== LISTA DE ANIMAIS ==");
                            animais = animalDAO.listarTodos();
                            for (Animal bicho : animais) {
                                System.out.println(
                                        "ID: " + bicho.getId() +
                                                ", Nome: " + bicho.getNome() +
                                                ", Espécie: " + bicho.getEspecie() +
                                                ", Raça: " + bicho.getRaca() +
                                                ", Nascimento: " + bicho.getData_de_nascimento() +
                                                ", Peso: " + bicho.getPeso() +
                                                ", Proprietário ID: " + bicho.getId_proprietario()
                                );
                            }
                    }
                    break;

            }
        } while (opcao != 0);

        System.out.println("Encerrando...");
    }
}

