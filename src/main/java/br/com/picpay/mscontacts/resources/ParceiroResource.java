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

import br.com.picpay.mscontacts.entities.Parceiro;
import br.com.picpay.mscontacts.services.ParceiroService;
import br.com.picpay.mscontacts.swagger.ParceiroApi;
import jakarta.persistence.EntityNotFoundException;




@RestController
@RequestMapping(value = "/api/parceiro", produces = "application/json")
public class ParceiroResource implements ParceiroApi {
	

	@Autowired
	private ParceiroService service;
	
	
	@GetMapping
	public ResponseEntity<List<Parceiro>> findAllParceiros(){
		 
		try {
			List<Parceiro> contatos = service.findaAllParceiros();
			return ResponseEntity.ok().body(contatos);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Parceiro encontrado.");
        }	
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Parceiro>> findParceiroById(@PathVariable Long id){
		 
		try {
			
			return ResponseEntity.ok().body(service.findParceiroById(id));
			
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Parceiro encontrado.");
        }	
		
	}
	

	@PostMapping(path = "/cadastro", produces = "application/json")
	public ResponseEntity<String> addParceiro( @RequestBody Parceiro obj){
		
		 try {
			 obj = service.addParceiro(obj);
				return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso.");
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar contato");
	        }
		
		
		
		
	}
	
	
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Parceiro> updateParceiro(@PathVariable Long id, @RequestBody Parceiro contato){
		
		try {
			Parceiro contatoAtualizado = service.updateParceiro(id ,contato);
			return ResponseEntity.ok(contatoAtualizado);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parceiro não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar parceiro", e);
        }
		
		
		
		
		
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<String> deleteParceiro(@PathVariable Long id){
		
		 try {
			 service.deleteParceiro(id);
				return ResponseEntity.ok("Parceiro deletado com sucesso");
	        } catch (EntityNotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parceiro não encontrada com o ID: " + id);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar parceiro", e);
	        }
		
	}
	
	
	@DeleteMapping("/delete")
	public  ResponseEntity<String> deleteParceiros(@RequestBody List<Long> ids){
		
		 try {
			 service.deleteParceiros(ids);
				return ResponseEntity.ok("Parceiros deletados com sucesso");
	        }  catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar contatos", e);
	        }	
		
	}
	
	


}
	
	
	
