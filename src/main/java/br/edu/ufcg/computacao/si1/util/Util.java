package br.edu.ufcg.computacao.si1.util;


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
	public static final String ROTA_ID = "/{id}";
	
	public static final String MOVEL = "movel";
	public static final String IMOVEL = "imovel";
	public static final String EMPREGO = "emprego";
	
	public static final String TIPOS = "tipos";
	public static final String ANUNCIOS = "anuncios";
	public static final String MENSAGEM = "mensagem";
	public static final String REDIRECT = "redirect:";
	public static final String CADASTRO = "cadastro";
	public static final String SALDO_DISPONIVEL = "saldoDisponivel";
	public static final String ANUNCIO_LISTA_FILTRO = "anunciolistaFiltro";
	public static final String LISTA_ANUNCIOS = "listaAnuncios";
	
	public static final String INDEX = "index";
	public static final String LOGIN = "login";
	public static final String ERROR = "error";
	public static final String QUINHENTOS = "/500";
	public static final String QUATROCENTO_TRES = "/403";
	public static final String QUATROCENTO_QUATRO = "/404";
	public static final String DATA_FORMAT = "yyyy-MM-dd";
	
	public static final String MENSAGEM_ANUNCIO_CADASTRO_SUCESSO = "Anúncio cadastrado.";
	public static final String MENSAGEM_COMPRA_SUCESSO = "Anúncio comprado.";
	public static final String MENSAGEM_COMPRA_INVALIDA = "Não foi possível comprar o anúncio. Verifique se a compra é possível de ser realizada.";
	public static final String MENSAGEM_USUARIO_CADASTRO_SUCESSO = "Usuario cadastrado.";
	public static final String MENSAGEM_EMAIL_EM_USO = "Este email já esta em uso.";
	public static final String MENSAGEM_FORMATO_DATA_INVALIDO = "Formato de data invalida. ERRO: ";
	public static final String MENSAGEM_TITULO_NULO = "O titulo não pode ser nulo.";
	public static final String MENSAGEM_TITULO_VAZIO = "O titulo não pode esta vazio.";
	public static final String MENSAGEM_TITULO_COMPRIMENTO = "O titulo deve ter entre 2 e 100 caracters";
	public static final String MENSAGEM_PRECO_NULO = "O preço não pode ser nulo.";
	public static final String MENSAGEM_PRECO_MINIMO = "O preço minimo é 0.1 para um anúncio.";
	public static final String MENSAGEM_TIPO_NULO = "O tipo de anúncio não pode ser nulo.";
	public static final String MENSAGEM_TIPO_ESCOLHA = "Escolha um tipo para o anúncio.";
	public static final String MENSAGEM_NOME_NULO = "O nome não pode ser nulo.";
	public static final String MENSAGEM_NOME_VAZIO = "O nome não pode ser vazio.";
	public static final String MENSAGEM_NOME_COMPRIMENTO = "O nome deve ter entre 2 e 100 caracteres.";
	public static final String MENSAGEM_EMAIL_VAZIO = "O email não pode ser vazio.";
	public static final String MENSAGEM_SENHA_NULA = "A senha não pode ser nula.";
	public static final String MENSAGEM_SENHA_COMPRIMENTO = "A senha deve ter entre 4 e 16 caracteres.";
	public static final String MENSAGEM_ID_NULO = "Id do usuario nao pode ser nula.";
	
	public static final String STATUS_CRIADO_LOG = "estah sendo criado";
    public static final String STATUS_RETORNADO_LOG = "estah sendo retornado";
    public static final String STATUS_ATUALIZADO_LOG = "estah sendo atualizado";
	
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

	public static final String ROTA_USUARIO_LISTAR_ANUNCIOS_POR_ID = ROTA_USER + ROTA_LISTAR_ANUNCIOS + ROTA_ID;
	public static final String ROTA_COMPANY_LISTAR_ANUNCIOS_POR_ID = ROTA_COMPANY + ROTA_LISTAR_ANUNCIOS + ROTA_ID;
	
	public static final String ROTA_CADASTRO = "/cadastrar-se";
	public static final String ROTA_LOGIN = "/login";
	
	public static final int FILTRO_0 = 0;
	public static final int FILTRO_1 = 1;
}
