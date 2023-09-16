package br.com.picpay.mscontacts.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="tb_contato")
public class Contato  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String cargo;
	private String time;
	private String tipo_contato;
	private String ddd_1;
	private String telefone_principal;
	private String ddd_2;
	private String telefone_secundario;
	
	
	
	@PrePersist
    @PreUpdate
    private void beforeSave() {
        nome = nome.toUpperCase();
        email = email.toUpperCase();
        cargo= cargo.toUpperCase();
        time = time.toUpperCase();
        tipo_contato= tipo_contato.toUpperCase();
        
    }
    
	
		
	
}
