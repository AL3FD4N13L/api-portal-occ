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

import br.com.picpay.mscontacts.entities.Pessoa;
import br.com.picpay.mscontacts.services.PessoaService;
import br.com.picpay.mscontacts.swagger.PessoaApi;
import jakarta.persistence.EntityNotFoundException;




@RestController
@RequestMapping(value = "/api/pessoa", produces = "application/json")
public class PessoaResource implements PessoaApi {
	

	@Autowired
	private PessoaService service;
	
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAllPessoas(){
		 
		try {
			List<Pessoa> contatos = service.findaAllPessoas();
			return ResponseEntity.ok().body(contatos);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Pessoa encontrado.");
        }	
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> findPessoaById(@PathVariable Long id){
		 
		try {
			
			return ResponseEntity.ok().body(service.findPessoaById(id));
			
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Pessoa encontrado.");
        }	
		
	}
	

	@PostMapping(path = "/cadastro", produces = "application/json")
	public ResponseEntity<String> addPessoa( @RequestBody Pessoa obj){
		
		 try {
			 obj = service.addPessoa(obj);
				return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro realizado com sucesso.");
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar contato");
	        }
		
		
		
		
	}
	
	
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa contato){
		
		try {
			Pessoa contatoAtualizado = service.updatePessoa(id ,contato);
			return ResponseEntity.ok(contatoAtualizado);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrado com o ID: " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar pessoa", e);
        }
		
		
		
		
		
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<String> deletePessoa(@PathVariable Long id){
		
		 try {
			 service.deletePessoa(id);
				return ResponseEntity.ok("Pessoa deletado com sucesso");
	        } catch (EntityNotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada com o ID: " + id);
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar pessoa", e);
	        }
		
	}
	
	
	@DeleteMapping("/delete")
	public  ResponseEntity<String> deletePessoas(@RequestBody List<Long> ids){
		
		 try {
			 service.deletePessoas(ids);
				return ResponseEntity.ok("Pessoas deletados com sucesso");
	        }  catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar contatos", e);
	        }	
		
	}
	
	


}
	
	
	
