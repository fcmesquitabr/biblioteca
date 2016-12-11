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

import br.fa7.biblioteca.distribuidora.DistribuidoraOrder;
import br.fa7.biblioteca.distribuidora.DistribuidoraOrderItem;
import br.fa7.biblioteca.model.Distribuidora;
import br.fa7.biblioteca.model.Pedido;
import br.fa7.biblioteca.model.PedidoLivro;
import br.fa7.biblioteca.model.SituacaoPedido;

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

	public void processar(Pedido pedido){
		pedido = insert(pedido);
		em.flush();
		enviarParaFila(pedido);
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

	private void enviarParaFila(Pedido pedido){
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
			fecharRecursos(connection, session, publisher);
		}
	}

	private void fecharRecursos(Connection connection, Session session, MessageProducer publisher) {
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

	public void processarRetornoDistribuidora(DistribuidoraOrder order) {
		Pedido pedido = find(Integer.parseInt(order.getClientOrderId()));
		processarDistribuidoraItens(pedido, order.getItems());
		pedido.setSituacaoPedido(new SituacaoPedido());
		pedido.getSituacaoPedido().setId(SituacaoPedido.PROCESSADO_PELA_DISTRIBUIDORA);
		update(pedido);
	}

	private void processarDistribuidoraItens(Pedido pedido, List<DistribuidoraOrderItem> items) {
		for (DistribuidoraOrderItem distribuidoraOrderItem : items) {
			processarDistribuidoraItem(pedido, distribuidoraOrderItem);
		}
		
	}

	private void processarDistribuidoraItem(Pedido pedido, DistribuidoraOrderItem distribuidoraOrderItem) {
		PedidoLivro pedidoLivro = buscarPedidoLivro(pedido, distribuidoraOrderItem.getIsbn().toString());
		pedidoLivro.setQuantidadeConfirmada(distribuidoraOrderItem.getAmountResponse());
		em.merge(pedidoLivro);
	}
	
	private PedidoLivro buscarPedidoLivro(Pedido pedido, String isbn){
		Query qry = em.createQuery("SELECT pl FROM PedidoLivro pl WHERE pl.pedido=:pedido AND pl.livro.isbn=:isbn");
		qry.setParameter("pedido", pedido);
		qry.setParameter("isbn", isbn);
		return (PedidoLivro) qry.getSingleResult();
	}
}
