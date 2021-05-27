package br.com.fmchagas.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fmchagas.cartao.Cartao;
import br.com.fmchagas.cartao.CartaoRequest;
import br.com.fmchagas.estabelecimento.Estabelecimento;
import br.com.fmchagas.estabelecimento.EstabelecimentoRequest;


public class EventoTransacaoRequest {
	
	
	private @NotBlank UUID id;
	
	private @NotNull BigDecimal valor;
	
	private @NotNull LocalDateTime efetivadaEm;
	
	private @NotNull EstabelecimentoRequest estabelecimentoRequest;
	
	private @NotNull CartaoRequest cartaoRequest;
	
	
	@JsonCreator
	public EventoTransacaoRequest(@JsonProperty("id") UUID id, @JsonProperty("valor") BigDecimal valor, 
			@JsonProperty("efetivadaEm") LocalDateTime efetivadaEm,
			@JsonProperty("estabelecimento") EstabelecimentoRequest estabelecimentoRequest, 
			@JsonProperty("cartao") CartaoRequest cartaoRequest) {
		
		this.id = id;
		this.valor = valor;
		this.efetivadaEm = efetivadaEm;
		this.estabelecimentoRequest = estabelecimentoRequest;
		this.cartaoRequest = cartaoRequest;
	}


	public Transacao toModel(@NotNull Cartao cartao) {
		@NotNull Estabelecimento estabelecimento = estabelecimentoRequest.toModel();
		
		Assert.notNull(estabelecimento, "O estabelecimento não deve ser nulo nesse ponto");
		Assert.notNull(cartao, "O cartao não deve ser nulo nesse ponto");
		
		return new Transacao(id, valor, efetivadaEm, estabelecimento, cartao);
	}
	
	public CartaoRequest getCartaoRequest() {
		return cartaoRequest;
	}

	public @NotNull Cartao getCartao() {
		return cartaoRequest.toModel();
	}

	public String getNumeroCartao() {
		return cartaoRequest.getNumero();
	}


	@Override
	public String toString() {
		return "EventoTransacaoRequest [id=" + id + ", valor=" + valor + ", efetivadaEm=" + efetivadaEm
				+ ", estabelecimentoRequest=" + estabelecimentoRequest + ", cartaoRequest=" + cartaoRequest + "]";
	}
}
