package br.com.fmchagas.estabelecimento;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Estabelecimento {
	
	@Column(name = "estb_nome", length = 64)
	private @NotBlank String nome;

	@Column(name = "estb_cidade", length = 40)
	private @NotBlank String cidade;
	
	@Column(name = "estb_endereco", length = 128)
	private @NotBlank String endereco;
	
	/**
	 * @Deprecated - único para hibernate
	 */
	@Deprecated
	public Estabelecimento() {}
	
	public Estabelecimento(@NotBlank String nome, @NotBlank String cidade, @NotBlank String endereco) {
		this.nome = nome;
		this.cidade = cidade;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEndereco() {
		return endereco;
	}
}
