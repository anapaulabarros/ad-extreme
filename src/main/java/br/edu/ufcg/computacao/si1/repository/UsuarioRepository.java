package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Usuario findByEmail(String email);
    Usuario findById(Long id);
}
