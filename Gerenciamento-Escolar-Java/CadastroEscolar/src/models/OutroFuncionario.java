package models;

import java.io.Serializable;

public class OutroFuncionario extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int proximoId = 1;
    private String cargo;

    public OutroFuncionario(String nome, int idade, String cargo) {
        super(nome, idade);
        this.cargo = cargo;
        this.setId(proximoId++); 
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "OutroFuncionario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", idade=" + getIdade() +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
