package br.com.petcare.view;

import br.com.petcare.dao.AnimalDAO;
import br.com.petcare.dao.ProprietarioDAO;
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

    public static Proprietario excluirProprietario(String cpf){
        System.out.println("== EXCLUIR PROPRIETÁRIO ==");
        ProprietarioDAO proprietarioDAO = new ProprietarioDAO();

        Proprietario p = proprietarioDAO.buscarPorCPF(cpf);

        if(p == null){
            System.out.println("Proprietário não encontrado!");
            return null;
        }

        System.out.println("Confirmar exclusão de "+p.getNome()+"? (s/n)");
        System.out.println("Excluindo um proprietário, excluirá também todos os animais que pertecem a ele no banco.");
        System.out.println("Deseja continuar? (s/n)");
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("s")){
            proprietarioDAO.deletar(cpf);
            System.out.println("Proprietário de id "+p.getId()+" foi excluído com sucesso!");
            return p;
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

    public static Proprietario atualizarProprietario(String cpf){
        System.out.println("== ATUALIZAÇÃO DE PROPRIETÁRIO ==");

        ProprietarioDAO proprietarioDAO = new ProprietarioDAO();

         Proprietario p = proprietarioDAO.buscarPorCPF(cpf);

         if(p == null){
             System.out.println("Proprietário não encontrado");
             return null;
         }

        System.out.println("\nProprietário encontrado:");
        System.out.println("ID: "+p.getId());
        System.out.println("Nome: "+p.getNome());
        System.out.println("CPF: "+p.getCpf());
        System.out.println("Telefone: "+p.getTelefone());
        System.out.println("Endereço: "+p.getEndereco());
        System.out.println("Email: "+p.getEmail());

        System.out.println("\nDeseja atualizar os dados? (s/n)");
        String resp = sc.nextLine();

        if(!resp.equalsIgnoreCase("s")){
            System.out.println("Atualização cancelada");
        }

        System.out.println("Novo nome (ou ENTER para manter "+p.getNome()+")");
        String novoNome = sc.nextLine();
        if(!novoNome.isEmpty()){
            p.setNome(novoNome);
        }

        System.out.println("Novo telefone (ou ENTER para manter "+p.getTelefone());
        String novoTelefone = sc.nextLine();
        if(!novoTelefone.isEmpty()){
            p.setTelefone(novoTelefone);
        }

        System.out.println("Novo endereço (ou ENTER para manter "+p.getEndereco()+")");
        String novoEndereco = sc.nextLine();
        if(!novoEndereco.isEmpty()){
            p.setEndereco(novoEndereco);
        }

        System.out.println("Novo email (ou ENTER para manter "+p.getEmail()+")");
        String novoEmail = sc.nextLine();
        if(!novoEmail.isEmpty()){
            p.setEmail(novoEmail);
        }

        proprietarioDAO.atualizar(p);

        System.out.println("Proprietário atualizado com sucesso!");
        return p;




    }

    public static Animal atualizarAnimal(int id){
        System.out.println("== ATUALIZAÇÃO DE ANIMAL ==");

        AnimalDAO animalDAO = new AnimalDAO();

        Animal a = animalDAO.buscarPorId(id);

        if(a == null){
            System.out.println("Animal não encontrado");
            return null;
        }

        System.out.println("\nAnimal encontrado:");
        System.out.println("ID: "+a.getId());
        System.out.println("Nome: "+a.getNome());
        System.out.println("Espécie: "+a.getEspecie());
        System.out.println("Raça: "+a.getRaca());
        System.out.println("Data de Nascimento: "+a.getData_de_nascimento());
        System.out.println("Peso: "+a.getPeso());
        System.out.println("ID proprietário: "+a.getId_proprietario());

        System.out.println("\nDeseja atualizar os dados? (s/n)");
        String resp = sc.nextLine();

        if(!resp.equalsIgnoreCase("s")){
            System.out.println("Atualização cancelada");
        }

        System.out.println("Novo nome (ou ENTER para manter "+a.getNome()+")");
        String novoNome = sc.nextLine();
        if(!novoNome.isEmpty()){
            a.setNome(novoNome);
        }

        System.out.println("Nova Espécie (ou ENTER para manter "+a.getEspecie());
        String novaEspecie = sc.nextLine();
        if(!novaEspecie.isEmpty()){
            a.setEspecie(novaEspecie);
        }

        System.out.println("Nova raça (ou ENTER para manter "+a.getRaca()+")");
        String novaRaca = sc.nextLine();
        if(!novaRaca.isEmpty()){
            a.setRaca(novaRaca);
        }

        System.out.println("Nova data de nascimento (ou ENTER para manter " + a.getData_de_nascimento() + "):");
        String dataDigitada = sc.nextLine();

        if(!dataDigitada.isEmpty()){
            try {
                LocalDate novaData = LocalDate.parse(dataDigitada);
                a.setData_de_nascimento(novaData);
            } catch (Exception e) {
                System.out.println("Data inválida! Use o formato yyyy-MM-dd");
            }
        }

        System.out.println("Novo peso (ou digite 0 para manter "+a.getPeso()+")");
        double novoPeso = sc.nextDouble();
        if(!(novoPeso == 0)){
            a.setPeso(novoPeso);
        }

        System.out.println("Novo ID do proprietário (ou digite 0 para manter "+a.getId_proprietario()+")");
        int novoId_Prop = sc.nextInt();
        if(!(novoId_Prop == 0)){
            a.setId_proprietario(novoId_Prop);
        }



        animalDAO.atualizar(a);

        System.out.println("Animal atualizado com sucesso!");
        return a;




    }

    //public static Veterinario lerDadosVeterinario()
    //public static Veterinario excluirVeterinario(int id)
    //método atualizar pra veterinario

    // public static Consulta lerDadosConsulta()
    // public static Consulta excluirConsulta(int id)
    // public static Consulta atualizarConsulta;

    //public static Proprietario excluirProprietario(STRING CPF) FEITO
    //public static Proprietario atualizarProprietario;



}
