package br.fa7.biblioteca.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.fa7.biblioteca.model.Pedido;
import br.fa7.biblioteca.service.PedidoService;

@Path("pedido")
@RequestScoped
public class PedidoRest {

	@Inject
	private PedidoService pedidoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> listar() {
		if(pedidoService !=null){			
			return pedidoService.list();
		}
		return new ArrayList<>();
	}

}
