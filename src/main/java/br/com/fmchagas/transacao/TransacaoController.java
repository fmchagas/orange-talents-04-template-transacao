package br.com.fmchagas.transacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fmchagas.cartao.CartaoRepository;


@RestController
@RequestMapping("/cartoes")
public class TransacaoController {
	
	private TransacaoRepository transacaoRepository;

	@Autowired
	public TransacaoController(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
		this.transacaoRepository = transacaoRepository;
	}
	
	
	@GetMapping("/{numeroCartao}/transacoes")
	public ResponseEntity<?> listar(@PathVariable String numeroCartao){
		var<Transacao> tranacoes = transacaoRepository.findFirst10ByCartaoNumeroOrderByEfetivadaEmDesc(numeroCartao);
		
		if (tranacoes.isEmpty()) {
			return new ResponseEntity<>("Registro(s) não encontrado", HttpStatus.NOT_FOUND);
		}
		
		List<TransacaoResponse> response = TransacaoResponse.converte(tranacoes);
		
		return ResponseEntity.ok(response);
	}
}
