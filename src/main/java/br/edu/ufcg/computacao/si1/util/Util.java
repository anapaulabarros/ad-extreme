package br.edu.ufcg.computacao.si1.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Util {
	public static final String ROOT = "/";
	private static final String USER = "user";
	private static final String COMPANY = "company";
	
	private static final String CADASTRAR_ANUNCIO = "/cadastrar_anuncio";
	private static final String LISTAR_MEUS_ANUNCIOS = "/listar_meus_anuncios";
	private static final String LISTAR_ANUNCIOS = "/listar_anuncios";

	private static final String ROTA_CADASTRAR_ANUNCIO = "/cadastrar/anuncio"; 
	private static final String ROTA_LISTAR_ANUNCIOS = "/listar/anuncios"; 
	private static final String ROTA_LISTAR_MEUS_ANUNCIOS = "/listar/meus-anuncios"; 
	
	public static final String TIPOS = "tipos";
	public static final String ANUNCIOS = "anuncios";
	public static final String MENSAGEM = "mensagem";
	public static final String REDIRECT = "redirect:";
	public static final String CADASTRO = "cadastro";
	public static final String SALDO_DISPONIVEL = "saldoDisponivel";
	public static final String ANUNCIO_LISTA_FILTRO = "anunciolistaFiltro";
	public static final String LISTA_ANUCIOS = "listaAnuncios";
	
	public static final String INDEX = "index";
	public static final String LOGIN = "login";
	public static final String ERROR = "error";
	public static final String QUINHENTOS = "/500";
	public static final String QUATROCENTO_TRES = "/403";
	public static final String QUATROCENTO_QUATRO = "/404";
	public static final String DATA_FORMAT = "yyyy-MM-dd";
	
	public static final String MENSAGEM_ANUNCIO_CADASTRO_SUCESSO = "Anúncio cadastrado com sucesso!";
	public static final String MENSAGEM_COMPRA_SUCESSO = "Anúncio foi comprado com sucesso!";
	public static final String MENSAGEM_COMPRA_INVALIDA = "ERRO: verifique se o anuncio pertence a outro usuario e/ou seu saldo!";
	public static final String MENSAGEM_USUARIO_CADASTRO_SUCESSO = "Usuario cadastrado com sucesso!";
	public static final String MENSAGEM_EMAIL_EM_USO= "Este email já esta em uso!";
	public static final String MENSAGEM_FORMATO_DATA_INVALIDO = "Formato de data invalida. ERROR: ";
	

	public static final String USER_CADASTRAR_ANUNCIO = USER + CADASTRAR_ANUNCIO;
	public static final String COMPANY_CADASTRAR_ANUNCIO = COMPANY + CADASTRAR_ANUNCIO;
	
	public static final String USER_LISTAR_ANUNCIOS = USER + LISTAR_ANUNCIOS;
	public static final String COMPANY_LISTAR_ANUNCIOS = COMPANY + LISTAR_ANUNCIOS;

	public static final String USER_LISTAR_MEUS_ANUNCIOS = USER + LISTAR_MEUS_ANUNCIOS;
	public static final String COMPANY_LISTAR_MEUS_ANUNCIOS = COMPANY + LISTAR_MEUS_ANUNCIOS;
	
	public static final String USER_INDEX = USER + ROOT + INDEX;
	public static final String COMPANY_INDEX = COMPANY + ROOT + INDEX;
	
	public static final String ROTA_USER = ROOT + USER;
	public static final String ROTA_COMPANY = ROOT + COMPANY;
	
	public static final String ROTA_USUARIO_CADASTRAR_ANUNCIO = ROTA_USER + ROTA_CADASTRAR_ANUNCIO;
	public static final String ROTA_COMPANY_CADASTRAR_ANUNCIO = ROTA_COMPANY + ROTA_CADASTRAR_ANUNCIO;
	
	public static final String ROTA_USUARIO_LISTAR_ANUNCIOS = ROTA_USER + ROTA_LISTAR_ANUNCIOS;
	public static final String ROTA_COMPANY_LISTAR_ANUNCIOS = ROTA_COMPANY + ROTA_LISTAR_ANUNCIOS;
	
	public static final String ROTA_USUARIO_LISTAR_MEUS_ANUNCIOS = ROTA_USER + ROTA_LISTAR_MEUS_ANUNCIOS; 
	public static final String ROTA_COMPANY_LISTAR_MEUS_ANUNCIOS = ROTA_COMPANY + ROTA_LISTAR_MEUS_ANUNCIOS; 

	public static final String ROTA_CADASTRO = "/cadastrar-se";
	public static final String ROTA_LOGIN = "/login";

	
	
 
 

	
	
}
