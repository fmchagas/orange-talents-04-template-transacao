package br.com.fmchagas.cartao;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Test;

class CartaoRequestTest {

	@Test
	void deveRetornarModelo_QuandoTiverValido() {
		CartaoRequest request = new CartaoRequest("ab12", "fer@gmail.com");
		
		@NotNull Cartao model = request.toModel();
		
		assertEquals("ab12", model.getNumero());
		assertEquals("fer@gmail.com", model.getEmail());
	}

}
