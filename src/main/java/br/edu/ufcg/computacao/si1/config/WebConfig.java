package br.edu.ufcg.computacao.si1.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.edu.ufcg.computacao.si1.util.Util;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, Util.QUATROCENTO_TRES);
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, Util.QUATROCENTO_QUATRO);
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, Util.QUINHENTOS);

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(Util.QUATROCENTO_TRES).setViewName(Util.ERROR + Util.QUATROCENTO_TRES);
        registry.addViewController(Util.QUATROCENTO_QUATRO).setViewName(Util.ERROR+ Util.QUATROCENTO_QUATRO);
        registry.addViewController(Util.QUINHENTOS).setViewName(Util.ERROR + Util.QUINHENTOS);
    }
}
