package br.edu.ufcg.computacao.si1.model;

import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User{
   
	@Id
	@GeneratedValue(generator="STORE_SEQ")
	@SequenceGenerator(name="STORE_SEQ",sequenceName="STORE_SEQ", allocationSize=1)
    private Long id;
	
   
    private String nome;
    
    @Column(unique = true)
    private String email;
    private String senha;
    private String role;
    private float saldoDebito;
    private float saldoCredito;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Anuncio> anuncios;

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
        this.anuncios = new ArrayList<Anuncio>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String r) {
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

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}
	
	public void setAnuncios(List<Anuncio> anuncios){
		this.anuncios = anuncios;
	}
    
}
