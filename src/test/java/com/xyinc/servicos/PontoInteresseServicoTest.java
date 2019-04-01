package com.xyinc.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xyinc.dtos.PontoInteresseDTO;
import com.xyinc.handlers.AlreadyExistsError;
import com.xyinc.modelos.PontoInteresse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PontoInteresseServicoTest {

	@Autowired
	private PontoInteresseServico service;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testListar() {
		List<PontoInteresse> lista = service.listar();
		Assert.assertNotNull("erro - esperando lista não vazia",lista);
		Assert.assertEquals("erro - esperando lista com 7 elementos",7,lista.size());
	}
	
	@Test
	public void testListarDistancia(){
		
		List<String> retornoEsperado = new ArrayList<>();
		retornoEsperado.add("Lanchonete");
		retornoEsperado.add("Joalheria");
		retornoEsperado.add("Pub");
		retornoEsperado.add("Supermercado");
		
		List<String> lista = service.ListarProximidade(20, 10, 10.0);
		Assert.assertNotNull("erro - esperando lista não vazia",lista);
		Assert.assertEquals("erro - lista não possui elementos esperados",retornoEsperado,lista);
	}
	
	@Test(expected = AlreadyExistsError.class)
	public void testCordenadasExistentes() {
		PontoInteresseDTO ponto = new PontoInteresseDTO(27,12,"Coordenada Existente");
		service.cadastrar(ponto);
	}
	
	@Test(expected = AlreadyExistsError.class)
	public void testNomeExistente() {
		PontoInteresseDTO ponto = new PontoInteresseDTO(20,10,"Lanchonete");
		service.cadastrar(ponto);
	}
}
