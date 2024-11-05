import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Sistema de Biblioteca =====");
            System.out.println("1. Gerenciar Usuários");
            System.out.println("2. Gerenciar Categorias");
            System.out.println("3. Gerenciar Livros");
            System.out.println("4. Gerenciar Empréstimos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> gerenciarUsuarios();
                case 2 -> gerenciarCategorias();
                case 3 -> gerenciarLivros();
                case 4 -> gerenciarEmprestimos();
                case 5 -> {
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void gerenciarUsuarios() {
        System.out.println("\n===== Gerenciamento de Usuários =====");
        System.out.println("1. Adicionar Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Atualizar Usuário");
        System.out.println("4. Excluir Usuário");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        try {
            switch (opcao) {
                case 1 -> {
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Telefone: ");
                    Long telefone = scanner.nextLong();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Tipo de Usuário: ");
                    String tipo = scanner.nextLine();
                    Usuario usuario = new Usuario(cpf, nome, endereco, telefone, email, senha, tipo);
                    usuario.adicionarUsuario();
                    System.out.println("Usuário adicionado com sucesso.");
                }
                case 2 -> {
                    List<Usuario> usuarios = Usuario.listarUsuarios();
                    System.out.println("Lista de Usuários:");
                    for (Usuario u : usuarios) {
                        System.out.println("CPF: " + u.getCpf_usuario() + " - Nome: " + u.getNome());
                    }
                }
                case 3 -> {
                    System.out.print("CPF do usuário a ser atualizado: ");
                    String cpf = scanner.nextLine();
                    Usuario usuario = Usuario.buscarUsuario(cpf);
                    if (usuario != null) {
                        System.out.print("Novo Nome: ");
                        usuario.setNome(scanner.nextLine());
                        System.out.print("Novo Endereço: ");
                        usuario.setEndereco(scanner.nextLine());
                        System.out.print("Novo Telefone: ");
                        usuario.setTelefone(scanner.nextLong());
                        scanner.nextLine(); // Consumir a quebra de linha
                        System.out.print("Novo Email: ");
                        usuario.setEmail(scanner.nextLine());
                        usuario.atualizarUsuario();
                        System.out.println("Usuário atualizado com sucesso.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("CPF do usuário a ser excluído: ");
                    String cpf = scanner.nextLine();
                    Usuario usuario = Usuario.buscarUsuario(cpf);
                    if (usuario != null) {
                        usuario.excluirUsuario();
                        System.out.println("Usuário excluído com sucesso.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                }
                default -> System.out.println("Opção inválida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void gerenciarCategorias() {
        System.out.println("\n===== Gerenciamento de Categorias =====");
        System.out.println("1. Adicionar Categoria");
        System.out.println("2. Listar Categorias");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        try {
            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome da Categoria: ");
                    String nome = scanner.nextLine();
                    Categoria categoria = new Categoria(nome);
                    categoria.adicionarCategoria();
                    System.out.println("Categoria adicionada com sucesso.");
                }
                case 2 -> {
                    List<Categoria> categorias = Categoria.listarCategorias();
                    System.out.println("Lista de Categorias:");
                    for (Categoria c : categorias) {
                        System.out.println("ID: " + c.getCod_categoria() + " - Nome: " + c.getCategoria());
                    }
                }
                default -> System.out.println("Opção inválida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void gerenciarLivros() {
        System.out.println("\n===== Gerenciamento de Livros =====");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Listar Livros");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        try {
            switch (opcao) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Código da Categoria: ");
                    int codCategoria = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    Livro livro = new Livro(0, titulo, autor, descricao, quantidade, codCategoria);
                    livro.adicionarLivro();
                    System.out.println("Livro adicionado com sucesso.");
                }
                case 2 -> {
                    List<Livro> livros = Livro.listarLivros();
                    System.out.println("Lista de Livros:");
                    for (Livro l : livros) {
                        System.out.println("ID: " + l.getCod_livro() + " - Título: " + l.getTitulo());
                    }
                }
                default -> System.out.println("Opção inválida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void gerenciarEmprestimos() {
        System.out.println("\n===== Gerenciamento de Empréstimos =====");
        System.out.println("1. Adicionar Empréstimo");
        System.out.println("2. Listar Empréstimos");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        try {
            switch (opcao) {
                case 1 -> {
                    System.out.print("Status: ");
                    String status = scanner.nextLine();
                    System.out.print("Data de Empréstimo (yyyy-MM-dd): ");
                    Date dataEmprestimo = java.sql.Date.valueOf(scanner.nextLine());
                    System.out.print("Código do Livro: ");
                    int codLivro = scanner.nextInt();
                    System.out.print("CPF do Usuário: ");
                    String cpfUsuario = scanner.next();
                    scanner.nextLine(); // Consumir a quebra de linha
                    Emprestimo emprestimo = new Emprestimo(0, status, dataEmprestimo, null, codLivro, cpfUsuario);
                    emprestimo.adicionarEmprestimo();
                    System.out.println("Empréstimo adicionado com sucesso.");
                }
                case 2 -> {
                    List<Emprestimo> emprestimos = Emprestimo.listarEmprestimos();
                    System.out.println("Lista de Empréstimos:");
                    for (Emprestimo e : emprestimos) {
                        System.out.println("ID: " + e.getCod_emprestimo() + " - Status: " + e.getStatus());
                    }
                }
                default -> System.out.println("Opção inválida.");
            }
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
