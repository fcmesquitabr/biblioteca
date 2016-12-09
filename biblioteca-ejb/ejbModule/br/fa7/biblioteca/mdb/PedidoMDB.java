package br.fa7.biblioteca.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.fa7.biblioteca.model.Pedido;
import br.fa7.biblioteca.service.PedidoService;

@MessageDriven(name="pedidosMDB", mappedName="pedidosMDB",
	activationConfig={
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), 
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/Pedidos"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class PedidoMDB implements MessageListener{

	@EJB
	PedidoService pedidoService;
	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = null;
		try {
			if (message instanceof TextMessage) {
				textMessage = (TextMessage) message;
				enviarPedidoParaDistribuidora(Integer.parseInt(textMessage.getText()));
			} else {
				System.out.println("Mensagem inválida na fila");
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}	
	}

	private void enviarPedidoParaDistribuidora(Integer parseInt) {
		Pedido pedido = pedidoService.find(parseInt);
		
	}
	
}
