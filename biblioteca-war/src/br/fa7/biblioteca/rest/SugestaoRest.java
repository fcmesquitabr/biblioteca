package br.fa7.biblioteca.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.fa7.biblioteca.model.SituacaoSugestao;
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

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public SugestaoLivro selecionar(@PathParam("id") Long id) {		
		SugestaoLivro sugestaoLivro = sugestaoService.find(id.intValue());
		if(sugestaoLivro != null){
			return sugestaoLivro;
		} else{
			throw new NotFoundException();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(SugestaoLivro[] body) {
		try {
			if(body == null || body.length == 0){
				return Response.ok().build();
			}
			
			List<Integer> entitiesId = new ArrayList<>();
			for (SugestaoLivro sugestaoLivro : body) {
				sugestaoLivro.setSituacaoSugestao(new SituacaoSugestao());
				sugestaoLivro.getSituacaoSugestao().setId(SituacaoSugestao.EM_ANALISE);
				sugestaoLivro.setDataRealizacao(new Date());
				SugestaoLivro sugestao = sugestaoService.insert(sugestaoLivro);
				entitiesId.add(sugestao.getId());
				
			}
			String url = "/api/sugestao/";
			return Response.status(Status.CREATED).header("Location", url).entity(entitiesId).build();				
		} catch (Exception e) {
			return tratarException(e);
		}
	}

	private Response tratarException(Exception e) {
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Long id) {
		SugestaoLivro sugestao = sugestaoService.find(id.intValue());
		if (sugestao != null) {
			sugestao = sugestaoService.remove(sugestao);
			return Response.status(Status.OK).entity(sugestao).build();
		} else {
			throw new NotFoundException();
		}
	}

	@PUT
	@Path("/cancelamento/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelar(@PathParam("id") Long id, SugestaoLivro sugestaoLivro) {
		try {
			sugestaoLivro.setSituacaoSugestao(new SituacaoSugestao());
			sugestaoLivro.getSituacaoSugestao().setId(SituacaoSugestao.CANCELADA);
			sugestaoLivro = sugestaoService.update(sugestaoLivro);
			return Response.status(Status.OK).entity(sugestaoLivro).build();
		} catch (Exception e) {
			return tratarException(e);
		}
	}

}
