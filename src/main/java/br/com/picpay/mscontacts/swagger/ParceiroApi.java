package br.com.picpay.mscontacts.swagger;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import br.com.picpay.mscontacts.entities.Parceiro;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


public interface ParceiroApi {
	
	@Tag(name = "Cadastro de contatos de parceiros Picpay|Original", description = "Cadastrar informações de Contatos de parceiros da Picpay|Original.")
	@Operation(summary = "Cadastro de Contatos de parceiros", description = "Endpoint onde se cadastra informações da contato de parceiros")
	
	@ApiResponses({
        @ApiResponse(code = 201, message = "Cadastro de parceiro realizado com sucesso. "),
        @ApiResponse(code = 500, message = "Erro ao cadastrar contato de parceiro")
    })
    
	public ResponseEntity<String> addParceiro( @RequestBody Parceiro obj);
	
	@Tag(name = "Consulta de contatos de parceiros Picpay|Original .", description = "Consultar informações de contato dos parceiros Picpay|Original.")
	@Operation(summary = "Consulta de contatos de parceiros Picpay|Original", description = "Endpoint para realizar consulta de contatos dos parceiros da Picpay|Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contatos encontrados",response = Parceiro.class),
        @ApiResponse(code = 404, message = "Nenhum Contato encontrado.")
    })
	public ResponseEntity<List<Parceiro>> findAllParceiros();
	
	@Tag(name = "Atualizar informações de contato dos parceiros Picpay|Original", description = "Atualizar informações de contatos dos parceiros Picpay|Original")
	@Operation(summary = "Atualização de contato dos parceiros Picpay|Original.", description = "Endpoint para realizar atualização de contatos dos parceiros Picpay|Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato atualizado com sucesso",response = Parceiro.class),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao atualizar Contato.")
    })
	public ResponseEntity<Parceiro> updateParceiro(@PathVariable Long id, @RequestBody Parceiro contato);
	
	@Tag(name = "Deletar contato de um parceiro Picpay|Original", description = "Deletar informações de contato do parceiro Picpay|Original")
	@Operation(summary = "Remoção de contato do parceiro Picpay|Original.", description = "Endpoint para remover de contato do parceiro Picpay|Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato deletado com sucesso"),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao deletar contato.")
    })
	public  ResponseEntity<String> deleteParceiro(@PathVariable Long id);
	
	@Tag(name = "Consultar contato por ID", description = "Consultar informações de contato dos parceiros ou funcionarios por ID.")
	@Operation(summary = "Consulta de contato por ID.", description = "Endpoint para consultar informações de contato dos parceiros ou funcionarios por ID.")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato encontrado"),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado.")})
	public ResponseEntity<Optional<Parceiro>> findParceiroById(@PathVariable Long id);
	
	
	@Tag(name = "Deletar varios contatos simultaneamente", description = "Deletar informações de contatos simultaneamente")
	@Operation(summary = "Remoção de contatos.", description = "Endpoint para realizar remoção de contatos dos parceiros e funcionarios da empresa Picpay e  Original simultaneamente")
	@ApiResponses({
	    @ApiResponse(code = 200,message = "Contatos deletado com sucesso"),
	    @ApiResponse(code = 500, message = "Erro ao deletar contatos.")
	})
	public  ResponseEntity<String> deleteParceiros(@RequestBody List<Long> ids);
	
	
}


