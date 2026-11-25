package br.inatel.c07;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// EXERCÍCIO: complete cada método no espaço indicado (onde está o TODO).
// Use sempre PreparedStatement com parâmetros (?) e feche os recursos no finally.
public class PlantaDAO extends ConnectionDAO {

    // Questão 1: inserir uma planta.
    // Deve inserir na tabela planta e devolver true se der certo, false se houver erro.
    public boolean inserirPlanta(Planta planta) {
        boolean sucesso = false;
        String sql = "INSERT INTO planta (nome, especie, preco, estoque) VALUES (?, ?, ?, ?)";
        try {
            connectToDb();
            pst = connection.prepareStatement(sql);
            pst.setString(1, planta.getNome());
            pst.setString(2, planta.getEspecie());
            pst.setDouble(3, planta.getPreco());
            pst.setInt(4, planta.getEstoque());
            pst.execute();
            sucesso = true;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Erro ao inserir planta: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return sucesso;
    }

    // Questão 2: listar todas as plantas, ordenadas pelo id.
    // Deve devolver uma lista de objetos Planta (use rs.next() para percorrer).
    public List<Planta> listarPlantas() {
        List<Planta> plantas = new ArrayList<>();
        String sql = "SELECT * FROM planta ORDER BY id";
        try {
            connectToDb();
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                plantas.add(new Planta(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getDouble("preco"),
                        rs.getInt("estoque")));
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Erro ao listar plantas: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return plantas;
    }

    // Questão 3: buscar as plantas de uma espécie.
    // Deve devolver a lista das plantas cuja espécie seja igual ao parâmetro.
    public List<Planta> buscarPorEspecie(String especie) {
        List<Planta> plantas = new ArrayList<>();
        String sql = "SELECT * FROM planta WHERE especie = ? ORDER BY id";
        try {
            connectToDb();
            pst = connection.prepareStatement(sql);
            pst.setString(1, especie);
            rs = pst.executeQuery();
            while (rs.next()) {
                plantas.add(new Planta(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getDouble("preco"),
                        rs.getInt("estoque")));
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Erro ao buscar por espécie: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return plantas;
    }

    // Questão 4: atualizar o preço de uma planta pelo id.
    // Deve devolver true se alguma linha foi alterada (executeUpdate() > 0), senão false.
    public boolean atualizarPreco(int id, double novoPreco) {
        boolean sucesso = false;
        String sql = "UPDATE planta SET preco = ? WHERE id = ?";
        try {
            connectToDb();
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, novoPreco);
            pst.setInt(2, id);
            sucesso = pst.executeUpdate() > 0;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Erro ao atualizar preço: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return sucesso;
    }

    // Questão 5: remover uma planta pelo id.
    // Deve devolver true se alguma linha foi removida, senão false.
    public boolean removerPlanta(int id) {
        boolean sucesso = false;
        String sql = "DELETE FROM planta WHERE id = ?";
        try {
            connectToDb();
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            sucesso = pst.executeUpdate() > 0;
        } catch (SQLException | NullPointerException e) {
            System.out.println("Erro ao remover planta: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return sucesso;
    }

    // Questão 6 (Desafio): valor total do estoque.
    // Deve devolver a soma de preco * estoque de todas as plantas.
    // Dica: SELECT COALESCE(SUM(preco * estoque), 0) AS total FROM planta
    public double valorTotalEstoque() {
        double total = 0.0;
        String sql = "SELECT COALESCE(SUM(preco * estoque), 0) AS total FROM planta";
        try {
            connectToDb();
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Erro ao calcular valor do estoque: " + e.getMessage());
        } finally {
            fecharRecursos();
        }
        return total;
    }

    // Já pronto: libera os recursos na ordem inversa da abertura.
    // Chame este método dentro do finally de cada questão.
    private void fecharRecursos() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (st != null) st.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
        rs = null;
        pst = null;
        st = null;
    }
}