package com.xyinc.controladores;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.xyinc.modelos.PontoInteresse;
import com.xyinc.servicos.PontoInteresseServico;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class PontoInteresseControladorTest {

	@Autowired
	private MockMvc mvc;

	@InjectMocks
	private PontoInteresseServico servico;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		Assert.assertNotNull(this.mappingJackson2HttpMessageConverter);
	}
	
	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void listarTodosTest() throws Exception {
		mvc.perform(get("/ponto-de-interesse")).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(7)));
	}

	@Test
	public void listarDistancia() throws Exception
	{
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("x", "20");
		params.add("y", "10");
		params.add("dMax", "10");
		
		mvc.perform(get("/ponto-de-interesse").params(params))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$",hasSize(4)));
		
	}
	
	@Test
	public void listarDistanciaXNegativo() throws Exception
	{
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("x", "-20");
		params.add("y", "10");
		params.add("dMax", "10");
		
		mvc.perform(get("/ponto-de-interesse").params(params))
		.andExpect(status().is4xxClientError())
		.andExpect(content().contentType(contentType));
		
	}
	
	@Test
	public void listarDistanciaYNegativo() throws Exception
	{
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("x", "20");
		params.add("y", "-10");
		params.add("dMax", "10");
		
		mvc.perform(get("/ponto-de-interesse").params(params))
		.andExpect(status().is4xxClientError())
		.andExpect(content().contentType(contentType));
		
	}
	
	@Test
	public void criar() throws Exception
	{
		String json = json(new PontoInteresse(10,15,"Novo Ponto"));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void criarWithoutX() throws Exception
	{
		String json = json(new PontoInteresse(null,15,"Novo Ponto"));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void criarWithoutY() throws Exception
	{
		String json = json(new PontoInteresse(15,null,"Novo Ponto"));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void criarWithoutName() throws Exception
	{
		String json = json(new PontoInteresse(15,15,null));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void criarWithNegativeX() throws Exception
	{
		String json = json(new PontoInteresse(-30,15,"Novo Ponto"));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void criarWithNegativeY() throws Exception
	{
		String json = json(new PontoInteresse(30,-30,"Novo Ponto"));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void criarWithSamePoints() throws Exception
	{
		String json = json(new PontoInteresse(27,12,"Novo Ponto"));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void criarWithSameName() throws Exception
	{
		String json = json(new PontoInteresse(50,50,"Lanchonete"));
	
		mvc.perform(post("/ponto-de-interesse").contentType(contentType).content(json))
		.andExpect(status().is4xxClientError());
	}

}
