 package org.acme.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CadastroProdutoDTO {

	private String nome;
	private BigDecimal valor;
}
