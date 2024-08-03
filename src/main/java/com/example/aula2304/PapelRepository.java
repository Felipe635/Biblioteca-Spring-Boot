package com.example.aula2304;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    Papel findByPapel(String papel); //busca se existe o papel no banco de dados
}
