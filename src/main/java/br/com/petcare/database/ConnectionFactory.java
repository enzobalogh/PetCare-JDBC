package br.com.petcare.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import static br.com.petcare.view.Menu.sc;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/clinicaveterinariadb";
    private static final String USER = "postgres";
    private static String PASSWORD;

    public static void testarConexao(){
        try (Connection con = getConnection()){
            System.out.println("Conexão com o banco realizada com sucesso!");
        } catch (Exception e){
            throw new RuntimeException("Falha ao conectar no banco: "+e.getMessage());
        }
    }

    public static void pedeSenha() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a senha do seu banco:");
        PASSWORD = sc.nextLine();
    }
    //Meu pgAdmin não tem senha
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e){
            throw new RuntimeException("Erro ao conectar ao banco "+ e.getMessage());
        }
    }

}
