import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca"; // URL de conexão com o banco de dados.
    private static final String USER = "root"; // usuario padrão, substituir pelo usuário correto
    private static final String PASSWORD = "SuaSenha123@"; // senha genérica, substituir pela senha do MySQL correta

    // Construtor privado para impedir a instância da classe, pois ela funciona
    // apenas com métodos estáticos.

    private ConexaoDAO() {
        /**
         * Estabelece e retorna uma conexão com o banco de dados.
         * 
         * @return - Um objeto Connection para o banco de dados.
         * @throwa SQLException - Se ocorrer um erro ao tentar conectar ao banco.
         */

    }

    public static Connection getConnection() throws SQLException {
        try { // Tenta estabelecer a conexão usando a URL, usuário e senha definidos.
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) { //Utilizado caso apresente erro na conexão.
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Fecha a conexão com o banco de dados, se estiver aberta.
     * 
     * @param connection A conexão a ser fechada.
     */

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close(); // Fecha a conexão para liberar recursos.
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
