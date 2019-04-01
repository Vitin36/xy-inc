package com.xyinc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xyinc.servicos.EstadoInicial;

@Configuration
public class Configuracao {
	
	@Autowired
	private EstadoInicial estadoInicial;
	
	@Bean
	public boolean EstadoInicial() {
		estadoInicial.iniciar();
		return true;
	}

}
