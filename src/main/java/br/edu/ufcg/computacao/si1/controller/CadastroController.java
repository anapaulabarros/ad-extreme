package br.edu.ufcg.computacao.si1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ufcg.computacao.si1.model.form.UsuarioForm;
import br.edu.ufcg.computacao.si1.service.UsuarioServiceImpl;

@Controller
public class CadastroController {
	private static final String REDIRECT = "redirect:";
	private static final String MENSAGEM = "mensagem";
	private static final String CADASTRO = "cadastro";
	private static final String ERROR = "error";
	
	private static final String MENSAGEM_USUARIO_CADASTRO_SUCESSO = "Anúncio cadastrado com sucesso!";
	private static final String MENSAGEM_EMAIL_EM_USO= "Este email já esta em uso!";
	
	private static final String ROTA_CADASTRO = "/cadastrar-se";
	
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @RequestMapping(value = ROTA_CADASTRO, method = RequestMethod.GET)
    public ModelAndView getPageCadastro(UsuarioForm usuarioForm){
        ModelAndView model = new ModelAndView();
        model.setViewName(CADASTRO);

        return model;
    }

    @RequestMapping(value = ROTA_CADASTRO, method = RequestMethod.POST)
    public ModelAndView cadastro(@Valid UsuarioForm usuarioForm, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            return getPageCadastro(usuarioForm);
        }
        if (usuarioService.getByEmail(usuarioForm.getEmail()).isPresent()){
            attributes.addFlashAttribute(ERROR, MENSAGEM_EMAIL_EM_USO);
            return new ModelAndView(REDIRECT + ROTA_CADASTRO);
        }

        usuarioService.create(usuarioForm);

        attributes.addFlashAttribute(MENSAGEM, MENSAGEM_USUARIO_CADASTRO_SUCESSO);
        return new ModelAndView(REDIRECT + ROTA_CADASTRO);
    }


}
