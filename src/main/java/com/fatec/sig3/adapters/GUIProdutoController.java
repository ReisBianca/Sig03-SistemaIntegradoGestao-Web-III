package com.fatec.sig3.adapters;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.sig3.model.Produto;
import com.fatec.sig3.ports.MantemProduto;

@Controller
@RequestMapping(path = "/sig")
public class GUIProdutoController {
	Logger logger = LogManager.getLogger(GUIProdutoController.class);
	@Autowired
	MantemProduto servico;

	@GetMapping("/produtos")
	public ModelAndView retornaFormDeConsultaTodosProdutos() {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}

	@GetMapping("/produto")
	public ModelAndView retornaFormDeCadastroDeProduto(Produto produto) {
		ModelAndView mv = new ModelAndView("cadastrarProduto");
		mv.addObject("produto", produto);
		return mv;
	}

	@GetMapping("/produtos/{codBarras}")
	public ModelAndView retornaFormParaEditarProduto(@PathVariable("codBarras") String codBarras) {
		ModelAndView modelAndView = new ModelAndView("atualizarProduto");
		modelAndView.addObject("produto", servico.consultaPorCodBarras(codBarras).get());
		return modelAndView;
	}

	@GetMapping("/produtos/id/{id}")
	public ModelAndView excluirNoFormDeConsultaProduto(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/produtos")
	public ModelAndView save(@Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarProduto");
		} else {
			if (servico.save(produto).isPresent()) {
				logger.info(">>>>>> controller chamou cadastrar e consulta todos");
				modelAndView.addObject("produtos", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("cadastrarProduto");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		return modelAndView;
	}

	@PostMapping("/produtos/id/{id}")
	public ModelAndView atualizaProduto(@PathVariable("id") Long id, @Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			produto.setId(id);
			return new ModelAndView("atualizarProduto");
		} else {
			servico.altera(produto);
			modelAndView.addObject("produtos", servico.consultaTodos());
		}
		return modelAndView;
	}

}
