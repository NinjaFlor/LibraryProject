import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Livro {

    private int cod_livro;
    private String titulo;
    private String autor;
    private String descricao;
    private int quantidade;
    private int cod_categoria;

    public Livro(int cod_livro, String titulo, String autor, String descricao, int quantidade, int cod_categoria) {
        this.cod_livro = cod_livro;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.cod_categoria = cod_categoria;
    }

    public int getCod_livro() {
        return cod_livro;
    }

    public void setCod_livro(int cod_livro) {
        this.cod_livro = cod_livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(int cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    // ADICIONAR
    public void adicionarLivro() throws SQLException {
        String sql = "INSERT INTO livro (titulo, autor, descricao, quantidade, cod_categoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.titulo);
            stmt.setString(2, this.autor);
            stmt.setString(3, this.descricao);
            stmt.setInt(4, this.quantidade);
            stmt.setInt(5, this.cod_categoria);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar livro: " + e.getMessage());
            throw e;
        }
    }

    // LISTAR
    public static List<Livro> listarLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("cod_livro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getInt("cod_categoria"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
            throw e;
        }
        return livros;
    }

    // BUSCAR
    public static Livro buscarLivro(int cod_livro) throws SQLException {
        String sql = "SELECT * FROM livro WHERE cod_livro = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cod_livro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getInt("cod_livro"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("descricao"),
                            rs.getInt("quantidade"),
                            rs.getInt("cod_categoria"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro: " + e.getMessage());
            throw e;
        }
        return null;
    }

    // ATUALIZAR
    public void atualizarLivro() throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, descricao = ?, quantidade = ?, cod_categoria = ? WHERE cod_livro = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.titulo);
            stmt.setString(2, this.autor);
            stmt.setString(3, this.descricao);
            stmt.setInt(4, this.quantidade);
            stmt.setInt(5, this.cod_categoria);
            stmt.setInt(6, this.cod_livro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
            throw e;
        }
    }

    // EXCLUIR
    public void excluirLivro() throws SQLException {
        String sql = "DELETE FROM livro WHERE cod_livro = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, this.cod_livro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir livro: " + e.getMessage());
            throw e;
        }
    }
}
