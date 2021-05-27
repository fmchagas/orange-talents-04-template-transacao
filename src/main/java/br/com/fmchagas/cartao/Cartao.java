package br.com.fmchagas.cartao;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {
	
	@Id @GeneratedValue(generator = "UUID")
	@Column(columnDefinition = "BINARY(16)")
	private @NotNull UUID id;
	
	@Column(length = 128, nullable = false)
	private @NotBlank String numero;
	private @NotBlank String email;
	
	/**
	 * @Deprecated - único para hibernate
	 */
	@Deprecated
	public Cartao() {}

	public Cartao(@NotBlank String numero, @NotBlank String email) {
		this.numero = numero;
		this.email = email;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public String getEmail() {
		return email;
	}
}
