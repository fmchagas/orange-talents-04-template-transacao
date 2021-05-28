package br.com.fmchagas.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fmchagas.cartao.Cartao;
import br.com.fmchagas.estabelecimento.Estabelecimento;
import br.com.fmchagas.transacao.Transacao;

public class Criador {
	
	public static Cartao criaCartaoValido() {
		return new Cartao("1020-2323", "dunha@mail.com");
	}
	
	public static Transacao criaTransacaoValida(Cartao cartao) {
		var estabelecimento = new Estabelecimento("Loja A. B", "Cuiaba", "Rua A, 13");
		return new Transacao(UUID.randomUUID(), BigDecimal.TEN, LocalDateTime.now(), estabelecimento, cartao);
	}
}
