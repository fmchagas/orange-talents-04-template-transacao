package br.com.fmchagas.transacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.fmchagas.cartao.CartaoRepository;
import br.com.fmchagas.util.Criador;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TransacaoRepositoryTest {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Test
	void deveSalvarTransacao() {
		var cartao = cartaoRepository.save(Criador.criaCartaoValido());
		var transacao = Criador.criaTransacaoValida(cartao);
		
		var transacaoSalva = transacaoRepository.save(transacao);
		assertNotNull(transacaoSalva);
	}
	
	@Test
	void deveRetornarListaVazio_QuandoNaoEncontrarRegistro(){
		var transacoes = transacaoRepository.findFirst10ByCartaoNumeroOrderByEfetivadaEmDesc("ab12");
		
		assertTrue(transacoes.isEmpty());
	}
	
	@Test
	void deveRetornarListaDeTransacao_QuandoEncontrarRegistro(){
		var cartao = cartaoRepository.save(Criador.criaCartaoValido());
		transacaoRepository.save(Criador.criaTransacaoValida(cartao));
		transacaoRepository.save(Criador.criaTransacaoValida(cartao));
		
		var transacoes = transacaoRepository.findFirst10ByCartaoNumeroOrderByEfetivadaEmDesc(cartao.getNumero());
		
		assertEquals(2, transacoes.size());
		assertEquals(BigDecimal.TEN, transacoes.get(0).getValor());
		assertEquals("Cuiaba", transacoes.get(0).getEstabelecimento().getCidade());
	}
}
