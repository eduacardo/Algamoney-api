package com.algamoneyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.model.Pessoa;
import com.algamoneyapi.repository.LancamentoRepository;
import com.algamoneyapi.repository.PessoasRepository;
import com.algamoneyapi.service.exception.PessoaInativaouInexistenteExeption;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoasRepository pessoaRepositiry;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		
		Pessoa pessoa = pessoaRepositiry.findOne(lancamento.getPessoa().getCodigo());
		
		if (pessoa == null || pessoa.isInativo()) {
			
			throw new PessoaInativaouInexistenteExeption();
		}
		return lancamentoRepository.save(lancamento);
	}

}
