package br.fa7.biblioteca.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.fa7.biblioteca.model.Distribuidora;
import br.fa7.biblioteca.model.Pedido;

@Stateless
public class PedidoService extends BaseService<Pedido>{

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName = "java:/jms/queue/MinhaFila")
    private Queue minhaFila;
	
	public PedidoService(){
		super(Pedido.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Distribuidora> listarDistribuidoras(){
		return em.createQuery("SELECT d FROM Distribuidora d").getResultList();
	}
	
	public void enviarMensagemFila(){
		Connection connection = null;
		Session session = null;
		MessageProducer publisher = null; 
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			publisher = session.createProducer(minhaFila);
			
			connection.start();
			TextMessage message = session.createTextMessage("texto de teste");
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
