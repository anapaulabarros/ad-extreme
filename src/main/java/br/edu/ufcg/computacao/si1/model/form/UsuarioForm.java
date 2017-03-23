package br.edu.ufcg.computacao.si1.model.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ufcg.computacao.si1.util.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioForm {
    @NotNull(message = Util.MENSAGEM_NOME_NULO)
    @NotEmpty(message = Util.MENSAGEM_NOME_VAZIO)
    @Size(min = 2, max = 100, message = Util.MENSAGEM_NOME_COMPRIMENTO)
    private String nome;
    @NotEmpty(message = Util.MENSAGEM_EMAIL_VAZIO)
    @Email
    private String email;
    @NotNull(message = Util.MENSAGEM_SENHA_NULA)
    @NotEmpty
    @Size(min = 4, max = 16, message = Util.MENSAGEM_SENHA_COMPRIMENTO)
    private String senha;
    @NotNull
    private Integer role;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
