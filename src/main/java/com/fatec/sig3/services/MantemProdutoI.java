package com.fatec.sig3.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.sig3.model.Produto;
import com.fatec.sig3.ports.MantemProduto;
import com.fatec.sig3.ports.ProdutoRepository;


@Service
public class MantemProdutoI implements MantemProduto{
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	ProdutoRepository repository;

	@Override
	public List<Produto> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Produto> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}

	@Override
	public Optional<Produto> consultaPorCodBarras(String codBarras) {
		logger.info(">>>>>> servico consultaPorCodBarras chamado");
		return repository.findByCodBarras(codBarras);
	}

	@Override
	public Optional<Produto> save(Produto produto) {
		logger.info(">>>>>> servico save chamado ");
		Optional<Produto> umProduto = consultaPorCodBarras(produto.getCodBarras());

		if (umProduto.isEmpty()) {
			logger.info(">>>>>> servico save - dados validos");
			return Optional.ofNullable(repository.save(produto));
		}else
			return Optional.empty();
	}

	@Override
	public Optional<Produto> altera(Produto produto) {
		logger.info(">>>>>> 1. servico altera produto chamado");
		Optional<Produto> umProduto = consultaPorId(produto.getId());
		if(umProduto.isPresent()) {
			Produto produtoModificado = new Produto(produto.getCodBarras(), produto.getNome(), produto.getDescricao(), produto.getCor(), produto.getTamanho(), produto.getQuantidade());
			produtoModificado.setId(produto.getId());
			logger.info(">>>>>> 2. servico altera cliente cep valido para o id => " + produtoModificado.getId());
			return Optional.ofNullable(repository.save(produtoModificado));
		}
		else
			return Optional.empty();
	}

	@Override
	public void delete(Long id) {
		logger.info(">>>>>> servico delete por id chamado");
		repository.deleteById(id);		
	}
}
