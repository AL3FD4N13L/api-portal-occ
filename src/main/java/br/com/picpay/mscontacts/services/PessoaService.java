package br.com.picpay.mscontacts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.picpay.mscontacts.entities.Contato;
import br.com.picpay.mscontacts.entities.Pessoa;
import br.com.picpay.mscontacts.repositories.ContatoRepository;
import br.com.picpay.mscontacts.repositories.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {
	
	
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	
	
	
	
	
	public Pessoa addPessoa(Pessoa obj){
		
		Pessoa pessoa = pessoaRepository.save(obj);
		Long pessoaId = pessoa.getId();
		
		
		for (Contato contato : pessoa.getContatos()) {
            contato.setPessoa(pessoa);
        }
		
		contatoRepository.saveAll(pessoa.getContatos());
		
			 return pessoa;
	}
	
	public List<Pessoa> findaAllPessoas(){
		
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		return pessoas;
	}
	
	

	public Pessoa updatePessoa(Long id, Pessoa pessoa) {
	    Pessoa pessoaExistente = pessoaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));

	    pessoaExistente.setNome(pessoa.getNome());
	    pessoaExistente.setEmail(pessoa.getEmail());
	    pessoaExistente.setCargo(pessoa.getCargo());
	    pessoaExistente.setTime(pessoa.getTime());

	    // Atualiza os contatos da pessoa existente com os novos contatos
	    List<Contato> contatosExistente = pessoaExistente.getContatos();
	    List<Contato> novosContatos = pessoa.getContatos();

	    // Cria uma cópia da lista de contatos existente para evitar ConcurrentModificationException
	    List<Contato> contatosRemover = new ArrayList<>(contatosExistente);

	    for (Contato novoContato : novosContatos) {
	        if (novoContato.getId() != null) {
	            // Se o contato tem um ID, é um contato existente que precisa ser atualizado
	            for (Contato contatoExistente : contatosExistente) {
	                if (contatoExistente.getId().equals(novoContato.getId())) {
	                    contatoExistente.setTelefone(novoContato.getTelefone());
	                    // Atualiza outros campos de contato, se necessário
	                }
	            }
	        } else {
	            // Se o contato não tem um ID, é um novo contato que deve ser adicionado
	            novoContato.setPessoa(pessoaExistente); // Define a pessoa no novo contato
	            contatosExistente.add(novoContato);
	        }

	        // Remove o contato da lista de contatos a ser removidos
	        contatosRemover.removeIf(contato -> contato.getId().equals(novoContato.getId()));
	    }

	    // Remove os contatos que não estão no JSON de atualização
	    contatosExistente.removeAll(contatosRemover);

	   

	    if (!contatosRemover.isEmpty()) {
	       
	        for (Contato contatoRemovido : contatosRemover) {
	            contatoRepository.delete(contatoRemovido);
	        }
	    }

	    Pessoa updatedPessoa = pessoaRepository.save(pessoaExistente);

	    return updatedPessoa;
	}

        
		
	@Transactional
	public void deletePessoa(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + id));
		
		pessoaRepository.deletePessoaById(id);
		
		pessoaRepository.delete(pessoa);
		
	}
	
	
	@Transactional
	public void deletePessoas(List<Long> ids) {
		
		pessoaRepository.deleteAllById(ids);
		
	}
	
	
	public Optional<Pessoa> findPessoaById(Long id) {
		return pessoaRepository.findById(id);
	}

}
	

