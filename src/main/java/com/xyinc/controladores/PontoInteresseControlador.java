package com.xyinc.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyinc.dtos.PontoInteresseDTO;
import com.xyinc.handlers.CommonError;
import com.xyinc.modelos.PontoInteresse;
import com.xyinc.servicos.PontoInteresseServico;

@RestController
@RequestMapping(value = "/ponto-de-interesse")
public class PontoInteresseControlador {

	@Autowired
	private PontoInteresseServico servico;

	@GetMapping()
	public ResponseEntity<List<PontoInteresse>> getAll() {
		return ResponseEntity.ok().body(servico.listar());
	}

	@GetMapping(params = { "x", "y", "dMax" })
	public ResponseEntity<List<String>> getByDistancia(@RequestParam("x") Integer x, @RequestParam("y") Integer y,
			@RequestParam("dMax") @Valid Double dMax) {
		
		if(x < 0 || y < 0) throw new CommonError("As coordenadas x e y devem ser inteiros não negativos");
		
		return ResponseEntity.ok().body(servico.ListarProximidade(x, y, dMax));
	}

	@PostMapping()
	public ResponseEntity<PontoInteresse> criar(@Valid @RequestBody PontoInteresseDTO ponto) {
		return ResponseEntity.ok().body(servico.cadastrar(ponto));
	}

}
