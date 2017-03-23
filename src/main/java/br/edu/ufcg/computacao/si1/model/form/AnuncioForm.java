package br.edu.ufcg.computacao.si1.model.form;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ufcg.computacao.si1.util.*;

import org.hibernate.validator.constraints.NotEmpty;

public class AnuncioForm {

    private static final String[] tipos = new String[] {Util.MOVEL, Util.IMOVEL, Util.EMPREGO};

    @NotNull(message = Util.MENSAGEM_TITULO_NULO)
    @NotEmpty(message = Util.MENSAGEM_TITULO_VAZIO)
    @Size(min = 10, max = 100, message = Util.MENSAGEM_TITULO_COMPRIMENTO)
    private String titulo;
    @NotNull(message = Util.MENSAGEM_PRECO_NULO)
    @DecimalMin(value = "0.1", message =  Util.MENSAGEM_PRECO_MINIMO)
    private Double preco;
    @NotNull(message = Util.MENSAGEM_TIPO_NULO)
    @NotEmpty(message = Util.MENSAGEM_TIPO_ESCOLHA)
    private String tipo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static String[] getTipos() {
        return tipos;
    }
}

