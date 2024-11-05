import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private int cod_categoria;
    private String categoria;

    public Categoria(int cod_categoria, String categoria) {
        this.cod_categoria = cod_categoria;
        this.categoria = categoria;
    }

    public Categoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // ADICIONAR
    public void adicionarCategoria() throws SQLException {
        String sql = "INSERT INTO categoria (categoria) VALUES (?)";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.categoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar categoria: " + e.getMessage());
            throw e;
        }
    }

    // LISTAR
    public static List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("cod_categoria"),
                        rs.getString("categoria"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
            throw e;
        }
        return categorias;
    }

    // BUSCAR
    public static Categoria buscarCategoria(int cod_categoria) throws SQLException {
        String sql = "SELECT * FROM categoria WHERE cod_categoria = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cod_categoria);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(
                            rs.getInt("cod_categoria"),
                            rs.getString("categoria"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria: " + e.getMessage());
            throw e;
        }
        return null;
    }

    // ATUALIZAR
    public void atualizarCategoria() throws SQLException {
        String sql = "UPDATE categoria SET categoria = ? WHERE cod_categoria = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.categoria);
            stmt.setInt(2, this.cod_categoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria: " + e.getMessage());
            throw e;
        }
    }

    // EXCLUIR
    public void excluirCategoria() throws SQLException {
        String sql = "DELETE FROM categoria WHERE cod_categoria = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, this.cod_categoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir categoria: " + e.getMessage());
            throw e;
        }
    }
}
