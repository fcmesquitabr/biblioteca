package br.fa7.biblioteca.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.fa7.biblioteca.model.Livro;
import br.fa7.biblioteca.service.LivroService;
import br.fa7.biblioteca.service.PedidoService;

@Path("livros")
@RequestScoped
public class LivroRest {

	@Inject
	private LivroService livroService;

	@Inject
	private PedidoService pedidoService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livro> listar() {
		if(pedidoService !=null){
			try{
				pedidoService.enviarMensagemFila();
			}catch(Exception e){
				System.out.println("erro ao postar mensagem na fila");
			}
		}
		if(livroService !=null){			
			return livroService.list();
		}
		return new ArrayList<>();
	}

}
