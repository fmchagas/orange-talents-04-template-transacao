package br.com.fmchagas.cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CartaoRequest {
	
	@NotBlank
	private String numero;
	@NotBlank
	private String email;
	
	@JsonCreator
	public CartaoRequest(@JsonProperty("id") String numero, 
			@JsonProperty("email") String email) {
		
		this.numero = numero;
		this.email = email;
	}

	public @NotNull Cartao toModel() {
		return new Cartao(numero, email);
	}
	
	public String getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return "CartaoRequest [numero=" + numero + ", email=" + email + "]";
	}
}
