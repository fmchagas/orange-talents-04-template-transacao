package br.com.fmchagas.transacao;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fmchagas.util.Criador;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransacaoController.class)
class TransacaoControllerTest {
	@MockBean
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	void deveRetornanar404_QuandoNaoEncontrarRegistro() {
		String uri = "/cartoes/{cartaoNumero}/transacoes";
		String cartaoNumero = "ab2020";
	
		when(transacaoRepository.findFirst10ByCartaoNumeroOrderByEfetivadaEmDesc(cartaoNumero)).thenReturn(List.of());
		
		try {
			mockMvc.perform(
					get(uri, cartaoNumero)
					.with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_transacao:read")))
					)
			.andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void deveRetornanar200_QuandoEncontrarRegistro() {
		String uri = "/cartoes/{numeroCartao}/transacoes";
		String cartaoNumero = "ab2020";
		var cartao = Criador.criaCartaoValido();
		
		List<Transacao> transacoes = List.of(Criador.criaTransacaoValida(cartao), Criador.criaTransacaoValida(cartao));
		
		when(transacaoRepository.findFirst10ByCartaoNumeroOrderByEfetivadaEmDesc(cartaoNumero))
			.thenReturn(transacoes);
		
		try {
			mockMvc.perform(
					get(uri, cartaoNumero)
					.with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_transacao:read")))
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
