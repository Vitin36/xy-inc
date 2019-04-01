package com.xyinc.handlers;

import java.util.ArrayList;
import java.util.List;

public class MultipleErrors {
	
	private String tipo;
	private Integer codigo;
	private List<InvalidParametersError> erros = new ArrayList<>();
	
	public MultipleErrors(String tipo, Integer codigo) {
		this.tipo = tipo;
		this.codigo = codigo;
	}

	public void add(InvalidParametersError err) {
		erros.add(err);
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<InvalidParametersError> getErros() {
		return erros;
	}

}
