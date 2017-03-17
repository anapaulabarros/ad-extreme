package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Usuario;
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
    public Anuncio create(Anuncio anuncio) {
        /*aqui salvamos o anuncio recem criado no repositorio jpa*/
    	
    	//Melhorar esse Desing - aqui ele atualiza a lista de anuncios do usuario e estabelece a relacao entre Anuncio e Usuario logado.
    	Authentication user = SecurityContextHolder.getContext().getAuthentication();
        String loginUsuario = user.getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(loginUsuario);
    	List<Anuncio> listaAtual = usuarioLogado.getAnuncios();
    	listaAtual.add(anuncio);
    	usuarioLogado.setAnuncios(listaAtual);
    	usuarioService.update(usuarioLogado);
    	
        return anuncioRepository.save(anuncio);
    }
    
    @Override
    public Optional<Anuncio> getAnuncioById(Long id) {
        /*aqui recuperamos o anuncio pelo seu id*/
        return Optional.ofNullable(anuncioRepository.findOne(id));
    }

    @Override
    public Collection<Anuncio> getAnunciosByType(String tipo) {

        /*pegamos aqui todos os anuncios, mas retornamos os anuncios por tipo
        * filtrando o tipo, pelo equals, retornando um arrayLista*/
        return anuncioRepository.findAll().stream()
                .filter(anuncio -> anuncio.getTipo().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }
         
   @Override
    public Collection<Anuncio> getAnunciosByIdUser(Long idUser) {

        //pegamos aqui todos os anuncios que pertencem ao usuario logado,  retornando um arrayLista
//        return anuncioRepository.findAll().stream()
//                .filter(anuncio -> anuncio.getUserId().equals(idUser))
//                .collect(Collectors.toCollection(ArrayList::new));
	   return null;
    }
    
    @Override
    public Collection<Anuncio> getAllAnuncios() {
        /*aqui retornamos todos os anuncios, sem distincao*/

        return anuncioRepository.findAll();
    }

  
    @Override
    public boolean updateAnuncio(Anuncio anuncio) {
        /*a atualizacao do anuncio eh feita apenas se o anuncio ja existir*/
        if (anuncioRepository.exists(anuncio.getId())) {
            anuncioRepository.save(anuncio);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAnuncio(Long id) {
        /*aqui se apaga o anuncio se ele existir*/


        if (anuncioRepository.exists(id)) {
            anuncioRepository.delete(id);
            return true;
        }
        return false;
    }

	@Override
	public List<Anuncio> findByDataDeCriacao(Date dataDeCriacao) {
		List<Anuncio> listaAnuncios = new ArrayList();
		//System.out.println(dataDeCriacao);
		anuncioRepository.findBydataDeCriacao(dataDeCriacao).forEach(listaAnuncios::add);
		return listaAnuncios;
	}
    
}
