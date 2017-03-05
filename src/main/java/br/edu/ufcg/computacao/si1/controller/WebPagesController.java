package br.edu.ufcg.computacao.si1.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ufcg.computacao.si1.service.UsuarioService;

@Controller
public class WebPagesController {

	
	@Autowired
	UsuarioService usuarioService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getPageIndex(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getPageLogin(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET) //exibe a view do usuario logado e informações de saldo (credor e disponível)
    public String getPageIndexUser(Model model){
        
    	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
        
        model.addAttribute("saldoCredor", usuarioService.getSaldoCredor(loginUsuario));
        model.addAttribute("saldoDisponivel", usuarioService.getSaldoDisponivel(loginUsuario));
     
        return "user/index";
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public ModelAndView getPageIndexCompany(){
        ModelAndView model = new ModelAndView();
        model.setViewName("company/index");

        return model;
    }
}
