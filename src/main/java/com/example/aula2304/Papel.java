package com.example.aula2304;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Papel {

    public Papel(){}

    public Papel(String papel) {
        super();
        this.papel = papel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "papeis", fetch = FetchType.EAGER) //relação muitos papeis para muitos usuarios - o atributo "papeis" está definido na classe Usuarios
    private List<Usuario> usuarios;

    private String papel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
