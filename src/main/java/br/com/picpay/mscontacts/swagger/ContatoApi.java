package br.com.picpay.mscontacts.swagger;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.picpay.mscontacts.entities.Contato;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


public interface ContatoApi {
	
	@Tag(name = "Cadastro de Contatos", description = "Cadastrar informações de Contatos de parceiros ou funcionarios.")
	@Operation(summary = "Cadastro de Contatos", description = "Endpoint onde se cadastra informações da Contato")
	
	@ApiResponses({
        @ApiResponse(code = 201, message = "Cadastro realizado com sucesso. "),
        @ApiResponse(code = 500, message = "Erro ao cadastrar contato")
    })
    
	public ResponseEntity<String> addContato( @RequestBody Contato obj);
	
	@Tag(name = "Consulta de contatos de parceiros ou funcionarios.", description = "Consultar informações de contato dos parceiros ou funcionarios.")
	@Operation(summary = "Consulta de contatos", description = "Endpoint para realizar consulta de contatos dos parceiros e funcionarios da empresa Picpay e  Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contatos encontrados",response = Contato.class),
        @ApiResponse(code = 404, message = "Nenhum Contato encontrado.")
    })
	public ResponseEntity<List<Contato>> findAllContatos();
	
	@Tag(name = "Atualizar informações de contato", description = "Atualizar informações de contato de Contatos e parceiros")
	@Operation(summary = "Atualização de contato.", description = "Endpoint para realizar atualização de contatos dos parceiros e funcionarios da empresa Picpay e  Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato atualizado com sucesso",response = Contato.class),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao atualizar Contato.")
    })
	public ResponseEntity<Contato> updateContato(@PathVariable Long id, @RequestBody Contato contato);
	
	@Tag(name = "Deletar contato de Contatos e empresas", description = "Deletar informações de contato de Contatos e parceiros")
	@Operation(summary = "Remoção de contato.", description = "Endpoint para realizar atualização de contatos dos parceiros e funcionarios da empresa Picpay e  Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato deletado com sucesso"),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao deletar contato.")
    })
	public  ResponseEntity<String> deleteContato(@PathVariable Long id);
	
	@Tag(name = "Consultar contato por ID", description = "Consultar informações de contato dos parceiros ou funcionarios por ID.")
	@Operation(summary = "Consulta de contato por ID.", description = "Endpoint para consultar informações de contato dos parceiros ou funcionarios por ID.")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato encontrado"),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado.")})
	public ResponseEntity<Optional<Contato>> findContatoById(@PathVariable Long id);
	
	
	@Tag(name = "Deletar varios contatos simultaneamente", description = "Deletar informações de contatos simultaneamente")
	@Operation(summary = "Remoção de contatos.", description = "Endpoint para realizar remoção de contatos dos parceiros e funcionarios da empresa Picpay e  Original simultaneamente")
	@ApiResponses({
	    @ApiResponse(code = 200,message = "Contatos deletado com sucesso"),
	    @ApiResponse(code = 500, message = "Erro ao deletar contatos.")
	})
	public  ResponseEntity<String> deleteContatos(@RequestBody List<Long> ids);
	
	
}


