package br.com.picpay.mscontacts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.picpay.mscontacts.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
		
	 public void deletePessoaById(Long id);
	
	
	
	
}
