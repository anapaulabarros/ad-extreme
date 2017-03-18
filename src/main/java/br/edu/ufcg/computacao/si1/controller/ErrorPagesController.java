package br.edu.ufcg.computacao.si1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ufcg.computacao.si1.util.*;

@Controller
public class ErrorPagesController {


    @RequestMapping(Util.QUATROCENTO_QUATRO)
    public String notFound() {
        return Util.ERROR + Util.QUATROCENTO_QUATRO;
    }

    @RequestMapping(Util.QUATROCENTO_TRES)
    public String forbidden() {
        return Util.ERROR + Util.QUATROCENTO_TRES;
    }

    @RequestMapping(Util.QUINHENTOS)
    public String internalServerError() {
        return Util.ERROR + Util.QUINHENTOS;
    }
}
