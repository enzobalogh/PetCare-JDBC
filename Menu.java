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
        sc.nextLine();

        System.out.print("ID do proprietário: ");
        int idProprietario = sc.nextInt();
        sc.nextLine();

        Animal animal = new Animal();
        animal.setNome(nome);
        animal.setEspecie(especie);
        animal.setRaca(raca);
        animal.setData_de_nascimento(LocalDate.parse(data));
        animal.setPeso(peso);
        animal.setId_proprietario(idProprietario);

        return animal;
    }
    public static Animal excluirAnimal(int id){
        System.out.println("== EXCLUIR ANIMAL ==");
        System.out.println("Digite o id do animal que deseja exlcuir:");
        id = sc.nextInt();
        AnimalDAO animaldao = new AnimalDAO();

        Animal a = animaldao.buscarPorId(id);

        if(a == null){
            System.out.println("Animal não encontrado!");
            return null;
        }

        System.out.println("Confirmar exclusão de "+a.getNome()+"? (s/n)");
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("s")){
            animaldao.deletar(id);
            System.out.println("Animal de id "+a.getId()+" foi excluído com sucesso!");
            return a;
        }
        System.out.println("Exclusão cancelada.");
        return null;

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
    //public static Veterinario lerDadosVeterinario()
    //public static Veterinario excluirVeterinario(int id)
    //método atualizar pra veterinario

    // public static Consulta lerDadosConsulta()
    // public static Consulta excluirConsulta(int id)
    // public static Consulta atualizarConsulta;

    //public static Proprietario excluirProprietario(int id)
    //public static Proprietario atualizarProprietario;



}
