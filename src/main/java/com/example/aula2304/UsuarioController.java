/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.aula2304;


import com.example.aula2304.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired //injeção de objeto
    private UsuarioRepository usuarioRepository; //cria a variavel do repositorio
    
    @GetMapping("/novo") //endpoint usuario/novo
    public String criarUsuario(Model model){ //cria um objeto model para encaminharmos o objeto usuario a pagina form
    model.addAttribute("usuario",
            new Usuario()); // esse "usuario" sera passado para o form em objeto
        return "form"; 
    }
    
    @PostMapping("/salvar")
    public String salvarUsuario(@Valid Usuario usuario, BindingResult result, Model model,  RedirectAttributes attributes){
        if(result.hasErrors()){
            return "form";
        }
        //se existir o email no banco, o findByEmail vai achar e colocar na variavel abaixo
        Usuario usr = usuarioRepository.findByEmail(usuario.getEmail());
        if(usr != null){
            model.addAttribute("loginExiste", "Login já existe!"); //cria a mensagem para colocar no form
            return "/form";
        }
        usuarioRepository.save(usuario); //salva o usuário no repositorio
        attributes.addFlashAttribute("message", "Usuário salvo com sucesso");
        return "redirect:/usuario/novo";
    }
    
    @RequestMapping("/admin/listar")
    public String listarUsuarios(Model model){
        model.addAttribute("usuarios", usuarioRepository.findAll()); //passa o usuario através do metodo findAll que vem do repositorio
        return "listar-usuarios";
    }

    @GetMapping("/admin/apagar/{id}")
    public String apagarUsuario(@PathVariable("id") long id, Model model){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id Inválido: " + id)); //pega o usuario do repositorio pelo id e caso não exista exibe a mensagem
        usuarioRepository.delete(usuario); //passa o usuario que deve ser deletado
        return "redirect:/usuario/admin/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") long id, Model model){
        Optional<Usuario> usuarioAntigo = usuarioRepository.findById(id);
        if(!usuarioAntigo.isPresent()){
            throw new IllegalArgumentException("Usuário inválido" + id);
        } //verifica se o usuario existe
        Usuario usuario = usuarioAntigo.get(); //insere o usuario antigo no usuario para ser editado na nova pagina
        model.addAttribute("usuario", usuario);
        return "/user/alterar-usuario";
    }

    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") long id, @Valid Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            usuario.setId(id); //passa pra pagina de edicao o id do usuario que estamos editando
            return "/user/alterar-usuario"; //se tiver erro volta pra pagina de edicao
        }
        usuarioRepository.save(usuario); //salva as alteracoes
        return "redirect:/usuario/admin/listar";
    }
}
