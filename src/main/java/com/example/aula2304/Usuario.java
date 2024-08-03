/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aula2304;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;
    
    @NotEmpty(message = "O nome é obrigatório")
    private @Getter @Setter String nome;
    
    @NotEmpty(message = "O email é obrigatório")
    private @Getter @Setter String email;
    
    @NotEmpty(message = "A senha é obrigatório")
    @Size(min = 3, message = "A senha deve conter no mínimo 3 caracteres")
    private @Getter @Setter String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="usuario_papel",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "papel_id")) //cria uma terceira tabela chamado usuario_papel com o id das duas tabelas como chaves estrangeiras
    private List<Papel> papeis;

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }
}
