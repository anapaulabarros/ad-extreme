package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesController {
	private static final String ERROR = "error";
	private static final String QUINHENTOS = "/500";
	private static final String QUATROCENTO_TRES = "/403";
	private static final String QUATROCENTO_QUATRO = "/404";

    @RequestMapping(QUATROCENTO_QUATRO)
    public String notFound() {
        return ERROR + QUATROCENTO_QUATRO;
    }

    @RequestMapping(QUATROCENTO_TRES)
    public String forbidden() {
        return ERROR + QUATROCENTO_TRES;
    }

    @RequestMapping(QUINHENTOS)
    public String internalServerError() {
        return ERROR + QUINHENTOS;
    }
}
