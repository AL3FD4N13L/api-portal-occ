package br.com.picpay.mscontacts.DTO;

import java.util.List;

import br.com.picpay.mscontacts.entities.Contato;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class PessoaDTO {
	private Long id;
	private String nome;
	private String cargo;
	private String time;
	private String tipo_contato;
	private List<Contato> contatos;
}
