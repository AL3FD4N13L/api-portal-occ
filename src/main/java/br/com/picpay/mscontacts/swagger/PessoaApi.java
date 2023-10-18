package br.com.picpay.mscontacts.swagger;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import br.com.picpay.mscontacts.entities.Pessoa;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


public interface PessoaApi {
	
	@Tag(name = "Cadastro de Contatos de funcionarios PicPay | Original", description = "Cadastrar informações de Contatos de parceiros ou funcionarios.")
	@Operation(summary = "Cadastro de Contatos", description = "Endpoint onde se cadastra informações da Contato")
	
	@ApiResponses({
        @ApiResponse(code = 201, message = "Cadastro realizado com sucesso. "),
        @ApiResponse(code = 500, message = "Erro ao cadastrar contato")
    })
    
	public ResponseEntity<String> addPessoa( @RequestBody Pessoa obj);
	
	@Tag(name = "Consulta de contatos de funcionarios PicPay | Original.", description = "Consultar informações de contato dos funcionarios PicPay | Original.")
	@Operation(summary = "Consulta de contatos dos funcionarios PicPay | Original", description = "Endpoint para realizar consulta de contatos dos funcionarios PicPay | Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contatos encontrados",response = Pessoa.class),
        @ApiResponse(code = 404, message = "Nenhum Contato encontrado.")
    })
	public ResponseEntity<List<Pessoa>> findAllPessoas();
	
	@Tag(name = "Atualizar informações de contato de funcionario PicPay | Original", description = "Atualizar informações de contato do funcionario PicPay | Original")
	@Operation(summary = "Atualização de contato do funcionario PicPay | Original.", description = "Endpoint para realizar atualização de contatos dos funcionarios PicPay | Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato atualizado com sucesso",response = Pessoa.class),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao atualizar Contato.")
    })
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa contato);
	
	@Tag(name = "Deletar contato de Contatos e empresas", description = "Deletar informações de contato de Contatos e parceiros")
	@Operation(summary = "Remoção de contato.", description = "Endpoint para realizar atualização de contatos dos parceiros e funcionarios da empresa Picpay e  Original")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato deletado com sucesso"),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado."),
        @ApiResponse(code = 500, message = "Erro ao deletar contato.")
    })
	public  ResponseEntity<String> deletePessoa(@PathVariable Long id);
	
	@Tag(name = "Consultar contato por ID", description = "Consultar informações de contato dos parceiros ou funcionarios por ID.")
	@Operation(summary = "Consulta de contato por ID.", description = "Endpoint para consultar informações de contato dos parceiros ou funcionarios por ID.")
	@ApiResponses({
        @ApiResponse(code = 200,message = "Contato encontrado"),
        @ApiResponse(code = 404, message = "Contato não encontrada com o ID informado.")})
	public ResponseEntity<Optional<Pessoa>> findPessoaById(@PathVariable Long id);
	
	
	@Tag(name = "Deletar varios contatos simultaneamente", description = "Deletar informações de contatos simultaneamente")
	@Operation(summary = "Remoção de contatos.", description = "Endpoint para realizar remoção de contatos dos parceiros e funcionarios da empresa Picpay e  Original simultaneamente")
	@ApiResponses({
	    @ApiResponse(code = 200,message = "Contatos deletado com sucesso"),
	    @ApiResponse(code = 500, message = "Erro ao deletar contatos.")
	})
	public  ResponseEntity<String> deletePessoas(@RequestBody List<Long> ids);
	
	
}


