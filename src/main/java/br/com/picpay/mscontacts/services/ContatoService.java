package br.com.picpay.mscontacts.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.picpay.mscontacts.entities.Contato;
import br.com.picpay.mscontacts.repositories.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ContatoService {
	
	
	
	@Autowired
	private ContatoRepository repository;
	
	
	
	
	
	
	
	public Contato addContato(Contato Contato){
		
			return repository.save(Contato);
	}
	
	public List<Contato> findaAllContatos(){
		
		List<Contato> contatos = repository.findAll();
		
		return contatos;
	}
	
	

	public Contato updateContato(Long id, Contato contato) {
		Contato ContatoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrada com o ID: " + id));
		
		ContatoExistente.setNome(contato.getNome());
		ContatoExistente.setEmail(contato.getEmail());
		ContatoExistente.setCargo(contato.getCargo());
		ContatoExistente.setTime(contato.getTime());
		ContatoExistente.setTipo_contato(contato.getTipo_contato());
		ContatoExistente.setDdd_1(contato.getDdd_1());
		ContatoExistente.setTelefone_principal(contato.getTelefone_principal());
		ContatoExistente.setDdd_2(contato.getDdd_2());
		ContatoExistente.setTelefone_secundario(contato.getTelefone_secundario());
		
		Contato updatedContato = repository.save(ContatoExistente);
		
		return updatedContato;
		
	}
	
	@Transactional
	public void deleteContato(Long id) {
		Contato Contato = repository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Contato não encontrada com o ID: " + id));
		
		repository.deleteContatoById(id);
		
		repository.delete(Contato);
		
	}
	
	
	@Transactional
	public void deleteContatos(List<Long> ids) {
		
		repository.deleteAllById(ids);
		
	}
	
	
	public Optional<Contato> findContatoById(Long id) {
		return repository.findById(id);
	}

	
	@Transactional
	public void processarEInserirContatos(MultipartFile file) throws IOException  {
		List<Contato> contatos = new ArrayList<>();
		
		
		
			 try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
		            reader.lines()
		                  .skip(1) 
		                  .map(line -> line.split(";"))
		                  .forEach(fields -> {
		                      
		                      Contato contato = new Contato();
		                      contato.setNome(fields[0]);
		                      contato.setEmail(fields[1]);
		                      contato.setCargo(fields[2]);
		                      contato.setTime(fields[3]);
		                      contato.setTipo_contato(fields[4]);
		                      contato.setDdd_1(fields[5]);
		                      contato.setTelefone_principal(fields[6]);

		                      
		                      if (fields.length >= 8) {
		                    	  contato.setDdd_2(fields[7]);
		                      }

		                      if (fields.length >= 9) {
		                    	  contato.setTelefone_secundario(fields[8]);
		                      }

		                     
		                      repository.save(contato);
		                  });
		        }
		    }
	
	
}
	

