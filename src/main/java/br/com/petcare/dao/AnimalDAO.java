package br.com.petcare.dao;

import br.com.petcare.database.ConnectionFactory;
import br.com.petcare.model.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public void inserir(Animal a) {
        String sql = "INSERT INTO Animais (nome_animal, especie, raca, data_nascimento, peso, id_proprietario) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEspecie());
            stmt.setString(3, a.getRaca());
            stmt.setDate(4, java.sql.Date.valueOf(a.getData_de_nascimento()));
            stmt.setDouble(5, a.getPeso());
            stmt.setInt(6, a.getId_proprietario());

            stmt.executeUpdate();


            System.out.println("Animal "+a.getNome()+" inserido com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Animal> listarTodos() {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Animais";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Animal a = new Animal();
                a.setId(rs.getInt("id_animal"));
                a.setNome(rs.getString("nome_animal"));
                a.setEspecie(rs.getString("especie"));
                a.setRaca(rs.getString("raca"));
                a.setData_de_nascimento(rs.getDate("data_nascimento").toLocalDate());
                a.setPeso(rs.getDouble("peso"));
                a.setId_proprietario(rs.getInt("id_proprietario"));

                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return lista;

    }

    public void atualizar(Animal a){
        String sql = "UPDATE Animais SET nome_animal = ?, especie = ?, raca = ?, data_nascimento = ?, peso = ?, id_proprietario = ? WHERE id_animal = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getEspecie());
            stmt.setString(3, a.getRaca());
            stmt.setDate(4, java.sql.Date.valueOf(a.getData_de_nascimento()));
            stmt.setDouble(5, a.getPeso());
            stmt.setInt(6, a.getId_proprietario());

            stmt.executeUpdate();
            System.out.println("Animal "+a.getNome()+" inserido com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public void deletar(String id){
        String sql = "DELETE FROM Animais WHERE id_animal = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1,id);
            stmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }



    }
}
