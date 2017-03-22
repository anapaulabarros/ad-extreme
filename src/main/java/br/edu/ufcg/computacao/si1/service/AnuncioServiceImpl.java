package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@Service
public class AnuncioServiceImpl implements AnuncioService {
    //TODO add validity checks

    private AnuncioRepository anuncioRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    public AnuncioServiceImpl(AnuncioRepository anuncioRepository) {
        /*neste codigo apenas atribuimos o repositorio jpa ao atributo */
        this.anuncioRepository = anuncioRepository;
    }

    public AnuncioRepository getAnuncioRepository(){
        return this.anuncioRepository;
    }

    @Override
    public Anuncio create(AnuncioForm anuncioForm) {

    	Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(anuncioForm.getTitulo());
        anuncio.setPreco(anuncioForm.getPreco());
        anuncio.setTipo(removeUltimoCatectere(anuncioForm.getTipo()));
    	// TODO Melhorar esse Desing - aqui ele atualiza a lista de anuncios do usuario e estabelece a relacao entre Anuncio e Usuario logado.
    	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(loginUsuario);
        
    	anuncio.setIdUsuario(usuarioLogado.getId());
    	usuarioService.update(usuarioLogado);
  
        return anuncioRepository.save(anuncio);
    }
    
    @Override
    public Optional<Anuncio> getAnuncioById(Long id) {
        return Optional.ofNullable(anuncioRepository.findOne(id));
    }

    @Override
    public Collection<Anuncio> getAnunciosByType(String tipo) {
        return anuncioRepository.findAll().stream()
                .filter(anuncio -> anuncio.getTipo().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    @Override
    public Collection<Anuncio> getAllAnuncios() {
        return anuncioRepository.findAll();
    }

  
    @Override
    public boolean updateAnuncio(Anuncio anuncio) {
        if (anuncioRepository.exists(anuncio.getId())) {
            anuncioRepository.save(anuncio);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAnuncio(Long id) {
        if (anuncioRepository.exists(id)) {
            anuncioRepository.delete(id);
            return true;
        }
        return false;
    }

	@Override
	public List<Anuncio> findByDataCriacao(Date dataCriacao) {
		List<Anuncio> listaAnuncios = new ArrayList();
		anuncioRepository.findBydataCriacao(dataCriacao).forEach(listaAnuncios::add);
		return listaAnuncios;
	}
    	
	private String removeUltimoCatectere(String palavra){
		if(palavra.contains(","))
			palavra = palavra.substring(0,palavra.length()-1);
		return palavra;
	}
}