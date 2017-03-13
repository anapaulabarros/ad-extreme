package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Anuncio;

import java.util.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
public interface AnuncioService {

    Anuncio create(Anuncio anuncio);

    Optional<Anuncio> getById(Long id);

    Collection<Anuncio> get(String tipo);
    
    Collection<Anuncio> getAnuncioByIdUser(Long idUser);

    Collection<Anuncio> getAll();

    boolean update(Anuncio anuncio);

    boolean delete(Long id);
    
    List<Anuncio> findByDataDeCriacao(Date dataDeCriacao);

}
