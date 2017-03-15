package br.edu.ufcg.computacao.si1.controller;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

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
	private static final String TIPOS = "tipos";
	private static final String ANUNCIOS = "anuncios";
	private static final String REDIRECT = "redirect:";
	private static final String MENSAGEM = "mensagem";
	
	private static final String MENSAGEM_ANUNCIO_CADASTRO_SUCESSO = "Anúncio cadastrado com sucesso!";
	private static final String CADASTRAR_ANUNCIO = "company/cadastrar_anuncio";
	private static final String LISTAR_ANUNCIOS = "company/listar_anuncios";
	private static final String LISTAR_MEUS_ANUNCIOS = "user/listar_meus_anuncios";
	
	private static final String ROTA_COMPANY_CADASTRAR_ANUNCIO = "/company/cadastrar/anuncio";
	private static final String ROTA_COMPANY_LISTAR_ANUNCIOS = "/company/listar/anuncios";
	private static final String ROTA_COMPANY_LISTAR_MEUS_ANUNCIOS = "/company/listar/meus-anuncios";
	
    @Autowired
    private AnuncioServiceImpl anuncioService;
    
    @Autowired
    private UsuarioRepository usuarioRep; 
    
    @Autowired
	private UsuarioService usuarioService;

    @RequestMapping(value = ROTA_COMPANY_CADASTRAR_ANUNCIO, method = RequestMethod.GET)
    public ModelAndView getPageCadastarAnuncio(AnuncioForm anuncioForm){
        ModelAndView model = new ModelAndView();
        
        model.addObject(TIPOS, anuncioForm.getTipos());
        model.setViewName(CADASTRAR_ANUNCIO);

        return model;
    }
 
    @RequestMapping(value = ROTA_COMPANY_LISTAR_MEUS_ANUNCIOS, method = RequestMethod.GET)
   	public String getPageListarMeusAnuncios(Model model){
       	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
       
        Usuario usuarioLogado = usuarioRep.findByEmail(loginUsuario);
       
   		model.addAttribute(ANUNCIOS, usuarioService.getAnuncios(usuarioLogado.getId()));
   		return LISTAR_MEUS_ANUNCIOS;
   	}
    
    @RequestMapping(value = ROTA_COMPANY_LISTAR_ANUNCIOS, method = RequestMethod.GET)
    public ModelAndView getPageListarAnuncios(){
        ModelAndView model = new ModelAndView();

        model.addObject(ANUNCIOS, anuncioService.getAnuncioRepository().findAll());

        model.setViewName(LISTAR_ANUNCIOS);

        return model;
    }

    @RequestMapping(value = ROTA_COMPANY_CADASTRAR_ANUNCIO, method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastarAnuncio(anuncioForm);
        }
        
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Long userId = usuarioRep.findByEmail(user.getName()).getId();

        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(anuncioForm.getTipo());
        anuncio.setUserId(userId);

        anuncioService.create(anuncio);

        attributes.addFlashAttribute(MENSAGEM, MENSAGEM_ANUNCIO_CADASTRO_SUCESSO);
        return new ModelAndView(REDIRECT + ROTA_COMPANY_CADASTRAR_ANUNCIO);
    }


}
