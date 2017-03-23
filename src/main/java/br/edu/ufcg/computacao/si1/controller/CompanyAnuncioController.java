package br.edu.ufcg.computacao.si1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioServiceImpl;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import br.edu.ufcg.computacao.si1.util.Util;

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
    	String loginUsuario = usuarioService.getLoginUsuarioLogado();
        Usuario usuarioLogado = usuarioRep.findByEmail(loginUsuario);
        
        model.addAttribute(Util.LISTA_ANUNCIOS, usuarioService.getAnuncios(usuarioLogado.getId()));
        
   		return Util.USER_LISTAR_MEUS_ANUNCIOS;
   	}
    
    @RequestMapping(value = Util.ROTA_COMPANY_LISTAR_ANUNCIOS, method = RequestMethod.GET)
    public String getPageListarAnuncios(Model model){
    	String loginUsuario = usuarioService.getLoginUsuarioLogado();
        
        model.addAttribute(Util.SALDO_DISPONIVEL, usuarioService.getSaldo(loginUsuario));
        model.addAttribute(Util.ANUNCIOS, anuncioService.getAnuncioRepository().findAll());

        return Util.COMPANY_LISTAR_ANUNCIOS;
    }

    @RequestMapping(value = Util.ROTA_COMPANY_CADASTRAR_ANUNCIO, method = RequestMethod.POST)
    public ModelAndView cadastroAnuncio(@Valid AnuncioForm anuncioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastarAnuncio(anuncioForm);
        }

        anuncioService.create(anuncioForm);

        attributes.addFlashAttribute(Util.MENSAGEM, Util.MENSAGEM_ANUNCIO_CADASTRO_SUCESSO);
        return new ModelAndView(Util.REDIRECT + Util.ROTA_COMPANY_CADASTRAR_ANUNCIO);
    }
}
