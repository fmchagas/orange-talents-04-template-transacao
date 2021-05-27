package br.com.fmchagas.cartao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartaoRepository extends CrudRepository<Cartao, UUID>{

	Optional<Cartao> findBynumero(String numeroCartao);

}
