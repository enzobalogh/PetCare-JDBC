package br.com.petcare.view;

import br.com.petcare.dao.AnimalDAO;
import br.com.petcare.model.Animal;
import br.com.petcare.model.Proprietario;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);

    public static Animal lerDadosAnimal() {
        System.out.println("=== Cadastro de Animal ===");

        System.out.print("Nome do animal: ");
        String nome = sc.nextLine();

        System.out.print("Espécie: ");
        String especie = sc.nextLine();

        System.out.print("Raça: ");
        String raca = sc.nextLine();

        System.out.print("Data de nascimento (yyyy-mm-dd): ");
        String data = sc.nextLine();

        System.out.print("Peso: ");
        double peso = sc.nextDouble();
        sc.nextLine(); // limpar buffer

        System.out.print("ID do proprietário: ");
        int idProprietario = sc.nextInt();
        sc.nextLine(); // limpar buffer

        Animal animal = new Animal();
        animal.setNome(nome);
        animal.setEspecie(especie);
        animal.setRaca(raca);
        animal.setData_de_nascimento(LocalDate.parse(data));
        animal.setPeso(peso);
        animal.setId_proprietario(idProprietario);

        return animal;
    }

    public static Proprietario lerDadosProprietario() {
        System.out.println("=== Cadastro de Prorietário ===");

        System.out.print("Nome do Proprietário: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Endereço");
        String endereco = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();



        Proprietario proprietario = new Proprietario();
        proprietario.setNome(nome);
        proprietario.setCpf(cpf);
        proprietario.setTelefone(telefone);
        proprietario.setEndereco(endereco);
        proprietario.setEmail(email);

        return proprietario;
    }

}
