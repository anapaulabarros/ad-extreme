package br.edu.ufcg.computacao.si1.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.service.AnuncioService;
import br.edu.ufcg.computacao.si1.service.UsuarioService;
import br.edu.ufcg.computacao.si1.util.*;

@Controller
public class WebPagesController {

	
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	AnuncioService anuncioService;
	
	 @Autowired
	 private UsuarioRepository usuarioRep;
	
	
    @RequestMapping(value = Util.ROOT, method = RequestMethod.GET)
    public ModelAndView getPageIndex(){
        ModelAndView model = new ModelAndView();
        model.setViewName(Util.INDEX);

        return model;
    }

    @RequestMapping(value = Util.ROTA_LOGIN, method = RequestMethod.GET)
    public ModelAndView getPageLogin(){
        ModelAndView model = new ModelAndView();
        model.setViewName(Util.LOGIN);

        return model;
    }

    @RequestMapping(value = Util.ROTA_USER, method = RequestMethod.GET) //exibe a view do usuario logado e informações de saldo (credor e disponível)
    public String getPageIndexUser(Model model){
        
    	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
        
        model.addAttribute(Util.SALDO_DISPONIVEL, usuarioService.getSaldo(loginUsuario));
        
        return Util.USER_INDEX;
    }
    
    @RequestMapping(value = Util.ROTA_USER, method = RequestMethod.POST) //Filtro de anuncios
    public String filtroAnuncio(@RequestParam int opcaoFiltro, @RequestParam String filtroAnuncio,Model model){
    	
    	if(opcaoFiltro == 0){
    		model.addAttribute(Util.ANUNCIO_LISTA_FILTRO, anuncioService.getAnunciosByType(filtroAnuncio.toLowerCase()));
    	}
    	if(opcaoFiltro == 1){
    		SimpleDateFormat dataFormatada  = new SimpleDateFormat(Util.DATA_FORMAT);    		
			try {
				Date dataFiltro = dataFormatada.parse(filtroAnuncio);
				model.addAttribute(Util.ANUNCIO_LISTA_FILTRO, anuncioService.findByDataCriacao(dataFiltro));
			} catch (ParseException e) {
				System.out.println(Util.MENSAGEM_FORMATO_DATA_INVALIDO + e.getMessage());
			}
			
    		
    	}
    	return Util.USER_INDEX;
    }
    

    @RequestMapping(value = Util.ROTA_COMPANY, method = RequestMethod.GET)
    public String getPageIndexCompany(Model model){
        

      //Melhorar esse Desing - codigo repetido em vários trechos do projeto: pega o usuario logado
    	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
        Usuario usuarioLogado = usuarioRep.findByEmail(loginUsuario);
        
        model.addAttribute("saldoDisponivel", usuarioService.getSaldo(loginUsuario));
        return Util.COMPANY_INDEX;
    }
        
}
