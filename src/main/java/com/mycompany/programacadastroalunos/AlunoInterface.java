package com.mycompany.programacadastroalunos;

import java.util.List;

public interface AlunoInterface {
    void cadastrarAluno(Aluno aluno);
    Aluno consultarPorRa(String ra);
    List<Aluno> listarTodos();
    List<Aluno> listarAprovados();
    List<Aluno> listarReprovados();
    
}
