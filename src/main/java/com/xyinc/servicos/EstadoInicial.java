package com.xyinc.servicos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyinc.modelos.PontoInteresse;
import com.xyinc.repositorios.PontoInteresseRepositorio;

@Service
public class EstadoInicial {

	@Autowired
	private PontoInteresseRepositorio repo;

	public void iniciar() {
		PontoInteresse lanchonete = new PontoInteresse(27, 12, "Lanchonete");
		PontoInteresse posto = new PontoInteresse(31, 18, "Posto");
		PontoInteresse joalheria = new PontoInteresse(15, 12, "Joalheria");
		PontoInteresse floricultura = new PontoInteresse(19, 21, "Floricultura");
		PontoInteresse pub = new PontoInteresse(12, 8, "Pub");
		PontoInteresse supermercado = new PontoInteresse(23, 6, "Supermercado");
		PontoInteresse churrascaria = new PontoInteresse(28, 2, "Churrascaria");
		repo.saveAll(Arrays.asList(lanchonete, posto, joalheria, floricultura, pub, supermercado, churrascaria));
	}

	
	
}
