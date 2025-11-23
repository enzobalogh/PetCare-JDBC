package br.com.petcare.dao;

import br.com.petcare.database.ConnectionFactory;
import br.com.petcare.model.Consulta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void inserir(Consulta c) {
        String sql = "INSERT INTO Consultas (data_hora, diagnostico, valor, id_animal, id_veterinario) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(c.getData_hora()));
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
                c.setData_hora(rs.getDate("data_hora").toLocalDate());
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
}