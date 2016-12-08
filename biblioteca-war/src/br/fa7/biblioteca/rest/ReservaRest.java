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

import br.fa7.biblioteca.model.ReservaLivro;
import br.fa7.biblioteca.model.SituacaoReserva;
import br.fa7.biblioteca.service.ReservaService;

@Path("reservas")
@RequestScoped
public class ReservaRest {

	@Inject
	private ReservaService reservaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReservaLivro> listar() {
		if(reservaService !=null){			
			return reservaService.list();
		}
		return new ArrayList<>();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ReservaLivro selecionar(@PathParam("id") Long id) {		
		ReservaLivro reservaLivro = reservaService.find(id.intValue());
		if(reservaLivro != null){
			return reservaLivro;
		} else{
			throw new NotFoundException();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(ReservaLivro[] body) {
		try {
			if(body == null || body.length == 0){
				return Response.ok().build();
			}
			
			List<Integer> entitiesId = new ArrayList<>();
			for (ReservaLivro reservaLivro : body) {
				reservaLivro.setSituacaoReserva(new SituacaoReserva());
				reservaLivro.getSituacaoReserva().setId(SituacaoReserva.AGUARDANDO_EMPRESTIMO);
				reservaLivro.setDataRealizacao(new Date());
				ReservaLivro reserva = reservaService.insert(reservaLivro);
				entitiesId.add(reserva.getId());
				
			}
			String url = "/api/reservas/";
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
		ReservaLivro reserva = reservaService.find(id.intValue());
		if (reserva != null) {
			reserva = reservaService.remove(reserva);
			return Response.status(Status.OK).entity(reserva).build();
		} else {
			throw new NotFoundException();
		}
	}

	@PUT
	@Path("emprestimo/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response emprestar(@PathParam("id") Long id, ReservaLivro reservaLivro) {
		try {
			reservaLivro.setSituacaoReserva(new SituacaoReserva());
			reservaLivro.getSituacaoReserva().setId(SituacaoReserva.EMPRESTADO);
			reservaLivro.setDataEmprestimo(new Date());
			reservaLivro = reservaService.update(reservaLivro);
			return Response.status(Status.OK).entity(reservaLivro).build();
		} catch (Exception e) {
			return tratarException(e);
		}
	}

	@PUT
	@Path("/cancelamento/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelar(@PathParam("id") Long id, ReservaLivro reservaLivro) {
		try {
			reservaLivro.setSituacaoReserva(new SituacaoReserva());
			reservaLivro.getSituacaoReserva().setId(SituacaoReserva.CANCELADO);
			reservaLivro.setDataEmprestimo(null);
			reservaLivro = reservaService.update(reservaLivro);
			return Response.status(Status.OK).entity(reservaLivro).build();
		} catch (Exception e) {
			return tratarException(e);
		}
	}
}
