package br.com.fmchagas.estabelecimento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EstabelecimentoRequest {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String cidade;
	@NotBlank
	private String endereco;

	@JsonCreator
	public EstabelecimentoRequest(@JsonProperty("nome") String nome, 
			@JsonProperty("cidade") String cidade, 
			@JsonProperty("endereco") String endereco) {
		
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
	}

	public @NotNull Estabelecimento toModel() {
		return new Estabelecimento(nome, cidade, endereco);
	}
}
