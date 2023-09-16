package br.com.picpay.mscontacts.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.com.picpay.mscontacts.entities.Contato;
import br.com.picpay.mscontacts.services.ContatoService;
import br.com.picpay.mscontacts.swagger.ContatoApi;
import jakarta.persistence.EntityNotFoundException;




@RestController
@RequestMapping(value = "/api/contato", produces = "application/json")
public class ContatoResource implements ContatoApi {
	
	
	@Autowired
	private ContatoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Contato>> findAllContatos(){
		 
		try {
			List<Contato> contatos = service.findaAllContatos();
			return ResponseEntity.ok().body(contatos);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Contato encontrado.");
        }	
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Contato>> findContatoById(@PathVariable Long id){
		 
		try {
			
			return ResponseEntity.ok().body(service.findContatoById(id));
			
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Contato encontrado.");
        }	
		
	}
	

	@PostMapping(path = "/cadastro", produces = "application/json")
	public ResponseEntity<String> addContato( @RequestBody Contato obj){
		
		 try {
			 obj = service.addContato(obj);
				return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso.");
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar contato");
	        }
		
		
		
		
	}
	
	
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Contato> updateContato(@PathVariable Long id, @RequestBody Contato contato){
		
		try {
			Contato contatoAtualizado = service.updateContato(id ,contato);
			return ResponseEntity.ok(contatoAtualizado);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar pessoa", e);
        }
		
		
		
		
		
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<String> deleteContato(@PathVariable Long id){
		
		 try {
			 service.deleteContato(id);
				return ResponseEntity.ok("Contato deletado com sucesso");
	        } catch (EntityNotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com o ID: " + id);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar pessoa", e);
	        }
		
	}
	
	
	@DeleteMapping("/delete")
	public  ResponseEntity<String> deleteContatos(@RequestBody List<Long> ids){
		
		 try {
			 service.deleteContatos(ids);
				return ResponseEntity.ok("Contatos deletados com sucesso");
	        }  catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar contatos", e);
	        }	
		
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file){
		
		try {
			service.processarEInserirContatos(file);
			return ResponseEntity.ok("Upload feito com sucesso");
		}catch(Exception e) {
			return ResponseEntity.status(500).body("Erro ao processar o arquivo CSV: " + e.getMessage());
		}
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
	
	
	
