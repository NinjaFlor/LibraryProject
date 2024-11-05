import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Emprestimo {
    private int cod_emprestimo;
    private String status;
    private Date data_emprestimo;
    private Date data_devolucao;
    private int cod_livro;
    private String cpf_usuario;

    public Emprestimo(int cod_emprestimo, String status, Date data_emprestimo, Date data_devolucao, int cod_livro,
            String cpf_usuario) {
        this.cod_emprestimo = cod_emprestimo;
        this.status = status;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
        this.cod_livro = cod_livro;
        this.cpf_usuario = cpf_usuario;
    }

    public int getCod_emprestimo() {
        return cod_emprestimo;
    }

    public String getStatus() {
        return status;
    }

    public Date getData_emprestimo() {
        return data_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public int getCod_livro() {
        return cod_livro;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    // ADICIONAR
    public void adicionarEmprestimo() throws SQLException {
        String sql = "INSERT INTO emprestimo (status, data_emprestimo, data_devolucao, cod_livro, cpf_usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.status);
            stmt.setDate(2, new java.sql.Date(this.data_emprestimo.getTime()));
            stmt.setDate(3, this.data_devolucao != null ? new java.sql.Date(this.data_devolucao.getTime()) : null);
            stmt.setInt(4, this.cod_livro);
            stmt.setString(5, this.cpf_usuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar empréstimo: " + e.getMessage());
            throw e;
        }
    }

    // LISTAR
    public static List<Emprestimo> listarEmprestimos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("cod_emprestimo"),
                        rs.getString("status"),
                        rs.getDate("data_emprestimo"),
                        rs.getDate("data_devolucao"),
                        rs.getInt("cod_livro"),
                        rs.getString("cpf_usuario"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
            throw e;
        }
        return emprestimos;
    }

    // BUSCAR
    public static Emprestimo buscarEmprestimo(int cod_emprestimo) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE cod_emprestimo = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cod_emprestimo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Emprestimo(
                            rs.getInt("cod_emprestimo"),
                            rs.getString("status"),
                            rs.getDate("data_emprestimo"),
                            rs.getDate("data_devolucao"),
                            rs.getInt("cod_livro"),
                            rs.getString("cpf_usuario"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
            throw e;
        }
        return null;
    }

    // ATUALIZAR
    public void atualizarEmprestimo() throws SQLException {
        String sql = "UPDATE emprestimo SET status = ?, data_emprestimo = ?, data_devolucao = ?, cod_livro = ?, cpf_usuario = ? WHERE cod_emprestimo = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.status);
            stmt.setDate(2, new java.sql.Date(this.data_emprestimo.getTime()));
            stmt.setDate(3, this.data_devolucao != null ? new java.sql.Date(this.data_devolucao.getTime()) : null);
            stmt.setInt(4, this.cod_livro);
            stmt.setString(5, this.cpf_usuario);
            stmt.setInt(6, this.cod_emprestimo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar empréstimo: " + e.getMessage());
            throw e;
        }
    }

    // EXCLUIR
    public void excluirEmprestimo() throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE cod_emprestimo = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, this.cod_emprestimo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir empréstimo: " + e.getMessage());
            throw e;
        }
    }
}
