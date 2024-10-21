package com.mycompany.programacadastroalunos;


public class Aluno {
    private int id;
    private String ra;
    private String nome;
    private double nota_b1;
    private double nota_b2;
  
    public Aluno(){
        }   
   
    
    public  Aluno(String ra, String nome, double nb1, double nb2){
        this.ra = ra;
        this.nome = nome;
        this.nota_b1 = nb1;
        this.nota_b2 = nb2;
    }       
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getRa(){
       return ra;
    }
   
    public void setRa(String ra){
        this.ra = ra;
    }
    
    public double getNb1(){
        return nota_b1;
    }
    
    public void setNb1(double nb1){
        this.nota_b1 = nb1;
    }
    
    public double getNb2(){
        return nota_b2;
    }
    
    public void setNb2(double nb2){
        this.nota_b2 = nb2;
    }
    
    
}
