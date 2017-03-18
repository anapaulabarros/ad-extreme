package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;
import br.edu.ufcg.computacao.si1.util.*;

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
public class CompanyAnuncioController {

    @Autowired
    private AnuncioServiceImpl anuncioService;
    
    @Autowired
    private UsuarioRepository usuarioRep; 
    
    @Autowired
	private UsuarioService usuarioService;

    @RequestMapping(value = Util.ROTA_COMPANY_CADASTRAR_ANUNCIO, method = RequestMethod.GET)
    public ModelAndView getPageCadastarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();
        
        model.addObject(Util.TIPOS, anuncioForm.getTipos());
        model.setViewName(Util.COMPANY_CADASTRAR_ANUNCIO);

        return model;
    }
 
    @RequestMapping(value = Util.ROTA_COMPANY_LISTAR_MEUS_ANUNCIOS, method = RequestMethod.GET)
   	public String getPageListarMeusAnuncios(Model model){
       	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
       
        Usuario usuarioLogado = usuarioRep.findByEmail(loginUsuario);
       
   		model.addAttribute(Util.ANUNCIOS, usuarioService.getAnuncios(usuarioLogado.getId()));
   		return Util.USER_LISTAR_MEUS_ANUNCIOS;
   	}
    
    @RequestMapping(value = Util.ROTA_COMPANY_LISTAR_ANUNCIOS, method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();

        model.addObject(Util.ANUNCIOS, anuncioService.getAnuncioRepository().findAll());

        model.setViewName(Util.COMPANY_LISTAR_ANUNCIOS);

        return model;
    }

    @RequestMapping(value = Util.ROTA_COMPANY_CADASTRAR_ANUNCIO, method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastarAnuncio(anuncioForm);
        }
        
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRep.findByEmail(user.getName());
        

        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());

        anuncioService.create(anuncio);
        
       // usuarioService.addAnuncioNaLista(usuario, anuncio);

        attributes.addFlashAttribute(Util.MENSAGEM, Util.MENSAGEM_ANUNCIO_CADASTRO_SUCESSO);
        return new ModelAndView(Util.REDIRECT + Util.ROTA_COMPANY_CADASTRAR_ANUNCIO);
    }


}
