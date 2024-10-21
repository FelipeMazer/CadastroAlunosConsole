package com.mycompany.programacadastroalunos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class AlunoImplements implements AlunoInterface {
    private Connection conn;
    
    public AlunoImplements(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrarAluno(Aluno aluno){
        String sql = "INSERT INTO alunos(ra, nome, nota_b1, nota_b2) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, aluno.getRa());
            stmt.setString(2, aluno.getNome());
            stmt.setDouble(3, aluno.getNb1());
            stmt.setDouble(4, aluno.getNb2());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " linha(s) inserida(s).");
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                aluno.setId(rs.getInt(1)); // Obtém o ID gerado pelo banco e atribui ao aluno
            }
            
            conn.commit();
        } catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Aluno consultarPorRa(String ra){
        Aluno aluno = null;
        String sql = "SELECT * FROM alunos WHERE ra = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, ra);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                aluno = new Aluno();
                aluno.setId(rs.getInt("idaluno"));
                aluno.setRa(rs.getString("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNb1(rs.getDouble("nota_b1"));
                aluno.setNb2(rs.getDouble("nota_b2"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return aluno;
    }

    @Override
    public List<Aluno> listarTodos(){
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            System.out.println("Todos Alunos Matriculados");
            while(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("idaluno"));
                aluno.setRa(rs.getString("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNb1(rs.getDouble("nota_b1"));
                aluno.setNb2(rs.getDouble("nota_b2"));
                alunos.add(aluno);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return alunos;
    }

    @Override
    public List<Aluno> listarAprovados(){
        List<Aluno> aprovados = new ArrayList<>();
        String sql = "SELECT * FROM alunos WHERE (nota_b1 + nota_b2)/2 >= 7";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            System.out.println("----------------");
            System.out.println("");
            System.out.println("Alunos Aprovados");
            while (rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("idaluno"));
                aluno.setRa(rs.getString("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNb1(rs.getDouble("nota_b1"));
                aluno.setNb2(rs.getDouble("nota_b2"));
                aprovados.add(aluno);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return aprovados;
    }

    @Override
    public List<Aluno> listarReprovados(){
        List<Aluno> reprovados = new ArrayList<>();
        String sql = "SELECT * FROM alunos WHERE (nota_b1 + nota_b2)/2 < 7";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            System.out.println("----------------");
            System.out.println("");
            System.out.println("Alunos Reprovados");
            while(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("idaluno"));
                aluno.setRa(rs.getString("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNb1(rs.getDouble("nota_b1"));
                aluno.setNb2(rs.getDouble("nota_b2"));
                reprovados.add(aluno);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reprovados;
        
    }
}
