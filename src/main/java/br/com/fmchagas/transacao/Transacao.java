package br.com.fmchagas.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.fmchagas.cartao.Cartao;
import br.com.fmchagas.estabelecimento.Estabelecimento;

@Entity
public class Transacao {
	
	@Id @Column(columnDefinition = "BINARY(16)")
	private @NotNull UUID id;
	
	private @NotNull @Positive BigDecimal valor;
	
	private @NotNull @PastOrPresent LocalDateTime efetivadaEm;
	
	@Embedded @NotNull
	private Estabelecimento estabelecimento;
	
	@ManyToOne(optional = false)
	private @NotNull Cartao cartao;
	
	/**
	 * @Deprecated - único para hibernate
	 */
	@Deprecated
    public Transacao() {}

	public Transacao(UUID id, BigDecimal valor, LocalDateTime efetivadaEm, 
			@NotNull Estabelecimento estabelecimento, @NotNull Cartao cartao) {
		
		Assert.notNull(estabelecimento, "O estabelecimento não deve ser nulo nesse ponto");
		Assert.notNull(cartao, "O cartao não deve ser nulo nesse ponto");
		
		this.id = id;
		this.valor = valor;
		this.efetivadaEm = efetivadaEm;
		this.estabelecimento = estabelecimento;
		this.cartao = cartao;
	}

	public UUID getId() {
		return id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDateTime getEfetivadaEm() {
		return efetivadaEm;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}
}
