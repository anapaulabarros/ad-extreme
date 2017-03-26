package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.repository.UsuarioRepository;
import br.edu.ufcg.computacao.si1.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
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
        
    	// Aqui cria-se a relacao de anuncio tem um dono, no casso o usuario logado da sessão atual
        String loginUsuario = usuarioService.getLoginUsuarioLogado();
        Usuario usuarioLogado = usuarioRepository.findByEmail(loginUsuario);
        
    	anuncio.setIdUsuario(usuarioLogado.getId());
  
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
		ArrayList<Anuncio> listaAnunciosRepositorio = (ArrayList<Anuncio>) getAllAnuncios();
		List<Anuncio> listaAnuncios = new ArrayList<Anuncio>();
		SimpleDateFormat dataFormatada  = new SimpleDateFormat(Util.DATA_FORMAT);
		String dataDoFiltro = dataFormatada.format(dataCriacao);

		for (Anuncio anuncio : listaAnunciosRepositorio) {
			String dataAnuncioAtual = dataFormatada.format(anuncio.getDataCriacao());
			if(dataAnuncioAtual.equals(dataDoFiltro))
				listaAnuncios.add(anuncio);
		}
		return listaAnuncios;
	}
    	
	private String removeUltimoCatectere(String palavra){
		if(palavra.contains(","))
			palavra = palavra.substring(0,palavra.length()-1);
		return palavra;
	}
}