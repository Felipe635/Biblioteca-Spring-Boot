package com.example.aula2304;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarregarDados implements CommandLineRunner {

    @Autowired
    private PapelRepository papelRepository;
    //maneira de criar papeis no banco de dados de forma automatica assim que o projeto roda
    @Override
    public void run(String... args) throws Exception {
        String [] papeis = {"ADMIN", "USER", "BIBLIOTECARIO"};

        for (String papelString: papeis){ //percorre a lista com os tipos de papeis
            Papel papel = papelRepository.findByPapel(papelString); //verifica se o papel existe no banco
            if(papel == null) { //se o papel não existir no banco, ee é criado abaixo
                papel = new Papel(papelString);
                papelRepository.save(papel);
            }
        }
    }
}
