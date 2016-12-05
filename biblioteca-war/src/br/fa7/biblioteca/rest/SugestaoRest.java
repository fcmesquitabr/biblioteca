package br.fa7.biblioteca.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.fa7.biblioteca.model.SugestaoLivro;
import br.fa7.biblioteca.service.SugestaoService;

@Path("sugestao")
@RequestScoped
public class SugestaoRest {

	@Inject
	private SugestaoService sugestaoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SugestaoLivro> listar() {
		if(sugestaoService !=null){			
			return sugestaoService.list();
		}
		return new ArrayList<>();
	}

}
