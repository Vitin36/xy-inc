package com.xyinc.modelos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Pontos")
public class PontoInteresse implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private XY xy;
	
	@NotNull(message = "Nome deve possuir um valor.")
	@Size(min = 1 , message = "Nome deve possuir pelo menos um caracter.")
	private String nome;

	public PontoInteresse() {
	}

	public PontoInteresse(Integer x, Integer y, String nome) {
		this.nome = nome;
		this.xy = new XY(x, y);
	}

	public Integer getX() {
		return xy.getX();
	}
	
	public Integer getY() {
		return xy.getY();
	}

	public void setXy(XY xy) {
		this.xy = xy;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xy == null) ? 0 : xy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PontoInteresse other = (PontoInteresse) obj;
		if (xy == null) {
			if (other.xy != null)
				return false;
		} else if (!xy.equals(other.xy))
			return false;
		return true;
	}

}
