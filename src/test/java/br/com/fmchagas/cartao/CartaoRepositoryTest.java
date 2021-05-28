package br.com.fmchagas.cartao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.fmchagas.util.Criador;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CartaoRepositoryTest {
	
	@Autowired
	private CartaoRepository cartaoRepository;

	@Test
	void deveRetornarVazio_QuandoNaoEncontrarCartao(){
		String numeroCartao = "AB1020";
		
		var possivelCartao = cartaoRepository.findBynumero(numeroCartao);
		
		assertTrue(possivelCartao.isEmpty());
	}
	
	@Test
	void deveRetornarCartao_QuandoEncontrarCartao(){
		var cartao = cartaoRepository.save(Criador.criaCartaoValido());
		
		var possivelCartao = cartaoRepository.findBynumero(cartao.getNumero());
		
		assertTrue(possivelCartao.isPresent());
		assertEquals(cartao.getNumero(), possivelCartao.get().getNumero());
		assertEquals(cartao.getEmail(), possivelCartao.get().getEmail());
	}
}
