package br.com.petcare.view;

import br.com.petcare.dao.AnimalDAO;
import br.com.petcare.dao.ConsultaDAO;
import br.com.petcare.dao.ProprietarioDAO;
import br.com.petcare.dao.VeterinarioDAO;
import br.com.petcare.model.Animal;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Proprietario;
import br.com.petcare.model.Veterinario;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        try {
            animal.setData_de_nascimento(LocalDate.parse(data));
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Inserindo data atual por padrão.");
            animal.setData_de_nascimento(LocalDate.now());
        }
        animal.setPeso(peso);
        animal.setId_proprietario(idProprietario);

        return animal;
    }

    public static Animal excluirAnimal(int id){
        System.out.println("== EXCLUIR ANIMAL ==");
        System.out.println("Digite o id do animal que deseja excluir:");
        id = sc.nextInt();
        sc.nextLine();

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
            return null;
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
                System.out.println("Data inválida! Mantendo a anterior.");
            }
        }

        System.out.println("Novo peso (ou digite 0 para manter "+a.getPeso()+")");
        double novoPeso = sc.nextDouble();
        if(novoPeso != 0){
            a.setPeso(novoPeso);
        }

        System.out.println("Novo ID do proprietário (ou digite 0 para manter "+a.getId_proprietario()+")");
        int novoId_Prop = sc.nextInt();
        sc.nextLine();
        if(novoId_Prop != 0){
            a.setId_proprietario(novoId_Prop);
        }

        animalDAO.atualizar(a);
        System.out.println("Animal atualizado com sucesso!");
        return a;
    }

    public static Proprietario lerDadosProprietario() {
        System.out.println("=== Cadastro de Proprietário ===");

        System.out.print("Nome do Proprietário: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Endereço: ");
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

    public static Proprietario excluirProprietario(String cpf){
        System.out.println("== EXCLUIR PROPRIETÁRIO ==");
        ProprietarioDAO proprietarioDAO = new ProprietarioDAO();

        Proprietario p = proprietarioDAO.buscarPorCPF(cpf);

        if(p == null){
            System.out.println("Proprietário não encontrado!");
            return null;
        }

        System.out.println("Confirmar exclusão de "+p.getNome()+"? (s/n)");
        System.out.println("ATENÇÃO: Excluindo um proprietário, excluirá também todos os animais dele.");
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("s")){
            proprietarioDAO.deletar(cpf);
            System.out.println("Proprietário de ID "+p.getId()+" excluído com sucesso!");
            return p;
        }
        System.out.println("Exclusão cancelada.");
        return null;
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
            return null;
        }

        System.out.println("Novo nome (ou ENTER para manter "+p.getNome()+")");
        String novoNome = sc.nextLine();
        if(!novoNome.isEmpty()){
            p.setNome(novoNome);
        }

        System.out.println("Novo telefone (ou ENTER para manter "+p.getTelefone()+")");
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

    public static Veterinario lerDadosVeterinario() {
        System.out.println("=== Cadastro de Veterinário ===");

        System.out.print("Nome do Veterinário: ");
        String nome = sc.nextLine();

        System.out.print("CRMV: ");
        String crmv = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Especialidade: ");
        String especialidade = sc.nextLine();

        Veterinario vet = new Veterinario();
        vet.setNome(nome);
        vet.setCrmv(crmv);
        vet.setTelefone(telefone);
        vet.setEspecialidade(especialidade);

        return vet;
    }

    public static Veterinario excluirVeterinario(int id) {
        System.out.println("== EXCLUIR VETERINÁRIO ==");
        System.out.println("ID do Veterinário a excluir: " + id);

        VeterinarioDAO dao = new VeterinarioDAO();
        Veterinario v = dao.buscarPorId(id);

        if (v == null) {
            System.out.println("Veterinário não encontrado!");
            return null;
        }

        System.out.println("Confirmar exclusão de " + v.getNome() + "? (s/n)");
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("s")) {
            dao.deletar(id);
            System.out.println("Veterinário excluído com sucesso!");
            return v;
        }
        System.out.println("Exclusão cancelada.");
        return null;
    }

    public static Veterinario atualizarVeterinario(int id) {
        System.out.println("== ATUALIZAÇÃO DE VETERINÁRIO ==");

        VeterinarioDAO dao = new VeterinarioDAO();
        Veterinario v = dao.buscarPorId(id);

        if (v == null) {
            System.out.println("Veterinário não encontrado.");
            return null;
        }

        System.out.println("\nVeterinário encontrado:");
        System.out.println("ID: " + v.getId());
        System.out.println("Nome: " + v.getNome());
        System.out.println("CRMV: " + v.getCrmv());
        System.out.println("Telefone: " + v.getTelefone());
        System.out.println("Especialidade: " + v.getEspecialidade());

        System.out.println("\nDeseja atualizar os dados? (s/n)");
        String resp = sc.nextLine();

        if (!resp.equalsIgnoreCase("s")) {
            System.out.println("Atualização cancelada");
            return null;
        }

        System.out.println("Novo nome (ou ENTER para manter " + v.getNome() + ")");
        String novoNome = sc.nextLine();
        if (!novoNome.isEmpty()) {
            v.setNome(novoNome);
        }

        System.out.println("Novo CRMV (ou ENTER para manter " + v.getCrmv() + ")");
        String novoCrmv = sc.nextLine();
        if (!novoCrmv.isEmpty()) {
            v.setCrmv(novoCrmv);
        }

        System.out.println("Novo Telefone (ou ENTER para manter " + v.getTelefone() + ")");
        String novoTel = sc.nextLine();
        if (!novoTel.isEmpty()) {
            v.setTelefone(novoTel);
        }

        System.out.println("Nova Especialidade (ou ENTER para manter " + v.getEspecialidade() + ")");
        String novaEsp = sc.nextLine();
        if (!novaEsp.isEmpty()) {
            v.setEspecialidade(novaEsp);
        }

        dao.atualizar(v);
        System.out.println("Veterinário atualizado com sucesso!");
        return v;
    }

    public static Consulta lerDadosConsulta() {
        System.out.println("=== Agendamento de Consulta ===");

        System.out.print("Data da Consulta (yyyy-mm-dd): ");
        String data = sc.nextLine();

        System.out.print("Motivo/Observação: ");
        String motivo = sc.nextLine();

        System.out.print("ID do Animal: ");
        int idAnimal = sc.nextInt();
        sc.nextLine();

        System.out.print("ID do Veterinário: ");
        int idVet = sc.nextInt();
        sc.nextLine();

        Consulta c = new Consulta();
        try {
            c.setData(LocalDate.parse(data));
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Inserindo data atual.");
            c.setData(LocalDate.now());
        }
        c.setMotivo(motivo);
        c.setIdAnimal(idAnimal);
        c.setIdVeterinario(idVet);

        return c;
    }

    public static Consulta excluirConsulta(int id) {
        System.out.println("== EXCLUIR/CANCELAR CONSULTA ==");
        System.out.println("ID da consulta: " + id);

        ConsultaDAO dao = new ConsultaDAO();
        Consulta c = dao.buscarPorId(id);

        if (c == null) {
            System.out.println("Consulta não encontrada!");
            return null;
        }

        System.out.println("Confirmar cancelamento da consulta do dia " + c.getData() + "? (s/n)");
        String resp = sc.nextLine();

        if (resp.equalsIgnoreCase("s")) {
            dao.deletar(id);
            System.out.println("Consulta cancelada com sucesso!");
            return c;
        }
        System.out.println("Cancelamento abortado.");
        return null;
    }

    public static Consulta atualizarConsulta(int id) {
        System.out.println("== ATUALIZAÇÃO DE CONSULTA ==");

        ConsultaDAO dao = new ConsultaDAO();
        Consulta c = dao.buscarPorId(id);

        if (c == null) {
            System.out.println("Consulta não encontrada.");
            return null;
        }

        System.out.println("\nConsulta encontrada:");
        System.out.println("ID: " + c.getId());
        System.out.println("Data: " + c.getData());
        System.out.println("Motivo: " + c.getMotivo());
        System.out.println("ID Animal: " + c.getIdAnimal());
        System.out.println("ID Veterinário: " + c.getIdVeterinario());

        System.out.println("\nDeseja atualizar os dados? (s/n)");
        String resp = sc.nextLine();

        if (!resp.equalsIgnoreCase("s")) {
            System.out.println("Atualização cancelada");
            return null;
        }

        System.out.println("Nova Data (ou ENTER para manter " + c.getData() + ")");
        String dataDigitada = sc.nextLine();
        if (!dataDigitada.isEmpty()) {
            try {
                LocalDate novaData = LocalDate.parse(dataDigitada);
                c.setData(novaData);
            } catch (Exception e) {
                System.out.println("Data inválida! Mantendo a anterior.");
            }
        }

        System.out.println("Novo Motivo (ou ENTER para manter " + c.getMotivo() + ")");
        String novoMotivo = sc.nextLine();
        if (!novoMotivo.isEmpty()) {
            c.setMotivo(novoMotivo);
        }

        System.out.println("Novo ID Animal (ou 0 para manter " + c.getIdAnimal() + ")");
        int novoIdAnimal = sc.nextInt();
        sc.nextLine();
        if (novoIdAnimal != 0) {
            c.setIdAnimal(novoIdAnimal);
        }

        System.out.println("Novo ID Veterinário (ou 0 para manter " + c.getIdVeterinario() + ")");
        int novoIdVet = sc.nextInt();
        sc.nextLine();
        if (novoIdVet != 0) {
            c.setIdVeterinario(novoIdVet);
        }

        dao.atualizar(c);
        System.out.println("Consulta atualizada com sucesso!");
        return c;
    }
}