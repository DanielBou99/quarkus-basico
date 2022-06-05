package org.acme.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.domain.Produto;
import org.acme.dto.CadastroProdutoDTO;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
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

	@PUT
	@Path("{id}")
	@Transactional
	public void editarProduto(@PathParam("id") Long id, CadastroProdutoDTO dto) {
		Optional<Produto> produtoEncontrado = Produto.findByIdOptional(id);
		if (produtoEncontrado.isPresent()) {
			Produto produto = produtoEncontrado.get();
			produto.setNome(dto.getNome());
			produto.setValor(dto.getValor());
			produto.persist();
		} else {
			throw new NotFoundException("ID não encontrado: " + id);
		}
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public void deletarProduto(@PathParam("id") Long id) {
		Optional<Produto> produtoEncontrado = Produto.findByIdOptional(id);
		produtoEncontrado.ifPresentOrElse(Produto::delete, () -> {
			throw new NotFoundException();
		});
	}

}
