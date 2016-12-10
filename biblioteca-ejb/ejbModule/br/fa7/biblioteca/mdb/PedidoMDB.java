package br.fa7.biblioteca.mdb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.fa7.biblioteca.distribuidora.DistribuidoraOrder;
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

	private void enviarPedidoParaDistribuidora(Integer pedidoId) {
		Pedido pedido = pedidoService.find(pedidoId);
		fazerPost(pedido);
	}
	
	private void fazerPost(Pedido pedido){
		 
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/order");

			StringEntity input = new StringEntity(getJsonString(pedido));
			input.setContentType("application/json");
			postRequest.setEntity(input);

			/*
			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}*/

			httpClient.getConnectionManager().shutdown();

		} /*catch (MalformedURLException e) {

			e.printStackTrace();

		}*/ catch (IOException e) {

			e.printStackTrace();

		}
	}

	private String getJsonString(Pedido pedido) {
		DistribuidoraOrder distribuidoraOrder = new DistribuidoraOrder(pedido);
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(distribuidoraOrder);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonInString;
	}
}
