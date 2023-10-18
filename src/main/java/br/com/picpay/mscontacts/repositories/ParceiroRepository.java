package br.com.picpay.mscontacts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.picpay.mscontacts.entities.Parceiro;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long>{
		
	 public void deleteParceiroById(Long id);
	
	
	
	
}
