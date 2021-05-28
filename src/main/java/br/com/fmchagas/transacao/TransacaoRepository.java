package br.com.fmchagas.transacao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, UUID> {

	List<Transacao> findFirst10ByCartaoNumeroOrderByEfetivadaEmDesc(String numeroCartao);
}
