package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Anuncio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
	
	//@Query("SELECT anuncio FROM Anuncio anuncio WHERE anuncio.dataDeCriacao = (:data_criacao)")
	List<Anuncio> findBydataDeCriacao(@DateTimeFormat(pattern = "yyyy-MM-dd")Date data_criacao);
}
