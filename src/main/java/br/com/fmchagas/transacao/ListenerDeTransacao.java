package br.com.fmchagas.transacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.fmchagas.cartao.Cartao;
import br.com.fmchagas.cartao.CartaoRepository;

@Component
public class ListenerDeTransacao {
	
	private final TransacaoRepository transacaoRepository;
	private CartaoRepository cartaoRepository;

	@Autowired
	public ListenerDeTransacao(TransacaoRepository transacaoRepository, 
			CartaoRepository cartaoRepository) {
		
		this.transacaoRepository = transacaoRepository;
		this.cartaoRepository = cartaoRepository;
	}

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(EventoTransacaoRequest request) {
    	
    	Optional<Cartao> possivelCartao = cartaoRepository.findBynumero(request.getNumeroCartao());
    	Cartao cartao;
    	
    	if (possivelCartao.isEmpty()) {
    		cartao = cartaoRepository.save(request.getCartao());
		}else {
			cartao = possivelCartao.get();
		}
    	
    	Transacao transacao = request.toModel(cartao);
    	
    	transacaoRepository.save(transacao);
    }

}