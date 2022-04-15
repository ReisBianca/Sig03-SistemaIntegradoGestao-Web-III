package com.fatec.sig3.ports;

import java.util.List;
import java.util.Optional;

import com.fatec.sig3.model.Produto;


public interface MantemProduto {
	List<Produto> consultaTodos();
	
	Optional<Produto> consultaPorId(Long id);
	Optional<Produto> consultaPorCodBarras(String codBarras);
	
	Optional<Produto> save(Produto produto);
	Optional<Produto> altera(Produto produto);
	
	void delete(Long id);
}
