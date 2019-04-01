package com.xyinc.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xyinc.modelos.PontoInteresse;
import com.xyinc.modelos.XY;

@Repository
public interface PontoInteresseRepositorio extends JpaRepository<PontoInteresse,XY>{

	@Query("SELECT COUNT(nome) from PontoInteresse obj WHERE obj.nome = :nome")
	public Integer countNome(@Param("nome") String nome);
	
}
