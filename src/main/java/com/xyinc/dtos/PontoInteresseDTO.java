package com.xyinc.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PontoInteresseDTO{

	@NotNull
	@Min(value = 0 , message = "A posição X deve ser um número inteiro não negativo.")
	private Integer x;

	@NotNull
	@Min(value = 0 , message = "A posição Y deve ser um número inteiro não negativo.")
	private Integer y;

	@NotNull
	@Size(min = 1 , message = "O nome deve conter pelo menos 1 caractere.")
	private String nome;

	public PontoInteresseDTO(Integer x, Integer y, String nome) {
		this.x = x;
		this.y = y;
		this.nome = nome;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
