package br.edu.ufcg.computacao.si1.controller;

import javax.validation.Valid;

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

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import br.edu.ufcg.computacao.si1.util.Util;

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
    
    
    @RequestMapping(value = Util.ROTA_USUARIO_CADASTRAR_ANUNCIO, method = RequestMethod.GET)
    public ModelAndView getPageCadastrarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();
     
        model.addObject(Util.TIPOS, anuncioForm.getTipos());
        model.setViewName(Util.USER_CADASTRAR_ANUNCIO);
       
        return model;
    }

    @RequestMapping(value = Util.ROTA_USUARIO_LISTAR_ANUNCIOS, method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();

        model.addObject(Util.ANUNCIOS, anuncioRep.findAll());

        model.setViewName(Util.USER_LISTAR_ANUNCIOS);

        return model;
    }
 
    @RequestMapping(value = Util.ROTA_USUARIO_LISTAR_MEUS_ANUNCIOS, method = RequestMethod.GET)
	public String getPageListarMeusAnuncios(Model model){
    	//Melhorar esse Desing - codigo repetido em vários trechos do projeto: pega o usuario logado
    	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
        Usuario usuarioLogado = usuarioRep.findByEmail(loginUsuario);
        
		model.addAttribute("listaAnuncios",  usuarioLogado.getAnuncios());
		model.addAttribute("saldoCredor", usuarioService.getSaldoCredor(loginUsuario));
	    model.addAttribute("saldoDisponivel", usuarioService.getSaldoDisponivel(loginUsuario));
		return Util.USER_LISTAR_MEUS_ANUNCIOS;
	}
    
    @RequestMapping(value = Util.ROTA_USUARIO_CADASTRAR_ANUNCIO, method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastrarAnuncio(anuncioForm);
        }
        
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());
        
        anuncioService.create(anuncio);
        
        attributes.addFlashAttribute(Util.MENSAGEM, Util.MENSAGEM_ANUNCIO_CADASTRO_SUCESSO);
        return new ModelAndView(Util.REDIRECT + Util.ROTA_USUARIO_CADASTRAR_ANUNCIO);
    }


}
