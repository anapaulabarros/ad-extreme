package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AnuncioController {

    @Autowired
    private AnuncioServiceImpl anuncioService;

    @Autowired
    private AnuncioRepository anuncioRep;
    
    @Autowired
    private UsuarioRepository usuarioRep;
    
    @Autowired
	UsuarioService usuarioService;

    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.GET)
    public ModelAndView getPageCadastrarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();

        model.addObject("tipos", anuncioForm.getTipos());
        model.setViewName("user/cadastrar_anuncio");

        return model;
    }

    @RequestMapping(value = "/user/listar/anuncios", method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();

        model.addObject("anuncios", anuncioRep.findAll());

        model.setViewName("user/listar_anuncios");

        return model;
    }
 
    @RequestMapping(value = "/user/listar/meus-anuncios", method = RequestMethod.GET)
	public String getPageListarMeusAnuncios(Model model){
    	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
        
        Usuario usuarioLogado = usuarioRep.findByEmail(loginUsuario);
        
		model.addAttribute("anuncios",  anuncioService.getAnuncioByIdUser(usuarioLogado.getId()));
		return "user/listar_meus_anuncios";
	}
    
    @RequestMapping(value = "/user/cadastrar/anuncio", method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastrarAnuncio(anuncioForm);
        }
        
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Long userId = usuarioRep.findByEmail(user.getName()).getId();
    
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());
        anuncio.setUserId(userId);
        anuncioService.create(anuncio);

        attributes.addFlashAttribute("mensagem", "Anúncio cadastrado com sucesso!");
        return new ModelAndView("redirect:/user/cadastrar/anuncio");
    }


}
