package br.com.picpay.mscontacts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.picpay.mscontacts.entities.Contato;
import br.com.picpay.mscontacts.entities.Parceiro;
import br.com.picpay.mscontacts.entities.Pessoa;
import br.com.picpay.mscontacts.repositories.ContatoRepository;
import br.com.picpay.mscontacts.repositories.ParceiroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ParceiroService {
	
	
	
	@Autowired
	private ParceiroRepository parceiroRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	
	
	
	
	
	public Parceiro addParceiro(Parceiro obj){
		
		Parceiro parceiro = parceiroRepository.save(obj);
        Long parceiroId = parceiro.getId();

        
        for (Contato contato : parceiro.getContatos()) {
            contato.setParceiro(parceiro);
        }

    
        contatoRepository.saveAll(parceiro.getContatos());

        return parceiro;
	}
	
	public List<Parceiro> findaAllParceiros(){
		
		List<Parceiro> parceiros = parceiroRepository.findAll();
		
		return parceiros;
	}
	
	

	
		public Parceiro updateParceiro(Long id, Parceiro parceiro) {
			Parceiro parceiroExistente = parceiroRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Parceiro não encontrada com o ID: " + id));
			
			parceiroExistente.setNome_empresa(parceiro.getNome_empresa());
			parceiroExistente.setEmail(parceiro.getEmail());
			parceiroExistente.setProduto(parceiro.getProduto());
			
			
			List<Contato> contatosExistente = parceiroExistente.getContatos();
		    List<Contato> novosContatos = parceiro.getContatos();
			
		    
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
		            novoContato.setParceiro(parceiroExistente); // Define a pessoa no novo contato
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

		    Parceiro updatedParceiro = parceiroRepository.save(parceiroExistente);

		    return updatedParceiro;
		}

	
	@Transactional
	public void deleteParceiro(Long id) {
		Parceiro parceiro = parceiroRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Parceiro não encontrada com o ID: " + id));
		
		parceiroRepository.deleteParceiroById(id);
		
		parceiroRepository.delete(parceiro);
	
	}
	
	
	@Transactional
	public void deleteParceiros(List<Long> ids) {
		
		parceiroRepository.deleteAllById(ids);
		
	}
	
	
	public Optional<Parceiro> findParceiroById(Long id) {
		return parceiroRepository.findById(id);
	}

}
	

