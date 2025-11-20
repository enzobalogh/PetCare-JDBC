package br.com.petcare.dao;

import br.com.petcare.database.ConnectionFactory;
import br.com.petcare.model.Proprietario;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO {
    public static void inserir(Proprietario p) {
        String sql = "INSERT INTO Proprietario (nome_proprietarios, cpf, telefone, endereco, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getEndereco());
            stmt.setString(5, p.getEmail());

            stmt.executeUpdate();


            System.out.println("Proprietário "+p.getNome()+" inserido com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Proprietario> listarTodos() {
        List<Proprietario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Proprietario";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proprietario p = new Proprietario();
                p.setId(rs.getInt("id_proprietario"));
                p.setNome(rs.getString("nome_proprietarios"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setEndereco(rs.getString("endereco"));
                p.setEmail(rs.getString("email"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return lista;

    }

    public void atualizar(Proprietario p){
        String sql = "UPDATE Proprietario SET nome_proprietarios = ?, telefone = ?, endereco = ?, email = ? WHERE cpf = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setString(3, p.getEndereco());
            stmt.setString(4, p.getEmail());
            stmt.setString(5, p.getCpf());

            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void deletar(String cpf){
        String sql = "DELETE FROM Proprietario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,cpf);
            stmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }



    }
}
