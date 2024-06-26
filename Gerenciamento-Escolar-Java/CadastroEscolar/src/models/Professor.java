package models;

import java.io.Serializable;

public class Professor extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Materia materia;

    public Professor(String nome, int idade, Materia materia) {
        super(nome, idade);
        this.materia = materia;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
