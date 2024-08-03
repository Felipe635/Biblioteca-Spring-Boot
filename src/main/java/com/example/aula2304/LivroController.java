package com.example.aula2304;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/novo")
    public String criarLivro(Model model){
        model.addAttribute("livro", new Livro());
        return "form-livro";
    }

    @PostMapping("/salvar")
    public String salvarLivro(@Valid Livro livro, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            return "form-livro";
        }
        Livro li = livroRepository.findByIsbn(livro.getIsbn());
        if(li != null){
            model.addAttribute("livroExiste", "Livro já está cadastrado!"); //cria a mensagem para colocar no form
            return "/form-livro";
        }
        livroRepository.save(livro);
        attributes.addFlashAttribute("message", "Livro salvo com sucesso");
        return "redirect:/livro/novo";
    }

   @RequestMapping("/listar")
    public String listarLivros(Model model){
       model.addAttribute("livros", livroRepository.findAll());
       return "livros";
   }

    @RequestMapping("/listar-livros")
    public String listarLivros2(Model model){
        //leva a tela para editar ou excluir os livros
        model.addAttribute("livros", livroRepository.findAll());
        return "listar-livros";
    }

    @GetMapping("/apagar/{id}")
    public String apagarLivro(@PathVariable ("id") long id, Model model){
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id Inválido: " + id));
        livroRepository.delete(livro);
        return "redirect:/livro/listar-livros";
    }

    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable("id") long id, Model model){
        Optional<Livro> livroAntigo = livroRepository.findById(id);
        if(!livroAntigo.isPresent()){
            throw new IllegalArgumentException("Livro inválido" + id);
        } //verifica se o livro existe
        Livro livro = livroAntigo.get();
        model.addAttribute("livro", livro);
        return "alterar-livro";
    }

    @PostMapping("/editar/{id}")
    public String editarLivro(@PathVariable("id") long id, @Valid Livro livro, BindingResult result){
        if(result.hasErrors()){
            livro.setId(id); //passa pra pagina de edicao o id do livro que estamos editando
            return "alterar-livro"; //se tiver erro volta pra pagina de edicao
        }
        livroRepository.save(livro); //salva as alteracoes
        return "redirect:/livro/listar-livros";
    }


}
