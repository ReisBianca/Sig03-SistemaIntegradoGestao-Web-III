package com.fatec.sig3.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String codBarras;
	private String nome;
	private String descricao;
	private String cor;
	private String tamanho;
	private int quantidade;
	
	public Produto() {}
	
	public Produto(String codBarras, String nome, String descricao, String cor, String tamanho, int quantidade) {
		this.codBarras = codBarras;
		this.nome = nome;
		this.descricao = descricao;
		this.cor = cor;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
		
}
