package br.fa7.biblioteca.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.fa7.biblioteca.distribuidora.DistribuidoraOrder;
import br.fa7.biblioteca.service.PedidoService;

@Path("distribuidora-callback")
@RequestScoped
public class DistribuidorCallbackRest {

	@EJB
	PedidoService pedidoService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processarRetornoDistribuidora(DistribuidoraOrder order){
		try {
			if(order == null) return Response.ok().build();

			pedidoService.processarRetornoDistribuidora(order);
			
			String url = "/api/distribuidora-callback/";
			System.out.println("Terminou o processmento do callback");
			return Response.status(Status.CREATED).header("Location", url).entity(order).build();	

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
		}
	}
}
