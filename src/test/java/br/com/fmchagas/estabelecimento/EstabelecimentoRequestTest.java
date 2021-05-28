package br.com.fmchagas.estabelecimento;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Test;

class EstabelecimentoRequestTest {

	@Test
	void deveRetornarModeloEstabelecimento_QuandoDadosValido() {
		var request = new EstabelecimentoRequest("Cunha", "Cuiaba", "Rua A");
		
		@NotNull Estabelecimento model = request.toModel();
		
		assertEquals("Cunha", model.getNome());
		assertEquals("Cuiaba", model.getCidade());
		assertEquals("Rua A", model.getEndereco());
	}
}
