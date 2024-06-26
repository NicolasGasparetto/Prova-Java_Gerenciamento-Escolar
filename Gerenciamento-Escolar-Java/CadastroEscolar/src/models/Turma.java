package models;

import java.io.Serializable;

public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private static int proximoId = 1;
    private String nome;
    private String descricao;

    public Turma(String nome, String descricao) {
        this.id = proximoId++;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getProximoId() {
        return proximoId;
    }

    public static void setProximoId(int proximoId) {
        Turma.proximoId = proximoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
