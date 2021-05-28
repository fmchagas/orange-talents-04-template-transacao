package br.com.fmchagas.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TransacaoResponse {
	
	private UUID id;
	private LocalDateTime efetivadaEm;
	private BigDecimal valor;
	private String nomeEstabelecimento;
	private String cidade;
	private String endereco;

	public TransacaoResponse(Transacao transacao) {
		id = transacao.getId();
		efetivadaEm = transacao.getEfetivadaEm();
		valor = transacao.getValor();
		nomeEstabelecimento = transacao.getEstabelecimento().getNome();
		cidade = transacao.getEstabelecimento().getCidade();
		endereco = transacao.getEstabelecimento().getEndereco();
	}

	public static List<TransacaoResponse> converte(List<Transacao> tranacoes) {
		return tranacoes.stream().map(TransacaoResponse::new).collect(Collectors.toList());
	}

	public UUID getId() {
		return id;
	}

	public LocalDateTime getEfetivadaEm() {
		return efetivadaEm;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEndereco() {
		return endereco;
	}
}
