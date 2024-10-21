package com.mycompany.programacadastroalunos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String URL = "jdbc:postgresql://localhost:5433/BancoAlunos"; // URL do banco de dados
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "mazer123"; 
    
    private static Connection connection = null; 
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Carrega o driver do PostgreSQL
                Class.forName("org.postgresql.Driver");
                
                // Tenta estabelecer a conexão
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexao com o banco de dados estabelecida com sucesso!");
            } catch (ClassNotFoundException e) {
                // Erro ao carregar o driver do PostgreSQL
                System.err.println("Driver do PostgreSQL não encontrado: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                // Erro ao conectar ao banco (exemplo: senha ou URL errada)
                System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
                e.printStackTrace();
                connection = null;  // Certifique-se de que a conexão seja nula em caso de falha
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexao com o banco de dados fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
