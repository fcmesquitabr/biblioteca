package br.fa7.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.Query;

import br.fa7.biblioteca.model.Distribuidora;
import br.fa7.biblioteca.model.Pedido;
import br.fa7.biblioteca.model.PedidoLivro;

@Stateless
public class PedidoService extends BaseService<Pedido>{

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName = "java:/jms/queue/Pedidos")
    private Queue filaPedidos;
	
	public PedidoService(){
		super(Pedido.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Distribuidora> listarDistribuidoras(){
		return em.createQuery("SELECT d FROM Distribuidora d ORDER BY d.nome").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PedidoLivro> listarPedidoLivro(Pedido pedido){
		if(pedido ==null || pedido.getId()==null){
			return new ArrayList<>();
		}
		Query qry = em.createQuery(
				"SELECT pl "
				+ "FROM PedidoLivro pl "
				+ "WHERE pl.pedido=:pedido "
				+ "ORDER BY pl.livro.titulo");
		qry.setParameter("pedido", pedido);
		return qry.getResultList();
	}

	public void enviarPedidoParaFila(Pedido pedido){
		Connection connection = null;
		Session session = null;
		MessageProducer publisher = null; 
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			publisher = session.createProducer(filaPedidos);
			
			connection.start();
			TextMessage message = session.createTextMessage(pedido.getId().toString());
			publisher.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (publisher != null)
				try {
					publisher.close();
				} catch (Exception ignore) {
				}
			if (session != null)
				try {
					session.close();
				} catch (Exception ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (Exception ignore) {
				}
		}
	}
}
