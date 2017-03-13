package br.edu.ufcg.computacao.si1.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column(unique = true)
    private String email;
    @Column
    private String senha;
    @Column
    private String role;
    @Column
    private float saldoDebito;
    @Column
    private float saldoCredito;
    @Column
    private ArrayList<Anuncio> anuncios;

	public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }

    public Usuario(String nome, String email, String senha, String role) {

        super(email, senha, AuthorityUtils.createAuthorityList(role));

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        //inicialização do saldo credor e disponivel para compra
        this.saldoDebito = 0;
        this.saldoCredito = 100;
        this.anuncios = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getN() {
        return nome;
    }

    public void setN(String n) {
        this.nome = n;
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

    public String getR() {
        return role;
    }

    public void setR(String r) {
        this.role = r;
    }

	public float getSaldoDebito() {
		return saldoDebito;
	}

	public void setSaldoDebito(float saldoDebito) {
		this.saldoDebito = saldoDebito;
	}

	public float getSaldoCredito() {
		return saldoCredito;
	}

	public void setSaldoCredito(float saldoCredito) {
		this.saldoCredito = saldoCredito;
	}

	public ArrayList<Anuncio> getAnuncios() {
		return anuncios;
	}
	
	public void setAnuncios(ArrayList<Anuncio> anuncios){
		this.anuncios = anuncios;
	}
    
}
