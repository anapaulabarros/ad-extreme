package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.form.AnuncioForm;

import java.util.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AnuncioService {

    Anuncio create(AnuncioForm anuncioForm);

    Optional<Anuncio> getAnuncioById(Long id);

    Collection<Anuncio> getAnunciosByType(String tipo);
    
    Collection<Anuncio> getAnunciosByIdUser(Long idUser);

    Collection<Anuncio> getAllAnuncios();

    boolean updateAnuncio(Anuncio anuncio);

    boolean deleteAnuncio(Long id);
    
    List<Anuncio> findByDataDeCriacao(Date dataDeCriacao);

}
