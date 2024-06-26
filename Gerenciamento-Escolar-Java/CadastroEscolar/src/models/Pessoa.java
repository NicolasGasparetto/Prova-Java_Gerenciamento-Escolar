package models;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected static int proximoId = 1;
    protected String nome;
    protected int idade;

    public Pessoa(String nome, int idade) {
        this.id = proximoId++;
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public static int getProximoId() {
        return proximoId;
    }

    public static void setProximoId(int proximoId) {
        Pessoa.proximoId = proximoId;
    }

    public String exibirInformacoes() {
        return "ID: " + id + "\nNome: " + nome + "\nIdade: " + idade;
    }
}
