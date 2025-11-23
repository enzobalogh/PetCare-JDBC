package br.com.petcare.dao;

import br.com.petcare.database.ConnectionFactory;
import br.com.petcare.model.Consulta;
import br.com.petcare.model.Veterinario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void inserir(Consulta c) {
        String sql = "INSERT INTO Consultas (data_hora, diagnostico, valor, id_animal, id_veterinario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(c.getData_hora()));
            stmt.setString(2, c.getDiagnostico());
            stmt.setDouble(3, c.getValor());
            stmt.setInt(4, c.getId_Animal());
            stmt.setInt(5, c.getId_Veterinario());

            stmt.executeUpdate();
            System.out.println("Consulta agendada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Consulta> listarTodas() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Consultas";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getInt("id_consulta"));
                c.setData_hora(rs.getTimestamp("data_hora").toLocalDateTime());
                c.setDiagnostico(rs.getString("diagnostico"));
                c.setValor(rs.getDouble("valor"));
                c.setId_Animal(rs.getInt("id_animal"));
                c.setId_Veterinario(rs.getInt("id_veterinario"));
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public void atualizar(Consulta c) {
        String sql = "UPDATE Consultas SET data_hora = ?, diagnostico = ?, valor = ?, id_animal = ?, id_veterinario = ? WHERE id_consulta = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(c.getData_hora()));
            stmt.setString(2, c.getDiagnostico());
            stmt.setDouble(3, c.getValor());
            stmt.setInt(4, c.getId_Animal());
            stmt.setInt(5, c.getId_Veterinario());
            stmt.setInt(6, c.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deletar(int id){
        String sql = "DELETE FROM Consulta WHERE id_consulta = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,id);
            stmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }



    }

}