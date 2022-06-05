package org.acme.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.domain.Produto;
import org.acme.dto.CadastroProdutoDTO;

@Path("produtos")
@Produces( MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {
	
	@GET
	public List<Produto> buscarTodosProdutos() {
		return Produto.listAll();
	}
	
	@POST
	@Transactional
	public void cadastrarProduto(CadastroProdutoDTO dto) {
		Produto produto = new Produto();
		produto.setNome(dto.getNome());
		produto.setValor(dto.getValor());
		produto.persist();
	}

}
