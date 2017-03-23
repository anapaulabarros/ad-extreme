package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.form.UsuarioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;
   
    private final String USER = "USER";
    private final String COMPANY = "COMPANY";
    
    protected Log logger = LogFactory.getLog(this.getClass());
    private final String STATUS_CRIADO_LOG = "estah sendo criado";
    private final String STATUS_RETORNADO_LOG = "estah sendo retornado";
    private final String STATUS_ATUALIZADO_LOG = "estah sendo atualizado";
 
    @Autowired
    private AnuncioRepository anuncioRepository;
    
    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //TODO criar usuario antes e depois setar o R
    @Override
    public Usuario create(UsuarioForm usuarioForm) {
        Usuario usuario=null;

        switch (usuarioForm.getRole()){
            case 1:
                usuario = new Usuario(usuarioForm.getNome(), usuarioForm.getEmail(),
                        usuarioForm.getSenha(), USER);
                break;
            case 2:
                usuario = new Usuario(usuarioForm.getNome(), usuarioForm.getEmail(),
                        usuarioForm.getSenha(), COMPANY);

                //new BCryptPasswordEncoder().encode(usuarioForm.getSenha()), "COMPANY");
                usuario.setRole(COMPANY);
                break;
        }

        logger.debug(usuario + STATUS_CRIADO_LOG);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        return Optional.ofNullable(usuarioRepository.findOne(id));
    }

    @Override
    public Optional<Usuario> getByEmail(String email) {
    	logger.debug(email + STATUS_RETORNADO_LOG);
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    @Override
    public Collection<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean update(Usuario usuario) {
    	logger.debug(usuario + STATUS_ATUALIZADO_LOG);
  
        if (usuarioRepository.exists(usuario.getId())) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (usuarioRepository.exists(id)) {
            usuarioRepository.delete(id);
            return true;
        }
        return false;
    }

    
	@Override
	public Usuario getUserById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("Id do usuario nao pode ser nula.");
		}
		else{
			Usuario usuarioLogado = usuarioRepository.findOne(id);
			return usuarioLogado;
		}
	}

	@Override
	public float getSaldo(String email) {
		Usuario usuarioLogado = usuarioRepository.findByEmail(email);
		return usuarioLogado.getSaldo();
	}
	
	/*
	 * Method: retorna apenas os anuncios do usuario logado
	 * @param Long userId
	 * @return ArrayList<Anuncio>
	 */
	@Override
	public List<Anuncio> getAnuncios(Long userId) {
		List listaAnunciosUsuarioLogado = new ArrayList();
		ArrayList<Anuncio> listaAnunciosGeral = (ArrayList<Anuncio>) anuncioRepository.findAll();
		
		for (Anuncio anuncio : listaAnunciosGeral) {
			if(anuncio.getIdUsuario() == userId)
				listaAnunciosUsuarioLogado.add(anuncio);
		}
		
		return listaAnunciosUsuarioLogado;
	}

	@Override
	public boolean realizaCompraVendaAnuncio(Long idAnuncio, Long id) {
		
		Anuncio anuncio = anuncioRepository.findOne(idAnuncio);
		Usuario usuarioLogado = usuarioRepository.findById(id);
		Usuario donoAnuncio = usuarioRepository.findById(anuncio.getIdUsuario());
		
		if((anuncio.getIdUsuario() != id) && (usuarioLogado.getSaldo() >= anuncio.getPreco())){
			
			float novoSaldoComprador = (float) (usuarioLogado.getSaldo() - anuncio.getPreco());
			usuarioLogado.setSaldo(novoSaldoComprador);
			
			usuarioRepository.save(usuarioLogado);
			
			float novoSaldoVendedor = (float) (donoAnuncio.getSaldo() + anuncio.getPreco());
			donoAnuncio.setSaldo(novoSaldoVendedor);
			
			usuarioRepository.save(donoAnuncio);
			
			anuncio.setIdUsuario(usuarioLogado.getId());
			anuncioRepository.save(anuncio);
			
			return true;
		}
		else
			return false;
	}
	
	@Override
	public String getLoginUsuarioLogado() {
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
		return loginUsuario;
	}

	
}
