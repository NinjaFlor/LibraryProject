import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String cpf_usuario;
    private String nome;
    private String endereco;
    private Long telefone;
    private String email;
    private String senha;
    private String tipo_usuario;

    public Usuario(String cpf_usuario, String nome, String endereco, Long telefone, String email, String senha,
            String tipo_usuario) {
        this.cpf_usuario = cpf_usuario;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.tipo_usuario = tipo_usuario;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    // ADICIONAR
    public void adicionarUsuario() throws SQLException {
        String sql = "INSERT INTO usuario (cpf_usuario, nome, endereco, telefone, email, senha, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.cpf_usuario);
            stmt.setString(2, this.nome);
            stmt.setString(3, this.endereco);
            stmt.setLong(4, this.telefone);
            stmt.setString(5, this.email);
            stmt.setString(6, this.senha);
            stmt.setString(7, this.tipo_usuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
            throw e;
        }
    }

    // LISTAR
    public static List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("cpf_usuario"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getLong("telefone"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("tipo_usuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            throw e;
        }
        return usuarios;
    }

    // BUSCAR
    public static Usuario buscarUsuario(String cpf_usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE cpf_usuario = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf_usuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getString("cpf_usuario"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getLong("telefone"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("tipo_usuario"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            throw e;
        }
        return null;
    }

    // ATUALIZAR
    public void atualizarUsuario() throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, endereco = ?, telefone = ?, email = ?, senha = ?, tipo_usuario = ? WHERE cpf_usuario = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.nome);
            stmt.setString(2, this.endereco);
            stmt.setLong(3, this.telefone);
            stmt.setString(4, this.email);
            stmt.setString(5, this.senha);
            stmt.setString(6, this.tipo_usuario);
            stmt.setString(7, this.cpf_usuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            throw e;
        }
    }

    // EXCLUIR
    public void excluirUsuario() throws SQLException {
        String sql = "DELETE FROM usuario WHERE cpf_usuario = ?";
        try (Connection connection = ConexaoDAO.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.cpf_usuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
            throw e;
        }
    }
}
