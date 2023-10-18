package br.com.picpay.mscontacts.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="tb_contato_parceiro")
public class Parceiro  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome_empresa;
	private String produto;
	private String email;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parceiro")
    private List<Contato> contatos = new ArrayList<>();
	
	
	
	@PrePersist
    @PreUpdate
    private void beforeSave() {
		nome_empresa = nome_empresa.toUpperCase();
		produto= produto.toUpperCase();
        email = email.toUpperCase();
        
       
        
    }
    
	
		
	
}
