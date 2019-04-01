package com.xyinc.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyinc.dtos.PontoInteresseDTO;
import com.xyinc.handlers.AlreadyExistsError;
import com.xyinc.modelos.PontoInteresse;
import com.xyinc.modelos.XY;
import com.xyinc.repositorios.PontoInteresseRepositorio;

@Service
public class PontoInteresseServico {

	@Autowired
	private PontoInteresseRepositorio repo;

	public PontoInteresse cadastrar(PontoInteresseDTO ponto) {
		if (!repo.existsById(new XY(ponto.getX(), ponto.getY())) && repo.countNome(ponto.getNome()) == 0) {
			return repo.save(new PontoInteresse(ponto.getX(), ponto.getY(), ponto.getNome()));
		}
		else throw new AlreadyExistsError("Um ponto com as cordenadas e/ou nome informadas(os) já existe.");
	}

	public List<PontoInteresse> listar() {
		return repo.findAll();
	}

	public List<String> ListarProximidade(Integer x, Integer y, Double dMax) {
		List<PontoInteresse> all = repo.findAll();
		List<String> retorno = new ArrayList<>();

		for (PontoInteresse ponto : all) {
			if (distancia(x, y, ponto.getX(), ponto.getY()) <= dMax) {
				retorno.add(ponto.getNome());
			}
		}

		return retorno;
	}

	private Double distancia(Integer x, Integer y, Integer xx, Integer yy) {
		/*
		 * Distância entre dois pontos A(x',y') e B(x'',y'') é igual = RAIZ( (x'' - x')²
		 * + (y'' - y')² )
		 */
		return Math.sqrt(Math.pow((x - xx), 2) + Math.pow((y - yy), 2));
	}
}
