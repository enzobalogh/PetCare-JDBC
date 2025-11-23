package br.com.petcare.dao;

import br.com.petcare.database.ConnectionFactory;
import br.com.petcare.model.Veterinario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    public void inserir(Veterinario v) {
        String sql = "INSERT INTO Veterinarios (nome, crmv, especialidade, telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getNome());
            stmt.setString(2, v.getCrmv());
            stmt.setString(3, v.getEspecialidade());
            stmt.setString(4, v.getTelefone());

            stmt.executeUpdate();
            System.out.println("Veterinário inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Veterinario> listarTodos() {
        List<Veterinario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Veterinarios";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setId(rs.getInt("id_veterinario"));
                v.setNome(rs.getString("nome"));
                v.setCrmv(rs.getString("crmv"));
                v.setEspecialidade(rs.getString("especialidade"));
                v.setTelefone(rs.getString("telefone"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}