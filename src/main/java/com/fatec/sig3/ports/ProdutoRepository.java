package com.fatec.sig3.ports;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.sig3.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Optional<Produto>findByCodBarras(String codBarras);
	List<Produto>findAllByNomeIgnoreCaseContaining(String nome);
}
