package com.example.aula2304;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Livro findByIsbn(Integer isbn);
}
