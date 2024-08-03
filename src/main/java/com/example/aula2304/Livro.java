package com.example.aula2304;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    @NotEmpty(message = "O titulo é obrigatório")
    private @Getter @Setter String titulo;

    @NotEmpty(message = "O autor é obrigatório")
    private @Getter @Setter String autor;

    private @Getter @Setter double preco;

    private @Getter @Setter Integer isbn;

    @NotEmpty(message = "A descrição é obrigatória")
    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
    private @Getter @Setter String descricao;
}
