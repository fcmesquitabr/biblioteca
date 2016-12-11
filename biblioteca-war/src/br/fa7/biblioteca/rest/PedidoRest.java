package br.fa7.biblioteca.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.fa7.biblioteca.model.Distribuidora;
import br.fa7.biblioteca.model.Pedido;
import br.fa7.biblioteca.model.PedidoLivro;
import br.fa7.biblioteca.model.SituacaoPedido;
import br.fa7.biblioteca.service.PedidoService;

@Path("pedido")
@RequestScoped
public class PedidoRest {

	@Inject
	private PedidoService pedidoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> listar() {
		if(pedidoService == null) return new ArrayList<>();
		try{
			List<Pedido> listaPedidos = pedidoService.list();
			for(Pedido pedido: listaPedidos){
				pedido.setPedidoLivros(null);
			}
			return listaPedidos;						
		} catch (Exception e){
			tratarException(e);
			return new ArrayList<>();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/livros")
	public List<PedidoLivro> listarPedidoLivros(@PathParam("id") Integer id) {
		if(pedidoService ==null) return new ArrayList<>();
		Pedido pedido = pedidoService.find(id);
		List<PedidoLivro> listaPedidoLivro = pedidoService.listarPedidoLivro(pedido);
		for (PedidoLivro pedidoLivro : listaPedidoLivro) {
			pedidoLivro.getPedido().setPedidoLivros(null);
		}
		return listaPedidoLivro;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("distribuidora")
	public List<Distribuidora> listarDistribuidoras() {
		if(pedidoService !=null){			
			return pedidoService.listarDistribuidoras();
		}
		return new ArrayList<>();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response realizarPedido(Pedido body) {
		try {
			if(body == null){
				return Response.ok().build();
			}
			
			body.setSituacaoPedido(new SituacaoPedido());
			body.getSituacaoPedido().setId(SituacaoPedido.AGUARDANDO_ENCAMINHAMENTO);
			body.setDataRealizacao(new Date());
			if(body.getPedidoLivros()!=null){
				for (PedidoLivro pedidoLivro: body.getPedidoLivros()) {
					pedidoLivro.setPedido(body);
				}
			}
			pedidoService.processar(body);
			String url = "/api/pedido/";
			return Response.status(Status.CREATED).header("Location", url).entity(body.getId()).build();				

		} catch (Exception e) {
			return tratarException(e);
		}
	}

	private Response tratarException(Exception e) {
		e.printStackTrace();
		return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
	}

}
