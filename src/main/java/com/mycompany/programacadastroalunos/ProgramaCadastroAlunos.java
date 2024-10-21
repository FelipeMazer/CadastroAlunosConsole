package com.mycompany.programacadastroalunos;


import com.mycompany.programacadastroalunos.Aluno;
import com.mycompany.programacadastroalunos.AlunoImplements;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ProgramaCadastroAlunos {
    public static void main(String[] args) {
        // Criando a conexão com o banco de dados
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/BancoAlunos", "postgres", "mazer123")) {
            AlunoImplements alunoImplements = new AlunoImplements(conn);

            // Chamando o método para listar todos os alunos
            
            List<Aluno> alunos = alunoImplements.listarTodos();
            for (Aluno a : alunos) {
                System.out.println(a.getNome() + " - " + a.getRa());
            }

            // Chamando o método para listar os alunos aprovados
            
            List<Aluno> aprovados = alunoImplements.listarAprovados();
            for (Aluno a : aprovados) {
                System.out.println(a.getNome());
            }

            // Chamando o método para listar os alunos reprovados
           
            List<Aluno> reprovados = alunoImplements.listarReprovados();
            for (Aluno a : reprovados) {
                System.out.println(a.getNome());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
